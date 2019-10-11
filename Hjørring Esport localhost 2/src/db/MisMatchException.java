package db;

public class MisMatchException extends Exception
{	
	String errorMessage = "";
	public MisMatchException(Exception e, String expl)
	{
		super(expl, e);
		errorMessage = expl;
	}
	
	public String getErrorMessage()
	{	
		return errorMessage;
	}
}