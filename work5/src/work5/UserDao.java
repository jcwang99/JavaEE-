package work5;


import java.util.List;

public interface UserDao {
	public int addUser(User user);
	
	public int deleteUserById(String id);
	
	public int updateUser(User user);
	
	public User queryUserById(String id);
	
	public List<User>queryAll(String id,String name,String tel,String QQ,String email);
	
	public List<User> queryUsers(String id,String name,String tel,String QQ,String email,int startIndex,int userPerpage);
	
	public List<User> queryAllUsers();
	
	public User logUser(String id,String password);
	
	public int uploadImg(String id,Object input);
	
	public int addtempUser(User user);
	
	public List<User> queryTempUsers();
	
	public List<User> querysomeTempUsers(int startIndex,int userPerpage);
	
	public int deletetemp();
}
