package com.ai.mapp.base.error;

/**
 * Service层公用的Exception.
 * 继承自RuntimeException, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * 默认RuntimeException异常触发回滚
 */
public class RollbackException extends RuntimeException{

	private static final long serialVersionUID = 7347975721755492575L;
	
	public RollbackException() {
		super();
	}

	public RollbackException(String message) {
		super(message);
	}

	public RollbackException(Throwable cause) {
		super(cause);
	}

	public RollbackException(String message, Throwable cause) {
		super(message, cause);
	}

}
