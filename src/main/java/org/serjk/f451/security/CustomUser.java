package org.serjk.f451.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Koyushev Sergey (mailto: serjk91@gmail.com)
 */
public class CustomUser implements Serializable, UserDetails
{
    private static final long serialVersionUID = 1201392234549297485L;
    private long id;
    private String password;
    private String username;
    private GrantedAuthority[] authorities = null;

    public CustomUser(long id, String username, String password, GrantedAuthority[] authorities)
    {
        this.id = id;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities()
    {
        Collection<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        Collections.addAll(auth, authorities);
        return auth;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
