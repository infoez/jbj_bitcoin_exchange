import com.account.model.AccountVO;
import com.account.service.AccountService;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import configuration.TestConfiguration;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TokenTest extends TestConfiguration {

    @Autowired
    AccountService accountService;

    @Test
    public void tokenTest() throws Exception {

        AccountVO accountVO = accountService.selectAccountByEmail("dldyddn0624@gmail.com");

        JWSSigner signer = new MACSigner("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f");
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .claim("account", "shot")
                .claim("timeStamp", System.currentTimeMillis() + 60 * 60 * 1000)
                .build();
        SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
        signedJWT.sign(signer);

        String tokenString = signedJWT.serialize();
        System.out.println(tokenString);

        SignedJWT jwt = SignedJWT.parse(tokenString);

        JWSVerifier verifier = new MACVerifier("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f");
        assertThat(jwt.verify(verifier), is(true));

        // _gNxR5WNkE6KpO4G_rarpSHFkRYjlelNuhA74iyOOS4
        // u_5qR86-1yx9dN7S2z01r03D5ME7LsQLlmDyqT0N5wk
        // Ecrrar-Fs0gFaK3dNuARm7gJQziAi9EFXQbGKH9zwQ4
    }

}
