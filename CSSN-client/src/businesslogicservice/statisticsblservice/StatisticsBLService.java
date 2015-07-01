package businesslogicservice.statisticsblservice;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.statistics.StatisticsVO;

public interface StatisticsBLService {
	public ArrayList<StatisticsVO> checkTeacherStatistics(String id) throws RemoteException;
	public StatisticsVO checkCourseStatistics(String courseNo) throws RemoteException;
	public StatisticsVO checkStuStatistics(String stuNo)throws RemoteException;
	public StatisticsVO checkGPA(String stuNo,String grade)throws RemoteException;
	public StatisticsVO checkAccess(String stuNO,String institute)throws RemoteException;
}
