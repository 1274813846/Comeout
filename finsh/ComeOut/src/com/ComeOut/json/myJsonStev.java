package com.ComeOut.json;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.ComeOut.entity.Job;
import com.ComeOut.entity.Task;
import com.ComeOut.entity.User;

public class myJsonStev<T> implements myJson<T> {
	
	@Override
	public JSONObject JsonA2O(String str) {
		// TODO Auto-generated method stub
		try {
			JSONArray json = new JSONArray(str); // 首先把字符串转成 JSONArray  对象
			String key;
			Object value;
			JSONObject jo = new JSONObject();
			if(json.length()>0){
			  for(int i=0;i<json.length();i++){
			    JSONObject jsonObject = json.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			    Iterator iterator = jsonObject.keys();
			    while(iterator.hasNext()){
			            key = (String) iterator.next();
			            value = jsonObject.get(key);
			            jo.put(key, value);
			    }
			  }
			}
			return jo;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User Json2User(JSONObject json) {
		String openid=null;
		String nickName=null;
		String avatarUrl=null;
		String major=null;
		
		try {
			openid=json.getString("openid");
			nickName=json.getString("nickName");
			avatarUrl=json.getString("avatarUrl");
			major=json.getString("major");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User user=new User(openid, nickName, avatarUrl, major) ;
		return user;
	}
	
	@Override
	public Job Json2Job(JSONObject json) {
		// TODO Auto-generated method stub
		int id= 0;
		String location= null;
		double salary= 0;
		String position= null;
		String tel= null;
		String company = null;
		double lng= 0;
		double lat= 0;
		String time= null;
		String detail= null;
		String imageUrl= null;
		
		try {
			id=json.getInt("id");
			location=json.getString("location");
			salary=json.getDouble("salary");
			position=json.getString("position");
			tel=json.getString("tel");
			company=json.getString("company");
			lng=json.getDouble("lng");
			lat=json.getDouble("lat");
			time=json.getString("time");
			detail=json.getString("detail");
			imageUrl=json.getString("imageUrl");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Job job=new Job(id,  location,  salary,  position,  tel,  company,  lng,
				 lat,  time,  detail,  imageUrl);
		return job;
	}

	@Override
	public Task Json2Target(JSONObject json) {
		// TODO Auto-generated method stub
		String openid=null;
		String name=null;
		String startTime=null;
		String endTime=null;
		
		try {
			openid=json.getString("openid");
			name=json.getString("name");
			startTime=json.getString("startTime");
			endTime=json.getString("endTime");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Task target=new Task(openid,name,startTime,endTime);
		return target;
	}

	@Override
	public  JSONArray Class2Json(List<T> list) {
		// TODO Auto-generated method stub
		JSONObject jo=null;
		JSONArray ja=new JSONArray();
		if(!list.isEmpty()){
			for(T t : list ){
				jo=new JSONObject(t);
//				JSONObject.fromObject(t);
				ja.put(jo);
			}
			return ja;
		}
		
		return null;
	}
}
