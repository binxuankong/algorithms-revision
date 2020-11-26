// Program which outputs all the permutations of a string given as a
// command line argument.
// (Warning: this program does not catch RuntimeExceptions.)
public class Anagrams
{
  // The argument, stored as characters for easy processing.
  private static char[] inputChars;

  // An array to build the current permutation.
  private static char[] permutation;

  // An array to record whether characters from the given string have
  // been used so far in the permutation being constructed.
  private static boolean[] charsUsed;

  public static void main(String[] args)
  {
    inputChars = args[0].toCharArray();
    permutation = new char[inputChars.length];
    charsUsed = new boolean[inputChars.length];
    for (int index = 0; index < inputChars.length; index++)
      charsUsed[index] = false;
    printPermutations(0);
  } // main


  // Generate all permutations of the string stored in inputChars
  // from indices currentIndex to inputChars.length - 1.
  private static void printPermutations(int currentIndex)
  {
    // Reached the end of the array, print the string.
    if (currentIndex >= inputChars.length)
    {
      System.out.println(permutation);
    } // if
    else
    {
      for (int index = 0; index < inputChars.length; index++)
      {
        if (!charsUsed[index])
        {
          charsUsed[index] = true;
          permutation[index] = inputChars[currentIndex];
          printPermutations(currentIndex + 1);
          charsUsed[index] = false;
        } // if
      } // for
    } // else
  } // printPermutations

} // class Anagrams
