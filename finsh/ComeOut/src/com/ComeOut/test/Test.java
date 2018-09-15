package com.ComeOut.test;

import java.util.List;


import com.ComeOut.DAO.Dao;
import com.ComeOut.entity.User;
import com.ComeOut.imple.DaoImple;
import com.ComeOut.json.myJson;
import com.ComeOut.json.myJsonStev;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User(null,"test223",null,"test4");
		User user2=new User();
		List<User> user3;
		Dao<User> dp = new DaoImple<>();
//		dp.add(user2);
//		dp.update(user2);
//		dp.delete(user2);
//		dp.delete(user2,user);
		user3=dp.query(user);
//		for(User u:user3){
//			System.out.println(u.getNickName());
//		}
		myJson<User> mjs=new myJsonStev<>() ;
		String str=mjs.Class2Json(user3).toString();
		System.out.println(str);
	}

}
