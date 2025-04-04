package com.yybm8.mylist.service.api;

import com.yybm8.mylist.dao.Result;
import com.yybm8.mylist.dao.User;
import com.yybm8.mylist.mapper.UserMapper;
import com.yybm8.mylist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceApi implements UserService {
    @Autowired
    private UserMapper usermapper;

    @Override
    public Result page(Integer page, Integer pageSize, String username, String password) {
        System.out.println("page:"+page+"pageSize:"+pageSize+"aaa");
        int count = usermapper.selectCount();
        page=(page-1)*pageSize;
        List<User> user=usermapper.selectAll(page,pageSize,username,password);
        Map<String,Object> map=new HashMap<>();
        map.put("data",user);
        map.put("count",count);

        if(user.isEmpty()){
            return  Result.noData();
        }
        else{
            return Result.success(map);
        }

    }

    @Override
    public Result insert(User user) {
        boolean a = usermapper.insert(user);
        return IF(a);
    }

    @Override
    public Result delete(User user) {
        boolean a = usermapper.delete(user);
        return IF(a);
    }

    @Override
    public Result update(User user) {
        boolean a = usermapper.update(user);
        return IF(a);
    }

    public Result IF(boolean a){
        if(a) {
            return Result.yesWork();
        }
        else{
            return Result.noWork();
        }
    }
}


