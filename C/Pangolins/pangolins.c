#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// List of words that are acceptable as 'yes' or 'no' inputs
const char *yes_words[5] = {"yes", "ya", "y", "correct", "absolutely"};
const int yes_length = 5;
const char *no_words[5] = {"no", "nope", "n", "incorrect", "wrong"};
const int no_length = 5;
FILE *read;

// The structure for the node
struct node {
  char *object;
  char *question;
  struct node *yes_ptr;
  struct node *no_ptr;
}; // node

// Turn the input to lower cases to make it easier to be compared with
char *strlwr(char *str)
{
  unsigned char *p = (unsigned char *)str;
  while (*p)
  {
     *p = tolower(*p);
      p++;
  } // while
  return str;
} // strlwr

// Return 1 if the given string matches with one of the list of words for
// 'yes' or 'no'
int stringYesOrNo(char *givenstring, char *yesorno)
{
  
  if (strcmp(yesorno, "yes") == 0)
  {
    for (int i = 0; i < yes_length; i++)
    {
      if (strcmp(strlwr(givenstring), yes_words[i]) == 0)
        return 1;
    } // for
  } // if
  else if (strcmp(yesorno, "no") == 0)
  {
    for (int i = 0; i < no_length; i++)
    {
      if (strcmp(strlwr(givenstring), no_words[i]) == 0)
        return 1;
    } // for
  } // else if
  else
    return 0;
} // stringYesOrNo

// Tailor the object by adding article in front of it if it does not have one
char *tailorAnswer(char *answer)
{
  char *article = (char*) malloc (10 * sizeof(char));
  if (article == NULL)
    fprintf(stderr, "Malloc error!\n");
  sscanf(answer, "%s", article);
  if (strcmp(article, "a") == 0 || strcmp(article, "an") == 0 ||
      strcmp(article, "the") == 0)
  {
    return answer;
    free(article);
  } // if
  else
  {
    article = "a/an/the ";
    char *temp = malloc(strlen(article) + strlen(answer) + 1);
    strcpy(temp, article);
    strcat(temp, answer);
    return temp;
  } // else
} // tailorAnswer

// Tailor the question by adding a question mark behind the sentence if it does
// not have one
char *tailorQuestion(char *question)
{
  int len = strlen(question);
  if (len > 0 && question[len - 1] == '?')
    return question;
  else
    return strcat(question, "?");
} // tailorQuestion

// Return 1 if the given line is a question, 2 if it is an object
int checkFirstWord(char *line)
{
  char *firstword = (char*) malloc (20 * sizeof(char));
  if (firstword == NULL)
    fprintf(stderr, "Malloc error!\n");
  sscanf(line, "%s", firstword);
  if (strcmp(firstword, "question:") == 0)
    return 1;
  else if (strcmp(firstword, "object:") == 0)
    return 2;
  free(firstword);
} // checkQuestion

// The first game, return the given question as the root of the tree
struct node * firstGame(struct node *current, char *input, int maxlength)
{
  int gameongoing = 0;
  int useriswrong = 0;
  char *objectname = (char*) malloc (maxlength * sizeof(char));
  char *thequestion = (char*) malloc (maxlength * sizeof(char));
  if (objectname == NULL || thequestion == NULL)
    fprintf(stderr, "Malloc error!\n");

  while (gameongoing == 0)
  {
    if (current -> object != NULL)
    {
      useriswrong = 0;
      while (useriswrong == 0)
      {
        printf("Is it %s?\n", current -> object);
        fgets(input, maxlength, stdin);
        strtok(input, "\n");
        if (stringYesOrNo(input, "yes") == 1)
        {
          printf("Well that was easy!\n");
          useriswrong = 1;
        } // if
        else if (stringYesOrNo(input, "no") == 1)
        {
          struct node *newobject = (struct node *) malloc(sizeof(struct node));
          struct node *newquestion = (struct node *) malloc(sizeof(struct node));
          if (newobject == NULL || newquestion == NULL)
            fprintf(stderr, "Malloc error!");
          printf("Oh well you win then. What were you thinking of?\n");
          fgets(objectname, maxlength, stdin);
          strtok(objectname, "\n");
          newobject -> object = tailorAnswer(objectname);
          printf("Please give me a question about %s, so I can tell the "
                 "difference between %s and %s.\n", newobject -> object,
                 newobject -> object, current -> object);
          fgets(thequestion, maxlength, stdin);
          strtok(thequestion, "\n");
          newquestion -> question = tailorQuestion(thequestion);
          printf("What is the answer for %s?\n", newobject -> object);
          fgets(input, maxlength, stdin);
          strtok(input, "\n");
          if (stringYesOrNo(input, "yes") == 1)
          {
            newquestion -> yes_ptr = newobject;
            newquestion -> no_ptr = current;
          } // if
          else if (stringYesOrNo(input, "no") == 1)
          {
            newquestion -> yes_ptr = current;
            newquestion -> no_ptr = newobject;
          } // else if
          else
          {
            printf("Sorry I don't get you. "
                   "Please answer with 'yes' or 'no' only.\n");    
          } // else
          return newquestion;
          printf("Thanks!\n");
          useriswrong = 1;
        } // else if
        else
        {
          printf("Sorry I don't get you. "
                 "Please answer with 'yes' or 'no' only.\n");
        } // else
      } // while
      gameongoing++;
    } // if
    else
    {
      useriswrong = 0;
      while (useriswrong == 0)
      {
        printf("%s\n", current -> question);
        fgets(input, maxlength, stdin);
        strtok(input, "\n");
        if (stringYesOrNo(input, "yes") == 1)
        {
          current = current -> yes_ptr;
          useriswrong = 1;
        } // if
        else if (stringYesOrNo(input, "no") == 1)
        {
          current = current -> no_ptr;
          useriswrong = 1;
        } // else if
        else
        {
          printf("Sorry I don't get you. "
                 "Please answer with 'yes' or 'no' only.\n");
        } // else
      } // while
    } // else
  } // while
} // firstGame

// Function to play the game
void playGame(struct node **nodes, char *input, int maxlength)
{
  int gameongoing = 0;
  int useriswrong = 0;
  struct node **ptr2ptr = nodes;
  char *objectname = (char*) malloc (maxlength * sizeof(char));
  char *thequestion = (char*) malloc (maxlength * sizeof(char));
  if (objectname == NULL || thequestion == NULL)
    fprintf(stderr, "Malloc error!\n");

  while (gameongoing == 0)
  {
    if ((*ptr2ptr) -> object != NULL)
    {
      useriswrong = 0;
      while (useriswrong == 0)
      {
        printf("Is it %s?\n", (*ptr2ptr) -> object);
        fgets(input, maxlength, stdin);
        strtok(input, "\n");
        if (stringYesOrNo(input, "yes") == 1)
        {
          printf("Well that was easy!\n");
          useriswrong = 1;
        } // if
        else if (stringYesOrNo(input, "no") == 1)
        {
          struct node *newobject = (struct node *) malloc(sizeof(struct node));
          struct node *newquestion = (struct node *)malloc(sizeof(struct node));
          if (newobject == NULL || newquestion == NULL)
            fprintf(stderr, "Malloc error!");
          printf("Oh well you win then. What were you thinking of?\n");
          fgets(objectname, maxlength, stdin);
          strtok(objectname, "\n");
          newobject -> object = tailorAnswer(objectname);
          printf("Please give me a question about %s, so I can tell the "
                 "difference between %s and %s.\n", newobject -> object,
                 newobject -> object, (*ptr2ptr) -> object);
          fgets(thequestion, maxlength, stdin);
          strtok(thequestion, "\n");
          newquestion -> question = tailorQuestion(thequestion);
          printf("What is the answer for %s?\n", newobject -> object);
          fgets(input, maxlength, stdin);
          strtok(input, "\n");
          if (stringYesOrNo(input, "yes") == 1)
          {
            newquestion -> yes_ptr = newobject;
            newquestion -> no_ptr = *ptr2ptr;
          } // if
          else if (stringYesOrNo(input, "no") == 1)
          {
            newquestion -> yes_ptr = *ptr2ptr;
            newquestion -> no_ptr = newobject;
          } // else if
          else
          {
            printf("Sorry I don't get you. "
                   "Please answer with 'yes' or 'no' only.\n");    
          } // else
          *ptr2ptr = newquestion;
          printf("Thanks!\n");
          useriswrong = 1;
        } // else if
        else
        {
          printf("Sorry I don't get you. "
                 "Please answer with 'yes' or 'no' only.\n");
        } // else
      } // while
      gameongoing++;
    } // if
    else
    {
      useriswrong = 0;
      while (useriswrong == 0)
      {
        printf("%s\n", (*ptr2ptr) -> question);
        fgets(input, maxlength, stdin);
        strtok(input, "\n");
        if (stringYesOrNo(input, "yes") == 1)
        {
          ptr2ptr = &(*ptr2ptr) -> yes_ptr;
          useriswrong = 1;
        } // if
        else if (stringYesOrNo(input, "no") == 1)
        {
          ptr2ptr = &(*ptr2ptr) -> no_ptr;
          useriswrong = 1;
        } // else if
        else
        {
          printf("Sorry I don't get you. "
                 "Please answer with 'yes' or 'no' only.\n");
        } // else
      } // while
    } // else
  } // while
} // playGame

// Print the tree of the game
void treePrint(struct node *ptr)
{
  if (ptr == NULL)
    return;
  else
  {
    if (ptr -> question != NULL)
    {
      printf("question: %s\n", ptr -> question);
      treePrint(ptr -> yes_ptr);
      treePrint(ptr -> no_ptr);
    } // if
    else
      printf("object: %s\n", ptr -> object);
  } // else
} // treePrint

// Read the tree of the game
struct node * treeRead()
{
  char *line = (char *) malloc (1000 * sizeof(char));
  fgets(line, 1000, read);
  if (line == NULL)
    return NULL;
  else
  {
    struct node *pointer = (struct node *) malloc (sizeof(struct node));
    if (pointer == NULL)
      fprintf(stderr, "Malloc error!\n");
    if (checkFirstWord(line) == 1)
    {
      char *thisquestion = strchr(line, ' ');
      memmove(thisquestion, thisquestion+1, strlen(thisquestion));
      strtok(thisquestion, "\n");
      pointer -> question = thisquestion;
      pointer -> yes_ptr = treeRead();
      pointer -> no_ptr = treeRead();
    } // if
    else if (checkFirstWord(line) == 2)
    {
      char *thisobject = strchr(line, ' ');
      memmove(thisobject, thisobject+1, strlen(thisobject));
      strtok(thisobject, "\n");
      pointer -> object = thisobject;
      pointer -> yes_ptr = pointer -> no_ptr = NULL;
    } // else if
    return pointer;
  } // else
  free(line);
} // treeRead

// Free all the memory that has been malloc-ed
void freeTree(struct node *pointer)
{
  if (pointer != NULL)
  {
    freeTree(pointer -> yes_ptr);
    freeTree(pointer -> no_ptr);
    free(pointer);
  } // if
} // freeTree

int main(int argc, char *argv[])
{
  int maxlength;
  char *input;
  char *filename = argv[1];

  printf("Enter the maximum length of string\n");
  scanf("%d", &maxlength);
  (void)getchar();

  input = (char*) malloc (maxlength * sizeof(char));
  if (input == NULL)
  {
    fprintf(stderr, "Malloc error!\n");
    return 1;
  } // if

  struct node *current = (struct node *) malloc (sizeof(struct node));
    if (current == NULL)
      fprintf(stderr, "Malloc error!\n");
  printf("Okay, please think of something.\n");
  printf("Type 'load' to load the game, or any key to start a new game.\n");
  fgets(input, maxlength, stdin);
  strtok(input, "\n");
  if (strcmp(strlwr(input), "load") == 0)
  {
    read = fopen(filename, "r");
    current = treeRead();
    fclose(read);
    playGame(&current, input, maxlength);
  } // if
  else
  {
    current -> object = "a pangolin";
    current = firstGame(current, input, maxlength);
  } // else

  int continuegame = 0;

  do {
    printf("Would you like to play again? Or type 'save' to save the game.\n");
    fgets(input, maxlength, stdin);
    strtok(input, "\n");
    if (stringYesOrNo(input, "yes") == 1)
      playGame(&current, input, maxlength);
    else if (strcmp(strlwr(input), "save") == 0)
    {
      if (filename != NULL)
      {
        FILE *write = freopen(filename, "w", stdout);
        treePrint(current);
        fclose(write);
        printf("Game successfully saved!\n");
      } // if
      else
        printf("Please provide a filename as argument to save the game.\n");
      continuegame++;
    } // else if
    else
      continuegame++;
  } while(continuegame == 0);

  freeTree(current);
  return 0;
} // main
