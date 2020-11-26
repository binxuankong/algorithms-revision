/**
 * This program stimulates a simple scenario in which students purchase and use
 * mobile phones. It creates some students, create some phones with accounts,
 * which the students purchase, and cause the students to make calls. As this
 * is done, it reports on the standard output, enabling the user of the program
 * to follow the events. So the amain method tells a story, and can easily be
 * altered to tell a different one.
 *
 * @author Bin Xuan Kong
 */
public class StudentsCalling
{
  // Private helper method to have a student buy a phone.
  private static void buyPhone(Student student, Phone phone, Account account)
  {
    System.out.println(student);
    System.out.println("is buying phone " + phone.getPhoneName());
    System.out.println("with account " + account.getProviderName());
    student.getPhone(phone);
    System.out.println("Result: ");
    System.out.println(student);
    System.out.println();
  } // buyPhone

  // Private helper method to help a student top up.
  private static void topUp(Student student, int desiredAmount)
  {
    System.out.println(student);
    System.out.println("is topping up by " + desiredAmount);
    student.studentTopUp(desiredAmount);
    System.out.println("Result: ");
    System.out.println(student);
    System.out.println();
  } // topUp

  // Private helper method to help a student make a phone call.
  private static void phoneCall(Student student, int desiredDuration)
  {
    System.out.println(student);
    System.out.println("is making a call for desired " + desiredDuration
                       + " seconds");
    student.studentTryToCall(desiredDuration);
    System.out.println("Result: ");
    System.out.println(student);
    System.out.println();
  } // phoneCall

  /**
   * The main method tells the 'story'.
   *
   */
  public static void main(String[] args)
  {
    // Create students.
    System.out.println("Creating student Ian");
    System.out.println("Result:");
    Student student1 = new Student("Ian");
    System.out.println(student1);
    System.out.println();
    System.out.println("Creating student Ann");
    System.out.println("Result:");
    Student student2 = new Student("Ann");
    System.out.println(student2);
    System.out.println();
    System.out.println("Creating student Eugene");
    System.out.println("Result:");
    Student student3 = new Student("Eugene");
    System.out.println(student3);
    System.out.println();
    System.out.println("Creating student Sherlynn");
    System.out.println("Result:");
    Student student4 = new Student("Sherlynn");
    System.out.println(student4);
    System.out.println();

    // Create providers and phones.
    Account account1 = new Account("Hotlink");
    Phone phone1 = new Phone("Samsung S4", account1);
    Account account2 = new Account("Digi");
    Phone phone2 = new Phone("Nokia X360", account2);
    Account account3 = new Account("giffgaff");
    Phone phone3 = new Phone("Blackberry M8TR01", account3);
    Account account4 = new Account("EE");
    Phone phone4 = new Phone("iPhone 6", account4);
    Account account5 = new Account("Three");
    Phone phone5 = new Phone("iPhone 6s", account5);

    buyPhone(student1, phone1, account1);
    buyPhone(student2, phone2, account2);
    buyPhone(student3, phone3, account3);
    buyPhone(student4, phone4, account4);
    topUp(student3, 20);
    phoneCall(student3, 1000);
    topUp(student1, 15);
    topUp(student2, 20);
    topUp(student4, 30);
    phoneCall(student2, 536);
    phoneCall(student1, 117);
    phoneCall(student3, 748);
    phoneCall(student4, 357);
    topUp(student3, 10);
    phoneCall(student3, 286);
    buyPhone(student4, phone5, account5);
    topUp(student4, 30);
    phoneCall(student1, 642);
    phoneCall(student4, 904);
    
  } // main

} // class StudentsCalling

