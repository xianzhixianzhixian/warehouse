package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Goods;
import model.Goods_Maintainer;
import util.StringUtil;


public class GoodsDao {
	public int GoodsAdd(Connection con,Goods g) throws Exception{
		String sql="INSERT INTO `goods` (`GoodsId`, `GoodsNum`, `GoodsName`,`GoodsType`, `BuyDate`, `StartUseDate`, "
				+ "`EndUseDate`, `Useyear`) "
				+ "VALUES (null, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, g.getGoods_Num());
		pstmt.setString(2, g.getGoods_Name());
		pstmt.setString(3, g.getGoods_Type());
		pstmt.setString(4, g.getGoods_BuyDate());
		pstmt.setString(5, g.getGoods_StartUseDate());
		pstmt.setString(6, g.getGoods_EndUseDate());
		pstmt.setInt(7, g.getGoods_UseYear());
		return pstmt.executeUpdate();
	}
	public ResultSet goodsList(Connection con,Goods g) throws SQLException{
		StringBuffer sb=new StringBuffer("select * from goods g ");
				//+ "  where gm.GoodId=g.GoodsId "
				//+ "  and gm.MaintainerId=m.MaintainerId ");
		if(g.getGoods_Id()!=0){
			sb.append(" and g.GoodsId like '%"+g.getGoods_Id()+"%'");
		}
		if(StringUtil.isNotEmpty(g.getGoods_Num())){
			sb.append(" and g.GoodsNum like '%"+g.getGoods_Num()+"%'");
		}
		if(StringUtil.isNotEmpty(g.getGoods_Type())){
			sb.append(" and g.GoodsType like '%"+g.getGoods_Type()+"%'");
		}
		if(StringUtil.isNotEmpty(g.getGoods_BuyDate())){
			sb.append(" and g.BuyDate like '%"+g.getGoods_BuyDate()+"%'");
		}
		if(StringUtil.isNotEmpty(g.getGoods_StartUseDate())){
			sb.append(" and g.StartUseDate like '%"+g.getGoods_StartUseDate()+"%'");
		}
		if(StringUtil.isNotEmpty(g.getGoods_EndUseDate())){
			sb.append(" and g.EndUseDate like '%"+g.getGoods_EndUseDate()+"%'");
		}
		if(g.getGoods_UseYear()!=0){
			sb.append(" and g.Useyear like '%"+g.getGoods_UseYear()+"%'");
		}
		
		PreparedStatement pstmt=con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	public int  Goods_Modify(Connection con,Goods g) throws SQLException{
		String sql="UPDATE `goods` "
				+ "  SET GoodsNum=?,"
				+ "  GoodsName=?,"
				+ "  GoodsType=?,"
				+ "  BuyDate=?,"
				+ "  StartUseDate=?,"
				+ "  EndUseDate=?,"
				+ "  Useyear=?"
			    + "  WHERE GoodsId=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1, g.getGoods_Num());
		pstmt.setString(2, g.getGoods_Name());
		pstmt.setString(3, g.getGoods_Type());
		pstmt.setString(4, g.getGoods_BuyDate());
		pstmt.setString(5, g.getGoods_StartUseDate());
		pstmt.setString(6, g.getGoods_EndUseDate());
		pstmt.setInt(7, g.getGoods_UseYear());
		pstmt.setInt(8, g.getGoods_Id());
		return pstmt.executeUpdate();
	}
	
	public int  Goods_delete(Connection con,int gid) throws SQLException{
		String sql="delete from goods"
				+ "  WHERE GoodsId=? ";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, gid);
		return pstmt.executeUpdate();
	}
	
}
