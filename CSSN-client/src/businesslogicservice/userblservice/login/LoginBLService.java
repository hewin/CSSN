package businesslogicservice.userblservice.login;

import java.rmi.Remote;
import java.rmi.RemoteException;
import vo.user.login.*;
/**
 * The Login Interface.
 * 
 * @author CaoYuting
 * 
 */
public interface LoginBLService extends Remote {

	/**
	 * log into the system, the return value contains the following case:
	 * <code>["-1"]--User name does not exist. ["-2"]--Password is not match. 
	 * else the value is the login user's name.</code>
	 * 
	 * @param vo
	 * @return
	 * @throws RemoteException
	 */
	public String login(LoginVO vo) throws RemoteException;

}
