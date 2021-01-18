package work5;



public class adminDaoimp extends BaseDao {
	public admin log(String username,String password) {
		String sql = "select `username` , `password` from admin where username = ?&&password=?";
		return queryForOne(admin.class, sql, username,password);
	}

}
