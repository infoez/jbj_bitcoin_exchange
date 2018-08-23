package com.account.session;

import com.account.model.AccountVO;
import com.util.Utils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccountSession {

    private Map<String, AccountVO> session;

    public AccountSession() {
        this.session = new HashMap();
    }

    public AccountVO getUser(String email, String token) throws Exception {
        if (isTokenValid(email, token)) {
            return this.session.get(email);
        } else {
            AccountVO accountVO = new AccountVO();
            accountVO.setSigned(false);
            return accountVO;
        }
    }

    public void setUser(AccountVO accountVO) throws Exception {
        String hash = Utils.sha256(accountVO.getEmail() + accountVO.getPassword());
        accountVO.setToken(hash);
        this.session.put(accountVO.getEmail(), accountVO);
    }

    public void invalidate(String email) {
        this.session.remove(email);
    }

    public boolean isTokenValid(String email, String token) {
        if (!this.session.containsKey(email)) return false;
        AccountVO account = this.session.get(email);
        String validation = account.getToken();
        if (token.equals(validation)) {
            return true;
        } else {
            return false;
        }
    }

}