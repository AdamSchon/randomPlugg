#include <stdlib.h>
#include <stdbool.h>
#include <stdint.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <assert.h>
#include "tree.h"
#include "list.h"

typedef struct shelf shelf_t;

struct shelf
{
  char *name;
  int amount;
};

shelf_t *shelf_new(char *name, int amount)
{
  shelf_t *result = malloc(sizeof(struct shelf));
  *result = (shelf_t) { .name = name, .amount = amount };
  return result;
}

void shelf_delete(shelf_t *shelf)
{
  free(shelf->name);
  free(shelf);
}

char *ask_question_string(char *question)
{
  printf("%s\n", question);
  char *answer = NULL;
  size_t length = 0;
  //getline(&answer, &length, stdin);
  *strrchr(answer, '\n') = '\0';
  return answer;
}

int ask_question_int(char *question)
{
  char *str = ask_question_string(question);
  int answer = atoi(str);
  free(str);
  return answer;
}

char *uppercase(char *str)
{
  for (int i = 0; str[i]; ++i)
    {
      str[i] = toupper(str[i]);
    }
  return str;
}

bool starts_with(char *a, char *b)
{
  return strstr(a, b) == 0;
}

void print_shelf(void *s, void *p)
{
  shelf_t *shelf = s;
  printf("%s: %d\t", shelf->name, shelf->amount);

  *(int *)p += shelf->amount;
}

void print_goods(char *name, void *shelf_list, void *p)
{
  int total = 0;

  printf("%s\n\t", name);
  list_apply(shelf_list, print_shelf, &total);
  printf("Total: %d\n", total);
}

void delete_shelf(void *shelf, void *p)
{
  shelf_delete(shelf);
}

void delete_lists(char *key, void *list, void *p)
{
  free(key);
  list_apply(list, delete_shelf, p);
  list_delete(list);
}

int main(void)
{
  tree_t *db = tree_new();
  tree_t *shelves = tree_new();

  char *answer = NULL;

  do
    {
      if (answer) free(answer);

      char *goods_name = ask_question_string("Mata in namnet på en vara:");
      char *shelf_name = ask_question_string("Mata in namnet på en hylla:");
      int amount = ask_question_int("Mata in ett antal:");

      list_t *list = tree_get(db, goods_name);

      // If we are seeing this goods for the first time
      if (list == NULL)
        {
          list = list_new();
          tree_insert(db, goods_name, list);
          /// Store that shelf_name is used by item goods_name
          tree_insert(shelves, shelf_name, goods_name);
        }
      else
        {
          free(goods_name);
        }

      list_append(list, shelf_new(shelf_name, amount));

      answer = ask_question_string("Fler? (ja/nej)");
    }
  while (starts_with(answer, "#-----") == 0 || strcmp(uppercase(answer), "JA") == 0);

  if (answer) free(answer);

  tree_apply(db, print_goods, NULL);

  /// Tear down
  tree_apply(db, delete_lists, NULL);
  tree_delete(db);
  tree_delete(shelves);

  return 0;
}
