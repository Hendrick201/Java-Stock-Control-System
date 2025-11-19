package exceptions;

public class InvalidNumberExceptions extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -580528578385033234L;

	public InvalidNumberExceptions()
	{
		super("Invalid number! Please type a valid number.");
	}

}
