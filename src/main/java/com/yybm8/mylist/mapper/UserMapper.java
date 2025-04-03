package com.yybm8.mylist.mapper;

import com.yybm8.mylist.dao.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
     List<User> selectAll(@Param("page")Integer page, @Param("pageSize")Integer pageSize,@Param("username") String username,@Param("password") String password);

    @Select("select count(*) from workdata.user")
     int selectCount();

    boolean insert(User user);

    boolean delete(User user);

    boolean update(User user);
}
