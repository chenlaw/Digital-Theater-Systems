package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AdminService;
import com.example.cinema.data.user.AccountMapper;
import com.example.cinema.po.User;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AccountMapper accountMapper;
    @Override
    public ResponseVO getAllUser() {
        ArrayList<UserVO> userVOArrayList = new ArrayList<UserVO>();
        try {
            for (User user:accountMapper.getAllUser()
            ) {
                userVOArrayList.add(new UserVO(user));
            }
            return ResponseVO.buildSuccess(userVOArrayList);
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getUserById(int userId) {
        try {
            User user = accountMapper.getAccountById(userId);
            UserVO userVO = new UserVO(user);
            return ResponseVO.buildSuccess(userVO);
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getUserByUserName(String username) {
        try {
            User user = accountMapper.getAccountByName(username);
            UserVO userVO = new UserVO(user);
            return ResponseVO.buildSuccess(userVO);
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateUser(UserVO userVO) {
        try {
            User user = new User();
            user.setId(userVO.getId());
            user.setLevel(userVO.getLevel());
            user.setPassword(userVO.getPassword());
            user.setUsername(userVO.getUsername());
            accountMapper.updateUser(user);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO updateUserLevel(int userId, int level) {
        try{
            accountMapper.updateUserLevel(userId, level);
            return  ResponseVO.buildSuccess();
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");

        }
    }

    @Override
    public ResponseVO deleteUser(int userId) {
        return null;
    }

    @Override
    public ResponseVO addUser(UserVO userVO) {
        try {
            System.out.println("?");
            accountMapper.addUser(userVO.getUsername(),userVO.getPassword(),userVO.getLevel());
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            return ResponseVO.buildFailure("失败");
        }
    }
}
