#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAXLEN 100

// Compute the highest common factor of a and b
// hcf stands for 'highest common factor'
unsigned long hcf(unsigned long a, unsigned long b)
{
  // if b is 0, the hcf is automatically a
  if (b == 0)
    return a;
  else
  {
    // r = a mod b -> r = qb - a
    // The common factors of a and b are the same as b and r
    long r = a % b;
    return hcf(b, r);
  } // else
} // hcf

// Compute the function of g^x mod p
// fme stands for 'fast modular exponentiation'
unsigned long fme(unsigned long g, unsigned long x, unsigned long p)
{
  long a = g;
  long b = x;
  long s = 1;
  // Continue until b is zero
  while (b != 0)
  {
    // If b is odd
    if (b % 2 != 0)
      s = (s * a) % p;
    a = (a * a) % p;
    b = b / 2;
  } // while
  return s;
} // fme

// Compute the unique integer x for a given y such that y=g^x mod p
// dl stands for 'discrete logarithm'
unsigned long dl(unsigned long y, unsigned long g, unsigned long p)
{
  long solution = 0;
  for (long x = 1; x < p; x++)
  {
    // if g^x mod p = y, then x is our solution
    if (fme(g, x, p) == y)
    {
      solution = x;
    } // if
  } // for
  return solution;
} // dl

// Compute the unique integet x for a given y such that xy=1 mod p
// imp stand for 'inverse modulo prime'
unsigned long imp(unsigned long y, unsigned long p)
{
  long phim = 0;
  // for m in the range of 1 to p-1, if the highest common factor of m and p
  // is 1, phim increases by 1
  for (long m = 1; m < p; m++)
  {
    if (hcf(m, p) == 1)
      phim++;
  } // for
  // The inverse modulo prime, x can be obtained by using the formula
  // y^phim-1 mod p
  return fme(y, phim - 1, p);
} // imp

int main(int argc, char *argv[])
{
  char *input;
  long p = 257;
  long g = 3;
  int stillongoing = 0;

  // Allocate memories for input
  input = (char*) malloc (MAXLEN * sizeof(char));
  if (input == NULL)
  {
    fprintf(stderr, "Malloc error!\n");
    return 1;
  } // if

  // Make it so the random number obtained is different every time
  srand(time(NULL));

  // While user does not exit
  while (stillongoing == 0)
  {
    // Provide p and g
    printf("\nPrime modulus is %ld\n", p);
    printf("Primitive root wrt %ld is %ld\n\n", p, g);
    printf("Choose: e (encrypt) | d (decrypt) |k (get public key) | "
            "x (exit)? ");

    fgets(input, MAXLEN, stdin);
    strtok(input, "\n");

    // get public key
    if (strcmp(input, "k") == 0)
    {
      // User provides private key
      printf("\nType private key: ");
      fgets(input, MAXLEN, stdin);
      long x;
      sscanf(input, "%ld", &x);
      // Provide public key based on private key
      long y = fme(g, x, p);
      printf("Public key is: %ld\n", y);
    } // if
    // encrypt message
    else if (strcmp(input, "e") == 0)
    {
      // User provides secret message
      printf("\nType secret number to send: ");
      fgets(input, MAXLEN, stdin);
      long M;
      sscanf(input, "%ld", &M);
      // User provides public key
      printf("Type recipient's public key: ");
      fgets(input, MAXLEN, stdin);
      long y;
      sscanf(input, "%ld", &y);
      // Provide encrypted message in the form of (a,b)
      long k = rand() % (p - 1) + 1;
      long a = fme(g, k, p);
      long b = fme(y, k, p);
      b = fme(M * b, 1, p);
      printf("\nThe encrypted secret is: (%ld,%ld)\n", a, b);
    } // else if
    // decrypt message
    else if (strcmp(input, "d") == 0)
    {
      // User provides encrypted message in the form of (a,b)
      printf("\nType in received message in form (a,b): ");
      fgets(input, MAXLEN, stdin);
      char *stringa = strtok(input, ",");
      char *stringb = strtok(NULL, ",");
      stringa = memmove(stringa, stringa+1, strlen(stringa));
      stringb[strlen(stringb) - 1] = 0;
      long a;
      long b;
      sscanf(stringa, "%ld", &a);
      sscanf(stringb, "%ld", &b);
      // User provides private key
      printf("Type in your private key: ");
      fgets(input, MAXLEN, stdin);
      long x;
      sscanf(input, "%ld", &x);
      // Provides decrypted message
      long a1 = fme(a, p - x - 1, p);
      long m = fme(b * a1, 1, p);
      printf("\nThe decrypted secret is: %ld\n", m);
    } // else if
    // exit
    else if (strcmp(input, "x") == 0)
    {
      stillongoing = 1;
    } // elseif
  } // while

  free(input);
  return 0;
} // main
