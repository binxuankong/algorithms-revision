#include "graph.h"
/* Good luck */
#define DFS
#undef BFS

#ifdef DFS
void visit(Graph *mygraph, int index, int *dfsnum)
{
  dfsnum[index] = 1;
  List* current = mygraph -> table[index].outlist;
  while (current != NULL)
  {
    if (dfsnum[current -> index] == 0)
      visit(mygraph, current-> index, dfsnum);
    current = current -> next;
  } // while

  printf("NODE %d %s\n", index, mygraph -> table[index].name);
} // visit

void depth_first_search(Graph *mygraph, int index)
{
  int dfsnum[mygraph -> MaxSize];
  for (int i = 0; i < mygraph -> MaxSize; i++)
    dfsnum[i] = 0;

  visit(mygraph, index, dfsnum);
} // depth_first_search
#endif

int get_first(int *searchQueue, int queueSize)
{
  int first = searchQueue[0];
  for (int i = 0; i < queueSize - 1; i++)
    searchQueue[i] = searchQueue[i + 1];
  searchQueue[queueSize - 1] = 0;

  return first;
} // get_first

void add_first(int *searchQueue, int item, int queueSize)
{
  for (int i = 0; i < queueSize; i++)
    searchQueue[i + 1] = searchQueue[i];

  searchQueue[0] = item;
} // add_first

void breadth_first_search(Graph *mygraph, int index)
{
  int bfsnum[mygraph -> MaxSize];
  for (int i = 0; i < mygraph -> MaxSize; i++)
    bfsnum[i] = 0;

  int searchQueue[mygraph -> MaxSize];
  searchQueue[0] = index;
  int queueSize = 1;

  while (queueSize > 0)
  {
#ifdef BFS
    int i = get_first(searchQueue, queueSize);
#endif

#ifdef DFS
    int j = 0;
    while (searchQueue[j] == 0)
    {
      j++;
    } // while
    int i = searchQueue[j];
#endif

    if (bfsnum[i] == 0)
    {
      bfsnum[i] = 1;
      queueSize--;
      List* current = mygraph -> table[i].outlist;
      while (current != NULL)
      {
        if (bfsnum[current -> index] == 0)
        {
#ifdef BFS
          searchQueue[queueSize] = current -> index;
#endif
#ifdef DFS
          add_first(searchQueue, current -> index, queueSize);
#endif
          queueSize++;
        } // if
        current = current -> next;
      } // while
      if (i != 0)
        printf("NODE %d %s\n", i, mygraph -> table[i].name);
    } // if
  } // while
} // breadth_first_search
