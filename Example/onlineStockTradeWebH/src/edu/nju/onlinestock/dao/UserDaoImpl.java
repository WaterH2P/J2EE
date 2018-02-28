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
	 * 像数据库中插入一条user记录
	 */
	public void save(User user)
	{
		super.save(user);
	}
	
	
	/*
	 * 根据参数列名和这个列上的值查找user对象,如果找到则返回这个对象,否则返回null
	 * column 列名
	 * value 列值
	 */
	public User find(String column,String value)
	{
	//
		return null;
	}
	
	/*
	 * 根据id更新user表的一条记录
	 */
	public void updateByUserid(User user)
	{
	//
		
	}
	/*
	 * 根据id查找user表的一条记录
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
