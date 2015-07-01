package businesslogicservice.courseselectionblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.course.CourseVO;
import vo.user.UserVO;

public interface CourseSelectionBLService extends Remote{
	//�鿴�ҵĿγ��б�
	public ArrayList<CourseVO> checkMyCourse(String id,String semester,String state) throws Exception;
	//��ѡ�γ�
	public String quitCourse(String courseNO,String id)  throws RemoteException;
	//ѡ��γ�
	public String chooseCourse(String courseNO,String id) throws RemoteException;
	//ѡ�����Σ��ȵ��ȵ�
	public String choosePELesson(String courseNO,String stuNO,String teacherNO) throws RemoteException;
	//�鿴��Ժ����γ�ѧ���б�
	public ArrayList<UserVO> checkAnyCourseStu(String courseNO) throws RemoteException;
	//�鿴�Լ��γ̵�ѡ���б�
	public ArrayList<UserVO> checkMyCourseStu(String courseNO,String teacherNO) throws RemoteException;
	//������ʦ����ѡ��ʱ��
	public int publishChooseCourseTime(String begin, String end) throws RemoteException;
	//������ʦ��ǩ
	public ArrayList<UserVO> allocate(String courseNO,String maxGrade) 
			throws RemoteException;
}
