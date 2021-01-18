package work5;


import java.util.List;

public class UserDaoimp extends BaseDao implements UserDao {

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO LIST (id, NAME, tel, qq, email) VALUES(?,?,?,?,?)";
		
		return update(sql, user.getId(),user.getName(),user.getTel(),user.getQQ(),user.getEmail());
	}

	@Override
	public int deleteUserById(String id) {
		// TODO Auto-generated method stub
		String sql="delete from list where id=?";
		
		return update(sql,id);

	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		String sql = "update list set `name`=?,`tel`=?,`qq`=?,`email`=? where id = ?";
		return update(sql, user.getName(),user.getTel(),user.getQQ(),user.getEmail(),user.getId());
	}

	@Override
	public User queryUserById(String id) {
		// TODO Auto-generated method stub
		 String sql = "select `id` , `name` , `tel` , `qq` , `email` from list where id = ?";
		 return queryForOne(User.class, sql, id);

	}

	@Override
	public List<User> queryUsers(String id, String name, String tel, String QQ, String email,int startIndex,int userPerpage) {
		// TODO Auto-generated method stub
		name=name+"%";
		email="%"+email+"%";
		String sql = "select `id` , `name` , `tel` , `qq` , `email` from list where id = ? or name like ? or tel=? or qq=? or email like ? limit ?,?";
		//System.out.println(name);
		return queryForList(User.class, sql, id,name,tel,QQ,email,startIndex,userPerpage);
	}

	@Override
	public List<User> queryAll(String id, String name, String tel, String QQ, String email) {
		// TODO Auto-generated method stub
		name=name+"%";
		email="%"+email+"%";
		String sql = "select `id` , `name` , `tel` , `qq` , `email` from list where id = ? or name like ? or tel=? or qq=? or email like ? ";
		//System.out.println(name);
		return queryForList(User.class, sql, id,name,tel,QQ,email);

	}

	@Override
	public User logUser(String id, String password) {
		// TODO Auto-generated method stub
		String sql = "select `id` , `password` from list where id = ?&&password=?";
		return queryForOne(User.class, sql, id,password);
	}

	@Override
	public int uploadImg(String id, Object input) {
		// TODO Auto-generated method stub
		String sql="update list set img=?  where id=?";
		return update(sql,input,id);
	}

	@Override
	public List<User> queryAllUsers() {
		// TODO Auto-generated method stub
		String sql = "select `id` , `name` , `tel` , `qq` , `email`,password,img from list where id !=?";
		return queryForList(User.class, sql,"-1");
	}

	@Override
	public int addtempUser(User user) {
		// TODO Auto-generated method stub
        String sql="INSERT INTO temp (id, NAME, tel, qq, email) VALUES(?,?,?,?,?)";	
		return update(sql, user.getId(),user.getName(),user.getTel(),user.getQQ(),user.getEmail());
	}

	@Override
	public List<User> queryTempUsers() {
		// TODO Auto-generated method stub
		String sql = "select `id` , `name` , `tel` , `qq` , `email` from temp where id !=?";
		return queryForList(User.class, sql,"-1");
	}

	@Override
	public List<User> querysomeTempUsers(int startIndex, int userPerpage) {
		// TODO Auto-generated method stub
		String sql = "select `id` , `name` , `tel` , `qq` , `email` from temp where id !=? limit ?,?";
		return queryForList(User.class, sql,"-1",startIndex,userPerpage);
		
		

	}

	@Override
	public int deletetemp() {
		// TODO Auto-generated method stub
        String sql="delete from temp where id!=?";
		return update(sql,"-1");
	}

}
