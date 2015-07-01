package businesslogicservice.scoreblservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.score.ScoreVO;

public interface ScoreBLService extends Remote {
	// �鿴ĳѧ��ĳ�ſεĳɼ�
	public ScoreVO checkStuScore(String coNo, String stuNo)
			throws RemoteException;

	// �鿴ĳѧ�����пγ̵ĳɼ�
	public ArrayList<ScoreVO> checkStuScore(String stuNo,
			ArrayList<String> courseList) throws RemoteException;

	// ���ĳɼ�(��������Ѿ��Ǽǹ��ɼ���ѧ���ĳɼ�)
	public void updateScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit) throws RemoteException;

	// �Ǽǳɼ�
	public void recordScore(String courseNo, String teacherNo, String stuNo,
			int score, int credit) throws RemoteException;

	// �鿴�ɼ�����ĳһѧ�ڵĳɼ���
	public ArrayList<ScoreVO> checkScoreList(String stuNo, String grade,
			String semester) throws RemoteException;

}