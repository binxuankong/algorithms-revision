import java.io.File;
import java.util.Scanner;

/* This program reads phone call details from a file and allows the user to
   see some of those calls with a total cost and duration.
 */
public class PhoneCalls
{
  // The PhoneCallList to be obtained.
  private static PhoneCallList callList;


  // The main method.
  public static void main(String[] args) throws Exception
  {
    callList = new PhoneCallList(new Scanner(new File(args[0])));
    Scanner inputScanner = new Scanner(System.in);
    String userInput;
    do
    {
      System.out.print("Enter phone number prefix, or Q to quit: ");
      userInput = inputScanner.nextLine();
      if (! userInput.equals("Q"))
        System.out.println(callList.matchingCallsReport(userInput));
    } while (! userInput.equals("Q"));
  } // main

} // class PhoneCalls
