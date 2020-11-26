#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

/* A program to read characters one-by-one from standrad input, convert all
upper-case characters to lower-case and all lower-case characters to upper-case,
and write the result to standard output. The program also count how many
characters are read, and how many of those are converted in each direction, and
output the totals at the end.
*/

int main(void)
{
#define MAXLEN 1000
  char *buffer;
  char readname[20];
  char writename[20];
  int index = 0;
  int ch;
  int upperToLower = 0;
  int lowerToUpper = 0;

  /* Prompt for the file to read */
  puts("Enter the file name to read:");
  scanf("%s", readname);
  fflush(stdin);

  /* Prompt for the file to write on */
  puts("Enter the file name to write:");
  scanf("%s", writename);
  fflush(stdin);

  // Read the file
  FILE *read = fopen(readname, "r");
  // Write the file
  FILE *write = fopen(writename, "w");

  if (!read) 
  {
    fprintf(stderr, "can't open input for reading\n");
    exit(-1);
  }

  buffer = malloc(MAXLEN);

  while ((ch = fgetc(read)) != EOF)
  {
    // If character is upper-case
    if (isupper(ch))
    {
      buffer[index] = tolower(ch);
      upperToLower++;
    } // if
    // Character is an alphabet(lower-case)
    else if (isalpha(ch))
    {
      buffer[index] = toupper(ch);
      lowerToUpper++;
    } // else if
    else
      buffer[index] = ch;
    index++;
  } // while

  // Terminate with the null character
  buffer[index] = '\0';
  
  fputs(buffer, write);
  fprintf(write, "Read %2d characters in total, %2d converted to upper-case, "
         "%2d to lower-case\n", (index + 1), lowerToUpper, upperToLower);

  fclose (read);
  fclose (write);
  return 0;

} // main

