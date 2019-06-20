package com.example.cinema.controller.user;

import com.example.cinema.bl.user.AdminService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/staff")
public class adminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("/add/user")
    public ResponseVO addUser(@RequestBody UserVO userVO){
        return adminService.addUser(userVO);
    }
    @PostMapping("/delete/user/{userId}")
    public ResponseVO deleteUser(@PathVariable int userId){
        System.out.println(userId);
        return adminService.deleteUser(userId);
    }
    @PostMapping("/update")
    public ResponseVO updateUser(@RequestBody UserVO userVO){
        return adminService.updateUser(userVO);
    }
    @PostMapping("/update/{userId}/{level}")
    public ResponseVO updateUserLevel(@PathVariable int userId,@PathVariable int level){
        return adminService.updateUserLevel(userId, level);
    }
    @GetMapping("/get/all")
    public ResponseVO getAllUser(){
        return adminService.getAllUser();
    }
    @GetMapping("/get/id/{userId}")
    public ResponseVO getUserById(@PathVariable int userId){
        return adminService.getUserById(userId);
    }

    @GetMapping("/get/username/{username}")
    public ResponseVO getUserByUserName(@PathVariable String username){
        return adminService.getUserByUserName(username);
    }


}
