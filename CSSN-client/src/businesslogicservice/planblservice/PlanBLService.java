package businesslogicservice.planblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.plan.Course;
import vo.plan.PlanVO;

public interface PlanBLService extends Remote{
	
	public PlanVO checkPlan(String institute,int grade,int semester) throws RemoteException;
	public void inputPlan(PlanVO plan) throws RemoteException;
	
	public ArrayList<Course> getOpenList(String institute,int grade,int semester)throws RemoteException;
	public ArrayList<Course> getMajorList(String institute,int grade,int semester)throws RemoteException;
}
