//package com.fmk.framework.web;
//
//import cn.hutool.core.codec.Base64;
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.fmk.framework.exception.ErrMsgs;
//import com.fmk.framework.validation.Precondition;
//import org.joda.time.DateTime;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.Resource;
//import org.springframework.security.jwt.Jwt;
//import org.springframework.security.jwt.JwtHelper;
//import org.springframework.security.jwt.crypto.sign.RsaSigner;
//import org.springframework.security.jwt.crypto.sign.RsaVerifier;
//import org.springframework.security.jwt.crypto.sign.SignatureVerifier;
//import org.springframework.security.jwt.crypto.sign.Signer;
//import org.springframework.util.Assert;
//
//import java.security.KeyPair;
//import java.security.PrivateKey;
//import java.security.interfaces.RSAPrivateKey;
//import java.security.interfaces.RSAPublicKey;
//import java.util.Map;
//
//public class JwtServiceOld {
//
//    public static final String EXP = "exp";
//
//    @Value("${jwt.certificate.store.file}")
//    private Resource keystore;
//
//    @Value("${jwt.certificate.store.password}")
//    private String keystorePassword;
//
//    @Value("${jwt.certificate.key.alias}")
//    private String keyAlias;
//
//    @Value("${jwt.certificate.key.password}")
//    private String keyPassword;
//
//    private String verifierKey;
//
//    private Signer signer;
//
//    private SignatureVerifier verifier;
//
//    private void initKeyPair() {
//        if(null != verifierKey) { return; }
//
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(keystore, keystorePassword.toCharArray());
//        KeyPair keyPair = keyStoreKeyFactory.getKeyPair(keyAlias, keyPassword.toCharArray());
//
//        PrivateKey privateKey = keyPair.getPrivate();
//        Assert.state(privateKey instanceof RSAPrivateKey, "KeyPair must be an RSA ");
//        signer = new RsaSigner((RSAPrivateKey) privateKey);
//        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
//        verifier = new RsaVerifier(publicKey);
//        verifierKey = "-----BEGIN PUBLIC KEY-----\n" + Base64.encode(publicKey.getEncoded())
//            + "\n-----END PUBLIC KEY-----";
//    }
//    public String encode(String content) {
//        initKeyPair();
//        String token = JwtHelper.encode(content, signer).getEncoded();
//        return token;
//    }
//
//    public Map<String, Object> decode(String token) {
//        initKeyPair();
//        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
//        String claimsStr = jwt.getClaims();
//        Map<String, Object> claims = JSON.parseObject(claimsStr);
//        if (claims.containsKey(EXP) && claims.get(EXP) instanceof Integer) {
//            Integer intValue = (Integer) claims.get(EXP);
//            claims.put(EXP, new Long(intValue));
//        }
//        return claims;
//    }
//    public Map<String, Object> decodeAndVerify(String token) {
//        initKeyPair();
//        Jwt jwt = JwtHelper.decodeAndVerify(token, verifier);
//        String claimsStr = jwt.getClaims();
//        JSONObject claims = JSON.parseObject(claimsStr);
//
//        Precondition.checkState(claims.containsKey(EXP), this.getClass(), ErrMsgs.Err_000000000010);
//
//        if (claims.get(EXP) instanceof Integer) {
//            Integer intValue = (Integer) claims.get(EXP);
//            claims.put(EXP, new Long(intValue));
//        }
//        long exptime = claims.getLongValue(EXP);
//        long curtime = DateTime.now().getMillis()/1000;
//
//        Precondition.checkState(exptime>curtime, this.getClass(), ErrMsgs.Err_000000000010);
//
//        return claims;
//    }
//}
