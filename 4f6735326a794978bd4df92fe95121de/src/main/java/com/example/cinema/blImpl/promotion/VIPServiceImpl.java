package com.example.cinema.blImpl.promotion;

import com.example.cinema.bl.promotion.VIPService;
import com.example.cinema.data.promotion.VIPCardMapper;
import com.example.cinema.data.promotion.VIPInfoMapper;
import com.example.cinema.vo.VIPCardForm;
import com.example.cinema.po.VIPCard;
import com.example.cinema.po.VIPInfo;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.VIPInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by liying on 2019/4/14.
 */
@Service
public class VIPServiceImpl implements VIPService {
    @Autowired
    VIPCardMapper vipCardMapper;
    VIPInfoMapper vipInfoMapper;

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
    public ResponseVO addVIPCard(int userId) {
        VIPCard vipCard = new VIPCard();
        vipCard.setUserId(userId);
        vipCard.setBalance(0);
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
    public ResponseVO getVIPInfoByUserId(int id) {
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
        double balance = vipCard.calculate(vipCardForm.getAmount());
        vipCard.setBalance(vipCard.getBalance() + balance);
        try {
            vipCardMapper.updateCardBalance(vipCardForm.getVipId(), vipCard.getBalance());
            return ResponseVO.buildSuccess(vipCard);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO getCardByUserId(int userId) {
        try {
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

    @Override
    public ResponseVO updateYIPInfo(VIPInfoVO vipinfovo) {
        //todo
        try {
            VIPInfo v = new VIPInfo(vipinfovo);
            vipInfoMapper.updateVIPInfo(v);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("失败");
        }
    }
}
