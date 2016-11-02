package com.lumenvox.tts;

public class LVTTSClientException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int errorCode = -1;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public LVTTSClientException(String message) {
		super(message);
	}

	public LVTTSClientException(String message, Throwable throwable) {
		super(message, throwable);
	}

	public LVTTSClientException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public LVTTSClientException(int errorCode, String message, Throwable throwable) {
		super(message, throwable);
		this.errorCode = errorCode;
	}
}
