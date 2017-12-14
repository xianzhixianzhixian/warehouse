/**
 * 商品信息
 */

package model;

public class Goods {
	private int goods_Id; //设备序号
	private String goods_Num; //设备编号
	private String goods_Name; //设备名称
	private String goods_Type; //设备类型
	private String goods_BuyDate; //购买日期
	private String goods_StartUseDate; //启用日期
	private String goods_EndUseDate; //使用到期日期
	private int goods_UseYear; //允许使用年限
	

	public Goods() {
		super();
	}
	public Goods(String goods_Num, String goods_Name, String goods_BuyDate, String goods_StartUseDate,
			String goods_EndUseDate, int goods_UseYear, String goods_Type) {
		super();
		this.goods_Num = goods_Num;
		this.goods_Name = goods_Name;
		this.goods_BuyDate = goods_BuyDate;
		this.goods_StartUseDate = goods_StartUseDate;
		this.goods_EndUseDate = goods_EndUseDate;
		this.goods_UseYear = goods_UseYear;
		this.goods_Type = goods_Type;
	}
	
	public Goods(int goods_Id, String goods_Num, String goods_Name, String goods_Type, String goods_BuyDate,
			String goods_StartUseDate, String goods_EndUseDate, int goods_UseYear) {
		super();
		this.goods_Id = goods_Id;
		this.goods_Num = goods_Num;
		this.goods_Name = goods_Name;
		this.goods_Type = goods_Type;
		this.goods_BuyDate = goods_BuyDate;
		this.goods_StartUseDate = goods_StartUseDate;
		this.goods_EndUseDate = goods_EndUseDate;
		this.goods_UseYear = goods_UseYear;
	}
	public int getGoods_Id() {
		return goods_Id;
	}
	public void setGoods_Id(int goods_Id) {
		this.goods_Id = goods_Id;
	}
	public String getGoods_Num() {
		return goods_Num;
	}
	public void setGoods_Num(String goods_Num) {
		this.goods_Num = goods_Num;
	}
	public String getGoods_Name() {
		return goods_Name;
	}
	public void setGoods_Name(String goods_Name) {
		this.goods_Name = goods_Name;
	}
	public String getGoods_BuyDate() {
		return goods_BuyDate;
	}
	public void setGoods_BuyDate(String goods_BuyDate) {
		this.goods_BuyDate = goods_BuyDate;
	}
	public String getGoods_StartUseDate() {	
		return goods_StartUseDate;
	}
	public void setGoods_StartUseDate(String goods_StartUseDate) {
		this.goods_StartUseDate = goods_StartUseDate;
	}
	
	

	public String getGoods_EndUseDate() {
		return goods_EndUseDate;
	}
	public void setGoods_EndUseDate(String goods_EndUseDate) {
		this.goods_EndUseDate = goods_EndUseDate;
	}
	public int getGoods_UseYear() {
		return goods_UseYear;
	}
	public void setGoods_UseYear(int goods_UseYear) {
		this.goods_UseYear = goods_UseYear;
	}
	
	public String getGoods_Type() {
		return goods_Type;
	}
	public void setGoods_Type(String goods_Type) {
		this.goods_Type = goods_Type;
	}
	
	

	
	
}
