package businesslogicservice.courseblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.course.CourseVO;

public interface CourseBLService extends Remote {

	// 院系教务老师发布课程
	public int publishYXCourse(CourseVO vo) throws RemoteException;

	// 教务处老师发布课程
	public int publishJWCourse(CourseVO vo) throws RemoteException;

	// 院系教务老师修改课程信息
	public int modifyCourseInfo(String oldCoID, CourseVO newCourseVO)
			throws RemoteException;

	// 任课老师填写课程信息
	public int completeCourseInfo(String coID, CourseVO addedVO)
			throws RemoteException;

	// 由教师工号得到教师应该填写信息的课程
	public ArrayList<CourseVO> getCourseToFinishInfo(String teacherId)
			throws RemoteException;

	// 由教师工号得到教师教授的课程
	public ArrayList<CourseVO> getTeacherCourse(String teacherId)
			throws RemoteException;

	// 院系教务老师查看本院课程列表
	public ArrayList<CourseVO> checkYXcourseList(String institution)
			throws RemoteException;

	// 院系教务老师查看本院任意课程信息
	public CourseVO checkYXcourseInfo(String coID, String institution)
			throws RemoteException;

	// 学生查看任意课程信息
	public CourseVO checkAnyCourseInfo(String coID) throws RemoteException;

	// 由课程模块得到课程列表
	public ArrayList<CourseVO> getModuleCourseList(String module)
			throws RemoteException;

	// 得到全校的课程列表
	public ArrayList<CourseVO> getAllCourseList() throws RemoteException;

	// 得到院系教务老师已经发布的且信息未填写完整的课程列表
	public ArrayList<CourseVO> checkMycourseList(String institution)
			throws RemoteException;

	// 院系教务老师查看未填写完整信息的课程列表的信息
	public CourseVO checkYXUnfinishedcourseInfo(String coID)
			throws RemoteException;

	// 通过模块名得到课程列表（每门课程含有17个属性）
	public ArrayList<CourseVO> getModuleCompletedCourseList(String module)
			throws RemoteException;
	
	// 通过院系得到该院系全部课程（每门课程含有17个属性）
	public ArrayList<CourseVO> checkYXCompletedcourseList(String institution)
					throws RemoteException;

}
