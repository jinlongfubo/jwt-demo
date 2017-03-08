package com.tanghuan.jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by tanghuan on 2017/3/8.
 */

@Controller
public class IndexController {

    @GetMapping(value = {"/", "/index"})
    public String indexPage() {
        return "index";
    }

    @GetMapping("/{url:.+\\.html$}")
    public String pages(@PathVariable String url) {
        return url.substring(0).replace(".html", "");
    }


    @GetMapping("/menu/{url:.+\\.html$}")
    public String subPages(@PathVariable String url) {
        return "menu/"+url.substring(0).replace(".html", "");
    }

}
