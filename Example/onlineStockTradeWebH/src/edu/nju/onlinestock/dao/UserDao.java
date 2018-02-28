package edu.nju.onlinestock.dao;

import edu.nju.onlinestock.model.User;

public interface UserDao extends BaseDao
{
	/*
	 * �����ݿ��в���һ��user��¼
	 */
	public void save(User user);
	
	public void delete(User user);
	/*
	 * ���ݲ���������������ϵ�ֵ����user����,����ҵ��򷵻��������,���򷵻�null
	 * column ����
	 * value ��ֵ
	 */
	public User find(String column,String value);
	
	
	/*
	 * ����id����user���һ����¼
	 */
	public void updateByUserid(User user);
	
	/*
	 * ����id����user���һ����¼
	 */
	public User FindUserById(String id);
	
}
