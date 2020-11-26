// Program to measure how much the mean of the integer command line arguments
// differs from the average of their minimum and maximum.
// (Warning: this program does not catch RunTimeExceptions.)
public class MeanMinMaxMinusMean
{
  public static void main(String[] args) throws RuntimeException
  {
    int[] array = new int[args.length];
    for (int index = 0; index < args.length; index++)
      array[index] = Integer.parseInt(args[index]);

    Triple<Integer, Integer, Double> stats = IntArrayStats.getStats(array);
    int max = stats.getFirst();
    int min = stats.getSecond();
    double mean = stats.getThird();
    System.out.println((min + max) / 2.0 - mean);
  } // main

} // class MeanMinMaxMinusMean
