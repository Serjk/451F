package org.serjk.f451.security;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public class UserGrantedAuthority implements GrantedAuthority {

    private static final long serialVersionUID = -3786297951121082644L;

    private String authority = null;
    public UserGrantedAuthority(String auth) { authority = auth; }

    @Override
    public String getAuthority() { return authority; }

}
