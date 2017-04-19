package com.gamezone.common.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import com.gamezone.common.error.GenericError;

public class Response {

	@JsonInclude(Include.NON_NULL)
	Object data;
	
	@JsonInclude(Include.NON_NULL)
	GenericError error;
	
	public Response(Object data){
		this.data = data;
	}
	
	public Response(GenericError error){
		this.error = error;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public GenericError getError() {
		return error;
	}
	public void setError(GenericError error) {
		this.error = error;
	}
}
