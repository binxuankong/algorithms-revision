// Program for solving number puzzles presented as command line arguments.
// (Warning: this program does not catch RuntimeExceptions.)
public class NumberPuzzle
{
  public static void main(String[] args)
  {
    // The last argument is the target.
    int target = Integer.parseInt(args[args.length - 1]);
    // And the preceding ones are the numbers to match the target.
    int[] puzzleNumbers = new int[args.length - 1];
    for (int index = 0; index < puzzleNumbers.length; index++)
      puzzleNumbers[index] = Integer.parseInt(args[index]);

    // Now create a NumberePuzzle with those numbers.
    NumberPuzzle puzzle = new NumberPuzzle(puzzleNumbers, target);
    // And solve it.
    System.out.println(puzzle.solve());
  } // main


  // The list of numbers is supplied as an array of int.
  private final int[] puzzleNumbers;

  // The required target.
  private final int target;


  // Constructor is given puzzle numbers and target.
  public NumberPuzzle(int[] requiredPuzzleNumbers, int requiredTarget)
  {
    puzzleNumbers = requiredPuzzleNumbers;
    target = requiredTarget;
  } // NumberPuzzle


  // Find a solution, if possible, and return it as a String.
  public String solve()
  {
    String solution = solveUpto(puzzleNumbers.length, target);
    if (solution == null) return "There is no solution";
    else                  return solution + " = " + target;
  } // solve


  // Find a solution, if possible, and return it as a String.
  // But only use the first noOfNumbersToUse puzzle numbers,
  // with the given target. If no solution then return null.
  private String solveUpto(int noOfNumbersToUse, int target)
  {
    // This means there were none to start with!
    if (noOfNumbersToUse < 1)
      return null;

    // Proper base case: only one number, which = or != the target.
    else if (noOfNumbersToUse == 1)
      if (puzzleNumbers[0] == target) return "" + target;
      else                            return null;

    // Recursive case: at least two numbers.
    else
    {
      // We shall ignore the last number and try to sub-puzzles.
      int numberToIgnore = puzzleNumbers[noOfNumbersToUse - 1];

      // Try an ADD before the ignored number.
      String subPuzzleResult
        = solveUpto(noOfNumbersToUse - 1, target - numberToIgnore);
      if (subPuzzleResult != null) // Success!
        return subPuzzleResult + " + " + numberToIgnore;

      // Try a SUBTRACT before the ignored number.
      subPuzzleResult
        = solveUpto(noOfNumbersToUse - 1, target + numberToIgnore);
      if (subPuzzleResult != null) // Success!
        return subPuzzleResult + " - " + numberToIgnore;

      // Try a MULTIPLY before the ignored number.
      if (target % numberToIgnore == 0)
      {
        subPuzzleResult
          = solveUpto(noOfNumbersToUse - 1, target / numberToIgnore);
        if (subPuzzleResult != null) // Success!
          return subPuzzleResult + " * " + numberToIgnore;
      } // if

      // Try a DIVIDE before the ignored number.
      subPuzzleResult
        = solveUpto(noOfNumbersToUse - 1, target * numberToIgnore);
      if (subPuzzleResult != null) // Success!
        return subPuzzleResult + " / " + numberToIgnore;

      // All four sub-puzzles failed.
      return null;
    } // else
  } // solveUpto

} // class NumberPuzzle
