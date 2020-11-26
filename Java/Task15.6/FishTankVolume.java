// Gets the width, depth and height of a rectangular box-shaped fish tank
// from the first three arguments, and report the volume of the fish tank.
// Gives an error message if width, depth or height is not a valid number.

public class FishTankVolume
{
  public static void main(String[] args)
  {
    try
    {
      int width = Integer.parseInt(args[0]);
      int depth = Integer.parseInt(args[1]);
      int height = Integer.parseInt(args[2]);
      if (args.length > 3)
        throw new ArrayIndexOutOfBoundsException
                    ("You have supplied " + args.length + " arguments!");
      if (width < 0)
        throw new NumberFormatException
                    ("The width of " + width + " is negative!");
      if (depth < 0)
        throw new NumberFormatException
                    ("The depth of " + depth + " is negative!");
      if (height < 0)
        throw new NumberFormatException
                    ("The height of " + height + " is negative!");
      int volume = width * depth * height;
      System.out.println("The volume of a tank with dimensions "
                       + "(" + width + "," + depth + "," + height + ") "
                       + "is " + volume);
    } // try
    catch (ArrayIndexOutOfBoundsException exception)
    {
      System.out.println("Please supply only the width, depth and height, and "
                         + "nothing else.");
      System.out.println("Exception message was: '"
                         + exception.getMessage() + "'");
      System.err.println(exception);
    } // catch
    catch (NumberFormatException exception)
    {
      System.out.println("The width, depth and height of the fish tank "
                         + "must be a non-negative whole number.");
      System.out.println("Exception message was: '"
                         + exception.getMessage() + "'");
      System.err.println(exception);
    } // catch
    catch (Exception exception)
    {
      System.out.println("Something unforeseen has happened. :-(");
      System.out.println("Exception message was: '"
                         + exception.getMessage() + "'");
      System.err.println(exception);
    } // catch
  } // main

} // class FishTankVolume
