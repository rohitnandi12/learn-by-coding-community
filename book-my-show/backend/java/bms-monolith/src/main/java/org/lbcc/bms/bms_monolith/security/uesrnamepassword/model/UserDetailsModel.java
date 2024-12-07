package org.lbcc.bms.bms_monolith.security.uesrnamepassword.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.lbcc.bms.bms_monolith.common.entity.RegisteredUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.util.Collection;
import java.util.stream.Collectors;

@AllArgsConstructor
@Data
public class UserDetailsModel implements UserDetails {

    @Serial
    private static final long serialVersionUID = -4335856020700036116L;
    private RegisteredUser registeredUser;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return registeredUser.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getLabel().name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return registeredUser.getPassword();
    }

    @Override
    public String getUsername() {
        return registeredUser.getUsername();
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
        return registeredUser.getIsActive();
    }
}

