package com.fmk.framework.basic;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import com.google.common.collect.Lists;
import jodd.util.StringUtil;

import java.util.*;

/**
 * @author larry
 * @date 2019/1/22
 */
public class ApiSignUtil {
    private static final String SIGN_NAME = "sign";

    /**
     * 验签
     *
     * @param params 参数map
     * @param secret 密钥
     * @return 签名是否有效
     */
    public static boolean verify(Map<String, String> params, String secret) {
        if (Objects.isNull(params)) {
            return false;
        }

        String originSign = params.get(SIGN_NAME);
        if (!StringUtil.isNotBlank(originSign)) {
            return false;
        }

        return originSign.equals(sign(params, secret));
    }

    /**
     * 加签
     *
     * @param params 参数map
     * @param secret 密钥
     * @return 签名后字符串
     */
    public static String sign(Map<String, String> params, String secret) {
        String content = getSignContent(params, secret);

        return digester(content, DigestAlgorithm.SHA1);
    }

    private static String getSignContent(Map<String, String> params, String secret) {
        if (Objects.isNull(params)) {
            return null;
        }

        params.remove(SIGN_NAME);
        List<String> keys = Lists.newArrayList(params.keySet());
        Collections.sort(keys);

        StringBuilder content = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            content.append(key);
            content.append(value);
        }

        return secret + content.toString() + secret;
    }

    private static String digester(String content, DigestAlgorithm algorithm) {
        Digester sha1 = new Digester(algorithm);
        return sha1.digestHex(content).toUpperCase();
    }

//    public static void main(String[] args) {
//        Map<String, String> params = new HashMap<>();
//        params.put("appkey", "100001");
//        params.put("format", "json");
//        params.put("locale", "zh");
//        params.put("name", "larry");
//        params.put("age", "24");
//        params.put("sign", "530049DE00998A81A12DF8358CD25C049C451F21");
//
////        System.out.println(sign(params, "abcdef"));
//        System.out.println(verify(params,"abcdef"));
//    }
}
