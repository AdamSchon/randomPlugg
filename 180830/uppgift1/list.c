#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "list.h"

typedef struct list list_t;
typedef struct node node_t;

struct list
{
  node_t *first;
};

struct node
{
  node_t *previous;
  node_t *next;
  int element;
};

/// Skapar en ny node med ett givet element (du får ändra denna om du vill)
static node_t *node_create(int element)
{
  node_t *result = malloc(sizeof(node_t));
  *result = (node_t) {
    .element = element
  };
  return result;
}

list_t *ioopm_list_create()
{
  list_t *newList = malloc(sizeof(list_t));
  node_t *firstNode = node_create(NULL);
  *newList = (list_t) {
    .first = firstNode
  };
  return newList;
}

void ioopm_list_destroy(list_t *list)
{
  do {
    ioopm_list_remove(list, 1);
  } while (list->first->next != list->first);
  free(list->first);
  free(list);
}

void ioopm_list_prepend(list_t *list, int element)
{
  node_t *newNode = node_create(element);

  list->first->next->previous = newNode;
  newNode->next = list->first->previous;
  newNode->previous = list->first;
  list->first->next = newNode;
}

void ioopm_list_append(list_t *list, int element)
{
  node_t *newNode = node_create(element);

  list->first->previous->next = newNode;
  newNode->previous = list->first->previous;
  newNode->next = list->first;
  list->first->previous = newNode;
}

bool ioopm_list_remove(list_t *list, int index)
{
  node_t *stop = list->first;
  node_t *cursor = list->first;
  if (index >= 0){
    cursor = stop->next;
    for (int i = 0; index != i && cursor != stop; i++) {
      cursor = cursor->next;
    }
  } else {
    cursor = stop->previous;
    for (int i = -1; index != i && cursor != stop; i--) {
      cursor = cursor->previous;
    }
  }

  if (cursor == stop) return false;

  cursor->previous->next = cursor->next;
  cursor->next->previous = cursor->previous;
  free(cursor);
  return(true);
}

int *ioopm_list_get(list_t *list, int index)
{
  node_t *stop = list->first;
  node_t *cursor = list->first;

  if (index >= 0){
    cursor = stop->next;
    for (int i = 0; index != i && cursor != stop; i++) {
      cursor = cursor->next;
    }
  } else {
    cursor = stop->previous;
    for (int i = -1; index != i && cursor != stop; i--) {
      cursor = cursor->previous;
    }
  }

  if (cursor == stop) return NULL;

  return(*cursor);
}

/// Du får ändra på denna kod om du vill -- t.ex. för att
/// den inte funkar enligt din design, eller för att det
/// är enklare att skriva om den än att förstå den!
bool ioopm_list_contains(list_t *list, int element)
{
  node_t *stop = list->first;
  node_t *cursor = stop->next;

  while (cursor != stop)
    {
      if (cursor->element == element) return true;
      cursor = cursor->next;
    }

  return false;
}

int ioopm_list_size(list_t *list)
{
  node_t *stop = list->first;
  node_t *cursor = stop->next;
  int size = 0;

  do {
    cursor = cursor->next;
    size++;
  } while(cursor != stop);
  return(size);
}
