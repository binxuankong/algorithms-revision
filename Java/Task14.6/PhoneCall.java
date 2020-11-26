// Representation of a single phone call
// comprising phone number, duration and cost.
public class PhoneCall
{
  // The phone number.
  private final String phoneNumber;

  // The duration of the call, in the format hh:mm:ss.
  private final Duration callDuration;

  // The cost of the call, in pounds, as a decimal number.
  private final double callCost;

  // The constructor method.
  public PhoneCall(String requiredPhoneNumber, Duration requiredCallDuration,
                   double requiredCallCost)
  {
    phoneNumber = requiredPhoneNumber;
    callDuration = requiredCallDuration;
    callCost = requiredCallCost;
  } // PhoneCall

  // Get the phone number.
  public String getNumber()
  {
    return phoneNumber;
  } // getNumber

  // Get the call duration.
  public Duration getDuration()
  {
    return callDuration;
  } // getDuration

  // Get the call cost.
  public double getCost()
  {
    return callCost;
  } // getCost

  // Return a string representation.
  public String toString()
  {
    return String.format("%-15s %-15s %.2f%n",
                         phoneNumber, callDuration, callCost);
  } // toString

} // class PhoneCall
