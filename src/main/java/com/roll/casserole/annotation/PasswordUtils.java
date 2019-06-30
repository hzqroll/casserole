package com.roll.casserole.annotation;

import java.util.List;

/**
 * @author haozq
 * Date: 2018/8/19 下午12:07
 */
public class PasswordUtils {

	private transient int a = 1;
	@UserCase(id = 47, description = "password must contain at lease on numeric")
	public boolean validatePassword(String password) {
		return password.matches("\\w*\\d\\w*");
	}

	@UserCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}

	@UserCase(id = 49, description = "new password can't equals previously used ones")
	public boolean checkFornewPassword(List<String> prePasswords, String password) {
		return !prePasswords.contains(password);
	}
}
