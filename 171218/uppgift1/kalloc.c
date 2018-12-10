/*
                         _______________________________________
                       / Please scroll down to the part marked   \
                       | important. Start there. You don't need  |
                       \ to read all the other stuff!            /
                         ---------------------------------------
                                \   ^__^
                                 \  (oo)\_______
                                    (__)\       )\/\
                                        ||----w |
                                         ||     ||
*/


#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#include "kalloc.h"

/// A pointer to the start of some allocated memory.
/// Note that a user's pointer starts AFTER the header.
typedef struct object obj_t;

struct object
{
  obj_t *next;       /// Internal pointer to the next memory allocation (possibly NULL)
  size_t size;       /// The size of the allocation (except obj_t header and magic)
  size_t line;       /// The line number where this allocation came from
  const char *file;  /// The file where this allocation came from
  const char *func;  /// The function where this allocation came from
  char remaining_bytes[]; /// including the magic number at the end
};

/// The magic number used to END each memory allocation
static unsigned long kalloc_magic;

/// The first memory allocation in the system (internal pointer)
static obj_t first; /// Note: not a pointer -- so it can be used as a sentinel

/// The last memory allocation in the system (internal pointer)
static obj_t *last = &first;

/// Takes a userland pointer to an object and returns an internal pointer to
/// the start of the allocated memory
///
///               ,----------------.
///               |                V
///               |       ,-> HHHH_DDDDDDDD_MM
///               |       |
/// kalloc_header(') == --'
#define kalloc_header(p) (((obj_t *)p) - 1)

/// Takes an internal pointer to a memory allocation and
/// returns its corresponding userland pointer
///
///                      ,---------.
///                      |         V
///               ,---------> HHHH_DDDDDDDD_MM
///               |      |
/// kalloc_object(') == -'
#define kalloc_object(p) (((obj_t *)p) + 1)

/// Takes an internal pointer to a memory allocation and
/// returns a pointer to its magic number (to read or write)
unsigned long *kalloc_magic_number(obj_t *p)
{
  return (unsigned long *) (((char *)kalloc_object(p)) + p->size);
}

/// Sets the magic number that ENDs each memory allocation. Must
/// be called exactly once for each program and before the first
/// kalloc call.
void kalloc_init(unsigned long magic)
{
  assert(kalloc_magic == 0);
  kalloc_magic = magic;
  assert(kalloc_magic != 0);
}

size_t kalloc_internal_size(size_t external_size_in_bytes)
{
  return sizeof(obj_t) + external_size_in_bytes + sizeof(unsigned long);
}

/// Allocates N bytes of memory where N = size_in_bytes + object +
/// magic number. Initialises object from file, func and line and
/// uses the global magic number. Returns a userland pointer.
void *kalloc_calloc(size_t size_in_bytes, const char *file, const char * func, size_t line)
{
  assert(kalloc_magic != 0);

  /// Create new internal pointer
  obj_t *p = calloc(1, kalloc_internal_size(size_in_bytes));

  p->size = size_in_bytes;
  p->line = line;
  p->file = file;
  p->func = func;

  *kalloc_magic_number(p) = kalloc_magic;

  if (first.next == NULL)
    {
      first.next = p;
      last = p;
    }
  else
    {
      last->next = p;
      last = p;
    }

  /// Translate internal pointer => userland pointer
  return kalloc_object(p);
}

/// ================================ This is the important part ==================================
/*
              ___________________________
            < This is the important part! >
              ---------------------------
                     \   ^__^
                      \  (oo)\_______
                         (__)\       )\/                  \
                             ||----w |
                             ||     ||
*/
///
/// Function: kalloc_inner_find (called once in kalloc_free)
///
///         ---- Explanation: ----
///
/// 1. mem _points_ to the first link in a linked list of internal pointers to allocated blocks
/// 2. each allocated block contains an address which we call a userland pointer.
/// 3. if you have a userland pointer, you can get its internal pointer using kalloc_header()
/// 4. if you have an internal pointer, you can get its userland pointer using kalloc_object()
///
/// Your task is to finish the implementation of kalloc_inner_find so that it
/// searches the linked list of allocated blocks starting in mem for the block whose userland address is p.
///
/// Returns a pointer to the _previous_ node (to support unlinking) to the one whose block
/// contains p, or NULL if p is not found in any block.
///
/// Example 1: if p is the pointer the start of Data47, the result below should be mem.
/// Example 2: if p is the pointer the start of Data52, the result below should be mem->next).
///
///            ,--------------.      ,--------------.
///   ,---->  H'H_Data42_MM   '---> H'H_Data47_MM   '--->H(null)H_Data52_MM
///   |
///   '-- mem
///
///
/// NOTE: p is a userland pointer
/// NOTE: mem is an internal pointer to an allocated block
///
/// NOTE -- if you want to test this function from your main file, remove the static
///         keyword and add obj_t *kalloc_inner_find(obj_t *, void *); to kalloc.h.
///         (You can also use the commented main function after the end of the important
///         part.)
///
static
obj_t *kalloc_inner_find(obj_t *mem, void *p)
{
    do{
      if (kalloc_header(p) == mem->next) return mem;

      mem = mem->next;
    } while mem;

    return NULL;
}
/// ================================ End of the important part ===================================
///
/// Hint: you can look in kalloc_free to see how kalloc_inner_find is being used.
///

/*
/// The following code tests example 1 and 2 above -- you can
/// uncomment this code and compile kalloc.c as a program of its
/// own if you want to test it.

int main(int argc, char *argv[argc])
{
  kalloc_init(0xC0DEFEFE);
  void *a = kalloc(42);
  void *b = kalloc(47);
  void *c = kalloc(52);

  assert(kalloc_inner_find(&first, a)->next == kalloc_header(a));
  assert(kalloc_inner_find(&first, b)->next == kalloc_header(b));
  assert(kalloc_inner_find(&first, c)->next == kalloc_header(c));

  return 0;
}
*/


/// Helper function that prints a message about a failed integrity
/// check for a given object
static
void kalloc_log_integrity_fail(obj_t *h)
{
  printf("Integrity check failed for: %p (%zd bytes, allocated at %s:%zd in %s) -- expected Ox%lX, found Ox%lX\n",
         (void *)kalloc_object(h), h->size, h->file, h->line, h->func, kalloc_magic, *kalloc_magic_number(h));
}

/// Free p and check that we did not write past the end of p
/// Returns true if p could be freed (regardless of integrity check), else false
bool kalloc_free(void *p)
{
  /// Find the pointer pointing to the memory we want to free (*slab is an internal pointer)
  /// Note: first is a global variable, holding a dummy (sentinel) node, _before_ the first block in a list of kallocated blocks
  obj_t *slab = kalloc_inner_find(&first, p);

  if (slab == NULL) {
    return false; /// Trying to free something which was not kallocated!
  }

  /// Save a temporary internal pointer to the memory we want to free
  obj_t *tmp = slab->next;

  /// Perform an integrity check
  if (*kalloc_magic_number(tmp) != kalloc_magic) kalloc_log_integrity_fail(tmp);

  /// Unlink the memory we are about to deallocate from the list of live objects
  slab->next = slab->next->next;

  /// Zero out the memory
  memset(tmp, 0, kalloc_internal_size(tmp->size));

  /// Finally free the memory
  free(tmp);

  return true;
}

bool kalloc_check_integrity(bool verbose)
{
  bool all_ok = true;

  for (obj_t *cursor = first.next; cursor; cursor = cursor->next)
    {
      bool ok = *kalloc_magic_number(cursor) == kalloc_magic;
      all_ok &= ok;

      if (verbose)
        {
          printf("Checking integrity for: %p (%zd bytes, allocated at %s:%zd in %s) ... %s\n",
                 (void *)kalloc_object(cursor), cursor->size, cursor->file, cursor->line, cursor->func, ok ? "OK" : "FAILED");
        }
      else
        {
          if (!ok) kalloc_log_integrity_fail(cursor);
        }
    }

  return all_ok;
}
