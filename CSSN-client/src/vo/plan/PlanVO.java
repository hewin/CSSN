package vo.plan;

import java.io.Serializable;
import java.util.ArrayList;

public class PlanVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String institute;//������ܻ�ĳ�ö��enum
	private int grade;//������String
	private int semester;//������String
	private ArrayList<Course> openList;//����ѡ���б�
	private ArrayList<Course> majorList;//רҵ�γ��б�
	
	public PlanVO(String institute,int grade,int semester
			,ArrayList<Course> openList,ArrayList<Course> majorList){
		this.institute=institute;
		this.grade=grade;
		this.semester=semester;
		this.openList=openList;
		this.majorList=majorList;
	}
	
	public String getInstitute(){
		return institute;
	}
	
	public int getGrade(){
		return grade;
	}
	public int getSemester(){
		return semester;
	}
	
	public ArrayList<Course> getOpenList(){
		return openList;
	}
	
	public ArrayList<Course> getMajorList(){
		return majorList;
	}
}
