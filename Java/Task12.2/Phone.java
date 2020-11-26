/**
 * This class represents phones and provides certain manipulations of them.
 *
 * @author Bin Xuan Kong
 */
public class Phone
{
  // The name of the phone.
  private final String phoneName;

  // The total number of seconds of phone calls made on it.
  private int totalCallDuration = 0;

  // The account of the phone.
  private final Account phoneAccount;

  /**
   * Construct a phone.
   *
   * @param requiredPhoneName The name of the phone.
   * @param requiredPhoneAccount The account of the phone.
   */
  public Phone(String requiredPhoneName, Account requiredPhoneAccount)
  {
    phoneName = requiredPhoneName;
    phoneAccount = requiredPhoneAccount;
  } // Phone

  /**
   * Top up the phone, which add to the balance of the account.
   *
   * @param amount Top up amount, in whole pounds.
   */
  public void phoneTopUp(int amount)
  {
    phoneAccount.accountTopUp(amount);
  } // phoneTopUp

  /**
   * Make phone call. Attempt to call the duration desired
   * but as much as we can if account balance is too low.
   * Add the actual phone call duration to the total phone call duration.
   *
   * @param desiredDuration The desired duration of the phone call.
   *
   */
  public void phoneTryToCall(int desiredDuration)
  {
    phoneAccount.accountTryToCall(desiredDuration);
    totalCallDuration += phoneAccount.accountTryToCall(desiredDuration);
  } // phoneTryToCall

  /**
   * Obtain the phone's name.
   *
   * @return A string of the phone's name.
   */
  public String getPhoneName()
  {
    return phoneName;
  } // getPhoneName

  /**
   * Provides the name, total call duration and account of the phone.
   *
   * @return A string giving the name, total call duration and account of
   * the phone.
   */
  public String toString()
  {
    return "Phone(" + phoneName + ", " + totalCallDuration + ", "
           + phoneAccount + ")";
  } // toString

} // class Phone
