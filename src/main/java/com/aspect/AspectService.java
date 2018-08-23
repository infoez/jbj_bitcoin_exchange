package com.aspect;

import com.account.session.AccountSession;
import com.account.model.AccountVO;
import com.account.service.AccountService;
import com.account.token.TokenService;
import com.core.Calc;
import com.core.OrderVO;
import com.exceptions.ErrorCode;
import com.exceptions.ErrorMessage;
import com.exceptions.ServiceException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Aspect
@Service
public class AspectService {

    @Autowired
    AccountSession accountSession;

    @Autowired
    AccountService accountService;

    @Autowired
    TokenService tokenService;

    private static final Logger logger = LoggerFactory.getLogger(AspectService.class);

    @Around("@annotation(org.springframework.web.bind.annotation.ResponseBody)")
    @Order(1)
    public Map returnTypeAround(ProceedingJoinPoint jp) {
        Map response = new HashMap();
        try {
            response.put("params", jp.proceed());
            response.put("result_YN", true);
        } catch (Throwable e) {
            response.put("result_YN", false);
            if (e instanceof ServiceException) {
                response.put("errorCode", ((ServiceException) e).getErrorCode());
                response.put("errorMessage", ((ServiceException) e).getErrorMessage());
                logger.warn(e.getMessage());
            } else {
                response.put("errorCode", ErrorCode.UnknownError);
                response.put("errorMessage", ErrorMessage.UnknownError);
                logger.error(e.getMessage());
            }
        }
        return response;
    }

    @Before("@annotation(com.aspect.Authentication)")
    @ResponseBody
    @Order(2)
    public void authentication(JoinPoint jp) throws Exception {
        Object[] signatureArgs = jp.getArgs();
        for (Object arg : signatureArgs) {
            if (arg instanceof OrderVO) {
                String token = ((OrderVO) arg).getToken();
                if(!tokenService.isTokenValid(token))
                    throw new ServiceException(ErrorCode.TokenInvalid, ErrorMessage.TokenInvalid);
                break;
            }
        }
    }

    @Before("@annotation(com.aspect.EnoughMoney)")
    @ResponseBody
    @Order(3)
    public void isEnoughMoney(JoinPoint jp) throws ServiceException {
        Object[] signatureArgs = jp.getArgs();
        for (Object arg : signatureArgs) {
            if (arg instanceof OrderVO) {
                OrderVO orderVO = (OrderVO) arg;
                String email = orderVO.getEmail();
                AccountVO compare = accountService.selectAccountByEmail(email);
                String requiredKrw = Calc.multiply(orderVO.getQuantity(), orderVO.getPrice());
                if (Calc.compare(compare.getKrw(), requiredKrw) < 0)
                    throw new ServiceException(ErrorCode.NotEnoughKRW, ErrorMessage.NotEnoughKRW);
                break;
            }
        }
    }

    @Before("@annotation(com.aspect.EnoughMoney)")
    @ResponseBody
    @Order(3)
    public void isEnoughBTC(JoinPoint jp) throws ServiceException {
        Object[] signatureArgs = jp.getArgs();
        for (Object arg : signatureArgs) {
            if (arg instanceof OrderVO) {
                OrderVO orderVO = (OrderVO) arg;
                String email = orderVO.getEmail();
                AccountVO compare = accountService.selectAccountByEmail(email);
                String requiredBitcoin = orderVO.getQuantity();
                if (Calc.compare(compare.getKrw(), requiredBitcoin) < 0)
                    throw new ServiceException(ErrorCode.NotEnoguhBTC, ErrorMessage.NotEnoguhBTC);
            }
        }
    }
}