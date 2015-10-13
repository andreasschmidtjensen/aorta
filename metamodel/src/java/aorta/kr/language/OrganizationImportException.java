package aorta.kr.language;

public class OrganizationImportException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrganizationImportException() {
		super();
	}

	public OrganizationImportException(String arg0, Throwable arg1,
			boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public OrganizationImportException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public OrganizationImportException(String arg0) {
		super(arg0);
	}

	public OrganizationImportException(Throwable arg0) {
		super(arg0);
	}

}
