package com.hearien.demo.user.model;

import com.hearien.demo.common.BaseEntity;

import java.util.Date;
import javax.persistence.*;

public class AppUser extends BaseEntity {
    @Id
    @Column(name = "appUserID")
    private Integer appuserid;

    private String birth;

    @Column(name = "contactAddress")
    private String contactaddress;

    private String email;

    private String flag;

    private String gender;

    @Column(name = "gesturePwd")
    private String gesturepwd;

    @Column(name = "hasSetUserName")
    private String hassetusername;

    @Column(name = "idNumber")
    private String idnumber;

    private Integer integral;

    @Column(name = "loginPassword")
    private String loginpassword;

    @Column(name = "nickName")
    private String nickname;

    private String phone;

    @Column(name = "realName")
    private String realname;

    @Column(name = "registIP")
    private String registip;

    @Column(name = "registTime")
    private Date registtime;

    @Column(name = "registType")
    private String registtype;

    private String state;

    @Column(name = "updateTime")
    private Date updatetime;

    @Column(name = "userLogo")
    private String userlogo;

    @Column(name = "userName")
    private String username;

    @Column(name = "verifyState")
    private String verifystate;

    @Column(name = "vipType")
    private String viptype;

    /**
     * @return appUserID
     */
    public Integer getAppuserid() {
        return appuserid;
    }

    /**
     * @param appuserid
     */
    public void setAppuserid(Integer appuserid) {
        this.appuserid = appuserid;
    }

    /**
     * @return birth
     */
    public String getBirth() {
        return birth;
    }

    /**
     * @param birth
     */
    public void setBirth(String birth) {
        this.birth = birth == null ? null : birth.trim();
    }

    /**
     * @return contactAddress
     */
    public String getContactaddress() {
        return contactaddress;
    }

    /**
     * @param contactaddress
     */
    public void setContactaddress(String contactaddress) {
        this.contactaddress = contactaddress == null ? null : contactaddress.trim();
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return flag
     */
    public String getFlag() {
        return flag;
    }

    /**
     * @param flag
     */
    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    /**
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    /**
     * @return gesturePwd
     */
    public String getGesturepwd() {
        return gesturepwd;
    }

    /**
     * @param gesturepwd
     */
    public void setGesturepwd(String gesturepwd) {
        this.gesturepwd = gesturepwd == null ? null : gesturepwd.trim();
    }

    /**
     * @return hasSetUserName
     */
    public String getHassetusername() {
        return hassetusername;
    }

    /**
     * @param hassetusername
     */
    public void setHassetusername(String hassetusername) {
        this.hassetusername = hassetusername == null ? null : hassetusername.trim();
    }

    /**
     * @return idNumber
     */
    public String getIdnumber() {
        return idnumber;
    }

    /**
     * @param idnumber
     */
    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    /**
     * @return integral
     */
    public Integer getIntegral() {
        return integral;
    }

    /**
     * @param integral
     */
    public void setIntegral(Integer integral) {
        this.integral = integral;
    }

    /**
     * @return loginPassword
     */
    public String getLoginpassword() {
        return loginpassword;
    }

    /**
     * @param loginpassword
     */
    public void setLoginpassword(String loginpassword) {
        this.loginpassword = loginpassword == null ? null : loginpassword.trim();
    }

    /**
     * @return nickName
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return realName
     */
    public String getRealname() {
        return realname;
    }

    /**
     * @param realname
     */
    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    /**
     * @return registIP
     */
    public String getRegistip() {
        return registip;
    }

    /**
     * @param registip
     */
    public void setRegistip(String registip) {
        this.registip = registip == null ? null : registip.trim();
    }

    /**
     * @return registTime
     */
    public Date getRegisttime() {
        return registtime;
    }

    /**
     * @param registtime
     */
    public void setRegisttime(Date registtime) {
        this.registtime = registtime;
    }

    /**
     * @return registType
     */
    public String getRegisttype() {
        return registtype;
    }

    /**
     * @param registtype
     */
    public void setRegisttype(String registtype) {
        this.registtype = registtype == null ? null : registtype.trim();
    }

    /**
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state
     */
    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    /**
     * @return updateTime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return userLogo
     */
    public String getUserlogo() {
        return userlogo;
    }

    /**
     * @param userlogo
     */
    public void setUserlogo(String userlogo) {
        this.userlogo = userlogo == null ? null : userlogo.trim();
    }

    /**
     * @return userName
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return verifyState
     */
    public String getVerifystate() {
        return verifystate;
    }

    /**
     * @param verifystate
     */
    public void setVerifystate(String verifystate) {
        this.verifystate = verifystate == null ? null : verifystate.trim();
    }

    /**
     * @return vipType
     */
    public String getViptype() {
        return viptype;
    }

    /**
     * @param viptype
     */
    public void setViptype(String viptype) {
        this.viptype = viptype == null ? null : viptype.trim();
    }
}