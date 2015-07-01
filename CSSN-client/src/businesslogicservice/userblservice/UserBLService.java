package businesslogicservice.userblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.user.UserVO;

/**
 * The User Interface. It contains methods to query the user database system.
 * 
 * @author CaoYuting
 * 
 */
public interface UserBLService extends Remote {

	/**
	 * get a list of <code><b>UserPO</b></code>
	 * 
	 * @throws RemoteException
	 */
	public ArrayList<UserVO> getUserList() throws RemoteException;

	/**
	 * get a <code><b>UserVO</b></code> by the given idNum and identity
	 * 
	 * @param idNum
	 * @param identity
	 * @return if the user does not exist, the return value is
	 *         <code><b>null</b></code>
	 */
	public UserVO getUserByIdNum(String idNum) throws RemoteException;

	public ArrayList<UserVO> getAllTeacher() throws RemoteException;

	public ArrayList<UserVO> getYXStuID(String institution)
			throws RemoteException;

	public void deleteUser(String idNum) throws RemoteException;

	public void updatePassword(String idNum, String newPassword)
			throws RemoteException;

	public boolean addUser(UserVO vo) throws RemoteException;

	public void modifyUserInfo(UserVO vo) throws RemoteException;
	
	public ArrayList<UserVO> getYXTeacher(String institution) throws RemoteException;
}
