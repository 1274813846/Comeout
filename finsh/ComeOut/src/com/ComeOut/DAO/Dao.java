package com.ComeOut.DAO;

import java.util.Currency;
import java.util.List;

public interface Dao <T>{
	/**
	 * 数据库操作的接口
	 * @param t
	 * @return
	 */
	public int add(T t);
	public int delete(T...t);
	public int update(T t);
	public List<T> query(T cu);
}
