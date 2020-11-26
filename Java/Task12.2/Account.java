/**
 * This class represents accounts and provides certain manipulations of them.
 *
 * @author Bin Xuan Kong
 */
public class Account
{
  // The name of the provider.
  private final String providerName;

  // The balance of the account, in whole pence.
  private int accountBalance = 0;

  /**
   * Construct an account.
   * The initial balance of the account, in whole pence is 0.
   *
   * @param requiredProviderName The name of the provider.
   */
  public Account(String requiredProviderName)
  {
    providerName = requiredProviderName;
  } // Account

  /**
   * Top up the account, which add to the balance of the account.
   *
   * @param amount Top up amount, in whole pounds.
   */
  public void accountTopUp(int amount)
  {
    accountBalance += amount * 100;
  } // accountTopUp

  /**
   * Make phone call. Attempt to call the duration desired
   * but as much as we can if account balance is too low.
   *
   * @param desiredDuration The desired duration of the phone call.
   *
   * @return The actual phone call duration.
   */
  public int accountTryToCall(int desiredDuration)
  {
    int phoneCallDuration = desiredDuration;
    if (phoneCallDuration > accountBalance)
      phoneCallDuration = accountBalance;

    accountBalance -= phoneCallDuration;
    return phoneCallDuration;
  } // accountTryToCall

  /**
   * Obtain the provider's name.
   *
   * @return A string of the provider's name.
   */
  public String getProviderName()
  {
    return providerName;
  } // getProviderName

  /**
   * Provides the provider's name and balance of the account.
   *
   * @return A string giving the name and balance of the account.
   */
  public String toString()
  {
    return "Account(" + providerName + ", " + accountBalance + ")";
  } // toString

} // class Account
