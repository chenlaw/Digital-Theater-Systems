package com.example.cinema.data.promotion;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.example.cinema.po.VIPInfo;
@Mapper
public interface VIPInfoMapper {
	void insertOneVIPInfo(VIPInfo vipInfo);

	VIPInfo selectVIPInfoById(@Param("id") int id);
	
	VIPInfo selectVIPInfoByDescription(@Param("description")String description);
	
	void updateVIPInfo(VIPInfo vipInfo);

}
