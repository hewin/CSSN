package vo.courseselection;

public class CourseSelectionVO {
	String courseNO;
	String stuNO;
	String teacherNO;
	String state;
	String semester;
	
	
	public CourseSelectionVO(String courseNO,String stuNO,String teacherNO,String state,String semester){
		this.courseNO = courseNO;
		this.stuNO = stuNO;
		this.teacherNO = teacherNO;
		this.state = state;
		this.semester = semester;
	}
	
	public String getCourseNO(){
		return this.courseNO;
	}
	
	
	public String getStuNO(){
		return this.stuNO;
	}
	
	public String getTeacherNO(){
		return this.teacherNO;
	}
	
	public String getState(){
		return this.state;
	}
	
	public String getSemester(){
		return this.semester;
	}
}
