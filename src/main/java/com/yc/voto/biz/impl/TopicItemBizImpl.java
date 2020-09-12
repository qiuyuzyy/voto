package com.yc.voto.biz.impl;

import java.util.List;
import java.util.Map;

import com.yc.voto.biz.ITopicItemBiz;
import com.yc.voto.dao.IBaseDao;
import com.yc.voto.dao.impl.BaseDaoImpl;

public class TopicItemBizImpl implements ITopicItemBiz{

	@Override
	public int add(List<Map<String, Object>> list) {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.insert("Topics.add", list);
	}

	@Override
	public int update(String[] inos) {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.insert("Topics.update", inos);
	}

}
