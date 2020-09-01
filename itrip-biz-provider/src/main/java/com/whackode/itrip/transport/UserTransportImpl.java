package com.whackode.itrip.transport;

import com.whackode.itrip.pojo.entity.User;
import com.whackode.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 和commons业务传输接口实现
 */
@RestController("userTransport")
@RequestMapping("/user/trans")
public class UserTransportImpl implements UserTransport{


	@Autowired
	private UserService service;
	/**
	 * 通过对象返回一个USER列表
	 * @param query USER对象
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<User> getListByQuery(@RequestBody User query) throws Exception {
		return service.getListByQuery(query);
	}

	/**
	 * 保存USER对象，注册新增业务
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean saveUser(@RequestBody User user) throws Exception {
		return service.saveUser(user);
	}

	/**
	 * 通过Code判断USER的Active值
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	@Override
	public String getActiveCodeByUserCode(@RequestParam String userCode) throws Exception {
		return service.getActiveCodeByUserCode(userCode);
	}

	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean updateUser(@RequestBody User user) throws Exception {
		return service.updateUser(user);
	}
}
