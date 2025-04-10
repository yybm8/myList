package com.yybm8.mylist.collection;

import com.yybm8.mylist.dao.Pages;
import com.yybm8.mylist.dao.Result;
import com.yybm8.mylist.dao.User;
import com.yybm8.mylist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:5173/")
@RestController
@RequestMapping("/users")
public class UserCollection {

    @Autowired
    private UserService userService;

    @PostMapping("/page")//分页
    public Result page(@RequestBody Pages pages){

        Integer page = pages.getPage();
        Integer pageSize = pages.getPageSize();
        String username = pages.getUsername();
        String password = pages.getPassword();
        if(page<=0&&pageSize<=0){
            page=1;
            pageSize=5;
        }
            return userService.page(page, pageSize, username, password);

    }
    @PostMapping("/insert")//添加
    public Result insert(@RequestBody User user){
        if(user.getUsername()==null&&user.getPassword()==null){
            return Result.noWork();
        }
        return userService.insert(user);
    }
    @PostMapping("/delete")//根据id或姓名或密码删除
    public Result delete(@RequestBody User user){
        if(user.getUsername()==null&&user.getPassword()==null&&user.getId()==null){
            return Result.noWork();
        }
        return userService.delete(user);
    }

    @PostMapping("/update")//根据传入的信息动态更新id必须传入
    public Result update(@RequestBody User user){
        if(user.getUsername()==null||user.getPassword()==null){
            return Result.noWork();
        }
        return userService.update(user);
    }
}
