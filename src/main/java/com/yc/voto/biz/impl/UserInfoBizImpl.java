package com.yc.voto.biz.impl;

import com.yc.voto.bean.UserInfo;
import com.yc.voto.biz.IUserInfoBiz;
import com.yc.voto.dao.IBaseDao;
import com.yc.voto.dao.impl.BaseDaoImpl;
import com.yc.voto.util.StringUtil;

public class UserInfoBizImpl implements IUserInfoBiz {

	@Override
	public int add(UserInfo uf) {
		if(StringUtil.checkNull(uf.getUname(),uf.getPwd())) {
			return -1;
		}
		IBaseDao baseDao = new BaseDaoImpl();
		
		return baseDao.insert("UserInfo.add", uf);
	}

	@Override
	public UserInfo login(UserInfo uf) {
		if(StringUtil.checkNull(uf.getUname(),uf.getPwd())) {
			return null;
		}
		IBaseDao baseDao = new BaseDaoImpl();
		
		return baseDao.find("UserInfo.login", uf);
	}

}
