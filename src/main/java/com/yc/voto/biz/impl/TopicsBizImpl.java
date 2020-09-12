package com.yc.voto.biz.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.yc.voto.bean.Topics;
import com.yc.voto.biz.ITopicsBiz;
import com.yc.voto.dao.IBaseDao;
import com.yc.voto.dao.impl.BaseDaoImpl;
import com.yc.voto.util.StringUtil;

public class TopicsBizImpl implements ITopicsBiz{

	@Override
	public int add(Topics topics) {
		if(StringUtil.checkNull(topics.getSdate(),topics.getEdate(),topics.getTname())) {
			return -1;
		}
		IBaseDao baseDao = new BaseDaoImpl();
		String tid = UUID.randomUUID().toString().replace("-", "");
		topics.setTid(tid);
		
		String[] inames = topics.getUsids().split(",");
		topics.setUsids("");
		
		List<String> sqlIds = Arrays.asList("Topics.add","TopicItem.add");
		List<Object> params =new ArrayList<Object>();
		params.add(topics);
		
		List<Object>param = new ArrayList<Object>();
		Map<String,Object> map =null;
		for(String iname : inames) {
			map = new HashMap<String, Object>();
			map.put("tid", tid);
			map.put("iname",iname);
			param.add(map);
		}
		
		params.add(param);
		return baseDao.update(sqlIds, params);
		
	}

	@Override
	public List<Topics> findAll() {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.finds("Topics.findAll");
	}

	@Override
	public Topics findByTid(String tid) {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.find("Topics.findByTid", tid);
	}

	@Override
	public int update(Map<String, Object> map) {
		IBaseDao baseDao = new BaseDaoImpl();
		return baseDao.update("Topics.update", map);
	}

	@Override
	public int update(String tid, int usid, String inos) {
		if(StringUtil.checkNull(tid,inos)) {
			return -1;
		}
		IBaseDao baseDao = new BaseDaoImpl();
		List<String> sqlIds = Arrays.asList("Topics.update","TopicItem.update");
		List<Object> params = new ArrayList<Object>();
		
		Map<String,Object>map = new HashMap<String, Object>();
		map.put("usid", usid);
		map.put("tid", tid);
		
		params.add(map);
		params.add(inos.split(","));
		return baseDao.update(sqlIds, params);
	}


}
