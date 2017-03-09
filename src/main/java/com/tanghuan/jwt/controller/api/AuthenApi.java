package com.tanghuan.jwt.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.tanghuan.jwt.security.entity.AuthenToken;
import com.tanghuan.jwt.security.entity.JwtAuthenToken;
import com.tanghuan.jwt.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by tanghuan on 2017/3/8.
 */

@RestController
@RequestMapping("/api")
public class AuthenApi {

    @Autowired
    private AuthenService authenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenToken autToken, Device device) {

        JwtAuthenToken token = authenService.login(autToken, device);

        return ResponseEntity.ok(token);
    }

}
