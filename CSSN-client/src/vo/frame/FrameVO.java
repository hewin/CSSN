package vo.frame;

import java.io.Serializable;

public class FrameVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8394362976487991108L;

	private int id;
	private String courseModule; // �γ�ģ����
	private String nature; // �γ�����
	private String category; // �γ����
	private String semester;// ����ѧ��
	private int creditLower; // ѧ�ַ�Χ����
	private int creditUpper; // ѧ�ַ�Χ����

	public FrameVO(String courseModule, String nature, String category,
			String semester,int creditLower, int creditUpper) {
		this(-1,courseModule, nature, category, semester, creditLower, creditUpper);
	}

	public FrameVO(int id, String courseModule, String nature, String category,
			String semester,int creditLower, int creditUpper) {
		this.id = id;
		this.courseModule = courseModule;
		this.nature = nature;
		this.category = category;
		this.semester = semester;
		this.creditLower = creditLower;
		this.creditUpper = creditUpper;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public String getCourseModule() {
		return courseModule;
	}

	public String getNature(){
		return nature;
	}
	
	public String getCategory(){
		return category;
	}
	
	public String getSemester(){
		return semester;
	}
	
	public int getCreditLower() {
		return creditLower;
	}
	
	public int getCreditUpper() {
		return creditUpper;
	}
	
	public String toString() {
		return id + " " + courseModule + " " + nature + " " + category + " "
				+ semester + " " + creditLower + " " + creditUpper;
	}

}

