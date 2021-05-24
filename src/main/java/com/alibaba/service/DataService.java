package com.alibaba.service;

import com.alibaba.bean.Result;
import com.alibaba.bean.ToDoList;
import com.alibaba.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author: FangCQU
 * @date: 2021/3/1 0:02
 * @description:
 */
@Service
@Transactional(rollbackFor = RuntimeException.class)
public class DataService {
    @Autowired
    private DataMapper dataMapper;
    @Autowired
    private MailService mailService;


    public Result insert(ToDoList toDoList) {
        System.out.println("service---------------------");
        System.out.println(toDoList);
        Result result = new Result();
        System.out.println(toDoList);
        dataMapper.insert(toDoList);
        result.setSuccess(true);
        result.setDetail(null);
        result.setMsg(null);
        return result;
    }

    public ToDoList[] findToDoListByName(String username) {
        ToDoList[] List = dataMapper.findToDoListByName(username);
        Arrays.toString(List);
        return List;
    }

    public Result delete(int id) {
        Result result = new Result();
        dataMapper.delete(id);
        result.setSuccess(true);
        result.setDetail(null);
        result.setMsg(null);
        return result;
    }

    public void findtargetinTime() {
        System.out.println("定时启动一次");
        int stamp = (int) (System.currentTimeMillis() / 1000);
        ToDoList[] lists = dataMapper.findtodolistinTime(stamp);
        System.out.println(Arrays.toString(lists));
        if (lists != null) {
            for (int i = 0; i < lists.length; i++) {
                ToDoList toDoList = lists[i];
                String email = dataMapper.emaily(toDoList.getUsername());
                mailService.sendSimpleMail(email, "来自To-Do List的提醒",
                        toDoList.getUsername() + ", 你好，您的任务 '"+toDoList.getHeader()+
                                "' 的截止时间为"+toDoList.getEnd_time()+ "\n将在十五分钟内到期。记得按时完成哦");
                System.out.println("发送");
            }
        }
    }

    public void update(String header, Date end_endtime, Date start_endtime, String addup, int ergency, int id) {
        System.out.println("update被调用");
        dataMapper.update(header, end_endtime, start_endtime, addup, ergency, id);
    }

}
