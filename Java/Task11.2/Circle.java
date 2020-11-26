// Representation of a circle.
public class Circle
{
  // The centre and radius of a circle.
  private final Point centre;
  private final double radius;

  // Construct a circle -- given the required centre and radius.
  public Circle(Point requiredCentre, double requiredRadius)
  {
    centre = requiredCentre;
    radius = requiredRadius;
  } // Circle

  // Return the area of the circle.
  public double area() {
    return Math.PI * Math.pow(radius, 2);
  } // area

  // Return the perimeter of the circle.
  public double perimeter() {
    return 2 * Math.PI * radius;
  } // perimeter

  // Return the new shifted circle.
  public Circle shift(double xShift, double yShift) {
    double newPointX = centre.getX() + xShift;
    double newPointY = centre.getY() + yShift;
    Point shiftedPoint = new Point(newPointX, newPointY);
    Circle shiftedCircle = new Circle(shiftedPoint, radius);
    return shiftedCircle;
  } // shift

  // Return the (centre, radius) representation of the circle.
  public String toString()
  {
    return "Circle(" + centre + ", " + radius + ")";
  } // toString
  

} // class Circle
