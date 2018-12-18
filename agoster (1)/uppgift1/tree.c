#include <stdlib.h>
#include <string.h>
#include <assert.h>
#include "tree.h"

typedef struct node node_t;

struct tree
{
  node_t *root;
};

struct node
{
  char *key;
  void *value;
  node_t *left;
  node_t *right;
};

tree_t *tree_new()
{
  return calloc(1, sizeof(struct tree));
}

node_t *node_new(char *key, void *value)
{
  assert(value);

  node_t *result = malloc(sizeof(struct node));
  *result = (node_t) { .key = key, .value = value };
  return result; 
}

node_t **tree_find(node_t **node, char *key)
{
  while (*node)
    {
      int cmp = strcmp(key, (*node)->key);

      if (cmp == 0) break;

      node = (cmp < 0) ? &(*node)->left : &(*node)->right;
    }

  return node;
}

void *tree_get(tree_t *tree, char *key)
{
  node_t **node = tree_find(&tree->root, key);

  return (*node) ? (*node)->value : NULL;
}

bool tree_insert(tree_t *tree, char *key, void *value)
{
  node_t **place = tree_find(&tree->root, key);
  if (*place) return FAILURE;

  *place = node_new(key, value);
  return SUCCESS;
}

void tree_inner_apply(node_t *node, tree_action_t fun, void *p)
{
  if (node)
    {
      tree_inner_apply(node->left, fun, p);
      fun(node->key, node->value, p);
      tree_inner_apply(node->right, fun, p);
    }
}

void tree_apply(tree_t *tree, tree_action_t fun, void *p)
{
  tree_inner_apply(tree->root, fun, p);
}

void tree_inner_delete(node_t *node)
{
  if (node)
    {
      tree_inner_delete(node->left);
      tree_inner_delete(node->right);
      free(node);
    }
}

void tree_delete(tree_t *tree)
{
  tree_inner_delete(tree->root);
  free(tree);
}
