// Representation of a Rectangle.
public class Rectangle
{
  // The four points of a rectangle.
  private final Point pointA, pointB, pointC, pointD;

  // Construct a rectangle -- given the two opposite points.
  public Rectangle(Point requiredPoint, Point requiredOppositePoint)
  {
    pointA = requiredPoint;
    pointB = new Point(requiredOppositePoint.getX(), requiredPoint.getY());
    pointC = requiredOppositePoint;
    pointD = new Point(requiredPoint.getX(), requiredOppositePoint.getY());
  } // Rectangle

  // Return the area of the rectangle.
  public double area() {
    double lengthA = pointA.distanceFromPoint(pointB);
    double lengthB = pointB.distanceFromPoint(pointC);
    return lengthA * lengthB;
  }

  // Return the perimeter of the rectangle.
  public double perimeter() {
    double lengthA = pointA.distanceFromPoint(pointB);
    double lengthB = pointB.distanceFromPoint(pointC);
    return 2 * lengthA + 2 * lengthB;
  }

  // Return the new shifted rectangle.
  public Rectangle shift(double xShift, double yShift) {
    double newPointAX = pointA.getX() + xShift;
    double newPointAY = pointA.getY() + yShift;
    double newPointCX = pointC.getX() + xShift;
    double newPointCY = pointC.getY() + yShift;
    Point shiftedPointA = new Point(newPointAX, newPointAY);
    Point shiftedPointC = new Point(newPointCX, newPointCY);
    Rectangle shiftedRectangle = new Rectangle(shiftedPointA, shiftedPointC);
    return shiftedRectangle;
  } // shift

  // Return the (pointA, pointB, pointC, pointD) representation of the rectangle.
  public String toString()
  {
    return "Rectangle(" + pointA + ", " + pointB + ", " + pointC + ", "
           + pointD + ")";
  } // toString
  

} // class Rectangle
