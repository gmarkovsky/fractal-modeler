package com.gmail.gbmekp.fm.common;

public class LSystemLoadException extends Exception {
	private static final long serialVersionUID = -930585916772932400L;
	
	private short code;

	public LSystemLoadException(short code) {
		super();
		this.code = code;
	}

	public short getCode() {
		return code;
	}
}
