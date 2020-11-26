// Representation of a point.
public class Point
{
  // Instance variables: x and y of a point.
  private final double x, y;

  // Construct a point -- given the required x and y.
  public Point(double requiredX, double requiredY)
  {
    x = requiredX;
    y = requiredY;
  } // Point

  // Return the x value of the point.
  public double getX()
  {
    return x;
  } // getX

  // Return the y value of the point.
  public double getY()
  {
    return y;
  } // getY

  // Return the distance between two points.
  public double distanceFromPoint(Point otherPoint)
  {
    double xDistance = x - otherPoint.x;
    double yDistance = y - otherPoint.y;
    return Math.sqrt(xDistance * xDistance + yDistance * yDistance);
  } // distanceFromPoint

  // Return the (x, y) representation of the point.
  public String toString()
  {
    return "(" + x + ", " + y + ")";
  } // toString

} // class Point
