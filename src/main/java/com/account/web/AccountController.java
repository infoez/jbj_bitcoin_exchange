package com.account.web;

import com.account.session.AccountSession;
import com.account.model.AccountVO;
import com.account.model.RegistrationVO;
import com.account.service.AccountService;
import com.account.token.TokenService;
import com.exceptions.ErrorCode;
import com.exceptions.ErrorMessage;
import com.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @Autowired
    TokenService tokenService;

    @Autowired
    AccountSession session;

    @RequestMapping(value = "/account/registration.do", method = RequestMethod.POST)
    @ResponseBody
    public Map registration(@RequestBody @Valid RegistrationVO registrationVO, BindingResult result) throws Exception {
        Map response = new HashMap();
        if (result.hasErrors())
            throw new Exception("validation exception");
        registrationVO = accountService.registration(registrationVO);
        response.put("ok", registrationVO.isOk());
        return response;
    }

    @RequestMapping(value = "/account/login.do", method = RequestMethod.POST)
    @ResponseBody
    public Map login(HttpServletRequest request, @RequestBody @Valid AccountVO accountVO, BindingResult result) throws Exception {
        Map response = new HashMap();
        if (result.hasFieldErrors("email") || result.hasFieldErrors("password")) {
            throw new ServiceException(ErrorCode.InvalidEmailOrPassword, ErrorMessage.InvalidEmailOrPassword);
        } else {
            accountVO = accountService.login(accountVO); /** ID, Password 확인 후 토큰 발급 **/
            response.put("account", accountVO);
            return response;
        }
    }

    @RequestMapping(value = "/account/refreshToken.do", method = RequestMethod.POST)
    @ResponseBody
    public Map tokenAuthentication(@Valid @RequestBody AccountVO accountVO, BindingResult result) throws Exception {
        Map response = new HashMap();
        response.put("refreshToken", tokenService.getRefreshedToken(accountVO.getToken()));
        return response;
    }

    @RequestMapping(value = "/account/tokenValidation.do", method = RequestMethod.POST)
    @ResponseBody
    public Map tokenValidation(@RequestBody @Valid AccountVO accountVO, BindingResult result) throws Exception {
        Map response = new HashMap();

        if (result.hasFieldErrors("token")) {
            throw new ServiceException(ErrorCode.TokenInvalid, ErrorMessage.TokenInvalid);
        } else {
            boolean isTokenValid = tokenService.isTokenValid(accountVO.getToken());
            if (isTokenValid) {
                response.put("account", accountVO);
            } else {
                AccountVO account = new AccountVO();
                account.setSigned(false);
                response.put("account", account);
            }
            return response;
        }
    }
}
