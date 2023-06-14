package com.visit.program.ReservationProgram.domain.ex;
/**
 * 이미 체크된 방문객을 다시 클릭하였을때 -> 예외 발생 시켜주기위한 클래스
 * */
public class AlreadyCheckedEx extends RuntimeException{
    public AlreadyCheckedEx() {
        super(ErrorMessage.ALREADY_CHECKED);
    }

    public AlreadyCheckedEx(String message) {
        super(message);
    }

    public AlreadyCheckedEx(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyCheckedEx(Throwable cause) {
        super(cause);
    }

    protected AlreadyCheckedEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
