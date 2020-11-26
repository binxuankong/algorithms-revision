// Three Objects grouped into a triple.
public class Triple<FirstType, SecondType, ThirdType>
{
  // The first object.
  private final FirstType first;

  // The second object.
  private final SecondType second;

  // The third object,
  private final ThirdType third;

  // Constructor is given the three objects.
  public Triple(FirstType requiredFirst, SecondType requiredSecond,
                ThirdType requiredThird)
  {
    first = requiredFirst;
    second = requiredSecond;
    third = requiredThird;
  } // Triple

  // Return the first object.
  public FirstType getFirst()
  {
    return first;
  } // getFirst

  // Return the second object.
  public SecondType getSecond()
  {
    return second;
  } // getSecond

  // Return the third object.
  public ThirdType getThird()
  {
    return third;
  } // getThird

} // class Triple
