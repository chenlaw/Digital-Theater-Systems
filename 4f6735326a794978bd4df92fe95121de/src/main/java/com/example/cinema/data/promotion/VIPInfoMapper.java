package com.example.cinema.data.promotion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.cinema.po.Coupon;
import com.example.cinema.po.VIPInfo;
@Mapper
public interface VIPInfoMapper {
	int insertVIPInfo(VIPInfo vipInfo);

	List<VIPInfo> selectALLVIPInfo();

	VIPInfo selectVIPInfoByName(String name);

	VIPInfo selectVIPInfoById(int id);

	void updateVIPInfo(VIPInfo vipInfo);

}
