package com.cssf.emil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @Auther:Fluke Kuang
 * @Date: 2022/9/18 - 09 -18 - 13:20
 * @Description: com.cssf.emil
 * @Version: 1.0
 */
@Component
public class sendMail {
    @Autowired
    JavaMailSenderImpl mailSender;
    public void sendMailUtils(String subject,String text) throws MessagingException, MessagingException {
        //一个复杂的邮件
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        //组装
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);

        helper.setSubject(subject);
        helper.setText(text);


        helper.setTo("2950488020@qq.com");
        helper.setFrom("1228231744@qq.com");

        mailSender.send(mimeMessage);
    }
}
