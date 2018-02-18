package com.bookDairy.domain;

import com.bookDairy.domain.enums.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public class UserDetailsImpl implements UserDetails {
    private User user;
    private Collection<SimpleGrantedAuthority> grantedAuthorities;

    private final Logger LOGGER = LoggerFactory.getLogger(UserDetailsImpl.class);


    public UserDetailsImpl(User user) {
        LOGGER.info("Create UserDetailsExt wrapping around {}", user);
        this.user = user;
        this.grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        roles.forEach(role->grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        LOGGER.trace("Getting password");
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        LOGGER.trace("Getting login");
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        LOGGER.trace("Setting isAccountNonExpired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        LOGGER.trace("Setting isAccountNonLocked");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        LOGGER.trace("Setting isCredentialsNonExpired");
        return true;
    }

    @Override
    public boolean isEnabled() {
        LOGGER.trace("Setting isEnabled");
        return true;
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsImpl that = (UserDetailsImpl) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(grantedAuthorities, that.grantedAuthorities);
    }

    @Override
    public int hashCode() {

        return Objects.hash(user, grantedAuthorities);
    }
}
