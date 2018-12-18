package com.baomidou.springboot.config.shiro;


import com.baomidou.springboot.entity.Permission;
import com.baomidou.springboot.entity.Role;
import com.baomidou.springboot.entity.User;
import com.baomidou.springboot.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRelam extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("用户权限配置。。。。。。。。。。");
        //访问@RequirePermission注解的url时触发
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userInfo  = (User)principals.getPrimaryPrincipal();
        //获得用户的角色，及权限进行绑定
        for(Role role:userInfo.getRoleList()){
            authorizationInfo.addRole(role.getRolename());
            for(Permission p:role.getPermissionList()){
                authorizationInfo.addStringPermission(p.getPermission());
            }
        }
        return authorizationInfo;
    }

    /**
     *  验证用户登录信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("验证用户登录信息");
        String username = (String)token.getPrincipal();
        System.out.println("登录用户名： "+username);
        System.out.println(token.getCredentials());
        //从数据库查询出User信息及用户关联的角色，权限信息，以备权限分配时使用
        User user = userService.findUserByName(username);
        if(null == user) {
            return null;
        }
        System.out.println("username: "+user.getUsername()+" ; password : "+user.getPassword());
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );

        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        // 用户对象
        session.setAttribute("userSession", user);
        return authenticationInfo;
    }
}
