package db;


public class WrongInputException extends Exception 
{
	String errorMessage = "";
	public WrongInputException(Exception e, String expl)
	{
		super(expl, e);
		errorMessage = expl;
	}
	
	public String getErrorMessage()
	{
		return errorMessage;
	}
}