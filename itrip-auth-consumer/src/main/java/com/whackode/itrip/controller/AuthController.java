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
@RequestMapping("/auth/api")
public class AuthController  extends BaseController {
	@Autowired
	private UserTransport userTransport;
	@Autowired
	private HotelTransport hotelTransport;


	@PostMapping(value = "/dologin")
	public ResponseDto<Object> loginUser(String name, String password) throws Exception{
			if (name!=null&&!name.trim().equals("")
					&&password!=null&&!password.trim().equals("")){
				// 通过登陆用户名查找相关信息，在比较密码是否相同
				User query = new User();
				query.setUserCode(name);
				//userTransport
				List<User> userList = userTransport.getListByQuery(query);
				// 进行结果判断
				if ( userList!=null&&userList.size()>0){
					User user=userList.get(0);
					System.out.println(user.getId()+"----------------------");
					// 比较密码是否相同
					/**
					// 登陆成功，按照相应的技术，生成一个Token令牌，以Cookie形式交给浏览器，
					// 每当浏览器在访问其他服务器的时候，都会携带该信息，如果需要校验该用户是否登陆，
					// 只需要校验该Token是否是按照系统规则生成的即可。
					// 在Java当中，Token技术使用了JWT（Java Web Token）来完成
					// 使用当前登陆用户的id生成Token信息
					 */
					if (user.getUserPassword().equals(MD5Util.encrypt(password))){
						if (user.getActivated() == UserActivatedEnum.USER_ACTIVATED_YES.getCode()) { //(1)

							System.out.println("-----------");
							System.out.println(user.getId());
							String token = JWTUtil.createToken(user.getId());
							// 将Token随着相应交给浏览器
							System.out.println(token);
							response.setHeader("token", token);
							//session加入token
							//session.setAttribute("token", token);
							//登录redis记录信息
							//redisUtils.putValue("token",token,30);
							return ResponseDto.success(token);

						}else {
							return ResponseDto.failure("该用户未激活");
						}
					}else {
						return ResponseDto.failure("登陆密码错误");
					}
				}else {
					return ResponseDto.failure("该用户未注册");
				}
			}else {
				return ResponseDto.failure("请填写登陆信息");
			}
	}

	//Email注册
	@PostMapping(value = "/doregister")
	public ResponseDto<Object> registryUser(UserVO userVO) throws Exception {
		String code = userVO.getUserCode();
		String password = userVO.getUserPassword();


		//System.out.println(code+","+password);
		// 校验用户所给定信息是否有效
		if (RegValidationUtil.validateEmail(code)&&password!=null&&!"".equals(password)){
			System.out.println(code+","+password+"contronller");
			User query = new User();
			query.setUserCode(code);
			List<User> userList = userTransport.getListByQuery(query);
			System.out.println(userList.size()+"----------");
			//确保数据库没有，邮件地址是唯一的
			if (userList==null||userList.size()<=0){
				User user = new User();// 将用户注册UserVO转换成User对象备用
				userVO.setUserPassword(MD5Util.encrypt(password));//加密
				BeanUtils.copyProperties(userVO, user);//UserVO转换成User
				// 当调用该方法的时候，用户属于自主注册
				user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
				// 将激活状态设置为未激活
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
				// 使用传输层，远程调用生产者进行用户信息注册工作
				if (userTransport.saveUser(user)){
					return ResponseDto.success();//注册OKK
				}
			}
		}
		return ResponseDto.failure("注册失败");
	}

	//手机注册
	@PostMapping(value = "/registerbyphone")
	public ResponseDto<Object> registryByCellphone(UserVO userVO) throws Exception {
		// 校验用户所给定信息是否有效
		if (RegValidationUtil.validateCellphone(userVO.getUserCode())&&!userVO.getUserPassword().equals("")
		&&userVO.getUserPassword()!=null){
			// 进行唯一性校验
			User query = new User();
			query.setUserCode(userVO.getUserCode());
			List<User> userList = userTransport.getListByQuery(query);
			System.out.println(userList.size());
			if (userList == null || userList.size() <= 0){
				System.out.println("userList == null || userList.size() <= 0满足了");
				//MD5
				userVO.setUserPassword(MD5Util.encrypt(userVO.getUserPassword()));
				//转换
				User user = new User();
				BeanUtils.copyProperties(userVO,user);
				// 当调用该方法的时候，用户属于自主注册
				user.setUserType(UserTypeEnum.USER_TYPE_REG.getCode());
				// 将激活状态设置为未激活
				user.setActivated(UserActivatedEnum.USER_ACTIVATED_NO.getCode());
				// 使用传输层，远程调用生产者进行用户信息注册工作
				boolean flag = userTransport.saveUser(user);
				if (flag){
					// 注册成功
					return ResponseDto.success();
				}
			}
			return ResponseDto.failure("该手机号码已经注册");
		}
		return ResponseDto.failure("注册失败");
	}



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
