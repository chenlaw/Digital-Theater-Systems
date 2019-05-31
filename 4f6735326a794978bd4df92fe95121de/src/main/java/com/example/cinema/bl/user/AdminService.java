package com.example.cinema.bl.user;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;

import javax.xml.ws.Response;

public interface AdminService {
    public ResponseVO getAllUser();

    public ResponseVO getUserById(int userId);

    public ResponseVO getUserByUserName(String username);

    public ResponseVO updateUser(UserVO userVO);

    public ResponseVO updateUserLevel(int userId,int level);

    public ResponseVO deleteUser(int userId);

    public ResponseVO addUser(UserVO userVO);
}