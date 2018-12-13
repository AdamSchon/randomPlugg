#define _GNU_SOURCE

#include <stdlib.h>
#include <stdbool.h>
#include <stdio.h>
#include <string.h>
#include "yourcode.h"

struct list
{
  link_t *first;
  link_t *last;
  void_cmp cmp;
};

static link_t *link_create(link_t *next, void *element)
{
  link_t *link = malloc(sizeof(link_t));
  link->next = next;
  link->element = element;
  return link;
}

list_t *list_create(void_cmp cmp)
{
  list_t *list = malloc(sizeof(list_t));
  list->first = list->last = link_create(NULL, NULL); /// Dummy!
  list->cmp = cmp;
  return list;
}

void list_insert(list_t *list, void *element)
{
  link_t *cursor = list->first;

  while (cursor->next && list->cmp(cursor->next->element, element) < 0)
    {
      cursor = cursor->next;
    }

  if (cursor->next)
    {
      if (list->cmp(cursor->next->element, element))
        {
          cursor->next = link_create(cursor->next, element);
        }
      else
        {
          cursor->next->element = element;
        }
    }
  else
    {
      list->last = cursor->next = link_create(NULL, element);
    }
}

int list_size(list_t *list)
{
  if (list == NULL) {
    return(-1);
  }

  link_t *curr = list->first;
  if (curr == NULL) {
    return(0);
  }

  int i = 0;
  do {
    curr = curr->next;
    i++;
  } while (curr != NULL);

  return i;
}

void list_destroy(list_t *list)
{
  if (list == NULL) {
    return;
  }

  link_t *curr = list->first;
  link_t *prev;
  do {
    prev = curr;
    curr = curr->next;
    free(prev->element);
    free(prev);
  } while (curr != NULL);

  free(list);
  /// Ta bort listan, alla länkar och alla länkars alla element
}


void list_merge(list_t *source, list_t *dest)
{
  if (dest == NULL) {
    return;
  }
  if (source == NULL) {
    return;
  }

  link_t *listA = source->first;
  link_t *listB = dest->first;
  link_t *final;
  puts((char *) listA->element);
  puts((char *) *listA->element);
  puts((char *) &listA->element);
  puts((char *) **listA->element);

  if ((char *) &listA->element < (char *) &listB->element) {
    puts("1");
    dest->first = listA;
    final = listA;
    listA = listA->next;
  } else {
    //puts((char *) listA->element);
    puts("2");
    final = listB;
    listB = listB->next;
  }
  do {
    if (listA == NULL) {
      puts("3");
      final->next = listB;
      final = final->next;
      listB = listB->next;
    }
    if (listB == NULL) {
      puts("4");
      final->next = listA;
      final = final->next;
      listA = listA->next;
    }
    if ((char *) listA->element < (char *) listB->element) {
      puts("5");
      final->next = listA;
      final = final->next;
      listA = listA->next;
    } else {
      puts("6");
      final->next = listB;
      final = final->next;
      listB = listB->next;
    }
  } while(listA != NULL && listB != NULL);

  link_t *new = link_create(NULL, NULL); //Creating dummy
  source->first = new;
  source->last = new;
  /// Alla länkar (och dess medföljande element, dock ej dummies)
  /// skall flyttas från source till dest.
}

/// Returns a string representation of the list to be printed
/// No need to read or change this function!!
char *list_print(list_t *list, elem_to_string to_string)
{
  char *result = calloc(2048, sizeof(char));
  strcat(result, "[");

  for (link_t *cursor = list->first->next; cursor; cursor = cursor->next)
    {
      char *tmp = NULL;
      asprintf(&tmp, "%s, ", to_string ? to_string(cursor->element) : (char *)cursor->element);
      strcat(result, tmp);
      free(tmp);
    }

  int size = strlen(result);
  strcpy(result + (size > 1 ? size - 2 : size), "]");

  return result;
}

/// Return the first link in the list for testing
/// No need to read or change this function!!
link_t *list_first(list_t *list)
{
  return list->first->next;
}
