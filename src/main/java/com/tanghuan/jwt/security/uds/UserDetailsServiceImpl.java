package com.tanghuan.jwt.security.uds;

import com.tanghuan.jwt.entity.domain.Role;
import com.tanghuan.jwt.entity.domain.User;
import com.tanghuan.jwt.repository.UserRepository;
import com.tanghuan.jwt.security.entity.GrantedAuthorityImpl;
import com.tanghuan.jwt.security.entity.UserDetailsImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanghuan on 2017/3/8.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username + " not found");
        }

        UserDetailsImpl ud = new UserDetailsImpl();
        BeanUtils.copyProperties(user, ud);

        List<Role> roles = user.getRoles();
        List<GrantedAuthorityImpl> gas = new ArrayList<>();
        if (roles != null && roles.size() > 0) {
            roles.forEach(role -> gas.add(new GrantedAuthorityImpl(role.getName(), role.isEnabled())));
            ud.setAuthorities(gas);
        }

        return ud;
    }
}
