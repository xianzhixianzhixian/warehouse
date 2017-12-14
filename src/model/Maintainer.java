/**
 * 保管人信息
 */

package model;

public class Maintainer {

	private int maintainer_id; //编号
	private String maintainer_name; //姓名
	private String maintainer_job; //类型
	private String maintainer_company; //公司
	private String maintainer_phone; //电话
	
	public Maintainer(int maintainer_id,String maintainer_name,String maintainer_job,String maintainer_company,
			String maintainer_phone){
		this.maintainer_id=maintainer_id;
		this.maintainer_name=maintainer_name;
		this.maintainer_job=maintainer_job;
		this.maintainer_company=maintainer_company;
		this.maintainer_phone=maintainer_phone;
		//this.goods=goods;
	}

	public Maintainer(String maintainer_name, String maintainer_job, String maintainer_company,
			String maintainer_phone) {
		super();
		this.maintainer_name = maintainer_name;
		this.maintainer_job = maintainer_job;
		this.maintainer_company = maintainer_company;
		this.maintainer_phone = maintainer_phone;
	}



	public int getMaintainer_id() {
		return maintainer_id;
	}

	public void setMaintainer_id(int maintainer_id) {
		this.maintainer_id = maintainer_id;
	}

	public String getMaintainer_name() {
		return maintainer_name;
	}

	public void setMaintainer_name(String maintainer_name) {
		this.maintainer_name = maintainer_name;
	}

	public String getMaintainer_job() {
		return maintainer_job;
	}

	public void setMaintainer_job(String maintainer_job) {
		this.maintainer_job = maintainer_job;
	}

	public String getMaintainer_company() {
		return maintainer_company;
	}

	public void setMaintainer_company(String maintainer_company) {
		this.maintainer_company = maintainer_company;
	}

	public String getMaintainer_phone() {
		return maintainer_phone;
	}

	public void setMaintainer_phone(String maintainer_phone) {
		this.maintainer_phone = maintainer_phone;
	}


	

	
}
