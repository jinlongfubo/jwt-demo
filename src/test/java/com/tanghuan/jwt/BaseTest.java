package com.tanghuan.jwt;

import com.tanghuan.jwt.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by tanghuan on 2017/2/27.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
public class BaseTest {

    @Test
    public void test() throws Exception {
        System.out.println("Hello Junit!");
    }

}
