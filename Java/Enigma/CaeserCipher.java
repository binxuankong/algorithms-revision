public class CaeserCipher
{
  private int cipherShift;

  public CaeserCipher(int requiredCipherShift)
  {
    cipherShift = (requiredCipherShift % 26 + 26) % 26;
  }

  public int getCipherShift()
  {
    return cipherShift;
  }

  public CaeserCipher incrementShift()
  {
    return new CaeserCipher(cipherShift + 1);
  }

  public CaeserCipher decrementShift()
  {
    return new CaeserCipher(cipherShift - 1);
  }

  public String translate(String message)
  {
    String result = "";
    for (int index = 0; index < message.length(); index++)
      result += translate(message.charAt(index));
    return result;
  }

  public char translate(char aChar)
  {
    if (aChar >= 'A' && aChar <= 'Z')
    {
      int letterNo = (int)aChar - (int)'A';
      letterNo = (letterNo + cipherShift) % 26;
      return (char) (letterNo + (int)'A');
    }
    else if (aChar >= 'a' && aChar <= 'z')
    {
      int letterNo = (int)aChar - (int)'a';
      letterNo = (letterNo + cipherShift) % 26;
      return (char) (letterNo + (int)'a');
    }
    else
      return aChar;
  }

}
