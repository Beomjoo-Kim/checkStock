package self.prac.checkStock.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class CustomRuntimeExceptionHandler {

    @ExceptionHandler(CustomRuntimeException.class)
    public CustomErrorResponse handleException(CustomRuntimeException e, HttpServletRequest request) {
        log.error("errorCode : {}, message : {}", e.getCode(), e.getMsg());
        return CustomErrorResponse.builder().code(e.getCode()).msg(e.getMsg()).build();
    }
}
