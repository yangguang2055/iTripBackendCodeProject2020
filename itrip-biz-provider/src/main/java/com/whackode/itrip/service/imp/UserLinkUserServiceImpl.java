package com.whackode.itrip.service.imp;

import com.whackode.itrip.dao.UserLinkUserDao;
import com.whackode.itrip.pojo.entity.UserLinkUser;
import com.whackode.itrip.service.UserLinkUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userLinkUserService")
@Transactional
public class UserLinkUserServiceImpl implements UserLinkUserService {

	@Autowired
	private UserLinkUserDao linkUserDao;


	@Override
	public List<UserLinkUser> getUserLinkUserListByQuery(UserLinkUser query) throws Exception {
		return linkUserDao.findUserLinkUserListByQuery(query);
	}
}
