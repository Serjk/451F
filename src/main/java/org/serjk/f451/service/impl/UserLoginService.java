package org.serjk.f451.service.impl;

import org.serjk.f451.dao.UserDAO;
import org.serjk.f451.model.User;
import org.serjk.f451.model.enums.UserType;
import org.serjk.f451.security.CustomUser;
import org.serjk.f451.security.UserGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */

@Service
public class UserLoginService implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    private Map<String,String> roles = new HashMap<String,String>();

    public UserLoginService()
    {
        roles.put(UserType.ROLE_USER.getDbRoleId(),UserType.ROLE_USER.getRuName());
        roles.put(UserType.ROLE_OFFICIAL.getDbRoleId(),UserType.ROLE_OFFICIAL.getRuName());
        roles.put(UserType.ROLE_FIREMAN.getDbRoleId(),UserType.ROLE_FIREMAN.getRuName());
        roles.put(UserType.ROLE_POLICE.getDbRoleId(),UserType.ROLE_POLICE.getRuName());
    }

    public Map<String,String> getRoles()
    {
        return roles;
    }

    public  User getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return  userDAO.getUserByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException, DataAccessException {
        if (login != null && !login.equals(""))
        {
            User user = userDAO.getUserByLogin(login);
            if (user == null) {
                return null;
            }
            String role = UserType.ROLE_USER.toString();
            if (!user.getRole().equals("")) role = user.getRole();
            GrantedAuthority grantedAuth = new UserGrantedAuthority(role);
            return new CustomUser(user.getId(), user.getLogin(), user.getPassword(), new GrantedAuthority[]{ grantedAuth });
        } else {
            return null;
        }
    }
}
