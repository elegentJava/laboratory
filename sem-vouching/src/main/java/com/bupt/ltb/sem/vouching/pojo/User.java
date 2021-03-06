package com.bupt.ltb.sem.vouching.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

import com.bupt.ltb.sem.vouching.frame.Consts;

/**
 * 用户实体
 *
 * @author Hogan
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String account;
    private String password;
    private String name;
    private Integer sex;
    private String email;
    private Integer isOnline;
    private Date lastLoginDate;
    private Integer role;
    private Integer credit;
    private Integer isPass;
    //计算练习，竞技，考试，案例的学分
    private Integer allCredit;

    // 前台显示要用到的
    private String formatLastLoginDate;
    private Integer classId;
    private String className;
    private String sexName;
    private String isOnlineName;
    private String roleName;

    //新增练习，竞技通过标记
    private Integer practicePassed;
    private Integer competitionPassed;
    //总共做的竞技题数
    private Integer competitionPracticed;
    //总共做的练习题数
    private Integer practiceDone;
    private Date loginDate;
    private String competitionIndex;
    private Integer competitionScore;

    private Class clas;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public void addAllCredit(Integer allCredit) {
        this.allCredit += allCredit;
    }

    public Integer getAllCredit() {
        return allCredit;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Class getClas() {
        return clas;
    }

    public void setClas(Class clas) {
        this.clas = clas;
    }

    public String getFormatLastLoginDate() {
        if (this.lastLoginDate == null) {
            formatLastLoginDate = "刚注册，还没有登录过!";
        } else {
            formatLastLoginDate = DateFormatUtils.format(this.lastLoginDate, Consts.DATE_SIMPLE_PATTERN);
        }
        return formatLastLoginDate;
    }

    public void setFormatLastLoginDate(String formatLastLoginDate) {
        this.formatLastLoginDate = formatLastLoginDate;
    }

    public Integer getClassId() {
        if (this.clas != null) {
            classId = this.clas.getClassId();
        }
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        if (this.clas != null) {
            className = this.clas.getClassName();
        }
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getSexName() {
        if (this.sex != null) {
            sexName = this.sex == 1 ? "男" : "女";
        }
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getIsOnlineName() {
        if (this.isOnline != null) {
            isOnlineName = this.isOnline == 1 ? "在线" : "不在线";
        }
        return isOnlineName;
    }

    public void setIsOnlineName(String isOnlineName) {
        this.isOnlineName = isOnlineName;
    }

    public String getRoleName() {
        if (this.role != null) {
            roleName = this.role == 1 ? "学生" : "教师";
        }
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getCompetitionIndex() {
        return competitionIndex;
    }

    public void setCompetitionIndex(String competitionIndex) {
        this.competitionIndex = competitionIndex;
    }

    public Integer getCompetitionScore() {
        return competitionScore;
    }

    public void setCompetitionScore(Integer competitionScore) {
        this.competitionScore = competitionScore;
    }

    public Integer getPracticePassed() {
        return practicePassed;
    }

    public void addPracticePassed(Integer practicePassed) {
        this.practicePassed += practicePassed;
    }

    public Integer getCompetitionPassed() {
        return competitionPassed;
    }

    public void addCompetitionPassed(Integer competitionPassed) {
        this.competitionPassed += competitionPassed;
    }

    public Integer getCompetitionPracticed() {
        return competitionPracticed;
    }

    public void addCompetitionPracticed(Integer competitionPracticed) {
        this.competitionPracticed += competitionPracticed;
    }

    public Integer getPracticeDone() {
        return practiceDone;
    }

    public void addPracticeDone(Integer practiceDone) {
        this.practiceDone += practiceDone;
    }
}
