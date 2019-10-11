package db;

public class NoConnectionException extends Exception 
{
	String errorMessage = "";
	public NoConnectionException(Exception e, String expl)
	{
		super(expl, e);
		errorMessage = expl;
	}
	public String getErrorMessage()
	{	
		return errorMessage;
	}
}
