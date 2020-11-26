#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#define __USE_BSD
#include <string.h>

#include "speller.h"
#include "dict.h"

typedef struct 
{ // hash-table entry
  Key_Type element; // only data is the key itself
  enum {empty, in_use, deleted} state;
} cell;

typedef unsigned int Table_size; // type for size-of or index-into hash table

struct table 
{
  cell *cells; Table_size table_size; // cell cells [table_size];
  Table_size num_entries; // number of cells in_use
  // add anything else that you need
  int random_one; // random number required for MAD compression
  int random_two;
  int num_collisions; // total number of collisions
};

Table initialize_table(Table_size tablesize) 
{
  if (tablesize < 1)
    return NULL;
  Table newtable = (Table) malloc(sizeof(Table));
  if (newtable == NULL)
    fprintf(stderr, "Malloc error!\n");

  cell newcell[tablesize];
  newtable -> cells = newcell;
  newtable -> table_size = tablesize;
  newtable -> num_entries = 0;
  int a = (rand() % tablesize) + 1;
  while (a % tablesize == 0)
  {
    a++;
  } // while
  int b = rand() % tablesize;
  newtable -> random_one = a;
  newtable -> random_two = b;
  newtable -> num_collisions = 0;

  return newtable;
}

Boolean is_prime(int number)
{
  if (number <= 1)
    return 0;
  else
  {
    for (int i = 2; i * i < number; i++)
      if (number % i == 0)
        return 0;
  } // else
  return 1;
} // is_prime

Table resize_table(Table thistable)
{
  int newsize = thistable -> table_size * 2;
  while (!is_prime(newsize))
  {
    newsize++;
  } // while
  
  Table newtable = (Table) malloc(sizeof(Table));
  if (newtable == NULL)
    fprintf(stderr, "Malloc error!\n");

  cell newcell[newsize];
  newtable -> cells = newcell;
  newtable -> table_size = newsize;
  newtable -> num_entries = thistable -> num_entries;
  int a = (rand() % newsize) + 1;
  while (a % newsize == 0)
  {
    a++;
  } // while
  int b = rand() % newsize;
  newtable -> random_one = a;
  newtable -> random_two = b;
  newtable -> num_collisions = thistable -> num_collisions;

  int i = 0;
  int counter = 0;
  while (counter < newtable -> num_entries)
  {
    cell *currentcell = &(thistable -> cells[i]);
    if (currentcell -> state == in_use)
    {
      Key_Type currentelement = thistable -> cells[i].element;
      insert(currentelement, newtable);
      counter++;
    } // if
    i++;
  } // while

  return newtable;
} // resize_table

// Use polynomial hash codes and MAD method for hash function
// http://www.orcca.on.ca/~yxie/courses/cs2210b-2011/htmls/notes/05-hashtable.pdf
int hash_function(Key_Type thiselement, Table thistable)
{
  int length = strlen(thiselement);
  int hash = atoi(&thiselement[length - 1]);
  int p = 33; // constant prime
  int i = length - 2;

  while (i >= 0)
  {
    hash = hash * p + atoi(&thiselement[i]);
    i--;
  } // while

  int a = thistable -> random_one;
  int b = thistable -> random_two;
  return (a * hash - b) % thistable -> table_size;
} // hash_function

int double_hash(int thiskey, Table thistable)
{
  int prime = 11;
  while (prime > thistable -> table_size && !is_prime(prime))
  {
    prime--;
  } // while
  return prime - (thiskey % prime);
} // double_hash

Table insert(Key_Type thiselement, Table thistable) 
{
  if (thistable -> num_entries >= thistable -> table_size)
    thistable = resize_table(thistable);

  int thishash = hash_function(thiselement, thistable);
  cell *thiscell = &(thistable -> cells[thishash]);
  while (thiscell != NULL || thiscell -> state == in_use)
  {
    thishash = (thishash + 1) % thistable -> table_size;
    thiscell = &(thistable -> cells[thishash]);
    thistable -> num_collisions = thistable -> num_collisions + 1;
  } // while
  thiscell -> element = thiselement;
  thiscell -> state = in_use;
  thistable -> num_entries = thistable -> num_entries + 1;
  return thistable;
}

Table insert_two(Key_Type thiselement, Table thistable)
{
  if (thistable -> num_entries >= thistable -> table_size)
    thistable = resize_table(thistable);

  int thishash = hash_function(thiselement, thistable);
  int doublehash = double_hash(thishash, thistable);
  cell *thiscell = &(thistable -> cells[thishash]);
  while (thiscell != NULL || thiscell -> state == in_use)
  {
    thishash = (thishash + doublehash) % thistable -> table_size;
    thiscell = &(thistable -> cells[thishash]);
    thistable -> num_collisions = thistable -> num_collisions + 1;
  } // while
  thiscell -> element = thiselement;
  thiscell -> state = in_use;
  thistable -> num_entries = thistable -> num_entries + 1;
  return thistable;
} // insert_double_hash

Boolean find(Key_Type thiselement, Table thistable) 
{
  int thishash = hash_function(thiselement, thistable);
  int pointer = 0;
  while (pointer <= thistable -> num_entries)
  {
    cell *thiscell = &(thistable -> cells[thishash]);
    if (thiscell == NULL)
      return 0;
    else if (thiscell -> state == in_use)
    {
      if (strcmp(thiscell -> element, thiselement) == 0)
        return 1;
    } // else if
    else
    {
      thishash = (thishash + 1) % thistable -> table_size;
      pointer = pointer + 1;
    } // else
  } // while
  return 0;
}

void print_table(Table thistable) 
{
  int i = 0;
  int counter = 0;
  while (counter < thistable -> num_entries)
  {
    cell *currentcell = &(thistable -> cells[i]);
    if (currentcell -> state == in_use)
    {
      printf("%s\n", currentcell -> element);
      counter++;
    } // if
    i++;
  } // while
}

void print_stats(Table thistable) 
{
  printf("The size of the table is %d.\n", thistable -> table_size);
  printf("The number of entries in the table is %d.\n",
         thistable -> num_entries);
  printf("The average number of collisions per insert in the table is %d.\n",
         thistable -> num_collisions / thistable -> num_entries);
}
