package self.prac.checkStock.global.error.exception;

import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    private CustomErrorCodes code;
    private String msg;

    public CustomRuntimeException(CustomErrorCodes code) {
        super(code.getMsg());
        this.code = code;
        this.msg = code.getMsg();
    }
}
