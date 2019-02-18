#include "DFA.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// FILE EKSTERNAL SECTION
void CreateDFA (arrSTATE *_State, STARTSTATE *SS, char* filename)
// Membuat tipe data yang menampung semua informasi mengenai state-state yang ada
// arrSTATE akan menampung state yang ada, stringnya, transisi, dan final state
// STARTSTATE berisi state awal saat memulai game
{
  // KAMUS LOKAL
  int i;
  int index;
  char output[255];
  FILE* pointer;

  // ALGORITMA
  // Membuka file
  pointer = fopen (filename, "r");

  if (pointer == NULL)
  {
    printf ("File yang dimaksudkan tidak ada\n");
  }
  else
  {
    while (fgets(output, sizeof (output), pointer) != NULL)
    {
      // LIST NAMA STATE YANG ADA
      const char str1[20] = "DAFTAR STATE\n";
      const char str2[20] = "TRANSISI STATE\n";
      const char str3[20] = "START STATE\n";
      const char str4[20] = "FINAL STATE\n";

      // DAFTAR STATE
      if (strcmp (output, str1) == 0)
      {
        Neff (*_State) = 0;
        while (strcmp (fgets (output, sizeof (output), pointer), "\n") != 0)
        {
          const char* state = strtok (output, ",");
          const char* string = strtok (NULL, ",");

          index = atoi (state);

          Neff (*_State)++;
          strcpy (String (*_State, index), string);
        }
        Neff(*_State) = index;
      }
      // TRANSISI STATE
      if (strcmp (output, str2) == 0)
      {
        while (strcmp (fgets (output, sizeof (output), pointer), "\n") != 0)
        {
          const char* state = strtok (output, ",");
          index = atoi(state);

          for (i = 1; i <= 9; i++)
          {
            const char* target = strtok (NULL, ",");
            Transition (*_State, index, i) = atoi (target);
          }
        }
      }