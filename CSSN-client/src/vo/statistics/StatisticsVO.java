package vo.statistics;

import java.io.Serializable;
import java.util.ArrayList;

import vo.course.CourseVO;

public class StatisticsVO implements Serializable {

	private static final long serialVersionUID = 1L;

	int excellent;
	int fine;
	int fail;
	int medium;
	int pass;

	int num;// 选课人数

	String teacherName;
	String teacherNo;
	String courseNo;
	String courseName;
	String stuNo;

	ArrayList<CourseVO> failList;// 挂科列表
	int majorCredit;// 学科专业模块修得学分
	int openCredit;// 开放选修模块修得学分
	int commonCredit;// 通修课程,包括微积分等
	int liberalEducation;// 通识教育14个学分

	ArrayList<Double> gpa;// 学分绩
	ArrayList<CourseVO> list;// 修到的其他院的课程列表
	ArrayList<CourseVO> majorList;// 专业核心课列表
	ArrayList<CourseVO> commonList;// 通识通修列表（微积分马原）
	ArrayList<CourseVO> liberalEducationList;// 14个学分课列表

	int Creditnum;// 修到的其他院的总学分数

	StatisticsVO stvo;// 查看学生审核统计数据的vo（内容是）
	
	public StatisticsVO(){}
	// 这是查看教师统计信息的VO
	public StatisticsVO(String courseName, String courseNo, int excellent,
			int fine, int medium, int pass, int fail) {
		this.courseName = courseName;
		this.courseNo = courseNo;
		this.excellent = excellent;
		this.fine = fine;
		this.medium = medium;
		this.pass = pass;
		this.fail = fail;
	}

	// 查看课程统计信息的VO
	public StatisticsVO(String courseNo, int num) {
		this.courseNo = courseNo;
		this.num = num;
	}

	// 查看学生审核统计数据
	public StatisticsVO(String stuNo, int majorCredit, int openCredit,
			int commonCredit, int liberalEducation,
			ArrayList<CourseVO> failList, ArrayList<CourseVO> majorList,
			ArrayList<CourseVO> commonList,
			ArrayList<CourseVO> liberalEducationList) {
		this.stuNo = stuNo;
		this.majorCredit = majorCredit;
		this.openCredit = openCredit;
		this.commonCredit = commonCredit;
		this.liberalEducation = liberalEducation;
		this.failList = failList;
		this.majorList=majorList;
		this.commonList=commonList;
	}

	// 第一个是查看学生审核统计数据的vo，第二个是下一个vo
	public StatisticsVO(StatisticsVO stvo, ArrayList<CourseVO> list,
			int Creditnum) {
		this.stvo = stvo;
		this.list = list;
		this.Creditnum = Creditnum;
	}

	// 修到的该院课程名，以及他们的总学分
	public StatisticsVO(ArrayList<CourseVO> list, int Creditnum) {

	}

	public StatisticsVO(ArrayList<Double> gpa) {
		this.gpa = gpa;
	}

	public int getExcellent() {
		return excellent;
	}

	public int getFine() {
		return fine;
	}

	public int getFail() {
		return fail;
	}

	public int getMedium() {
		return medium;
	}

	public int getPass() {
		return pass;
	}

	public int getNum() {
		return num;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public String getTeacherNo() {
		return teacherNo;
	}

	public String getCourseNo() {
		return courseNo;
	}

	public String getCourseName() {
		return courseName;
	}

	public String getStuNo() {
		return stuNo;
	}

	public ArrayList<CourseVO> getFailList() {
		return failList;
	}

	public int getMajorCredit() {
		return majorCredit;
	}

	public int getOpenCredit() {
		return openCredit;
	}

	public int getCommonCredit() {
		return commonCredit;
	}

	public int getLiberalEducation() {
		return liberalEducation;
	}

	public ArrayList<Double> getGpa() {
		return gpa;
	}

	public ArrayList<CourseVO> getList() {
		return list;
	}

	public int getCreditnum() {
		return Creditnum;
	}

	public ArrayList<CourseVO> getMajorList() {
		return majorList;
	}

	public ArrayList<CourseVO> getLiberalEducationList() {
		return liberalEducationList;
	}

	public ArrayList<CourseVO> getCommonList() {
		return commonList;
	}

	public StatisticsVO getVO(){
		return stvo;
	}
	
}
