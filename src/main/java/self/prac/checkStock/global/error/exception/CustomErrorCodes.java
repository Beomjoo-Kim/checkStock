package self.prac.checkStock.global.error.exception;

public enum CustomErrorCodes {
    //member
    ALREADY_SIGNED("이미 가입된 이메일"),
    OVER_SIGNED("해당 이메일로 가입된 회원이 둘 이상"),
    NOT_SIGNED("해당 이메일로 가입된 회원이 없음"),
    INCORRECT_PASSWORD("비밀번호 이상"),
    //item
    NOT_ENOUGH_STOCK("재고 부족"),
    NO_ITEM_CATEGORY("해당 물품 분류 없음"),
    ITEM_NOT_FOUND("해당 물품 없음"),

    //auth
    NO_AUTH("권한 없음"),

    //token
    TOKEN_NOT_FOUND("해당 토큰을 찾을 수 없음"),
    TOKEN_EXPIRED("토큰 만료됨"),
    ;

    private String msg;

    CustomErrorCodes(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
