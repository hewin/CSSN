package vo.plan;

import java.io.Serializable;

public class Course implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int credit;
	private String module;//ËùÊôÄ£¿é
	
	public Course(String name,int credit,String module){
		this.name=name;
		this.credit=credit;
		this.module=module;//major/common
	}
	
	public Course(String name, int credit){
		this.name=name;
		this.credit=credit;
	}
	
	public String getName(){
		return name;
	}
	
	public int getCredit(){
		return credit;
	}
	
	public String getModule(){
		return module;
	}
	
}
