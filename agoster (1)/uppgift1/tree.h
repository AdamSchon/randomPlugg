#pragma once

#include <stdbool.h>

typedef struct tree tree_t;
typedef void (*tree_action_t)(char *key, void *element, void *p);

#define SUCCESS true
#define FAILURE false

/// Create a new tree whose keys are strings and values void *
tree_t *tree_new();
/// Delete a tree
void tree_delete(tree_t *tree);
/// Get the value associated with the key key
void *tree_get(tree_t *tree, char *key);
/// Associate the value value with the key key in tree
bool tree_insert(tree_t *tree, char *key, void *value);
/// Visit all nodes inorder and apply tree_action to them
void tree_apply(tree_t *tree, tree_action_t fun, void *p);



