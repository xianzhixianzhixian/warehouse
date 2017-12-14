package model;

public class Goods_Maintainer {
	private int goods_Id; //�豸���
	private int maintainer_id; //ά����Ա���
	private String goods_StartMaintainDate; //ά����������
	private String goods_EndMaintainDate; //ά����������
	
	
	public Goods_Maintainer() {
		super();
	}
	
	public Goods_Maintainer(int goods_Id, int maintainer_id, String goods_StartMaintainDate,
			String goods_EndMaintainDate) {
		super();
		this.goods_Id = goods_Id;
		this.maintainer_id = maintainer_id;
		this.goods_StartMaintainDate = goods_StartMaintainDate;
		this.goods_EndMaintainDate = goods_EndMaintainDate;
	}
	public int getGoods_Id() {
		return goods_Id;
	}
	public void setGoods_Id(int goods_Id) {
		this.goods_Id = goods_Id;
	}
	public int getMaintainer_id() {
		return maintainer_id;
	}
	public void setMaintainer_id(int maintainer_id) {
		this.maintainer_id = maintainer_id;
	}
	public String getGoods_StartMaintainDate() {
		return goods_StartMaintainDate;
	}
	public void setGoods_StartMaintainDate(String goods_StartMaintainDate) {
		this.goods_StartMaintainDate = goods_StartMaintainDate;
	}
	public String getGoods_EndMaintainDate() {
		return goods_EndMaintainDate;
	}
	public void setGoods_EndMaintainDate(String goods_EndMaintainDate) {
		this.goods_EndMaintainDate = goods_EndMaintainDate;
	}
	
	

}
