package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Maintainer;
import model.User;
import util.StringUtil;

public class MaintainerDao {
	
	public ResultSet maintainerList(Connection con,String GoodsName) throws SQLException{
		
		StringBuffer sb=new StringBuffer(""
				+ "select * "
				+ " from maintainers m,maintain_goods mg,goods g"
				+ " where m.MaintainerId=mg.MaintainerId"
				+ " and mg.GoodId=g.GoodsId");
		if(!StringUtil.isEmpty(GoodsName)){
			sb.append(" and g.GoodsName like '%"+GoodsName+"%'");
		}
		PreparedStatement psmt=con.prepareStatement(sb.toString());
		return psmt.executeQuery();
	}
	
public ResultSet maintainerList(Connection con) throws SQLException{
		
		StringBuffer sb=new StringBuffer(""
				+ "select * "
				+ " from maintainers m"
				+ " where m.MaintainerId not in("
				+ " select MaintainerId from maintain_goods "
				+ ")");
		
		PreparedStatement psmt=con.prepareStatement(sb.toString());
		return psmt.executeQuery();
	}
	
	
	public int ModifyMaintainer(Connection con,Maintainer m) throws Exception{
		String sql="update maintainers "
				+ " set MaintainerName=?,MaintainerJob=?,MaintainerCompany=?,MaintainerPhone=? "
				+ " where MaintainerId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, m.getMaintainer_name());
		pstmt.setString(2, m.getMaintainer_job());
		pstmt.setString(3, m.getMaintainer_company());
		pstmt.setString(4, m.getMaintainer_phone());
		pstmt.setInt(5, m.getMaintainer_id());
		return pstmt.executeUpdate();
		
	}
	
	public int DeleteMaintainer(Connection con,int id) throws Exception{
		String sql="delete from maintainers where MaintainerId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, id);
		return pstmt.executeUpdate();
		
	}
	public int AddMaintainer(Connection con,Maintainer m) throws Exception{
		String sql=" INSERT INTO  maintainers (MaintainerName, MaintainerJob, MaintainerCompany,MaintainerPhone) "
				+ "  VALUES ( ?, ?, ?, ?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,m.getMaintainer_name());
		pstmt.setString(2, m.getMaintainer_job());
		pstmt.setString(3, m.getMaintainer_company());
		pstmt.setString(4, m.getMaintainer_phone());
		
		return pstmt.executeUpdate();
		
	}

}
