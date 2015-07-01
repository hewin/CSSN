package vo.course;

import java.io.Serializable;

public class CourseVO implements Serializable {

	private static final long serialVersionUID = 1L;
	String coID;
	String coName;
	boolean isCompulsory;
	String credit;
	String hour;
	String module;
	String teaID;
	String teacher;
	String semester;
	String grade;
	String institution;
	String numOfStu;
	String time;
	String location;
	String description;
	String textbook;
	String reference;

	public CourseVO() {
	}

	public CourseVO(String coID, String coName) {
		this.coID = coID;
		this.coName = coName;
	}

	// 教务处老师发布完整信息
		// 课程号_课程名_课程性质_学分_课时_所属模块_任课老师工号_任课老师_开设学期_开设年级_开设院系_开课人数_上课时间__上课地点_课程描述_教材_参考书目
		public CourseVO(String coID, String coName, boolean isCompulsory,
				String credit, String hour, String module, String teaID,
				String teacher,String semester, String grade, String institution, String numOfStu,String time,
				String location, String description, String textbook,
				String reference) {
			this.coID = coID;
			this.coName = coName;
			this.isCompulsory = isCompulsory;
			this.credit = credit;
			this.hour = hour;
			this.module = module;
			this.teaID = teaID;
			this.teacher = teacher;
			this.semester=semester;
			this.grade = grade;
			this.institution = institution;
			this.numOfStu=numOfStu;
			this.time = time;
			this.location = location;
			this.description = description;
			this.textbook = textbook;
			this.reference = reference;
		}
		
		// 院系教务老师发布部分信息
		// 课程号_课程名_课程性质_学分_课时_所属模块_任课老师工号_任课老师_开设学期_开设年级_开设院系_开课人数_上课时间__上课地点
		public CourseVO(String coID, String coName, boolean isCompulsory,
				String credit, String hour, String module, String teaID,
				String teacher, String semester,String grade, String institution, String numOfStu,String time,
				String location) {
			this.coID = coID;
			this.coName = coName;
			this.isCompulsory = isCompulsory;
			this.credit = credit;
			this.hour = hour;
			this.module = module;
			this.teaID = teaID;
			this.teacher = teacher;
			this.semester=semester;
			this.grade = grade;
			this.institution = institution;
			this.numOfStu=numOfStu;
			this.time = time;
			this.location = location;
		}

		// 任课老师填写课程信息时补充的信息
		// 课程描述_教材_参考书目
		public CourseVO(String description, String textbook, String reference) {
			this.description = description;
			this.textbook = textbook;
			this.reference = reference;
		}

		//为任课教师查看课程准备的
		public CourseVO(String coName, String coID, int credit) {
			this.coName=coName;
			this.coID=coID;
			this.credit=credit+"";
		}


		//为任课教师查看课程准备的
		public CourseVO(String coID, int credit,String coName,String grade,String semester) {
			this.coName=coName;
			this.coID=coID;
			this.credit=credit+"";
			this.grade=grade;
			this.semester=semester;
		}
		
	// get方法
	public String getCoID() {
		return coID;
	}

	public String getCoName() {
		return coName;
	}

	public boolean getIsCompulsory() {
		return isCompulsory;
	}

	public String getCredit() {
		return credit;
	}

	public String getHour() {
		return hour;
	}

	public String getModule() {
		return module;
	}

	public String getTeaID() {
		return teaID;
	}

	public String getTeacher() {
		return teacher;
	}
	public String getSemester(){
		return semester;
	}

	public String getTime() {
		return time;
	}

	public String getLocation() {
		return location;
	}

	public String getGrade() {
		return grade;
	}

	public String getInstitution() {
		return institution;
	}
	
	public String getNumOfStu() {
		return numOfStu;
	}

	public String getDescription() {
		return description;
	}

	public String getTextbook() {
		return textbook;
	}

	public String getReference() {
		return reference;
	}
	
	public String toString() {
		return coID + " " + coName + " " + isCompulsory + " "
				+ credit + " " + hour + " " + module+ " " + teaID+ " " + teacher
				+ " " + grade+ " " + institution+ " " + numOfStu +" "+ time+ " " + location
				+ " " + description+ " " + textbook+ " " + reference;
	}
}
