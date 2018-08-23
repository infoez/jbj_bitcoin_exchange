package com.socket.interceptor;

import com.account.token.TokenService;
import com.exceptions.ErrorCode;
import com.exceptions.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptorAdapter;

import java.util.List;

public class TokenPermissionInterceptor extends ChannelInterceptorAdapter {

    @Autowired
    TokenService tokenService;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(message);
        if (isTransactionSubscribe(headerAccessor)) {
            List tokenRow = headerAccessor.getNativeHeader("token");
            if (!((tokenRow.size() == 1) && tokenService.isTokenValid((String) tokenRow.get(0))))
                throw new IllegalArgumentException(ErrorCode.TokenInvalid + " : " + ErrorMessage.TokenInvalid);
        }
        return message;
    }

    private boolean isTransactionSubscribe(StompHeaderAccessor headerAccessor) {
        String push_transaction = "/push/transaction";
        return (StompCommand.SUBSCRIBE.equals(headerAccessor.getCommand()) && headerAccessor.getDestination().contains(push_transaction));
    }


}
