package com.account.token;

import com.account.model.AccountVO;

public interface TokenService {

    String getToken(AccountVO accountVO) throws Exception;

    String getRefreshedToken(String token) throws Exception;

    long getTokenExpiration(String token) throws Exception;

    boolean isTokenValid(String token);

    void pushNewTokenIntoAccount(AccountVO account) throws Exception;
}
