package Exceptions;

/**
 * Thrown if a/0 is attempted
 */
public class DivideByZeroException extends Exception
{
	/**
	 * Default
	 */
	public DivideByZeroException()
	{
		super();
	}

	/**
	 * Custome Message
	 * @param message - String
	 */
	public DivideByZeroException(String message)
	{
		super(message);
	}
}
