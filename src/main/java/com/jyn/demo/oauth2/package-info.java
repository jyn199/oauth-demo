/**
 * OAUTH2的DEMO
 */
/**
 * @author yongnian.jiang
 *
 */
package com.jyn.demo.oauth2;

interface PkgConst{
	static final int SYSTEM_BUSY = -1;
	static final int SUCCESS = 0;
	static final int WRONG_APPSECRET = 40001;
	static final int ILLEGAL_CARDTYPE = 40002;
	static final int ILLEGAL_OPENID = 40003;
	static final int ILLEGAL_APPID = 40013;
	static final int ACCESS_TOKEN_TIMEOUT = 42001;
	static final int REFRESH_TOKEN_TIMEOUT = 42002;
	static final int OAUTH_CODE_TIMEOUT = 42003;
	static final int NEED_GET_METHOD = 43001;
	static final int NEED_POST_METHOD = 43002;
	static final int NEED_HTTPS_TYPE = 43003;
}