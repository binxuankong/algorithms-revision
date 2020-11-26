/**
 * This class represents students and provides certain manipulations of them.
 *
 * @author Bin Xuan Kong
 */
public class Student
{
  // The name of the student.
  private final String studentName;

  // The phone of the student, initally none.
  private Phone currentPhone = null;

  /**
   * Construct a student.
   * Initially the student has no phone.
   *
   * @param requiredStudentName The name of the student.
   */
  public Student(String requiredStudentName)
  {
    studentName = requiredStudentName;
  } // Student

  /**
   * Provides a phone to the student.
   *
   * @param newPhone The phone that the student will obtain.
   */
  public void getPhone(Phone newPhone)
  {
    currentPhone = newPhone;
  } // getPhone

  /**
   * Top up the phone, which add to the balance of the account.
   * This will only work if the student has a phone.
   *
   * @param amount Top up amount, in whole pounds.
   */
  public void studentTopUp(int amount)
  {
    if (currentPhone == null)
      return;

    currentPhone.phoneTopUp(amount);
  } // studentTopUp

  /**
   * Make phone call. Attempt to call the duration desired
   * but as much as we can if account balance is too low.
   * This will only work if the student has a phone.
   *
   * @param desiredDuration The desired duration of the phone call.
   *
   */
  public void studentTryToCall(int desiredDuration)
  {
    if (currentPhone == null)
      return;

    currentPhone.phoneTryToCall(desiredDuration);
  } // studentTryToCall

  /**
   * Provides the name and phone of the student.
   *
   * @return A string giving the name and phone of the student.
   */
  public String toString()
  {
    return "Student(" + studentName + ", " + currentPhone + ")";
  } // toString

} // class Student
