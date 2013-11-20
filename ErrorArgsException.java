// Name: Yinzi Bao
// USC loginid: yinzibao@usc.edu
// CS 455 PA4
// Fall 2013

/**
	ErrorArgsException class

	author: yinzi bao

	This class extends IllegalArgumentException class to handle the errors
	in command line
*/
import java.lang.IllegalArgumentException;

public class ErrorArgsException extends IllegalArgumentException {
	public ErrorArgsException() {}
	public ErrorArgsException(String message) {
		super(message);
	}
}