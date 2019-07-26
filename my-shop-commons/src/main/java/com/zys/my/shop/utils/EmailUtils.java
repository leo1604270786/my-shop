package com.zys.my.shop.utils;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * @program: my-shop
 * @description: 邮箱工具类
 * @author: Leo
 * @create: 2019-07-07 15:58
 **/
public class EmailUtils {
    /**
    * @Description: 发送简单邮件
    * @Param: [username, password, theme, content, from, to] 用户名，密码，主题，内容，发送者，接受者
    * @Author: Leo
    */
    public static void sendSimpleEmail(String username, String password, String theme, String content, String from, String to){
        try {
            Email email = new SimpleEmail();
            //发送邮件的服务器地址
            email.setHostName("smtp.qq.com");
            //端口号
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            //SSL安全连接
            email.setSSLOnConnect(true);
            email.setFrom(from);
            email.setSubject(theme);
            email.setMsg(content);
            email.addTo(to);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }
}