#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#define __USE_BSD
#include <string.h>

#include "speller.h"
#include "dict.h"

typedef struct node *tree_ptr;
struct node 
{
  Key_Type element; // only data is the key itself
  tree_ptr left, right;
  // add anything else that you need
  int height; // height of this node
};

struct table 
{
  tree_ptr head; // points to the head of the tree
  // add anything else that you need
  int height; // height of the tree
  int elementcount; // number of elements
  int strcmpcount; // number of strcmp methods called
};

Table initialize_table(/*ignore parameter*/) 
{
  Table newtable = (Table) malloc(sizeof(struct table));
  if (newtable == NULL)
    fprintf(stderr, "Malloc error!\n");

  newtable -> head = NULL;
  newtable -> height = 0;
  newtable -> elementcount = 0;
  newtable -> strcmpcount = 0;

  return newtable;
}

// Create a new node
tree_ptr new_node(Key_Type thiselement)
{
  tree_ptr newnode = (tree_ptr) malloc(sizeof(tree_ptr));
  if (newnode == NULL)
    fprintf(stderr, "Malloc error!\n");

  newnode -> element = thiselement;
  newnode -> left = NULL;
  newnode -> right = NULL;
  newnode -> height = 1;

  return newnode;
} // new_node

// Update height of node
int new_height(tree_ptr current)
{
  if (current == NULL || (current -> left == NULL && current -> right == NULL))
    return 0;
  else if (current -> left == NULL)
    return current -> right -> height + 1;
  else if (current -> right == NULL)
    return current -> left -> height + 1;
  else if (current -> left -> height > current -> right -> height)
    return current -> height = current -> left -> height + 1;
  else
    return current -> height = current -> right -> height + 1;
} // new_height

// Insert node
tree_ptr insert_node(Key_Type thiselement, tree_ptr current, Table thistable)
{
  Key_Type copyelement = strdup(thiselement);
  if (current == NULL)
  {
    thistable -> elementcount = thistable -> elementcount + 1;
    return new_node(copyelement);
  } // if

  if (strcmp(copyelement, current -> element) == 0)
  {
    thistable -> strcmpcount = thistable -> strcmpcount + 1;
    return current;
  } // if
  else if (strcmp(copyelement, current -> element) < 0)
  {
    thistable -> strcmpcount = thistable -> strcmpcount + 1;
    current -> left = insert_node(copyelement, current -> left, thistable);
  } // else if
  else
    current -> right = insert_node(copyelement, current -> right, thistable);

  current -> height = new_height(current);

  return current;
} // insert_node

Table insert(Key_Type thiselement, Table thistable) 
{
  thistable -> head = insert_node(thiselement, thistable -> head, thistable);
  thistable -> height = thistable -> head -> height;

  return thistable;
}

// http://www.geeksforgeeks.org/avl-tree-set-1-insertion/
tree_ptr left_rotation(tree_ptr x)
{
  tree_ptr y = x -> right;
  tree_ptr T2 = y -> left;
  y -> left = x;
  x -> right = T2;

  x -> height = new_height(x);
  y -> height = new_height(y);

  return y;
} // left_rotation

tree_ptr right_rotation(tree_ptr x)
{
  tree_ptr y = x -> left;
  tree_ptr T3 = y -> right;
  y -> right = x;
  x -> left = T3;

  x -> height = new_height(x);
  y -> height = new_height(y);

  return y;
} // right_rotation

// Get the height difference of the left child and right child of a tree ptr
// Return leftheight - rightheight;
int height_difference(tree_ptr current)
{
  if (current == NULL || (current -> left == NULL && current -> right == NULL))
    return 0;
  else if (current -> left == NULL)
    return 0 - current -> right -> height;
  else if (current -> right == NULL)
    return current -> left -> height;
  else
    return current -> left -> height - current -> right -> height;
} // height_difference

tree_ptr insert_node_two(Key_Type thiselement, tree_ptr current, Table thistable)
{
  Key_Type copyelement = strdup(thiselement);
  if (current == NULL)
  {
    thistable -> elementcount = thistable -> elementcount + 1;
    return new_node(copyelement);
  } // if

  if (strcmp(copyelement, current -> element) == 0)
  {
    thistable -> strcmpcount = thistable -> strcmpcount + 1;
    return current;
  } // if
  else if (strcmp(copyelement, current -> element) < 0)
  {
    thistable -> strcmpcount = thistable -> strcmpcount + 1;
    current -> left = insert_node_two(copyelement, current -> left, thistable);
  } // else if
  else
    current -> right = insert_node_two(copyelement, current -> right, thistable);

  current -> height = new_height(current);

  // If the tree is unbalanced, restructure the tree
  int heightdifference = height_difference(current);
  // Left single rotation
  if (heightdifference > 1
      && strcmp(current -> element, current -> left -> element) < 0)
    return right_rotation(current);
  // Right single rotation
  else if (heightdifference < -1
           && strcmp(current -> element, current -> right -> element) > 0)
    return left_rotation(current);
  // Left double rotation
  else if (heightdifference > 1
           && strcmp(current -> element, current -> left -> element) > 0)
  {
    current -> left = left_rotation(current -> left);
    return right_rotation(current);
  } // else if
  // Right double rotation
  else if (heightdifference < -1
           && strcmp(current -> element, current -> right -> element) < 0)
  {
    current -> right = right_rotation(current -> right);
    return left_rotation(current);
  } // else if
  else
    return current;
} // restructure_tree

Table insert_two(Key_Type thiselement, Table thistable) 
{
  thistable -> head = insert_node_two(thiselement, thistable -> head, thistable);
  thistable -> height = thistable -> head -> height;

  return thistable;
} // insert_two

Boolean find_node(Key_Type thiselement, tree_ptr current)
{
  if (current == NULL)
    return 0;
  else
  {
    if (strcmp(thiselement, current -> element) == 0)
      return 1;
    else if (strcmp(thiselement, current -> element) < 0)
      return find_node(thiselement, current -> left);
    else
      return find_node(thiselement, current -> right);
  } // else
}

Boolean find(Key_Type thiselement, Table thistable) 
{
  if (thistable -> head == NULL)
    return 0;
  else
    return find_node(thiselement, thistable -> head);
}

Boolean find_two(Key_Type thiselement, Table thistable) 
{
  if (thistable -> head == NULL)
    return 0;
  else
    return find_node(thiselement, thistable -> head);
} // find_two

void print_node(tree_ptr current)
{
  if (current == NULL)
    return;

  print_node(current -> left);
  printf("%s\n", current -> element);
  print_node(current -> right);
} // print_node

void print_table(Table thistable) 
{
  if (thistable -> head == NULL)
    return;

  print_node(thistable -> head);
}

void print_node_height(tree_ptr current)
{
  if (current == NULL)
    return;

  print_node_height(current -> left);
  printf("%s\theight: %d\n", current -> element, current -> height);
  print_node_height(current -> right);
} // print_node_depth

void print_stats (Table thistable) 
{
  printf("The height of the tree is %d.\n", thistable -> height);
  printf("The number of elements in the tree is %d.\n",
         thistable -> elementcount);
  printf("The average number of strcmp methods called per element is %.2f.\n",
         (float) thistable -> strcmpcount / thistable -> elementcount);
  print_node_height(thistable -> head);
}

