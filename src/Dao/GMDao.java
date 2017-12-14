package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Goods_Maintainer;
import model.User;
import util.StringUtil;

public class GMDao {

	public int GM_Add(Connection con,Goods_Maintainer gm) throws Exception{
		String sql="insert into maintain_goods values(?,?,?,?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,gm.getMaintainer_id());
		pstmt.setInt(2,gm.getGoods_Id());
		pstmt.setString(3, gm.getGoods_StartMaintainDate());
		pstmt.setString(4, gm.getGoods_EndMaintainDate());
		return pstmt.executeUpdate();
	}
	public ResultSet gmList(Connection con,Goods_Maintainer gm) throws SQLException{
		StringBuffer sb=new StringBuffer("select * from maintain_goods gm, goods g,maintainers m "
				+ "  where gm.GoodId=g.GoodsId "
				+ "  and gm.MaintainerId=m.MaintainerId ");
		if(gm.getGoods_Id()!=0){
			sb.append(" and g.GoodsId like '%"+gm.getGoods_Id()+"%'");
		}
		if(gm.getMaintainer_id()!=0){
			sb.append(" and m.MaintainerId like '%"+gm.getMaintainer_id()+"%'");
		}
		if(StringUtil.isNotEmpty(gm.getGoods_EndMaintainDate())){
			sb.append(" and gm.EndMaintainDate < '"+gm.getGoods_EndMaintainDate()+"' ");
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString());

		return pstmt.executeQuery();
	}

	
	public int GM_modify(Connection con,String gname,String mname,String start,String end) throws SQLException{
		String sql="UPDATE maintain_goods"
				+ "  SET StartMaintainDate=?,"
				+ "  EndMaintainDate=?"
				+ "  WHERE GoodId IN"
				+ "  (SELECT GoodsId FROM goods WHERE GoodsName=?)"
				+ "  AND MaintainerId IN"
				+ "  (SELECT MaintainerId FROM maintainers WHERE MaintainerName=?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, start);
		pstmt.setString(2, end);
		pstmt.setString(3, gname);
		pstmt.setString(4, mname);
		return pstmt.executeUpdate();
	}
	public int GM_delete(Connection con,String gname,String mname) throws SQLException{
		String sql="delete from maintain_goods"
				+ "  WHERE GoodId IN"
				+ "  (SELECT GoodsId FROM goods WHERE GoodsName=?)"
				+ "  AND MaintainerId IN"
				+ "  (SELECT MaintainerId FROM maintainers WHERE MaintainerName=?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, gname);
		pstmt.setString(2, mname);
		return pstmt.executeUpdate();
	}
	


}
