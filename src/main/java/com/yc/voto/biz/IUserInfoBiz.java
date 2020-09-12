package com.yc.voto.biz;

import com.yc.voto.bean.UserInfo;

public interface IUserInfoBiz {
	public int add(UserInfo uf);
	
	public UserInfo login(UserInfo uf);
}
