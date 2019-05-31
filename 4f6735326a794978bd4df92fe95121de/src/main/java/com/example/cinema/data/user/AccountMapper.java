package com.example.cinema.data.user;

import com.example.cinema.po.User;
import com.example.cinema.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;

/**
 * @author huwen
 * @date 2019/3/23
 */
@Mapper
public interface AccountMapper {

    /**
     * 创建一个新的账号
     * @param username
     * @param password
     * @return
     */
    public int createNewAccount(@Param("username") String username, @Param("password") String password);

    /**
     * 根据用户名查找账号
     * @param username
     * @return
     */
    public User getAccountByName(@Param("username") String username);
    /**
     * 根据用户id账号
     * @param id
     * @return User
     */
    public User getAccountById(@Param("id") int id);

    public int updateUser(User user);

    public int updateUserLevel(int userId,int level);

    public ArrayList<User> getAllUser();

    public int addUser(@Param("username") String username, @Param("password") String password,@Param("level") int level);
}
