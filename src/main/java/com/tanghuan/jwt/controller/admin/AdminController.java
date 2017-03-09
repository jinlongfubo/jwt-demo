package com.tanghuan.jwt.controller.admin;

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

    @GetMapping("/{url:.+\\.html$}")
    public String pages(@PathVariable String url) {
        return "admin/" + url.substring(0).replace(".html", "");
    }

    @GetMapping("/menu/{url:.+\\.html$}")
    public String subPages(@PathVariable String url) {
        return "admin/menu/"+url.substring(0).replace(".html", "");
    }
}
