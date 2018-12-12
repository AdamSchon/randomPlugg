#include <stdlib.h>
#include <stdio.h>

typedef struct entry entry_t;
typedef struct hash_table hash_table_t;

struct entry {
  int key;
  char *value;
  entry_t *next;
};

struct hash_table {
  entry_t *buckets[19];
};

hash_table_t *create_hash_table() {
  hash_table_t *ht = malloc(sizeof(hash_table_t));
  return(ht);
}

void hash_table_insert(hash_table_t *ht, int key, char *value) {
  entry_t *entry = malloc(sizeof(entry_t));
  entry->key = key;
  entry->value = value;
  entry_t *bucket = ht->buckets[key % 19];

  if (bucket == NULL) {
    buckets[key % 19] = entry;
    return;
  }
  
  do {
    if (bucket->key == key) {
      return;
    }
    bucket = bucket->next;
  } while(bucket->next != NULL);

  if (bucket->key == key) {
    return;
  }
  bucket->next = entry;
}

void *hash_table_lookup(hash_table_t *ht, int key) {
  entry_t *bucket = ht->buckets[key % 19];

  do {
    if (bucket->key == key) {
      return(&bucket);
    }
    bucket = bucket->next;
  } while(bucket != NULL);
  return(NULL);
}

char *hash_table_remove(hash_table_t *ht, int key) {
  entry_t *bucket = ht->buckets[key % 19];

  do {
    if (bucket->next->key == key) {
      entry_t *temp = bucket->next;
      bucket->next = temp->next;
      char *returnValue = temp->value;
      free(temp);
      return(returnValue);
    }
  } while (bucket->next != NULL);
  return(NULL);
}

int main(int argc, char *argv[]) {
  hash_table_t *ht = create_hash_table();
  char *one = "hejsan";
  hash_table_insert(ht, 14, one);
  char *two = hash_table_lookup(ht, 14);
  printf("%s", two);
  hash_table_remove(ht, 14);
  char *three = hash_table_lookup(ht, 14);
  printf("%s", three);
}
