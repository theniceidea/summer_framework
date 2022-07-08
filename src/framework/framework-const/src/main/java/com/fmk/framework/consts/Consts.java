package com.fmk.framework.consts;

public class Consts {
    public static final String SERVICE_RESULT_CODE = "servcie-result-code";
    public static final String SERVICE_RESULT_MSG = "servcie-result-msg";
    public static final String SERVICE_TRANSACTION_ID = "service-transaction-id";
    public static final String SERVICE_REQUEST_ID = "service-request-id";
    public static final String SERVICE_REQUEST_NUMBER = "service-request-number";
    public static final String SERVICE_USER_ID = "service-user-id";
    public static final String SERVICE_TRANSACTION_ENTITY = "service-transaction-entity-";
    public static final String SERVICE_TRANSACTION_ENTITY_KEYS = "service-transaction-entity-keys-";
    public static final String SERVICE_IS_MICRO_SERVICE_CALL = "service-is-micro-service-call";
    public static final String SERVICE_RESTTEMPLATE_CLIENT = "service-resttemplate-client";

    public static final String REQUEST_URL_PREFIX_CMS = "/cms/";
    public static final String REQUEST_URL_PREFIX_PORTAL = "/portal/";

//    public static final String REQUEST_CURRENT_USER_ID_KEY = "current-user-id";
    public static final String REQUEST_CURRENT_SESSION_ID = "current-session-id";
    public static final String REQUEST_SKIP_RESULT_WRAP = "skip-result-wrap";
    public static final String REQUEST_REQUEST_VERSION = "request-version";
    public static final String REQUEST_REQUEST_GRAY = "request-gray";

//    public static final String REQUEST_HEADER_login_sms_NOTIFY_KEY = "notify-login-sms-key";
//    public static final String REQUEST_HEADER_login_pic_NOTIFY_KEY = "notify-login-pic-key";
//    public static final String REQUEST_HEADER_reg_sms_NOTIFY_KEY = "notify-reg-sms-key";
//    public static final String REQUEST_HEADER_reg_pic_NOTIFY_KEY = "notify-reg-pic-key";
    public static final String REQUEST_HEADER_VERIFY_KEY = "b9b-Verify-Key";
    public static final String REQUEST_HEADER_B = "b9b-Verify-Key";


    public static final String SESSION_ID_COOKIE_KEY = "b9b-session-id";

    public static final String REDIS_PREFIX = "b9b-";
    public static final String REDIS_TRANSACTION_ID = "b9b-transaction-";
    public static final String REDIS_TRANSACTION_ITEM = "b9b-transaction-item-";
    public static final String REDIS_VERIFY_CODE_PREFIX = "b9b-verifycode-";
    public static final String REDIS_SESSION_KEY = "b9b-session-key-";
    public static final String REDIS_CURRENT_USER = "b9b-current-user-";
    public static final String REDIS_VERIFY_KEY_KEY = "b9b-verify-key-";
    public static final String REDIS_MOBILE_VERIFY_SEND_TIME_KEY = "b9b-mobile-verify-send-time-key-";
    public static final String REDIS_ROLES_KEY = "b9b-roles-key";
    public static final String REDIS_REFRESH_TOKEN_KEY = "b9b-refresh-token-key-";
    public static final String REDIS_ORDER_NUMBER_KEY = "b9b-order-number-key-";
    public static final String REDIS_SENTINEL_DS_KEY = "b9b-sentinel-ds-key-";
    public static final String REDIS_APP_KEY_SECRET_KEY = "b9b-app-key-secret-key";
    public static final String REDIS_REGISTER_VERIFY_KEY = "b9b-register-verify-key-";
    public static final String REDIS_JWT_BLACK_LIST_KEY = "b9b-jwt-black-list-key";
    public static final String REDIS_USER_BLACK_LIST_KEY = "b9b-user-black-list-key";
    public static final String REDIS_VERIFY_BIND_MOBILE = "b9b-verify-bind-mobile-";
    public static final String REDIS_VERIFY_EMAIL_CODE = "b9b-verify-email-code-";


    public static final String SYSTEM_PKG_COM_FMK_PREFIX = "com.fmk";
    public static String SYSTEM_COMP_NAME = "testcomp";
    public static String SYSTEM_PKG_COM_COMP_PREFIX = "com."+SYSTEM_COMP_NAME;
    public static String SYSTEM_PKG_COM_COMP_BIZ_PREFIX = "com."+SYSTEM_COMP_NAME+".biz";
    public static String SYSTEM_PKG_COMP_CONTROLLER = "com."+SYSTEM_COMP_NAME+".controller";
    public static final String SYSTEM_DOT_SEPARATOR = ".";

    public static final String FEIGN_CLIENT_CLASS_NAME = "org.springframework.cloud.openfeign.FeignClient";
    public static final String PRE_AUTHORIZE_CLASS_NAME = "org.springframework.security.access.prepost.PreAuthorize";

    public static final String USER_PROPERTY_ADMIN = "admin";
    public static final String USER_PROPERTY_ID = "userid";
    public static final String USER_PROPERTY_USER_TYPE = "usertype";
    public static final String USER_PROPERTY_LOGIN_ID = "loginid";
    public static final String USER_PROPERTY_FIRST_NAME = "firstname";
    public static final String USER_PROPERTY_LAST_NAME = "lastname";
    public static final String USER_PROPERTY_FULL_NAME  = "fullname";
    public static final String USER_PROPERTY_NICK_NAME = "nickname";
    public static final String USER_PROPERTY_MOBILE = "mobile";
    public static final String USER_PROPERTY_AREA_CODE = "areacode";
    public static final String USER_PROPERTY_EMAIL = "email";
    public static final String USER_PROPERTY_RANK = "rank";
    public static final String USER_PROPERTY_AVATAR = "avatar";
    public static final String USER_PROPERTY_LOGIN_TIME = "logintime";
    public static final String USER_PROPERTY_AGREE_TO_TERMS = "agreetoterms";
    public static final String USER_PROPERTY_ROLE = "roleid";
    public static final String USER_PROPERTY_EXP = "exp";
    public static final String USER_ROLE_RIGHTS = "rights";

    public static final String API_COMMON_PARAM_APPKEY = "appkey";
    public static final String API_COMMON_PARAM_FORMAT = "format";
    public static final String API_COMMON_PARAM_LOCALE= "locale";
    public static final String API_COMMON_PARAM_SIGN = "sign";

//    public static final String SERVICE_ROOT_REQUEST_ID = "service-root-request-id";
//    public static final String SERVICE_ROOT_REQUEST_INDEX = "service-root-request-index";
}
