import java.lang.IllegalArgumentException;

public class ErrorArgsException extends IllegalArgumentException {
	public ErrorArgsException() {}
	public ErrorArgsException(String message) {
		super(message);
	}
}