package com.whackode.itrip.service;

import com.whackode.itrip.pojo.entity.Hotel;
import com.whackode.itrip.pojo.vo.HotelVO;
import com.whackode.itrip.pojo.vo.SearchHotCityVO;

import java.util.List;

public interface HotelService {

	public List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO queryVO) throws Exception ;


	public Hotel getHotelById(Long hotelId) throws Exception ;

}
