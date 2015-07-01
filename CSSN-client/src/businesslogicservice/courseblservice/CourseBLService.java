package businesslogicservice.courseblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.course.CourseVO;

public interface CourseBLService extends Remote {

	// Ժϵ������ʦ�����γ�
	public int publishYXCourse(CourseVO vo) throws RemoteException;

	// ������ʦ�����γ�
	public int publishJWCourse(CourseVO vo) throws RemoteException;

	// Ժϵ������ʦ�޸Ŀγ���Ϣ
	public int modifyCourseInfo(String oldCoID, CourseVO newCourseVO)
			throws RemoteException;

	// �ο���ʦ��д�γ���Ϣ
	public int completeCourseInfo(String coID, CourseVO addedVO)
			throws RemoteException;

	// �ɽ�ʦ���ŵõ���ʦӦ����д��Ϣ�Ŀγ�
	public ArrayList<CourseVO> getCourseToFinishInfo(String teacherId)
			throws RemoteException;

	// �ɽ�ʦ���ŵõ���ʦ���ڵĿγ�
	public ArrayList<CourseVO> getTeacherCourse(String teacherId)
			throws RemoteException;

	// Ժϵ������ʦ�鿴��Ժ�γ��б�
	public ArrayList<CourseVO> checkYXcourseList(String institution)
			throws RemoteException;

	// Ժϵ������ʦ�鿴��Ժ����γ���Ϣ
	public CourseVO checkYXcourseInfo(String coID, String institution)
			throws RemoteException;

	// ѧ���鿴����γ���Ϣ
	public CourseVO checkAnyCourseInfo(String coID) throws RemoteException;

	// �ɿγ�ģ��õ��γ��б�
	public ArrayList<CourseVO> getModuleCourseList(String module)
			throws RemoteException;

	// �õ�ȫУ�Ŀγ��б�
	public ArrayList<CourseVO> getAllCourseList() throws RemoteException;

	// �õ�Ժϵ������ʦ�Ѿ�����������Ϣδ��д�����Ŀγ��б�
	public ArrayList<CourseVO> checkMycourseList(String institution)
			throws RemoteException;

	// Ժϵ������ʦ�鿴δ��д������Ϣ�Ŀγ��б����Ϣ
	public CourseVO checkYXUnfinishedcourseInfo(String coID)
			throws RemoteException;

	// ͨ��ģ�����õ��γ��б�ÿ�ſγ̺���17�����ԣ�
	public ArrayList<CourseVO> getModuleCompletedCourseList(String module)
			throws RemoteException;
	
	// ͨ��Ժϵ�õ���Ժϵȫ���γ̣�ÿ�ſγ̺���17�����ԣ�
	public ArrayList<CourseVO> checkYXCompletedcourseList(String institution)
					throws RemoteException;

}
