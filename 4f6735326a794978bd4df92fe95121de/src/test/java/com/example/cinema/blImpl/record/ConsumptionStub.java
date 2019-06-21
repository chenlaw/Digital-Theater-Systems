package java.com.example.cinema.blImpl.record;

import com.example.cinema.vo.ConsumptionVO;

import java.util.Date;

public class ConsumptionStub {
    public static ConsumptionVO insertConsumption(){
        ConsumptionVO vo=new ConsumptionVO(5,27,new Date(),"银行卡购买");
        return vo;
    }
}
