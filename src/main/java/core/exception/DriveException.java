package core.exception;

public class DriveException extends Exception {

	private static final long serialVersionUID = 1L;
	private ErrorType erorType;

	public DriveException(ErrorType erorType) {
		this.erorType = erorType;
	}

	public DriveException(String message, ErrorType erorType) {
		super(message);
		this.erorType = erorType;
	}

	public DriveException(String message, Throwable exception, ErrorType erorType) {
		super(message, exception);
		this.erorType = erorType;
	}

	public DriveException(Throwable exception, ErrorType erorType) {
		super(exception);
		this.erorType = erorType;
	}

	public DriveException(Throwable exception) {
		super(exception);
	}

	public ErrorType getErorType() {
		return erorType;
	}
}
