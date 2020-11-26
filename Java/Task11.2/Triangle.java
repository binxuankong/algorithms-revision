// Representation of a triangle.
public class Triangle
{
  // The three points of a triangle.
  private final Point pointA, pointB, pointC;

  // Construct a triangle -- given the three points.
  public Triangle(Point requiredPointA, Point requiredPointB, Point requiredPointC)
  {
    pointA = requiredPointA;
    pointB = requiredPointB;
    pointC = requiredPointC;
  } // Triangle

  // Return the area of the triangle.
  public double area() {
    double lengthA = pointA.distanceFromPoint(pointB);
    double lengthB = pointA.distanceFromPoint(pointC);
    double lengthC = pointB.distanceFromPoint(pointC);
    double semiPerimeter = (lengthA + lengthB + lengthC) / 2;
    return Math.sqrt(semiPerimeter * (semiPerimeter - lengthA)
                     * (semiPerimeter - lengthB) * (semiPerimeter - lengthC));
  }

  // Return the perimeter of the triangle.
  public double perimeter() {
    double lengthA = pointA.distanceFromPoint(pointB);
    double lengthB = pointA.distanceFromPoint(pointC);
    double lengthC = pointB.distanceFromPoint(pointC);
    return lengthA + lengthB + lengthC;
  }

  // Return the new shifted triangle.
  public Triangle shift(double xShift, double yShift) {
    double newPointAX = pointA.getX() + xShift;
    double newPointAY = pointA.getY() + yShift;
    double newPointBX = pointB.getX() + xShift;
    double newPointBY = pointB.getY() + yShift;
    double newPointCX = pointC.getX() + xShift;
    double newPointCY = pointC.getY() + yShift;
    Point shiftedPointA = new Point(newPointAX, newPointAY);
    Point shiftedPointB = new Point(newPointBX, newPointBY);
    Point shiftedPointC = new Point(newPointCX, newPointCY);
    Triangle shiftedTriangle = new Triangle(shiftedPointA, shiftedPointB,
                                            shiftedPointC);
    return shiftedTriangle;
  } // shift

  // Return the (pointA, pointB, pointC) representation of the triangle.
  public String toString()
  {
    return "Triangle(" + pointA + ", " + pointB + ", " + pointC + ")";
  } // toString
  

} // class Triangle
