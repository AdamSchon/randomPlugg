#pragma once

#include <stdbool.h>

typedef struct list list_t;
typedef struct iter iter_t;
typedef void (*list_action_t)(void *element, void *p);
typedef bool (*list_cmp_t)(void *element, void *p);

/// Create a new empty list
list_t *list_new();
/// Delete a list
void list_delete(list_t *list);
/// Prepend an element to the list
void list_prepend(list_t *list, void *element);
/// Append an elemnt to the list
void list_append(list_t *list, void *element);
/// Visit all elements in list order and apply fun to them
void list_apply(list_t *list, list_action_t fun, void *p);

/// List iterator -- useful for searching though a list

/// Get iterator for list, positioned at the first element
iter_t *list_iterator(list_t *list);
/// Returns true if an iterator is not positioned at the end of a list
bool iter_has_next(iter_t *iter);
/// Return the next element
void *iter_next(iter_t *iter);
/// Removes the current element
void iter_remove(iter_t *iter);
/// Returns the current element
void *iter_current(iter_t *iter);
/// Delete an iterator
void iter_delete(iter_t *iter);
