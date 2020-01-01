package com.sc.dao;

import com.sc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {

    List<User> getAll();

    @Select("select * from tbl_user where id = #{id}")
    User selectOne();

}
