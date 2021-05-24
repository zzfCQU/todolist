package com.alibaba.controller;

import com.alibaba.bean.Result;
import com.alibaba.bean.ToDoList;
import com.alibaba.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Date;


/**
 * @author: FangCQU
 * @date: 2021/2/28 23:54
 * @description:
 */
@RestController
@RequestMapping("/data")
public class DataController extends BaseController {
    @Autowired
    private DataService dataService;

    @GetMapping(value = "/update")
    @ResponseBody
    public Result regist(ToDoList toDoList) {
        System.out.println("controller---------------------");
        System.out.println(toDoList);
        return dataService.insert(toDoList);
    }

    @GetMapping(value = "/delete")
    @ResponseBody
    public Result delete(int id) {
        return dataService.delete(id);
    }

    @GetMapping(value = "/update1")
    @ResponseBody
    public ToDoList[] findToDoListByName(String username) {
        return dataService.findToDoListByName(username);
    }

    @GetMapping(value = "/update2")
    @ResponseBody
    public void update(String header, Date end_time, Date start_time, String addup, int ergency, int id) throws UnsupportedEncodingException {
        System.out.println(header);
        System.out.println(end_time);
        System.out.println(start_time);
        System.out.println(addup);
        System.out.println("controller的update被调用");
        dataService.update(header, end_time, start_time, addup, ergency, id);
    }
}
