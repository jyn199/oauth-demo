package com.jyn.demo.oauth2.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Oauth2Model {
	@Value("${AppID}")
	private String AppID;
	
	@Value("${AppSecret}")
	private String AppSecret;

	@Value("${keystoreFileName}")
	private String keystoreFileName;
	
	@Value("${keystorePassword}")
	private String keystorePassword;
	
	@Value("${supportedCipherSuites}")
	private String supportedCipherSuites;
	
	@Value("${accessTokenUrl}")
	private String accessTokenUrl;
	
	@Value("${accessTokenGrantType}")
	private String accessTokenGrantType;
	
	@Value("${userSummaryUrl}")
	private String userSummaryUrl;
	
	@Value("${kfAddUrl}")
	private String kfAddUrl;
	
	@Value("${userListUrl}")
	private String userListUrl;
	
	public String getUserListUrl() {
		return userListUrl;
	}

	public void setUserListUrl(String userListUrl) {
		this.userListUrl = userListUrl;
	}

	public String getKfAddUrl() {
		return kfAddUrl;
	}

	public void setKfAddUrl(String kfAddUrl) {
		this.kfAddUrl = kfAddUrl;
	}

	public String getUserSummaryUrl() {
		return userSummaryUrl;
	}

	public void setUserSummaryUrl(String userSummaryUrl) {
		this.userSummaryUrl = userSummaryUrl;
	}

	private String accessToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getKeystoreFileName() {
		return keystoreFileName;
	}

	public void setKeystoreFileName(String keystoreFileName) {
		this.keystoreFileName = keystoreFileName;
	}

	public String getKeystorePassword() {
		return keystorePassword;
	}

	public void setKeystorePassword(String keystorePassword) {
		this.keystorePassword = keystorePassword;
	}

	public String getSupportedCipherSuites() {
		return supportedCipherSuites;
	}

	public void setSupportedCipherSuites(String supportedCipherSuites) {
		this.supportedCipherSuites = supportedCipherSuites;
	}

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}

	public String getAccessTokenGrantType() {
		return accessTokenGrantType;
	}

	public void setAccessTokenGrantType(String accessTokenGrantType) {
		this.accessTokenGrantType = accessTokenGrantType;
	}
	
	public String getAppID() {
		return AppID;
	}

	public void setAppID(String appID) {
		AppID = appID;
	}

	public String getAppSecret() {
		return AppSecret;
	}

	public void setAppSecret(String appSecret) {
		AppSecret = appSecret;
	}
}
