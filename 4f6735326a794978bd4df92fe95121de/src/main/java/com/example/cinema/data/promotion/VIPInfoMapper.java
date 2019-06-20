package com.example.cinema.data.promotion;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.cinema.po.Coupon;
import com.example.cinema.po.VIPInfo;
@Mapper
public interface VIPInfoMapper {
	/**
	 * @author wph
	 * @param vipInfo
	 * @return
	 */
	int insertVIPInfo(VIPInfo vipInfo);

	/**
	 * @author wph
	 * @return
	 */
	List<VIPInfo> selectALLVIPInfo();

	/**
	 * @author wph
	 * @param name
	 * @return
	 */
	VIPInfo selectVIPInfoByName(String name);

	/**
	 * @author wph
	 * @param id
	 * @return
	 */
	VIPInfo selectVIPInfoById(int id);

	/**
	 * @author wph
	 * @param vipInfo
	 */
	void updateVIPInfo(VIPInfo vipInfo);

}
