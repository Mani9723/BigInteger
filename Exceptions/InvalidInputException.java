package RevisedBigInt.Exceptions;

/**
 * Thrown if the user enter non numerical data
 */
public class InvalidInputException extends Exception
{
	/**
	 * Default
	 */
	public InvalidInputException() {
		super();
	}

	/**
	 * User Message
	 * @param message - Message
	 */
	public InvalidInputException(String message) {
		super(message);
	}
}
