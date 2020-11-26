// Takes an array of ints and find the maximum integer in the array, the
// minimum, and also the mean of all the values.
public class IntArrayStats
{
  // Find the maximum integer in an array, the minimum, and the mean of all
  // the values and returns them as a Triple.
  // Throw IllegalArgumentException if array is null or empty.
  public static Triple<Integer, Integer, Double> getStats(int[] array)
                throws IllegalArgumentException
  {
    if (array == null || array.length == 0)
      throw new IllegalArgumentException("Array must exist and be non-empty");

    int max = array[0];
    int min = array[0];
    int sum = array[0];

    for (int index = 1; index < array.length; index++)
    {
      if (array[index] > max)
        max = array[index];
      if (array[index] < min)
        min = array[index];
      sum += array[index];
    } // for

    double mean = (double) sum / array.length;

    return new Triple<Integer, Integer, Double>(new Integer(max),
                                                new Integer(min),
                                                new Double(mean));
  } // getStats

} // class IntArrayStats
