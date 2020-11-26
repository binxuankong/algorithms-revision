/**
 * This class converts between Roman Numbers and decimal numbers. It can be
 * used to convert an integer to its Roman equivalent string or the other way.
 *
 * @author Bin Xuan Kong
 */
public class RomanNumber
{
  // The string of the Roman number.
  private final String romanNumberString;

  /**
   * Construct a Roman number.
   * It can only consists of the characters 'M', 'D', 'C', 'L', 'X', 'V'
   * and 'I'.
   *
   * @param requiredString The string of the Roman number.
  */
  public RomanNumber(String requiredString)
  {
    romanNumberString = requiredString;
  } // RomanNumber

  /**
   * Convert a Roman number string to an integer.
   *
   * @param romanDigits The string of Roman number to be converted.
   * @return The integer value of Roman number.
  */
  public int convertStringToInt(String romanDigits)
  {
    int value = romanCharToInt(romanDigits.charAt(0));
    for (int index = 1; index < romanDigits.length(); index++)
      if (romanCharToInt(romanDigits.charAt(index))
         <= romanCharToInt(romanDigits.charAt(index - 1)))
        value += romanCharToInt(romanDigits.charAt(index));
      else
        value += -2 * romanCharToInt(romanDigits.charAt(index - 1))
                  + romanCharToInt(romanDigits.charAt(index));
    return value;
  } // convertStringToInt

  /**
   * Convert a single character to its value.
   *
   * @param aChar The character to be converted.
   * @return The integer value of character.
  */
  public int romanCharToInt(char aChar)
  {
    switch (aChar)
    {
      case 'I': return 1;
      case 'V': return 5;
      case 'X': return 10;
      case 'L': return 50;
      case 'C': return 100;
      case 'D': return 500;
      case 'M': return 1000;
      default: return 0;
    }
  } // romanCharToInt

  /**
   * Convert an integer to a Roman number string.
   *
   * @param integer The integer to be converted.
   * @return The Roman number string of integer.
  */
  public String convertIntToString(int integer)
  {
    String result = "";
    while (integer >= 1000)
    {
      result += "M";
      integer -= 1000;
    }
    while (integer >= 900)
    {
      result += "CM";
      integer -= 900;
    }
    while (integer >= 500)
    {
      result += "D";
      integer -= 500;
    }
    while (integer >= 400)
    {
      result += "CD";
      integer -= 400;
    }
    while (integer >= 100)
    {
      result += "C";
      integer -= 100;
    }
    while (integer >= 90)
    {
      result += "XC";
      integer -= 90;
    }
    while (integer >= 50)
    {
      result += "L";
      integer -= 50;
    }
    while (integer >= 40)
    {
      result += "XL";
      integer -= 40;
    }
    while (integer >= 10)
    {
      result += "X";
      integer -= 10;
    }
    while (integer >= 9)
    {
      result += "IX";
      integer -= 9;
    }
    while (integer >= 5)
    {
      result += "V";
      integer -= 5;
    }
    while (integer >= 4)
    {
      result += "IV";
      integer -= 4;
    }
    while (integer >= 1)
    {
      result += "I";
      integer -= 1;
    }
    return result;
  } // convertIntToString

} // class RomanNumber

