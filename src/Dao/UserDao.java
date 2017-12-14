package Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import model.User;
import util.StringUtil;

public class UserDao {
	
	public User login(Connection con,User user) throws SQLException{
		User resultUser=null;
		String sql="select * from users where UserName=? and UserPassword=? and UserType=?";
		PreparedStatement psmt=con.prepareStatement(sql);
		psmt.setString(1, user.getUserName());
		psmt.setString(2, user.getUserPassword());
		psmt.setString(3, user.getUserType());
		ResultSet rs=psmt.executeQuery();
		if(rs.next()){
			resultUser=new User();
			resultUser.setUserName(rs.getString("UserName"));
			resultUser.setUserPassword(rs.getString("UserPassword"));
			resultUser.setUserType(rs.getString("UserType"));
		}
		return resultUser;
	}
	
	public int register(Connection con,User user) throws Exception{
		String sql="insert into users values(null,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getUserPassword());
		pstmt.setString(3, user.getUserType());
		return pstmt.executeUpdate();
	}
	
	public ResultSet userList(Connection con,String username) throws SQLException{
		StringBuffer sb=new StringBuffer("select * from users");
		if(!StringUtil.isEmpty(username)){
			sb.append(" where UserName like '%"+username+"%'");
		}
		PreparedStatement psmt=con.prepareStatement(sb.toString());
		return psmt.executeQuery();
	}
	
	public int ModifyUser(Connection con,User user) throws Exception{
		String sql="update users set UserName=?,UserPassword=?,UserType=? where UserId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, user.getUserName());
		pstmt.setString(2, user.getUserPassword());
		pstmt.setString(3, user.getUserType());
		pstmt.setInt(4, user.getId());
		return pstmt.executeUpdate();
		
	}
	
	public int DeleteUser(Connection con,int uid) throws Exception{
		String sql="delete from users where UserId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, uid);
		return pstmt.executeUpdate();
		
	}

}
