package com.fmk.framework.web;

import cn.hutool.core.convert.Convert;
import com.fmk.framework.exception.ErrMsgs;
import com.fmk.framework.exception.Excep;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.validation.Precondition;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import net.minidev.json.JSONObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

public class JwtService {
    private static Logger logger = Logger.getLogger(JwtService.class);

    public static final String EXP = "exp";
//    private static final byte[] secret = "abf99c37da3f46219271f631c1e1b762815cd36ca40b4886972fbfc8c83f3a2e".getBytes();

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    public String encode(String content) {

        try {
            //3.先建立一个头部Header
            /**
             * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
             * 一般只需要传入加密算法法则就可以。
             * 这里则采用HS256
             *
             * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
             */
            JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

            //建立一个载荷Payload
            Payload payload = new Payload(content);

            //将头部和载荷结合在一起
            JWSObject jwsObject = new JWSObject(jwsHeader, payload);

            //建立一个密匙

            JWSSigner jwsSigner = new MACSigner(jwtSecret);

            //签名
            jwsObject.sign(jwsSigner);

            //生成token
            return jwsObject.serialize();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw Excep.le(ErrMsgs.Err_000000000010);
        }
    }

    public Map<String, Object> decode(String token) {

        try {
            JWSObject jwsObject = JWSObject.parse(token);
            Payload payload = jwsObject.getPayload();
            JWSVerifier jwsVerifier = new MACVerifier(jwtSecret);
            if (jwsObject.verify(jwsVerifier)) {
                return payload.toJSONObject();
            }
            return new JSONObject();
        }catch (Exception e){
            logger.error(e.getMessage(), e);
            throw Excep.le(ErrMsgs.Err_000000000010);
        }
    }
    public Map<String, Object> decodeAndVerify(String token) {
        JSONObject claims = (JSONObject) decode(token);

        Precondition.checkState(claims.containsKey(EXP), this.getClass(), ErrMsgs.Err_000000000010);

        long exptime =Convert.toLong(claims.get("exp"));
        long curtime = DateTime.now().getMillis()/1000;

        Precondition.checkState(exptime>curtime, this.getClass(), ErrMsgs.Err_000000000010);

        return claims;
    }
}
