package com.tanghuan.jwt.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.tanghuan.jwt.security.entity.AuthenToken;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenToken token, Device device) {

        JSONObject object = new JSONObject();
        object.put("username", token.getUsername());
        object.put("password", token.getPassword());
        object.put("device", device.toString());

        return ResponseEntity.ok(object);
    }

}
