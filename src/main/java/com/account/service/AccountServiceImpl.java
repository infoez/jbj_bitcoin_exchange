
package com.account.service;


import com.account.model.AccountVO;
import com.account.model.RegistrationVO;
import com.account.token.TokenService;
import com.common.dao.DaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    DaoService daoService;

    @Autowired
    TokenService tokenService;

    @Override
    public AccountVO selectAccountByEmail(AccountVO accountVO) {
        AccountVO result = (AccountVO) daoService.selectOne("accountMapper.selectAccountByEmail", accountVO);
        return result;
    }

    @Override
    public AccountVO selectAccountByEmail(String email) {
        AccountVO result = (AccountVO) daoService.selectOne("accountMapper.selectAccountByEmail", email);
        return result;
    }

    @Override
    public List selectAccountListByEmails(List accounts) {
        return daoService.selectList("accountMapper.selectAccountListByEmails", accounts);
    }

    @Override
    public RegistrationVO registration(RegistrationVO registrationVO) {

        AccountVO accountVO = new AccountVO();
        accountVO.setEmail(registrationVO.getEmail());

        boolean emailValidation = this.selectAccountByEmail(accountVO) == null;

        if (emailValidation) {
            daoService.insert("accountMapper.registration", registrationVO);
            registrationVO.setOk(true);
        } else {
            registrationVO.setOk(false);
        }

        return registrationVO;
    }

    @Override
    public AccountVO login(AccountVO accountVO) throws Exception {
        AccountVO login = (AccountVO) daoService.selectOne("accountMapper.login", accountVO);
        if (login != null) {
            String token = tokenService.getToken(login);
            login.setToken(token);
            login.setExpiration(tokenService.getTokenExpiration(token));
            login.setSigned(true);
        } else {
            login = new AccountVO();
            login.setSigned(false);
        }
        return login;
    }
}

