package com.yc.voto.biz;

import java.util.List;
import java.util.Map;


public interface ITopicItemBiz {
	public int add(List<Map<String,Object>>list);
	
	public int update(String[] inos);
}
