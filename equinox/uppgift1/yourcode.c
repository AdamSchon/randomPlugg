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
  if (list->first->next == NULL) {
    return(0);
  }

  link_t *curr = list->first->next;
  int i = 1;

  do {
    curr = curr->next;
    i++;
  } while (curr != list->last);

  return i;
}

void list_destroy(list_t *list)
{
  if (list == NULL) {
    return;
  }

  link_t *curr = list->first->next;
  link_t *prev;
  do {
    prev = curr;
    curr = curr->next;
    free(prev->element);
    free(prev);
  } while (curr != list->last);

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

  link_t *cursor = dest->first;
  link_t *temp;
  do {
    if (dest->cmp(source->first->next->element, cursor->next->element) > 0) {
      //source->first->next is bigger than cursor
      puts("1");
      cursor = cursor->next;
    } else {
      puts("2");
      //Putting source first next before cursor->next.

      temp = source->first->next->next;
      source->first->next->next = cursor->next;
      cursor = source->first->next;
      source->first->next = temp;
    }
  } while (source->first->next != NULL && cursor->next != NULL);
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
