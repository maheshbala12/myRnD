package com.mydemo.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Channel {
	private String channelEndpoint;
	private String clientId;
	private String clientSecret;
	private String userId;
	private String password;
	private String type;
	private String processingType;
	private String debugMode;
	
	@XmlAttribute
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	@XmlAttribute
	public String getProcessingType() {
		return processingType;
	}
	
	public void setProcessingType(String processingType) {
		this.processingType = processingType;
	}

	@XmlAttribute
	public String getDebugMode() {
		return debugMode;
	}

	public void setDebugMode(String debug_mode) {
		this.debugMode = debug_mode;
	}
	
	@XmlElement	
	public String getChannelEndpoint() {
		return channelEndpoint;
	}
	public void setChannelEndpoint(String channelEndpoint) {
		this.channelEndpoint = channelEndpoint;
	}

	@XmlElement	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	@XmlElement	
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	@XmlElement	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@XmlElement	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj==null || !(this.getClass().getName().equals(obj.getClass().getName())) )
			return false;
		Channel channelObj = (Channel) obj;
		return getType().equals(channelObj.getType());
	}
}
