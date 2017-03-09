package com.tanghuan.jwt.service.impl;

import com.tanghuan.jwt.security.entity.AuthenToken;
import com.tanghuan.jwt.security.entity.JwtAuthenToken;
import com.tanghuan.jwt.security.utils.JwtUtil;
import com.tanghuan.jwt.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by Arthur on 2017/3/9.
 */

@Service
public class AuthenServiceImpl implements AuthenService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public JwtAuthenToken login(AuthenToken autToken, Device device) {

        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                autToken.getUsername(),
                autToken.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        // 生成Token
        String token = jwtUtil.generateToken(userDetails, device);

        return new JwtAuthenToken(token);
    }
}
