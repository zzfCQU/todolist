package com.alibaba.controller;

import com.alibaba.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/tourist")
public class RegisterController {

    public static Map<String, Object> resultMap = new HashMap<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    private String sender = "593058278@qq.com";// 发送者
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/register")
    public String register() {
        return "tourist/register";
    }


    @RequestMapping("/sendEmail")
    //转换json数据  @ResponseBody
    @ResponseBody
    public String sendEmail(String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        String code = VerifyCode(6);    //随机数生成6位验证码
        message.setFrom(sender);
        message.setTo(email);
        message.setSubject("todoList");// 标题
        message.setText("【todoList】你的验证码为：" + code + "，有效时间为5分钟(若不是本人操作，可忽略该条邮件)");// 内容
        try {
            javaMailSender.send(message);
            logger.info("文本邮件发送成功！");
            saveCode(code);
            return "success";
        } catch (MailSendException e) {
            logger.error("目标邮箱不存在");
            return "false";
        } catch (Exception e) {
            logger.error("文本邮件发送异常！", e);
            return "failure";
        }
    }

    private String VerifyCode(int n) {
        Random r = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int ran1 = r.nextInt(10);
            sb.append(String.valueOf(ran1));
        }
//        System.out.println(sb);
        return sb.toString();
    }

    //保存验证码和时间
    private void saveCode(String code) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 5);
        String currentTime = sf.format(c.getTime());// 生成5分钟后时间，用户校验是否过期

        String hash = MD5Utils.code(code);//生成MD5值
        resultMap.put("hash", hash);
        resultMap.put("tamp", currentTime);
    }

}