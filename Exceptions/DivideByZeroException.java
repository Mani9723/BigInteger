package RevisedBigInt.Exceptions;

public class DivideByZeroException extends Exception
{
	public DivideByZeroException()
	{
		super();
	}
	public DivideByZeroException(String message)
	{
		super(message);
	}
}
