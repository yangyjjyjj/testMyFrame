package com.wtwd.ldl.security.support;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @Author ldaoliang
 * @Date 2019/10/28 0028 下午 4:31
 * @Description TODO
 **/
public class UrlAccessDecisionManager implements AccessDecisionManager {
	/**
	 * 决策处理
	 */
	@Override
	public void decide(Authentication auth, Object object, Collection<ConfigAttribute> cas) throws AccessDeniedException, InsufficientAuthenticationException {
		//迭代ConfigAttribute，判断是否有权限
		Iterator<ConfigAttribute> iterator = cas.iterator();
		List<String> needRole = new ArrayList<>();
		while (iterator.hasNext()) {
			ConfigAttribute ca = iterator.next();
			//当前请求需要的权限    这个needRole只是获取的第一个，应该怎么处理呢？
			String role = ca.getAttribute();
			needRole.add(role);
		}
		//处理未做限制的url(允许访问);必须要在未登录之前判断一下，不然会被未登录拦截
		for(String role : needRole){
			if ("ROLE_LOGIN".equals(role)) {
				return;
			}
		}
		//非内部调用判断是否登录，未登录跳转至登录
		if (auth instanceof AnonymousAuthenticationToken) {
			//若未登录则直接返回
			throw new BadCredentialsException("未登录，重定向到登录请求");
		}
		//当前用户所具有的权限
		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		//当前账户拥有的角色和访问该url需要的角色进行对比
		for (GrantedAuthority authority : authorities) {
			for(String role : needRole){
				if(authority.getAuthority().equals(role)){
					return;
				}

			}

		}
		throw new AccessDeniedException("权限不足");

	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return false;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}
}
