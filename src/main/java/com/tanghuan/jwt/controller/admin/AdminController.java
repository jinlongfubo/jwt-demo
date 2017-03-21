package com.tanghuan.jwt.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Arthur on 2017/3/9.
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/{url:.+\\.html$}")
    public String pages(@PathVariable String url) {

        // 打印当前在线人数
        System.out.println(sessionRegistry.getAllPrincipals().size());

        return "admin/" + url.substring(0).replace(".html", "");
    }

    @GetMapping("/menu/{url:.+\\.html$}")
    public String subPages(@PathVariable String url) {
        return "admin/menu/"+url.substring(0).replace(".html", "");
    }
}
