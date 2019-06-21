package java.com.example.cinema.blImpl.record;

import com.example.cinema.vo.RechargeVO;

import java.util.Date;

public class RechargeStub {
    public static RechargeVO insertRecharge(){
        RechargeVO rechargeVO=new RechargeVO(3,new Date(),24);
       return rechargeVO;

    }
}
