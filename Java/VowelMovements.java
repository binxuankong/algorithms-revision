public class VowelMovements
{
private static StringBuffer inputStringBuffer = new StringBuffer("**");
private static void outputVowelMovements(int scanPosition)
{
if (scanPosition >= inputStringBuffer.length())
System.out.println(inputStringBuffer);
else if (inputStringBuffer.charAt(scanPosition) != '*')
outputVowelMovements(scanPosition + 1);
else
{
inputStringBuffer.setCharAt(scanPosition, 'a');
outputVowelMovements(scanPosition + 1);
inputStringBuffer.setCharAt(scanPosition, 'e');
outputVowelMovements(scanPosition + 1);
inputStringBuffer.setCharAt(scanPosition, 'i');
outputVowelMovements(scanPosition + 1);
inputStringBuffer.setCharAt(scanPosition, 'o');
outputVowelMovements(scanPosition + 1);
inputStringBuffer.setCharAt(scanPosition, 'u');
outputVowelMovements(scanPosition + 1);
}
}
public static void main(String[] args)
{
outputVowelMovements(0);
}
}
