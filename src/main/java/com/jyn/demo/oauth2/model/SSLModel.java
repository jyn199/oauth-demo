package com.jyn.demo.oauth2.model;


public class SSLModel {
	private String keystoreFileName;
	
	private String keystorePassword;
	
	private String supportedCipherSuites;

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
	
}
