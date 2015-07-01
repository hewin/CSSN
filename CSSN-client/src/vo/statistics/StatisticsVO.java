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

	int num;// ѡ������

	String teacherName;
	String teacherNo;
	String courseNo;
	String courseName;
	String stuNo;

	ArrayList<CourseVO> failList;// �ҿ��б�
	int majorCredit;// ѧ��רҵģ���޵�ѧ��
	int openCredit;// ����ѡ��ģ���޵�ѧ��
	int commonCredit;// ͨ�޿γ�,����΢���ֵ�
	int liberalEducation;// ͨʶ����14��ѧ��

	ArrayList<Double> gpa;// ѧ�ּ�
	ArrayList<CourseVO> list;// �޵�������Ժ�Ŀγ��б�
	ArrayList<CourseVO> majorList;// רҵ���Ŀ��б�
	ArrayList<CourseVO> commonList;// ͨʶͨ���б�΢������ԭ��
	ArrayList<CourseVO> liberalEducationList;// 14��ѧ�ֿ��б�

	int Creditnum;// �޵�������Ժ����ѧ����

	StatisticsVO stvo;// �鿴ѧ�����ͳ�����ݵ�vo�������ǣ�
	
	public StatisticsVO(){}
	// ���ǲ鿴��ʦͳ����Ϣ��VO
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

	// �鿴�γ�ͳ����Ϣ��VO
	public StatisticsVO(String courseNo, int num) {
		this.courseNo = courseNo;
		this.num = num;
	}

	// �鿴ѧ�����ͳ������
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

	// ��һ���ǲ鿴ѧ�����ͳ�����ݵ�vo���ڶ�������һ��vo
	public StatisticsVO(StatisticsVO stvo, ArrayList<CourseVO> list,
			int Creditnum) {
		this.stvo = stvo;
		this.list = list;
		this.Creditnum = Creditnum;
	}

	// �޵��ĸ�Ժ�γ������Լ����ǵ���ѧ��
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
