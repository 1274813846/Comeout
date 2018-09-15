package com.ComeOut.json;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import com.ComeOut.entity.Job;
import com.ComeOut.entity.Task;
import com.ComeOut.entity.User;

/**
 * 将json和Class类之间的转换接口
 * @author lk
 *
 */
public interface myJson <T>{

	/**
	 *将json数据转换为Class类 
	 */
	public User Json2User(JSONObject json);
	public Job Json2Job(JSONObject json);
	public Task Json2Target(JSONObject json);
	
	public JSONObject JsonA2O(String str);
	
	/**
	 * 将Class类包装成json类型
	 */
	public  JSONArray Class2Json(List<T> t);
}
