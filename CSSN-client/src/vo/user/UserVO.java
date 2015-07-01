package vo.user;

import java.io.Serializable;

import vo.user.Identity;

public class UserVO implements Serializable {

	private static final long serialVersionUID = -7431771796010342179L;

	private Identity identity;
	
	private String idNum;

	private String userName;

	private String password;
	
	private String institute;
	
	private String grade;

	//������ʦ���ον�ʦ������Ա
	public UserVO(String idNum, String userName, String password, Identity identity) {
		this.idNum = idNum;
		this.userName = userName;
		this.password = password;
		this.identity = identity;
	}
	
	//Ժϵ������ʦ
	public UserVO(String idNum, String userName, String password, Identity identity, String institute){
		this(idNum, userName, password, identity);
		this.institute = institute;
	}
	
	//ѧ��
	public UserVO(String idNum, String userName, String password, Identity identity, String institute, String grade){
		this(idNum, userName, password, identity);
		this.institute = institute;
		this.grade = grade;
	}
	
	public String getIdNum(){
		return idNum;
	}
	
	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public Identity getIdentity() {
		return identity;
	}
	
	public String getInstitute(){
		return institute;
	}
	
	public String getGrade(){
		return grade;
	}

	public void setIdentity(Identity identity) {
		this.identity = identity;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
