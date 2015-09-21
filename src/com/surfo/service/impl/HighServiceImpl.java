package com.surfo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.surfo.service.HighService;

@Service("highService")
public class HighServiceImpl implements HighService{
	public List getX_Axis(){
		List list = new ArrayList();
		for(int i=0;i<24;i++){
			list.add(i);
		}
		return list;
	}
	public List<Map<String,Object>> getY_Axis(){
		List list = new ArrayList();
		for(int i=0;i<3;i++){
			Map map = new HashMap();
			map.put("id", i+1);
			map.put("name", "name"+i);
			map.put("data", getData("name"+i));
			list.add(map);
		}
		return list;
	}
	protected List getData(String name){
		List list = new ArrayList();
		for(int i=0;i<24;i++){
			list.add(Math.round(Math.random() * 10));
		}
		return list;
	}
}
