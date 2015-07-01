package vo.user.login;

import java.io.Serializable;

import vo.user.Identity;

/**
 * A VO encapsulate the login information.
 * 
 * @author CaoYuting
 * 
 */
public class LoginVO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Identity identity;
	private String name;
	private String passwd;

	public LoginVO(String name, String passwd, Identity identity) {
		this.name = name;
		this.passwd = passwd;
		this.identity = identity;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return passwd;
	}

	public Identity getIdentity() {
		return identity;
	}
}
