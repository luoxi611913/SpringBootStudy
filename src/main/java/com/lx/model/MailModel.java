package com.lx.model;

import java.io.Serializable;

public class MailModel implements Serializable {

    /**
     * 任务id
     */
    private Integer tid;

    /**
     * 任务日期
     */
    private String tchron;

    /*
    接收者
     */
    private String toUser;

    /*
    发送者
     */
    private String fromUser;

    /*
    标题
     */
    private String mailTitle;

    /*
    内容
     */
    private String mailContents;

    /*
    日期
     */
    private String doDate;

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getMailTitle() {
        return mailTitle;
    }

    public void setMailTitle(String mailTitle) {
        this.mailTitle = mailTitle;
    }

    public String getMailContents() {
        return mailContents;
    }

    public void setMailContents(String mailContents) {
        this.mailContents = mailContents;
    }

    public String getDoDate() {
        return doDate;
    }

    public void setDoDate(String doDate) {
        this.doDate = doDate;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTchron() {
        return tchron;
    }

    public void setTchron(String tchron) {
        this.tchron = tchron;
    }

    @Override
    public String toString() {
        return "MailModel{" +
                "tid=" + tid +
                ", tchron='" + tchron + '\'' +
                ", toUser='" + toUser + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", mailTitle='" + mailTitle + '\'' +
                ", mailContents='" + mailContents + '\'' +
                ", doDate='" + doDate + '\'' +
                '}';
    }
}
