#include "graph.h"

Node get_largest_outdegree(Graph *mygraph)
{
  int currentLargest = 0;
  int index = 0;
  for (int i = 0; i < mygraph -> MaxSize; i++)
  {
    if (mygraph -> table[i].outdegree > currentLargest)
    {
      currentLargest = mygraph -> table[i].outdegree;
      index = i;
    } // if
  } // for

  return mygraph -> table[index];
} // get_largest_outdegree

Node get_smallest_outdegree(Graph *mygraph)
{
  int j = 0;
  while (mygraph -> table[j].outdegree == 0)
  {
    j++;
  } // while

  int currentSmallest = mygraph -> table[j].outdegree;
  int index = j;
  for (int i = j; i < mygraph -> MaxSize; i++)
  {
    if (mygraph -> table[i].outdegree < currentSmallest
        && mygraph -> table[i].outdegree != 0)
    {
      currentSmallest = mygraph -> table[i].outdegree;
      index = i;
    } // if
  } // for

  return mygraph -> table[index];
} // get_smallest_outdegree

Node get_largest_indegree(Graph *mygraph)
{
  int currentLargest = 0;
  int index = 0;
  int temp[mygraph -> MaxSize];

  for (int i = 0; i < mygraph -> MaxSize; i++)
    temp[i] = 0;

  List* current;
  for (int i = 0; i < mygraph -> MaxSize; i++)
  {
    current = mygraph -> table[i].outlist;
    while (current != NULL)
    {
      temp[current -> index]++;
      current = current -> next;
    } // while
  } // for

  for (int i = 0; i < mygraph -> MaxSize; i++)
  {
    if (temp[i] > currentLargest)
    {
      currentLargest = temp[i];
      index = i;
    } // if
  } // for

  return mygraph -> table[index];
} // get_largest_outdegree

Node get_smallest_indegree(Graph *mygraph)
{
  int index = 0;
  int temp[mygraph -> MaxSize];

  for (int i = 0; i < mygraph -> MaxSize; i++)
    temp[i] = 0;

  List* current;
  for (int i = 0; i < mygraph -> MaxSize; i++)
  {
    current = mygraph -> table[i].outlist;
    while (current != NULL)
    {
      temp[current -> index]++;
      current = current -> next;
    } // while
  } // for

  int j = 0;
  while (temp[j] == 0)
  {
    j++;
  } // while
  int currentSmallest = temp[j];
  index = j;

  for (int i = j; i < mygraph -> MaxSize; i++)
  {
    if (temp[i] < currentSmallest && temp[i] != 0)
    {
      currentSmallest = temp[i];
      index = i;
    } // if
  } // for

  return mygraph -> table[index];
} // get_smallest_outdegree

int main(int argc,char *argv[])
{
  Graph mygraph;

  read_graph(&mygraph,argv[1]);

  /* you take it from here */
  print_graph(&mygraph);

  Node largestOutdegree = get_largest_outdegree(&mygraph);
  Node smallestOutdegree = get_smallest_outdegree(&mygraph);
  Node largestIndegree = get_largest_indegree(&mygraph);
  Node smallestIndegree = get_smallest_indegree(&mygraph);

  printf("The node with the largest out-degree is\n");
  printf("NODE %s\t OUT-DEGREE %d\n", largestOutdegree.name,
         largestOutdegree.outdegree);
  printf("The node with the smallest non-zero out-degree is\n");
  printf("NODE %s\t OUT-DEGREE %d\n", smallestOutdegree.name,
         smallestOutdegree.outdegree);
  printf("The node with the largest in-degree is\n");
  printf("NODE %s\n", largestIndegree.name);
  printf("The node with the smallest non-zero in-degree is\n");
  printf("NODE %s\n", smallestIndegree.name);

  return(0);
}
