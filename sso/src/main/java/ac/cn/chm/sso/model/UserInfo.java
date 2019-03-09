package ac.cn.chm.sso.model;

import java.util.Date;

public class UserInfo {
    private Long userId;

    private String userAccount;

    private String userPass;

    private Date addTime;

    private String userIcon;

    private String userStatus;

    private Date valiTime;

    private Long operatorId;

    private Long ownerId;

    private String userRow1;

    private String userRow2;

    private String userRow3;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass == null ? null : userPass.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon == null ? null : userIcon.trim();
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public Date getValiTime() {
        return valiTime;
    }

    public void setValiTime(Date valiTime) {
        this.valiTime = valiTime;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getUserRow1() {
        return userRow1;
    }

    public void setUserRow1(String userRow1) {
        this.userRow1 = userRow1 == null ? null : userRow1.trim();
    }

    public String getUserRow2() {
        return userRow2;
    }

    public void setUserRow2(String userRow2) {
        this.userRow2 = userRow2 == null ? null : userRow2.trim();
    }

    public String getUserRow3() {
        return userRow3;
    }

    public void setUserRow3(String userRow3) {
        this.userRow3 = userRow3 == null ? null : userRow3.trim();
    }
}