/* A program that accepts a Roman number string from the first command line
   argument, convert it to an integer and the using a loop, print that number
   and the next 19 numbers, each with its Roman number equivalent, on the
   standard output. The program assumes that the argument is a legal Roman
   number.
 */
public class RomanNumberTest
{
  public static void main(String[] args)
  {
    RomanNumber romanDigits = new RomanNumber(args[0]);
    int firstInt = romanDigits.convertStringToInt(args[0]);
    for (int number = firstInt; number <= firstInt + 19; number++)
      System.out.println("Roman for " + number + " is "
                         + romanDigits.convertIntToString(number));
  } // main

} // class RomanNumberTest
