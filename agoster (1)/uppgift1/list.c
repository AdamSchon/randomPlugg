#include <stdlib.h>
#include "list.h"

typedef struct link link_t;

struct list
{
  link_t *first;
  link_t *last;
};

struct link
{
  void *elem;
  link_t *next;
};

struct iter
{
  link_t **current;
};

list_t *list_new()
{
  return calloc(1, sizeof(struct list));
}

link_t *link_new(void *element, link_t *next)
{
  link_t *result = malloc(sizeof(struct link));
  *result = (link_t) { .elem = element, .next = next };
  return result;
}

void list_prepend(list_t *list, void *element)
{
  if (list->first == NULL)
    {
      list->first = link_new(element, list->first);
      list->last = list->first;
    }
  else
    {
      list->first = link_new(element, list->first);
    }
}

void list_append(list_t *list, void *element)
{
  link_t *new_link = link_new(element, NULL);
  
  if (list->first == NULL)
    {
      list->first = new_link;
    }
  else
    {
      list->last->next = new_link;
    }
  list->last = new_link;
}

void list_apply(list_t *list, list_action_t fun, void *p)
{
  for (link_t *cursor = list->first; cursor; cursor = cursor->next)
    {
      fun(cursor->elem, p);
    }
}

void list_delete(list_t *list)
{
  link_t *cursor = list->first;

  while (cursor)
    {
      link_t *delete = cursor;
      cursor = cursor->next;
      free(delete);
    }

  free(list);
}

iter_t *list_iterator(list_t *list)
{
  iter_t *result = malloc(sizeof(struct iter));
  *result = (iter_t) { .current = &list->first };
  return result;
}

bool iter_has_next(iter_t *iter)
{
  return *iter->current != NULL;
}

void *iter_next(iter_t *iter)
{
  void *result = (*iter->current)->elem;
  iter->current = &(*iter->current)->next;
  return result;
}

void iter_delete(iter_t *iter)
{
  free(iter);
}

void *iter_current(iter_t *iter)
{
  return (*iter->current)->elem;
}

void iter_remove(iter_t *iter)
{
  link_t *tmp = *iter->current;
  *iter->current = tmp->next;
  free(tmp);
}
