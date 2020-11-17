package com.sinosoft.surrender.common.exception;

public class LockFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public LockFailedException() {
		super();
	}

	public LockFailedException(String msg) {
		super(msg);
	}

}
