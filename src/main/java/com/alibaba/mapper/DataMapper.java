package com.alibaba.mapper;

import com.alibaba.bean.ToDoList;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * @author: FangCQU
 * @date: 2021/2/28 23:54
 * @description:
 */
@Mapper //标记mapper文件位置，否则在Application.class启动类上配置mapper包扫描
@Repository
public interface DataMapper {
    @Insert("insert into todolist values(#{header},#{end_time},#{start_time},#{addup},#{ergency},#{username},#{id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(ToDoList todolist);

    @Delete(value = "delete from todolist where id=#{id}")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void delete(@Param("id") int id);

    @Select(value = "select t.header,t.end_time,t.start_time,t.addup,t.ergency,t.username,t.id from todolist t where t.username=#{username}")
    @Results
            ({@Result(property = "header", column = "header"),
                    @Result(property = "end_time", column = "end_time"),
                    @Result(property = "start_time", column = "start_time"),
                    @Result(property = "addup", column = "addup"),
                    @Result(property = "ergency", column = "ergency"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "id", column = "id")
            })
    ToDoList[] findToDoListByName(@Param("username") String username);


    @Update(value = "update todolist set header = #{header},end_time=#{end_time},start_time=#{start_time},addup=#{addup},ergency=#{ergency} where id=#{id}")
    void update(@Param("header") String header, @Param("end_time") Date end_endtime, @Param("start_time") Date start_endtime, @Param("addup") String addup, @Param("ergency") int ergency, @Param("id") int id);


    @Select(value = "select t.header,t.end_time,t.start_time,t.addup,t.ergency,t.username,t.id from todolist t where (( UNIX_TIMESTAMP(t.end_time)) - #{timestamp} )<900 and ( UNIX_TIMESTAMP(t.end_time) - #{timestamp} )>0")
    @Results
            ({@Result(property = "header", column = "header"),
                    @Result(property = "end_time", column = "end_time"),
                    @Result(property = "start_time", column = "start_time"),
                    @Result(property = "addup", column = "addup"),
                    @Result(property = "ergency", column = "ergency"),
                    @Result(property = "username", column = "username"),
                    @Result(property = "id", column = "id")
            })
    ToDoList[] findtodolistinTime(@Param("timestamp") long timestamp);

    @Select(value = "select u.email  from user u where u.username=#{username}")
    String emaily(@Param("username") String username);

}
