package com.whackode.itrip.transport;

import com.whackode.itrip.pojo.entity.HotelOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotelorder/trans")
public interface HotelOrderTransport {
	//coms
	@PostMapping(value = "/list")
	List<HotelOrder> getHotelListByQuery(@RequestBody HotelOrder query) throws Exception;

	@PostMapping(value = "/save")
	boolean save(@RequestBody HotelOrder hotelOrder) throws Exception;

	@PostMapping(value = "/update")
	boolean update(@RequestBody HotelOrder hotelOrder) throws Exception;

	@PostMapping(value = "/id")
	HotelOrder getHotelOrderById(@RequestParam Long orderId) throws Exception;

	@PostMapping(value = "/no")
	HotelOrder getHotelOrderByNo(@RequestParam String orderNo) throws Exception;
}
