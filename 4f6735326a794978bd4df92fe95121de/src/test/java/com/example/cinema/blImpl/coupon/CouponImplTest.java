package java.com.example.cinema.blImpl.coupon;

import com.example.cinema.CinemaApplication;
import com.example.cinema.bl.promotion.CouponService;
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
public class CouponImplTest {
    @Autowired
    CouponService couponService;
    @Test
    public void sendCouponTest(){
        int[] user={3,5};
        int[] coupon={1,8,9};
        Assert.assertEquals(true,couponService.sendCoupons(user,coupon).getSuccess());
    }
}
