package self.prac.checkStock.domain.member;

public enum MemberStatus {

    NORMAL("USC001"),
    BANNED("USC002"),
    WITHDRAW("USC003");

    private String code;

    MemberStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

}
