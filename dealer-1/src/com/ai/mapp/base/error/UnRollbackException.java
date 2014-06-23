package com.ai.mapp.base.error;

/**
 * Service层公用的Exception.
 * 继承自Exception, 从由Spring管理事务的函数中明确不抛出时会触发事务回滚.
 * 默认非RuntimeException异常不触发回滚
 */
public class UnRollbackException extends Exception {

	private static final long serialVersionUID = 3583566093089790852L;

	public UnRollbackException() {
		super();
	}

	public UnRollbackException(String message) {
		super(message);
	}

	public UnRollbackException(Throwable cause) {
		super(cause);
	}

	public UnRollbackException(String message, Throwable cause) {
		super(message, cause);
	}
}
