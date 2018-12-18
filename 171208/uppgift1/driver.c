#include "kalloc.h"
#include <string.h>
#include <stdio.h>
#include <stdbool.h>
#include <assert.h>

int main(int argc, char *argv[argc])
{
  /// Initialise the system -- put 0xDEADBEEF right after each allocation
  kalloc_init(0xDEADBEEF);

  /// Allocate 42 bytes
  void *leak = kalloc(42);

  /// Allocate 4 bytes
  char *str = kalloc(4);

  /// This will write 5 bytes to a 4 byte string, causing
  /// subsequent integrity checks to FAIL
  strcpy(str, "heja");

  puts("\nTest 1: ------------------------");
  /// Freeing str will perform the integrity check and print the
  /// FAIL above
  printf(
         "Expected output:\n"
         "Integrity check failed for: %p (4 bytes, allocated at driver.c:16 in main) -- expected OxDEADBEEF, found OxDEADBE00\n"
         "Actual output:\n",
         (void *)str
         );
  bool free_str_1 = kalloc_free(str);
  /// This should succeed
  assert(free_str_1 == true);

  /// Double free is "supported" in the sense that we ignore the
  /// second free
  bool free_str_2 = kalloc_free(str);
  /// This should not succeed since str is already free'd
  assert(free_str_2 == false);

  puts("\nTest 2: ------------------------");
  printf(
         "Expected output:\n"
         "Successfully ignored second attempt at freeing str\n"
         "Actual output:\n"
         );
  if (free_str_2 == false)
    {
      puts("Successfully ignored second attempt at freeing str");
    }
  else
    {
      puts("ERROR: did not ignore second attempt at freeing str");
    }

  puts("\nTest 3: ------------------------");
  /// Only have one live allocation (in leak) -- this should find
  /// that and report that its integrity is OK
  printf(
         "Expected output:\n"
         "Checking integrity for: %p (42 bytes, allocated at driver.c:13 in main) ... OK\n"
         "Actual output:\n",
         leak
         );
  kalloc_check_integrity(true);
  puts("--------------------------------");

  /// Freeing this so valgrind can report zero leaks
  kalloc_free(leak);

  return 0;
}
