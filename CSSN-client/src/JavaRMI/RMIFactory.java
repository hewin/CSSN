package JavaRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

import businesslogicservice.courseblservice.CourseBLService;
import businesslogicservice.courseselectionblservice.CourseSelectionBLService;
import businesslogicservice.frameblservice.FrameBLService;
import businesslogicservice.planblservice.PlanBLService;
import businesslogicservice.scoreblservice.ScoreBLService;
import businesslogicservice.statisticsblservice.StatisticsBLService;
import businesslogicservice.userblservice.UserBLService;
import businesslogicservice.userblservice.login.LoginBLService;

public interface RMIFactory extends Remote{

	public CourseBLService getCQService() throws RemoteException;

	public CourseSelectionBLService getCSQService() throws RemoteException;
	
	public FrameBLService getFQService() throws RemoteException;

	public LoginBLService getLoginService() throws RemoteException;

	public PlanBLService getPLQService() throws RemoteException;

	public ScoreBLService getSCQService() throws RemoteException;

	public StatisticsBLService getSTQService() throws RemoteException;

	public UserBLService getUQService() throws RemoteException;
	
}
