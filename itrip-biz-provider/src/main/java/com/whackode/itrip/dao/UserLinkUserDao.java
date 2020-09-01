package com.whackode.itrip.dao;


import com.whackode.itrip.pojo.entity.UserLinkUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户联系人持久化接口findUserLinkUserListByQuery(UserLinkUser query)
 */
@Repository
public interface UserLinkUserDao {
	/**
	 *根据查询信息查询用户信息列表
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<UserLinkUser> findUserLinkUserListByQuery(UserLinkUser query) throws Exception;
}
