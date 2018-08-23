package com.account.token;

import com.account.model.AccountVO;
import com.aspect.AspectService;
import com.exceptions.ErrorCode;
import com.exceptions.ErrorMessage;
import com.exceptions.ServiceException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.util.Utils;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class TokenStamp implements TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenStamp.class);

    private JWSSigner signer;
    private JWSVerifier verifier;

    public TokenStamp() throws JOSEException {
        String secretKey = "000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f";
        this.signer = new MACSigner(secretKey);
        this.verifier = new MACVerifier(secretKey);
    }

    public String getToken(String email) throws Exception {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("account", email)
                .claim("exp", System.currentTimeMillis() + 60 * 60 * 1000)
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    public String getToken(AccountVO accountVO) throws Exception {
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("email", accountVO.getEmail())
                .claim("exp", System.currentTimeMillis() + 60 * 60 * 1000)
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    public long getTokenExpiration(String tokenString) {
        try {
            SignedJWT jwt = Utils.isNotNull(tokenString) ? SignedJWT.parse(tokenString) : SignedJWT.parse("");
            JSONObject json = jwt.getPayload().toJSONObject();
            long expiration = (Long) json.get("exp");
            return expiration;
        } catch (ParseException e) {
            logger.error("received token : " + tokenString);
            logger.error(ErrorCode.TokenInvalid + " : " + ErrorMessage.TokenInvalid);
            return System.currentTimeMillis() - 10000 * 60 * 60;
        }
    }

    public boolean isTokenValid(String tokenString) {
        try {
            SignedJWT jwt = Utils.isNotNull(tokenString) ? SignedJWT.parse(tokenString) : SignedJWT.parse("");
            JSONObject json = jwt.getPayload().toJSONObject();
            long expiration = (Long) json.get("exp");
            return jwt.verify(verifier) && expiration >= System.currentTimeMillis();
        } catch (ParseException | JOSEException e) {
            logger.error("received token : " + tokenString);
            logger.error(ErrorCode.TokenInvalid + " : " + ErrorMessage.TokenInvalid);
            return false;
        }
    }

    @Override
    public void pushNewTokenIntoAccount(AccountVO account) throws Exception {
        String token = this.getToken(account);
        account.setToken(token);
        account.setExpiration(this.getTokenExpiration(token));
        account.setSigned(true);
    }

    public String getRefreshedToken(String tokenString) throws Exception {
        SignedJWT jwt = SignedJWT.parse(tokenString);
        JSONObject json = jwt.getPayload().toJSONObject();
        String email = (String) json.get("email");
        long expiration = (Long) json.get("exp");
        if (!jwt.verify(verifier)) {
            throw new ServiceException(ErrorCode.TokenExpirationError, ErrorMessage.TokenExpirationError);
        } else if (expiration < System.currentTimeMillis()) {
            throw new ServiceException(ErrorCode.TokenInvalid, ErrorMessage.TokenInvalid);
        } else {
            return this.getToken(email);
        }
    }

}
