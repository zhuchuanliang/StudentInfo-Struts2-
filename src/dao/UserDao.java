package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDao {
	//µÇÂ½ÑéÖ¤
		public User login(Connection con,User user) throws SQLException{
			User resultUser=null;
			String sql="select * from t_user where userName=? and password=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()){
				resultUser=new User();
				resultUser.setUserName(rs.getString("userName"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setUserId(rs.getInt("userId"));
			}
			return resultUser;
			
		}
		
		
		
	public int modifyPassword(Connection con,User user)throws Exception{
		String sql="update t_user set password=? where userId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getPassword());
		pstmt.setInt(2, user.getUserId());
		return pstmt.executeUpdate();
	}
	

}
