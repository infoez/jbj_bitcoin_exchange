package com.exceptions;

public class ErrorMessage {


    public static String AuthenticationError = "제출 된 로그인 토큰 값이 유효하지 않습니다. 다시 로그인 하세요.";
    public static String TokenInvalid = "토큰 서명이 유효하지 않습니다.";
    public static String TokenExpirationError = "제출 된 토큰의 기간이 만료 되었습니다. 다시 로그인 하세요.";
    public static String InvalidEmailOrPassword = "이메일 및 비밀번호를 확인하여 주세요.";

    public static String UnknownError = "알 수 없는 에러가 발생 하였습니다.";
    public static String NotEnoughKRW = "사용 가능한 원화가 충분하지 않습니다.";
    public static String NotEnoguhBTC = "사용 가능한 비트코인이 충분하지 않습니다.";
    public static String UncertainTransactionType = "요청의 구매와 판매 여부를 확인 할 수 없습니다.";

}
