package com.tanghuan.jwt.service;

import com.tanghuan.jwt.security.entity.AuthenToken;
import com.tanghuan.jwt.security.entity.JwtAuthenToken;
import org.springframework.mobile.device.Device;

/**
 * Created by Arthur on 2017/3/9.
 */
public interface AuthenService {

    JwtAuthenToken login(AuthenToken autToken, Device device);

}
