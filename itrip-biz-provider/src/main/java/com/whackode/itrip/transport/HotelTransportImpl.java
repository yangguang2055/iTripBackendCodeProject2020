package com.whackode.itrip.transport;

import com.whackode.itrip.pojo.entity.Hotel;
import com.whackode.itrip.pojo.vo.HotelVO;
import com.whackode.itrip.pojo.vo.SearchHotCityVO;
import com.whackode.itrip.service.HotelService;
import com.whackode.itrip.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("hotelTransport")
@RequestMapping("/hotel/trans")
public class HotelTransportImpl implements HotelTransport {

	@Autowired
	private HotelService hotelService;


	@Override
	@PostMapping(value = "/searchItripHotelListByHotCity")
	public List<HotelVO> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO queryVO) throws Exception {
		System.out.println("HotelTransportImpl");
		//System.out.println("HotelTransportImpl-HotelList:"+hotelService.searchItripHotelListByHotCity(queryVO).size());
		return hotelService.searchItripHotelListByHotCity(queryVO);

	}

	@Override
	@PostMapping(value = "/id")
	public Hotel getHotelById(Long hotelId) throws Exception {
		Hotel hotel=new Hotel();
		hotel.setAddress(hotelId.toString());
		hotel.setHotelName("测试");
		return hotel;
	}
}
