#include "graph.h"

int main(int argc,char *argv[])
{
  Graph mygraph;

  read_graph(&mygraph,argv[1]);

  /* you take it from here */
  breadth_first_search(&mygraph, 3);

  return(0);
}
