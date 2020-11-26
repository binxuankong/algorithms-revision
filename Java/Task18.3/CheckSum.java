import java.io.IOException;

// Program wchich reads all the bytes from standard input and outputs a single
// number on standard output. The BSD check sum[10] algorithm is used for this
// program. For each byte in the input, the check sum computed so far is
// subjected to a rotate right, and then that byte is added to it.
public class CheckSum
{
  public static void main(String[] args)
  {
    // The check sum value so far.
    int checkSum = 0;

    try
    {
      int currentByte;
      while ((currentByte = System.in.read()) != -1)
      {
        // Rotate checkSum right by one bit, treating it as a 16 bit number.
        if (checkSum % 2 == 0)
          checkSum /= 2;
        else
        {
          checkSum /= 2;
          checkSum += 32768;
        } // else
        // Add the value of the current byte to check sum.
        checkSum += currentByte;
        // Restrict checkSum to 16 bits.
        if (checkSum >= 65536)
          checkSum -= 65536;
      } // while
    } // try
    catch (IOException exception)
    {
      System.err.println(exception);
    } // catch
    finally
    {
      try { System.in.close(); }
      catch (IOException exception)
        { System.err.println("Could not close input " + exception); }
    } // finally

    // Report results.
    System.out.println(checkSum);
  } // main

} // class CheckSum
