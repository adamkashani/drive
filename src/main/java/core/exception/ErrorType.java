package core.exception;

public enum ErrorType {

	// user
	USER_NAME_EXISTS(403, "the user name you insert already exists", false),
	UNVALIDATED_EMAIL(403, "the email you insert unvalidated ", false),
	EMAIL_EXISTS(403, "the email you insert already exists", false),
	UNVALIDATED_PASSWORD(403, "the password you insert unvalidated ", false),
	SHORT_PASSWORD(403, "the password you insert short must at least four characters", false),
	SHORT_NAME(403, "the name you insert short must at least four characters", false),
	UNVALIDATED_NAME(403, "the name you insert unvalidated ", false),

	// token exists
	TOKEN_NOT_EXISTS(403, "the token not exists", true),

	// file
	FILE_NOT_EXISTS_ON_SET_FILES(403, "the file name you insert not exists", false);

	private final String errorClientMessage;
//	private final String getErrorServerMessage;
	private final int eror;
	private final boolean log;

	private ErrorType(int error, String errorClientMessage, boolean log) {
		this.eror = error;
		this.errorClientMessage = errorClientMessage;
		this.log = log;
	}

	public boolean isLog() {

		return log;
	}

	public String getErrorClientMessage() {
		return errorClientMessage;
	}

	public int getEror() {
		return eror;
	}

//	public String getErrorServerMessage() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
