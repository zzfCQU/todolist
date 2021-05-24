package com.alibaba.service;

import com.alibaba.bean.Result;
import com.alibaba.bean.User;
import com.alibaba.controller.MD5Utils;
import com.alibaba.controller.RegisterController;
import com.alibaba.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    /**
     * 注册
     *
     * @param user 参数封装
     * @return Result
     */
    public Result regist(User user, String code, RedirectAttributes attributes) {
        System.out.println("user:" + user);
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        System.out.println("code:" + code);
        if (!codeisright(code, attributes)) {
            result.setMsg("验证码错误");
            return result;
        }
        try {
            User existUser = userMapper.findUserByName(user.getUsername());
            if (existUser != null) {
                //如果用户名已存在
                result.setMsg("用户名已存在");
            } else {
                userMapper.regist(user);
                result.setMsg("注册成功");
                result.setSuccess(true);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Boolean codeisright(String code, RedirectAttributes attributes) {
        System.out.println("cir函数内-------------------------------------");
        System.out.println(code);
        Map<String, Object> resultMap = RegisterController.resultMap;
        String requestHash = resultMap.get("hash").toString();
        String tamp = resultMap.get("tamp").toString();
        System.out.println("requestHash: " + requestHash);
        System.out.println("tamp: " + tamp);
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");//当前时间
        Calendar c = Calendar.getInstance();
        String currentTime = sf.format(c.getTime());
        if (tamp.compareTo(currentTime) > 0) {
            String hash = MD5Utils.code(code);//生成MD5值
            System.out.println("hash: " + hash);
            if (hash.equalsIgnoreCase(requestHash)) {
                //校验成功
                return true;
            } else {
                //验证码不正确，校验失败
                return false;
            }
        } else {
            // 超时
            return false;
        }

    }

    /**
     * 登录
     *
     * @param user 用户名和密码
     * @return Result
     */
    public Result login(User user) {
        Result result = new Result();
        result.setSuccess(false);
        result.setDetail(null);
        try {
            System.out.println(user);
            Long userId = userMapper.login(user);
            if (userId == null) {
                result.setMsg("用户名不存在或密码错误");
            } else {
                result.setMsg("登录成功");
                result.setSuccess(true);
                user.setId(userId);
                result.setDetail(user);
            }
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Result update(String username, String password) {
        Result result = new Result();
        result.setSuccess(true);
        result.setDetail(null);
        result.setMsg(null);
        userMapper.update(username, password);
        return result;
    }
    public Result find(String email){
        User u = userMapper.findUserByEmail(email);
        Result result = new Result();
        result.setSuccess(u != null);
        if(result.isSuccess()){
            mailService.sendSimpleMail(email,"找回密码",
                    u.getUsername()+" 你好,你的密码是 "+u.getPassword()+"\n下次不要弄丢了噢");
        }
        return result;
    }
}
