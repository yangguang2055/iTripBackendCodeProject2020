package com.whackode.itrip.transport;

import com.whackode.itrip.pojo.entity.UserLinkUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/linkuser/trans")
public interface UserLinkUserTransport {
	/**
	 * <b>根据查询信息获得查询列表</b>
	 * @param query
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/list")
	List<UserLinkUser> queryUserLinkUserListByQuery(@RequestBody UserLinkUser query) throws Exception;
}
