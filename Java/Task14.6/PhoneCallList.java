import java.util.Scanner;

// Keeps a list of PhoneCall items, and provides a search facility.
public class PhoneCallList
{
  // For array extension of phoneCallList.
  private static final int INITIAL_ARRAY_SIZE = 50, ARRAY_RESIZE_FACTOR = 2;

  // The PhoneCall objects are stored in a partially filled array.
  private int noOfPhoneCalls;
  private PhoneCall[] phoneCallList;


  // The constructor reads the phone call details from the given scanner
  // and stores them in phoneCallList, extending as necessary.
  public PhoneCallList(Scanner scanner)
  {
    phoneCallList = new PhoneCall[INITIAL_ARRAY_SIZE];
    noOfPhoneCalls = 0;
    while (scanner.hasNextLine())
    {
      // Obtain the next PhoneCall.
      PhoneCall currentPhoneCall = readOnePhoneCall(scanner);
      // Extend the array if it is too small.
      if (noOfPhoneCalls == phoneCallList.length)
      {
        PhoneCall[] biggerArray
          = new PhoneCall[phoneCallList.length * ARRAY_RESIZE_FACTOR];
        for (int index = 0; index < phoneCallList.length; index++)
          biggerArray[index] = phoneCallList[index];
        phoneCallList = biggerArray;
      } // if
      // Finally store the PhoneCall and update noOfPhoneCalls.
      phoneCallList[noOfPhoneCalls] = currentPhoneCall;
      noOfPhoneCalls++;
    } // while
  } // PhoneCallList

  // Find the PhoneCall object corresponding to phone number prefix
  // or return null if not found.
  public PhoneCall[] findNumber(String prefix)
  {
    int numbersFound = 0;
    PhoneCall[] matchingPhoneCalls = new PhoneCall[0];
    for (int index1 = 0; index1 < noOfPhoneCalls; index1++)
    {
      if (phoneCallList[index1].getNumber().startsWith(prefix))
      {
        PhoneCall[] biggerArray
          = new PhoneCall[matchingPhoneCalls.length + 1];
        for (int index2 = 0; index2 < matchingPhoneCalls.length; index2++)
          biggerArray[index2] = matchingPhoneCalls[index2];
        matchingPhoneCalls = biggerArray;
        matchingPhoneCalls[numbersFound] = phoneCallList[index1];
        numbersFound++;
      } // if
    } // for
    if (numbersFound == 0)
      return null;
    else
      return matchingPhoneCalls;
  } // findNumber

  // Read one line of text from the Scanner,
  // split it into phone number <TAB> call duration <TAB> cost,
  // creating a corresponding PhoneCall and return it.
  private PhoneCall readOnePhoneCall(Scanner scanner)
  {
    String[] phoneCallData = scanner.nextLine().split("\t");
    Duration thisDuration = new Duration(phoneCallData[1]);
    return new PhoneCall(phoneCallData[0], thisDuration,
                         Double.parseDouble(phoneCallData[2]));
  } // readOnePhoneCall
  // The correct line separator for this platform.
  private static final String NLS = System.getProperty("line.separator");

  // Read the phone call details from the given Scanner
  // accumulating calls matched, total duration and cost.
  public String matchingCallsReport(String userInput)
  {
    String result = "";
    // Calls matched and initial total duration and cost.
    int callsMatched = 0;
    Duration totalDuration = new Duration(0);
    double totalCost = 0;

    PhoneCall[] matchingCall = findNumber(userInput);
    if (matchingCall == null)
      result += "No phone numbers starting with " + userInput + " found." + NLS;
    else {
      for (int index = 0; index < matchingCall.length; index++)
      {
        result += "" + matchingCall[index];
        totalDuration = totalDuration.add(matchingCall[index].getDuration());
        totalCost += matchingCall[index].getCost();
      } // for
      callsMatched = matchingCall.length;
    } // else

    result += NLS + "Calls matched:  " + callsMatched + NLS
              + "Total duration: " + totalDuration + NLS
              + String.format("Total cost:     %.2f%n", totalCost);
    return result;
  } // matchingCallsReport

} // class PhoneCallList
