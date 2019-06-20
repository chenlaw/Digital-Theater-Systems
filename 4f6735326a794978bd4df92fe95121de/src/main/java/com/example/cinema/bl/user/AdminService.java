package com.example.cinema.bl.user;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.UserVO;
import javax.xml.ws.Response;

public interface AdminService {
    /**
     * 获取系统所有用户
     * @author wph
     * @return
     */
    public ResponseVO getAllUser();

    /**
     * 根据用户id查找用户
     * @author wph
     * @param userId
     * @return
     */
    public ResponseVO getUserById(int userId);

    /**
     * 根据用户名获取用户
     * @author wph
     * @param username
     * @return
     */
    public ResponseVO getUserByUserName(String username);

    /**
     * 修改用户信息
     * @author wph
     * @param userVO
     * @return
     */
    public ResponseVO updateUser(UserVO userVO);

    /**
     * 修改用户权限等级
     * @author wph
     * @param userId
     * @param level
     * @return
     */
    public ResponseVO updateUserLevel(int userId,int level);


    /**
     * 删除用户
     * @author wph
     * @param userId
     * @return
     */
    public ResponseVO deleteUser(int userId);

    /**
     * 新增用户
     * @author wph
     * @param userVO
     * @return
     */
    public ResponseVO addUser(UserVO userVO);
}