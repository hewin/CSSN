package vo.plan;

import java.io.Serializable;
import java.util.ArrayList;

public class PlanVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String institute;//这个可能会改成枚举enum
	private int grade;//或许是String
	private int semester;//或许是String
	private ArrayList<Course> openList;//开放选修列表
	private ArrayList<Course> majorList;//专业课程列表
	
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
