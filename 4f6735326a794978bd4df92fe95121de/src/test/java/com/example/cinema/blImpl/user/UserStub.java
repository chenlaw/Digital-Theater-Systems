package com.example.cinema.blImpl.user;

import com.example.cinema.vo.UserVO;

/**
 * blimpl.user的stub类
 */
public class UserStub {
    /**
     * 模拟前端请求更新用户信息
     * @return
     */
    public static UserVO  postUpdateUserInfo(){
        UserVO test = new UserVO();
        test.setUsername("testname");
        test.setLevel(2);
        test.setPassword("testtest");
        return test;
    }

    public static UserVO  postAddUserInfo(){
        UserVO test = new UserVO();
        test.setUsername("testadd");
        test.setLevel(2);
        test.setPassword("testtest");
        return test;
    }
}
