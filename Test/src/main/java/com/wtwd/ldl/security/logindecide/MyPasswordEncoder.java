package com.wtwd.ldl.security.logindecide;

import com.wtwd.ldl.util.AESUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author lixiangyi
 * @date 2019/4/18 13:56
 * @description
 */
public class MyPasswordEncoder implements PasswordEncoder {

	/**
	 * 编码
	 * @param rawPassword
	 * @return
	 */
	@Override
	public String encode(CharSequence rawPassword) {
		if(rawPassword == null || rawPassword.length()==0){
			return "";
		}
		try {
			return AESUtils.encrypt(rawPassword.toString());
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 匹配
	 * @param rawPassword
	 * @param encodedPassword
	 * @return
	 */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (rawPassword == null || rawPassword.length() == 0 ||
				encodedPassword == null ||  encodedPassword.length() == 0) {
			return false;
		}
		try {
			return rawPassword.equals(AESUtils.decrypt(encodedPassword));
		}catch (Exception e){
			return false;
		}
	}
}

