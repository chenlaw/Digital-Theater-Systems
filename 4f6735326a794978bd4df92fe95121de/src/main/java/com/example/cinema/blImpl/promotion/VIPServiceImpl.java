package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.bl.record.RechargeService;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.data.promotion.VIPInfoMapper;
import com.example.cinema.po.Recharge;
import com.example.cinema.vo.RechargeVO;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.po.VIPCard;
import com.example.cinema.po.VIPInfo;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by liying on 2019/4/14.
 */
@Service
public class VIPServiceImpl implements VIPService {
    @Autowired
    VIPCardMapper vipCardMapper;
    @Autowired
    VIPInfoMapper vipInfoMapper;
    @Autowired
    RechargeService rechargeService;
    @Override
    public ResponseVO updateVIPInfo(VIPInfoVO vipInfoVO) {
        try {
            VIPInfo v= new VIPInfo(vipInfoVO);
            vipInfoMapper.updateVIPInfo(v);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
            e.printStackTrace();
            return  ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getAllVIPInfo() {
        try {
            List<VIPInfo> vipInfoList = vipInfoMapper.selectALLVIPInfo();
            List<VIPInfoVO> vipInfoVOList = new ArrayList<VIPInfoVO>();
            for (VIPInfo v:vipInfoList
            ) {
                vipInfoVOList.add(v.getVO());
            }
            return ResponseVO.buildSuccess(vipInfoVOList);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
    
    
    @Override
    //增加会员卡种类
    public ResponseVO addVIPInfo(VIPInfoVO vipInfoVO) {
        try {
            VIPInfo v= new VIPInfo(vipInfoVO);
            vipInfoMapper.insertVIPInfo(v);
            return ResponseVO.buildSuccess();
        }catch (Exception e){
    	    e.printStackTrace();
    	    return  ResponseVO.buildFailure("失败");
        }
    }
    
    @Override
    public ResponseVO addVIPCard(int userId,int vipInfoId) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setBalance(0);
        vipCard.setVipInfoId(vipInfoId);
        try {
            int id = vipCardMapper.insertOneCard(vipCard);
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardById(int id) {
        try {
            return ResponseVO.buildSuccess(vipCardMapper.selectCardById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getVIPInfoByName(String name) {
        try {
            VIPInfoVO v = vipInfoMapper.selectVIPInfoByName(name).getVO();
            return ResponseVO.buildSuccess(v);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseVO.buildFailure("失败");
        }

    }

    @Override
    public ResponseVO getVIPInfoById(int id) {
        try {
            VIPInfoVO v = vipInfoMapper.selectVIPInfoById(id).getVO();
            return ResponseVO.buildSuccess(v);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseVO.buildFailure("失败");
        }
    }
    
    @Override
    public ResponseVO charge(VIPCardForm vipCardForm) {

        VIPCard vipCard = vipCardMapper.selectCardById(vipCardForm.getVipId());
        if (vipCard == null) {
            return ResponseVO.buildFailure("会员卡不存在");
        }
        VIPInfo vipInfo = vipInfoMapper.selectVIPInfoById(vipCard.getVipInfoId());
        double balance = vipCard.calculate(vipCardForm.getAmount(),vipInfo.getMinimumCharge(),vipInfo.getExtraCharge());
        System.out.println(balance);
        System.out.println(vipCard.getBalance());
        double amount = vipCard.getBalance() + balance;
        vipCard.setBalance(amount);
        vipCard.getBalance();
        try {
            System.out.println("id"+vipCardForm.getVipId());
            vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
            System.out.println(vipCardMapper.selectCardById(vipCardForm.getVipId()).getBalance());
            RechargeVO vo=new RechargeVO(vipCard.getUserId(),new Date(),balance);
            rechargeService.recordRecharge(vo);
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
            System.out.println("开始前");
            VIPCard vipCard = vipCardMapper.selectCardByUserId(userId);
            if(vipCard==null){
                return ResponseVO.buildFailure("用户卡不存在");
            }
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

}
