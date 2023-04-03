package self.prac.checkStock.exception;

public enum CustomErrorCodes {
    NOT_ENOUGH_STOCK("재고 부족");

    private String msg;

    CustomErrorCodes(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
