package businesslogicservice.frameblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.frame.FrameVO;

/**
 * The Frame Interface.
 *  It contains methods to query the frame database system.
 * 
 * @author CaoYuting
 * 
 */
public interface FrameBLService extends Remote {

	/**
	 * get a list of <code><b>FrameVO</b></code>
	 *
	 * @throws RemoteException
	 */
	public ArrayList<FrameVO> getFrameList() throws RemoteException;
	
	/**
	 * get a list of <code><b>String</b></code>
	 *
	 * @throws RemoteException
	 */
	public String[] getModuleNameList() throws RemoteException;

	/**
	 * get a <code><b>FrameVO</b></code> by the given frameId
	 * 
	 * @param frameId
	 * @return if the frameId does not exist, 
	 * the return value is <code><b>null</b></code>
	 */
	public FrameVO getFrameById(int frameId) throws RemoteException;
	
	/**
	 * add a new frame to the frame database system.
	 * 
	 * @param po
	 * @return 1--Success 
	 *         0--courseModule already exist
	 *        -1--creditUpper < creditLower 
	 *        -2--creditLower < 0 
	 *        -3--creditUpper < 0
	 * @throws RemoteException
	 */
	public int addFrameVO(FrameVO po) throws RemoteException;
	
	/**
	 * delete an exist frame from the frame database system,
	 * 
	 * @param frameId
	 * @return 1--Success 
	 *        -1--invalid frameId
	 * @throws RemoteException
	 */
	public int deleteFrameVO(int frameId) throws RemoteException;
	
	/**
	 * modify the previous frameVO into the current frameVO, and update the
	 * database
	 * 
	 * @param oldFrameId
	 * @param newFrameVO
	 * @return 1--Success 
	 *        -1--The oldFrameId is not exist.
	 *        -2--The LowerCredit > the UpperCredit
	 * @throws RemoteException
	 */
	public int modifyFrameVO(int oldFrameId, FrameVO newFrameVO)
			throws RemoteException;
}

