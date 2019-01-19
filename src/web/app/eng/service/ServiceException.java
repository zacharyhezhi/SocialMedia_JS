package web.app.eng.service;

public class ServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}
	
	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
