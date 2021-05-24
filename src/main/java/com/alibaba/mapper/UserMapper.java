package com.alibaba.mapper;

import com.alibaba.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * mapper的具体表达式
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 查询用户名是否存在，若存在，不允许注册
     * 注解@Param(value) 若value与可变参数相同，注解可省略
     * 注解@Results  列名和字段名相同，注解可省略
     *
     * @param username
     * @return
     */
    @Select(value = "select u.username,u.password from user u where u.username=#{username}")
    @Results
            ({@Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password")})
    User findUserByName(@Param("username") String username);

    @Select(value = "select u.username,u.password from user u where u.email=#{email} limit 1")
    @Results
            ({@Result(property = "username", column = "username"),
                    @Result(property = "password", column = "password")})
    User findUserByEmail(@Param("email") String email);

    /**
     * 注册  插入一条user记录
     *
     * @param user
     * @return
     */
    @Insert("insert into user values(#{id},#{username},#{password},#{email})")
    //加入该注解可以保存对象后，查看对象插入id
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void regist(User user);

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @Select("select u.id from user u where u.username = #{username} and password = #{password}")
    Long login(User user);


    @Update(value = "update user set password=#{password} where username = #{username}")
    void update(@Param("username") String username, @Param("password") String password);
}
