package businesslogicservice.courseselectionblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.course.CourseVO;
import vo.user.UserVO;

public interface CourseSelectionBLService extends Remote{
	//查看我的课程列表
	public ArrayList<CourseVO> checkMyCourse(String id,String semester,String state) throws Exception;
	//退选课程
	public String quitCourse(String courseNO,String id)  throws RemoteException;
	//选择课程
	public String chooseCourse(String courseNO,String id) throws RemoteException;
	//选体育课，先到先得
	public String choosePELesson(String courseNO,String stuNO,String teacherNO) throws RemoteException;
	//查看本院任意课程学生列表
	public ArrayList<UserVO> checkAnyCourseStu(String courseNO) throws RemoteException;
	//查看自己课程的选课列表
	public ArrayList<UserVO> checkMyCourseStu(String courseNO,String teacherNO) throws RemoteException;
	//教务处老师发布选课时间
	public int publishChooseCourseTime(String begin, String end) throws RemoteException;
	//教务处老师抽签
	public ArrayList<UserVO> allocate(String courseNO,String maxGrade) 
			throws RemoteException;
}
