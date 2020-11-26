package com.wtwd.ldl.security.support;

import com.wtwd.ldl.bean.SysPer;
import com.wtwd.ldl.bean.SysRole;
import com.wtwd.ldl.service.Impl.SysRolePerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author ldaoliang
 * @Date 2019/10/26 0026 上午 11:27
 * @Description TODO    对url的处理
 **/
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	private SysRolePerServiceImpl rolePerService;

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		//Object中包含用户的request信息
		HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
		//获取请求的url地址
		String perUrl = request.getRequestURI();
		//判断请求该url需要什么样的角色
		//存放认证信息的map   key=url    value = 权限集合
		Map<String, Collection<ConfigAttribute>> map = null;
		if("/login".equals(perUrl) || "/getKaptchaImage".equals(perUrl)){ //如果是这些路径，在后面不判断
			return SecurityConfig.createList("ROLE_LOGIN");
		}
		if(perUrl != null){ // 加载该url需要的资源
			map = loadResourceByUrl(perUrl);
		}
		if(map != null && map.keySet().size() > 0){
			for(String url : map.keySet()){
				if(new AntPathRequestMatcher(url).matches(request)){
					return map.get(url);
				}
			}
		}
		//没有匹配上的都是登录请求
		return SecurityConfig.createList("ROLE_LOGIN");
	}

	/**
	 * 获取url所需要的权限信息
	 * @param perUrl
	 * @return
	 */
	private Map<String,Collection<ConfigAttribute>> loadResourceByUrl(String perUrl){
		//存放认证信息的map   key=url    value = 权限集合
		HashMap<String, Collection<ConfigAttribute>> map = null;
			// 根据url 获取 permission对象（里面有roleList）
			SysPer sysPer = rolePerService.getUrlPerRoleListByUrl(perUrl);
			if(sysPer != null){
				List<SysRole> sysRoleList = sysPer.getRoleList();
				if(sysRoleList != null && sysRoleList.size()>0){
					map = new HashMap<>();
					//遍历角色列表，把角色转为  authentication ,并且存放到ConfigAttribute中
					for(SysRole roles : sysRoleList){
						String roleName =roles.getRole_name();
						ConfigAttribute role = new SecurityConfig(roleName);
						if(map.containsKey(perUrl)){  //判断map中是否存在该key；如果存在，就往上加
							map.get(perUrl).add(role);
						}else{
							map.put(perUrl,new ArrayList<ConfigAttribute>(){{
								add(role);
							}});
						}
					}
				}
			}

		return map;
	}




	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}
}
