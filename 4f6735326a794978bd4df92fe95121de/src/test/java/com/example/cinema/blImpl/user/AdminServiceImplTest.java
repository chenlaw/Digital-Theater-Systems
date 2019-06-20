package com.example.cinema.blImpl.user;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.user.AdminService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
@SpringBootTest(classes = CinemaApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Transactional
public class AdminServiceImplTest {
    @Autowired
    private AdminService adminService;


    @Test
    public void getAllUser() {
        Assert.assertEquals(true,adminService.getAllUser().getSuccess());
    }

    @Test
    public void getUserById() {
        Assert.assertEquals(true,adminService.getUserById(1).getSuccess());
    }

    @Test
    public void getUserByUserName() {
        Assert.assertEquals(true,adminService.getUserByUserName("testname").getSuccess());
    }

    @Test
    public void updateUser() {
        Assert.assertEquals(true,adminService.updateUser(UserStub.postUpdateUserInfo()).getSuccess());
    }

    @Test
    public void updateUserLevel() {
        Assert.assertEquals(true,adminService.updateUser(UserStub.postUpdateUserInfo()).getSuccess());
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void addUser() {
        Assert.assertEquals();
    }
}