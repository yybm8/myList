package com.yybm8.mylist.service;

import com.yybm8.mylist.dao.Result;
import com.yybm8.mylist.dao.User;

import java.util.List;
import java.util.Map;

public interface UserService
{
    Result page(Integer page, Integer PageSize,String username, String password);

    Result insert(User user);

    Result delete(User user);

    Result update(User user);
}
