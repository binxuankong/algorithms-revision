// Exceptions to be used for the DeleteField program.
public class DeleteFieldException extends RuntimeException
{
  // Create DeleteFieldException with no message and no cause.
  public DeleteFieldException()
  {
    super();
  } // DeleteFieldException


  // Create DeleteFieldException with message but no cause.
  public DeleteFieldException(String message)
  {
    super(message);
  } // DeleteFieldException


  // Create DeleteFieldException with message and cause.
  public DeleteFieldException(String message, Throwable cause)
  {
    super(message, cause);
  } // DeleteFieldException


  // Create DeleteFieldException with no message but with cause.
  public DeleteFieldException(Throwable cause)
  {
    super(cause);
  } // DeleteFieldException


} // class DeleteFieldException
