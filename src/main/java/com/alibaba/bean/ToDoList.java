package com.alibaba.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author: FangCQU
 * @date: 2021/2/28 23:59
 * @description:
 */
public class ToDoList {
    private String header;
    //end_time=2021-03-03T20%3A01
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date end_time;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm")
    private Date start_time;
    private String addup;
    private int ergency;
    private String username;
    private int id;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public String getAddup() {
        return addup;
    }

    public void setAddup(String addup) {
        this.addup = addup;
    }

    public int getErgency() {
        return ergency;
    }

    public void setErgency(int ergency) {
        this.ergency = ergency;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ToDoList{" +
                "header='" + header + '\'' +
                ", end_time='" + end_time + '\'' +
                ", start_time='" + start_time + '\'' +
                ", addup='" + addup + '\'' +
                ", ergency=" + ergency +
                ", username='" + username + '\'' +
                '}';
    }
}
