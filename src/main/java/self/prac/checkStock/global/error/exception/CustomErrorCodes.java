package self.prac.checkStock.global.error.exception;

public enum CustomErrorCodes {
    //member
    ALREADY_SIGNED("이미 가입된 이메일"),
    OVER_SIGNED("해당 이메일로 가입된 회원이 둘 이상"),
    NOT_SIGNED("해당 이메일로 가입된 회원이 없음"),
    INCORRECT_PASSWORD("비밀번호 이상"),
    //item
    NOT_ENOUGH_STOCK("재고 부족"),

    //auth
    NO_AUTH("권한 없음"),
    ;

    private String msg;

    CustomErrorCodes(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
