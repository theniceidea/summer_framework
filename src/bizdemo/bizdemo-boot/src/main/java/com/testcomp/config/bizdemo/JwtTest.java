package com.testcomp.config.bizdemo;

import com.alibaba.fastjson.JSONObject;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.web.JwtService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtTest implements ApplicationRunner {
    private static final int SESSION_LIFE_TIME = 3;

    @Autowired
    private JwtService jwtService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final JSONObject json = new JSONObject();
        json.put(Consts.USER_PROPERTY_ID, "id000001");
        json.put(Consts.USER_PROPERTY_ADMIN, "1");
        json.put(Consts.USER_PROPERTY_EXP, DateTime.now().plusDays(SESSION_LIFE_TIME).getMillis() / 1000);
        final String encode = jwtService.encode(json.toJSONString());
        System.out.println("jwt token: Bearer "+encode);
        final Map<String, Object> map = jwtService.decodeAndVerify(encode);
        System.out.println("");
    }
}
