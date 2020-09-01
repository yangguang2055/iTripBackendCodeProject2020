package com.whackode.itrip.service.imp;

import com.whackode.itrip.dao.UserDao;
import com.whackode.itrip.pojo.entity.User;
import com.whackode.itrip.service.UserService;
import com.whackode.itrip.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.Socket;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
@Service("userService")
@Transactional //事务
public class UserServiceImpl implements UserService {


	@Autowired
	private UserDao userDao;
	/**
	 * 邮件util，云联短信，redis注入
	 */
	@Autowired
	private MailSenderUtil mailSenderUtil;
	@Autowired
	private SmsSenderUtil smsSenderUtil;
	@Autowired
	private sms sms;
	@Autowired
	private StringRedisTemplate redisTemplate;



	@Override
	public List<User> getListByQuery(User query) throws Exception {
		return userDao.findListByQuery(query);
	}

	@Override//注册业务
	public boolean saveUser(User user) throws Exception {
		//注册时间
		user.setCreationDate(new Date());
		//dao层持久化成功时
		if (userDao.saveUser(user)>0){
			//生成激活码存redis --ActiveCodeUtil
			String code = ActiveCodeUtil.createActiveCode();
			/**
			 * redisTemplate.opsForValue()返回ValueOperations<K, V>  valueOps
			 * valueOps是redisTemplate的属性再调用set方法
			 * 存入redis K:email V：activeCode
			 */
			String key= user.getUserCode();
			redisTemplate.opsForValue().set(key,code);
			//设置储存时间
			redisTemplate.expire(key,30, TimeUnit.MINUTES);
			// 判断此时用户注册使用的是手机号码还是邮箱地址
			if (RegValidationUtil.validateEmail(user.getUserCode())){
				// 通过发送邮件，将激活码发送给用户
			/*	String test = mailSenderUtil.test();
				System.out.println(test);*/
				return mailSenderUtil.sendActiveCodeMail(key,code);

			} else if (RegValidationUtil.validateCellphone(key)) {
				// 使用手机号码注册，将激活码发送到对方的手机中
				return sms.SMS(key,code);

			}
		}
		return false;
	}

	@Override
	public String getActiveCodeByUserCode(String userCode) throws Exception {
		// 通过Redis查询对应的激活码
		String activeCode = redisTemplate.opsForValue().get(userCode);
		return activeCode;
	}
	@Override
	public boolean updateUser(User user) throws Exception {
		if (userDao.updateUser(user)>0) {
			return true;
		}
		return false;

	}
}
