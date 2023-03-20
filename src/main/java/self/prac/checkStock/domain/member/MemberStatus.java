package self.prac.checkStock.domain.member;

public enum MemberStatus {

    NORMAL("USC001"),       //정상
    BANNED("USC002"),       //추방
    WITHDRAW("USC003");     //탈퇴

    private String code;

    MemberStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
