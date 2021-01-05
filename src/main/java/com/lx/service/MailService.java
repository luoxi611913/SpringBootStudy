package com.lx.service;

import com.lx.model.MailModel;

public interface MailService {
    /**
     * 发送文本邮件
     * @param to 收件人
     * @param subject 主题
     * @param content 内容
     */
    public void sendSimpleMail(String to, String subject, String content);

    /*
    插入一个新计划
     */
    public void insertNewSchedu(MailModel mailModel);
}
