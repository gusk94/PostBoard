package ac.dankook.postboard.data;

public class User {
    private int userNo;
    private String userId;
    private String userPw;
    private String userName;
    private String email;

    public int getUserNo() { return userNo; }

    public void setUserNo(int userNo) { this.userNo = userNo; }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() { return userPw; }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}