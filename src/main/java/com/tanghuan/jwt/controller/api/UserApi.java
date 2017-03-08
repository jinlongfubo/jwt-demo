package com.tanghuan.jwt.controller.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanghuan on 2017/3/8.
 */

@RestController
@RequestMapping("/api")
public class UserApi {


    @GetMapping("/users")
    public ResponseEntity users() {

        JSONArray array = new JSONArray();

        for (int i = 1; i < 10; i++) {
            JSONObject obj = new JSONObject();
            obj.put("name", "user" + 1);
            obj.put("age", 20 + 1);
            array.add(obj);
        }


        return ResponseEntity.ok(array);
    }

}
