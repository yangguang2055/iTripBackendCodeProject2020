package com.whackode.itrip.controller;

import com.whackode.itrip.base.controller.BaseController;
import com.whackode.itrip.base.enums.UserActivatedEnum;
import com.whackode.itrip.base.enums.UserTypeEnum;
import com.whackode.itrip.base.pojo.vo.ResponseDto;
import com.whackode.itrip.pojo.entity.Hotel;
import com.whackode.itrip.pojo.entity.User;
import com.whackode.itrip.pojo.vo.HotelVO;
import com.whackode.itrip.pojo.vo.SearchHotCityVO;
import com.whackode.itrip.pojo.vo.UserVO;
import com.whackode.itrip.transport.HotelTransport;
import com.whackode.itrip.transport.UserTransport;
import com.whackode.itrip.util.JWTUtil;
import com.whackode.itrip.util.MD5Util;
import com.whackode.itrip.util.RegValidationUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("authController")
@RequestMapping("/search/api")
public class AuthController extends BaseController {

	@Autowired
	private HotelTransport hotelTransport;



	/**
	 * <b>根据热门城市查询酒店</b>
	 * @param queryVO
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/hotellist/searchByHotCity")
	public ResponseDto<Object> searchItripHotelListByHotCity(@RequestBody SearchHotCityVO queryVO)
			throws Exception {
		System.out.println("kaishi");
		List<HotelVO> hotelList = hotelTransport.searchItripHotelListByHotCity(queryVO);
		System.out.println("控制层"+hotelList.size());
		//System.out.println(ResponseDto.success(hotelList).getData()!=null);;
		//BeanUtils.copyProperties(ResponseDto<Object>, user);
		System.out.println("---");
		return  ResponseDto.success(hotelList);
		//return "okk";
	}


	@PostMapping(value = "/hotellist/test")
	public Hotel test(@RequestParam long id)
			throws Exception {
		Hotel hotel = hotelTransport.getHotelById(id);
		return hotel;
	}


}
