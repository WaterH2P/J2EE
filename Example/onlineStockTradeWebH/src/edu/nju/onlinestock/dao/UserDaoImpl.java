package edu.nju.onlinestock.dao;




import edu.nju.onlinestock.model.User;


public class UserDaoImpl extends BaseDaoImpl implements UserDao
{
	private static UserDaoImpl userDao = new UserDaoImpl();

	private UserDaoImpl() {

	}

	public static UserDaoImpl getInstance() {
		return userDao;
	}

	
	//protected Logger log = Logger.getLogger(this.getClass());

   
	/*
	 * �����ݿ��в���һ��user��¼
	 */
	public void save(User user)
	{
		super.save(user);
	}
	
	
	/*
	 * ���ݲ���������������ϵ�ֵ����user����,����ҵ��򷵻��������,���򷵻�null
	 * column ����
	 * value ��ֵ
	 */
	public User find(String column,String value)
	{
	//
		return null;
	}
	
	/*
	 * ����id����user���һ����¼
	 */
	public void updateByUserid(User user)
	{
	//
		
	}
	/*
	 * ����id����user���һ����¼
	 */
	public User FindUserById(String id){
		User user= (User) super.load(User.class, id);
		return user;

	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		super.delete(user);
	}

}
