package com.whackode.itrip.service;

import com.whackode.itrip.pojo.entity.UserLinkUser;

import java.util.List;

public interface UserLinkUserService {
	/**
	 * <b>根据查询信息查询联系人列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<UserLinkUser> getUserLinkUserListByQuery(UserLinkUser query) throws Exception;
}
