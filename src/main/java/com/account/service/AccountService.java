
package com.account.service;

import com.account.model.AccountVO;
import com.account.model.RegistrationVO;

import java.util.HashSet;
import java.util.List;

public interface AccountService {

    AccountVO selectAccountByEmail(AccountVO accountVO);

    AccountVO selectAccountByEmail(String email);

    List selectAccountListByEmails(List accounts);

    RegistrationVO registration(RegistrationVO registrationVO);

    AccountVO login(AccountVO accountVO) throws Exception;
}

