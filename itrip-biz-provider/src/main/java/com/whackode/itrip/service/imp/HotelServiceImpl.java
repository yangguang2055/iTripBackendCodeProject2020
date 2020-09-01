package com.whackode.itrip.service.imp;

import com.whackode.itrip.pojo.entity.Hotel;
import com.whackode.itrip.pojo.vo.HotelVO;
import com.whackode.itrip.pojo.vo.SearchHotCityVO;
import com.whackode.itrip.service.HotelService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {
	@Autowired
	private SolrClient solrClient;

	//private HttpSolrClient httpSolrClient;



	//private HttpSolrClient httpSolrClient;




	@Override
	public List<HotelVO> searchItripHotelListByHotCity(SearchHotCityVO queryVO) throws Exception {
		// 对于Spring Boot注入的SolrClient就是HttpSolrClient对象，进行强制类型转换
		HttpSolrClient httpSolrClient = (HttpSolrClient) solrClient;
		System.out.println("HotelServiceImpl:"+httpSolrClient.getBaseURL()+"---cityId:" + queryVO.getCityId()+"***"+queryVO.getCount());
		httpSolrClient.setParser(new XMLResponseParser()); // 设置响应解析器
		// 创建Solr的查询参数

		SolrQuery solrQuery = new SolrQuery();
		solrQuery.setQuery("cityId:" + queryVO.getCityId());
		solrQuery.setRows(queryVO.getCount());

		// 使用SolrClient进行查询，QueryResponse
		QueryResponse queryResponsey = solrClient.query(solrQuery);
		List<HotelVO> hotelList =queryResponsey.getBeans(HotelVO.class);
			System.out.println("HotelServiceImpl:"+hotelList.size());
		for (HotelVO ARR:hotelList) {
			System.out.println(ARR.getHotelName());

		}


		// 通过使用QueryResponse提取结果
		return queryResponsey.getBeans(HotelVO.class);


	}

	@Override
	public Hotel getHotelById(Long hotelId) throws Exception {
		return null;
	}
}
