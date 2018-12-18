package com.baomidou.springboot.config.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;

public class UsercenterCredentialsMatcher implements CredentialsMatcher {

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		SimpleAuthenticationInfo authInfo = (SimpleAuthenticationInfo)info;
		String md5Password = String.copyValueOf((char[])token.getCredentials());
		if(StringUtils.isNotBlank(md5Password) && StringUtils.equals(md5Password, info.getCredentials().toString())){
			return true;
		}
		return false;
	}

}
