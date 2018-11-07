#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
#include <assert.h>
#include "interned.h"

#define No_Buckets 17

typedef struct entry entry_t;

struct entry
{
  char *string;
  int refcount;
  entry_t *next;
};

/// Global variable..
static entry_t **buckets; /// Denna kallas "strings" i 2.1.4 i uppgiftstexten.

/// Calculate hash for a string -- no need to read this function
static unsigned long string_hash(char *str)
{
  unsigned long result = 0;
  do
    {
      result = result * 31 + *str;
    }
  while (*++str != '\0');
  return result;
}

/// Helper function to create an entry in the hash table. Probably
/// no need to change it, but allowed.
static entry_t *entry_create(char *str, entry_t *next)
{
  entry_t *result = malloc(sizeof(entry_t));
  *result = (entry_t) {
    .string = strdup(str),
    .refcount = 1,
    .next = next
  };
  return result;
}

/// Returns the interned string for str. If str is already
/// interned, increase refcount by 1, else set initial refcount to
/// 1.
char *intstr_create(char *str)
{
  /// Get the hash code for the string and use it to find the
  /// right bucket
  unsigned long bucket = string_hash(str) % No_Buckets;
  entry_t *entry = buckets[bucket];

  /// Search through the bucket for a string
  while (entry)
    {
      /// Use strcmp here -- because we expect str to be not interned
      if (strcmp(str, entry->string) == 0)
        {
          /// If the string was already interned, return the
          /// interned string and increase its refcount by 1
          ++entry->refcount;
          return entry->string;
        }
      entry = entry->next;
    }

  /// First time called on str -- create a new entry, add it to
  /// the front of the bucket, and return the entry's string
  entry_t *new_entry = entry_create(str, buckets[bucket]);
  buckets[bucket] = new_entry;

  return new_entry->string;
}

/// Returns true if str is interned, else false
bool intstr_is_interned(char *str)
{
  unsigned long bucket = string_hash(str) % No_Buckets;
  entry_t *entry = buckets[bucket];

  while (entry)
    {
      /// Use strcmp here -- because we expect str to be not interned
      if (strcmp(str, entry->string) == 0)
        {
          /// If the string was already interned, return true
          return true;
        }
      entry = entry->next;
    }

  return false; /// remove -- placed here to get the file to compile
}

/// Decrease the refcount of str by 1. If refcount hits 0, remove
/// the entry for the interned string and the interned string from
/// memory.
void intstr_destroy(char *str)
{
  unsigned long bucket = string_hash(str) % No_Buckets;
  entry_t *entry = buckets[bucket];

  if (entry) {
    if (entry->string == str) {
      buckets[bucket] = entry->next;
      free(entry);
      //Can I return in void function? Else make it skip next while loop
      return();
    }
    while (entry->next)
      {
        /// Use strcmp here -- because we expect str to be not interned
        if (str == entry->next->string)
          {
              if (entry->next->refcount > 1) {
                --entry->next->refcount;
                break;
              } else {
                entry_t *removed = entry->next;
                entry->next = removed->next;
                free(removed);
                break;
              }
          }
        entry = entry->next;
      }
    }
}

/// Return the refcount for str if str is interned, else 0.
int intstr_refcount(char *str)
{

  unsigned long bucket = string_hash(str) % No_Buckets;
  entry_t **entry = &buckets[bucket];

  while (*entry)
    {
      if (str == (*entry)->string)
        {
          return (*entry)->refcount;
        }
        if (str == NULL) return 0;
      entry = &(*entry)->next;
    }

  return 0;
}

void intstr_init()
{
  /// Initiate the global array of buckets
  buckets = calloc(No_Buckets, sizeof(entry_t *));
}

void intstr_done()
{
  for (i = 0; i < No_Buckets; i++) {
    entry_t entry = buckets[i]
    while (entry) {
      intstr_destroy(entry->string);
      entry = buckets[i];
    }
  }
  free(buckets);
}
