package com.gamezone.common.error;

import org.springframework.stereotype.Component;

@Component
public class GenericError {
	private Long errCode;
	private String errMsg;
	
	public Long getErrCode() {
		return errCode;
	}
	public void setErrCode(Long errCode) {
		this.errCode = errCode;
	}
	public String getErrMsg() {
		return errMsg;
	}
	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
}
