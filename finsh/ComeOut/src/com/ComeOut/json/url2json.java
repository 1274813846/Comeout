package com.ComeOut.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * java根据url获取json对象
 * @author lk
 *
 */
public class url2json {

	private static String readAll(Reader rd) throws IOException{
		StringBuilder sb=new StringBuilder();
		int cp;
		while((cp=rd.read())!=-1){
			sb.append((char)cp);
		}
		return sb.toString();
		
	}
	public static JSONObject readJsonFromUrl(String url) throws MalformedURLException, IOException, JSONException{
		InputStream is=new URL(url).openStream();
		
		BufferedReader  rd=new BufferedReader (new InputStreamReader(is));  //Charset().forName("UTF-8")
		String jsonText = readAll(rd);
		JSONObject json=new JSONObject(jsonText);
		is.close();
		return json;
		
	}
	
	public static JSONObject code2json(String code){
		String url="https://api.weixin.qq.com/sns/jscode2session"
	          +"?appid=wxea91df7d6e137e92"
	          +"&secret=e1098542ac9dbeadc7e7499071b28604"
	          + "&js_code=" + code
	          +"&grant_type=authorization_code";
		JSONObject json=null;
		
		try {
			json=readJsonFromUrl(url);
			System.out.println(json.toString());
//			content= json.get("openid").toString();
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;	
	}
	public static void main(String[] args) throws MalformedURLException, IOException, JSONException {
		String code="0036agBx08AvIj1RvMCx0QHUAx06agB2";
		String url="https://api.weixin.qq.com/sns/jscode2session"
		          +"?appid=wxea91df7d6e137e92"
		          +"&secret=e1098542ac9dbeadc7e7499071b28604"
		          + "&js_code=" + code
		          +"&grant_type=authorization_code";
		JSONObject json=readJsonFromUrl(url);
		System.out.println(json.toString());
		System.out.println();
	}
	
}
