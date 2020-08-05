package Exceptions;

/**
 * Is called if the exponent is negative
 */
public class NegativeExponentException extends Exception
{
	/**
	 * Default
	 */
	public NegativeExponentException()
	{
		super();
	}

	/**
	 * User defined
	 * @param message - user message
	 */
	public NegativeExponentException(String message)
	{
		super(message);
	}
}
