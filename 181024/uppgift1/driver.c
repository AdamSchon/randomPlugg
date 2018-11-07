#define XOPEN_SOURCE 500

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include "interned.h"

#define assert_eq(got, expected)                                        \
  do {                                                                  \
    int _b_ = (got); int _a_ = (expected);                              \
    if (_a_ != _b_) printf("Assertion failed: expected %d, got %d \t\t\t\t | %s:%d (%s)\n", _a_, _b_, __FILE__, __LINE__, __FUNCTION__); } \
  while (0);

#define assert_eq_ptr(got, expected)                                    \
  do {                                                                  \
    void *_b_ = (got); void *_a_ = (expected);                          \
    if (_a_ != _b_) printf("Assertion failed: expected %p, got %p \t\t\t\t | %s:%d (%s)\n", _a_, _b_, __FILE__, __LINE__, __FUNCTION__); } \
  while (0);


void test_that_I_can_create_interned_strings_from_strings_in_the_binary()
{
  char *a = "String in the binary";
  char *str = intstr_create(a);
  assert_eq(strcmp(str, a), 0);
  assert_eq(intstr_is_interned(str), true);
  assert_eq(intstr_refcount(str), 1);
  intstr_destroy(str);
  assert_eq(intstr_is_interned(a), false);
}

void test_that_I_can_create_interned_strings_from_strings_on_the_stack()
{
  char a[] = "String on the stack";
  char *str = intstr_create(a);
  assert_eq(strcmp(str, a), 0);
  assert_eq(intstr_is_interned(str), true);
  assert_eq(intstr_refcount(str), 1);
  intstr_destroy(str);
  assert_eq(intstr_is_interned(a), false);
}

void test_that_I_can_create_interned_strings_from_strings_on_the_heap()
{
  char *a = strdup("String on the heap");
  char *str = intstr_create(a);
  assert_eq(strcmp(str, a), 0);
  assert_eq(intstr_is_interned(str), true);
  assert_eq(intstr_refcount(str), 1);
  intstr_destroy(str);
  assert_eq(intstr_is_interned(a), false);
  free(a);
}

void test_that_I_can_call_destroy_with_non_interned_strings()
{
  char *a = "String in the binary";
  char b[] = "String on the stack";
  char *c = strdup("String on the heap");

  /// Yes -- all of these should work without crashing
  intstr_destroy(a);
  intstr_destroy(b);
  intstr_destroy(c);
  intstr_destroy(NULL);

  free(c);
}

void test_that_I_can_call_refcount_with_non_interned_strings()
{
  char *a = "String in the binary";
  char b[] = "String on the stack";
  char *c = strdup("String on the heap");

  /// Yes -- all of these should work without crashing
  assert_eq(intstr_refcount(a), 0);
  assert_eq(intstr_refcount(b), 0);
  assert_eq(intstr_refcount(c), 0);
  assert_eq(intstr_refcount(NULL), 0);

  free(c);
}

void test_that_I_can_call_is_interned_with_non_interned_strings()
{
  char *a = "String in the binary";
  char b[] = "String on the stack";
  char *c = strdup("String on the heap");

  /// Yes -- all of these should work without crashing
  assert_eq(intstr_is_interned(a), false);
  assert_eq(intstr_is_interned(b), false);
  assert_eq(intstr_is_interned(c), false);
  assert_eq(intstr_is_interned(NULL), false);

  free(c);
}

void test_that_multiple_calls_to_create_returns_the_same_string()
{
  char *a = "test_that_multiple_calls_to_create_returns_the_same_string";

  /// An interned string is equivalent to its "origin"
  char *str = intstr_create(a);
  assert_eq(strcmp(str, a), 0);

  /// Subsequent calls to intstr_create return the same string and
  char *b = intstr_create(a); /// using a
  assert_eq(strcmp(str, b), 0);
  char *c = intstr_create(str); /// using the non-interned string
  assert_eq(strcmp(str, c), 0);

  /// Note -- this test keeps a interened to test memory reclamation in done
}

void test_that_multiple_calls_to_create_increases_refcount()
{
  char *a = "test_that_multiple_calls_to_create_increases_refcount";

  /// An interned string has a refcount of 1
  char *str = intstr_create(a);
  assert_eq(intstr_refcount(str), 1);

  /// Subsequent calls to intstr_create increases refcount
  intstr_create(a);
  assert_eq(intstr_refcount(str), 2);
  intstr_create(a);
  assert_eq(intstr_refcount(str), 3);

  /// Note -- this test keeps a interened to test memory reclamation in done
}

void test_that_multiple_calls_to_destroy_decreases_refcount()
{
  char *a = "test_that_multiple_calls_to_destroy_decreases_refcount";

  /// Create an interned string withrefcount of 3
  char *str = intstr_create(a);
  intstr_create(a);
  intstr_create(a);
  assert_eq(intstr_refcount(str), 3);

  /// Destroy decreases refcount
  intstr_destroy(str);
  assert_eq(intstr_refcount(str), 2);

  /// Possible to increment after destroy
  intstr_create(a);
  assert_eq(intstr_refcount(str), 3);

  /// Multiple calls to destroy back to back
  intstr_destroy(str);
  intstr_destroy(str);
  assert_eq(intstr_refcount(str), 1);

  /// Release the string
  intstr_destroy(str);
}

void test_that_create_following_destroy_still_returns_the_same_string()
{
  char *a = "test_that_create_following_destroy_still_returns_the_same_string";
  char *b = strdup(a);

  char *str = intstr_create(a);
  assert_eq_ptr(intstr_create(a), str);
  intstr_destroy(str);
  assert_eq_ptr(intstr_create(b), str);
  intstr_destroy(str);
  intstr_destroy(str);

  free(b);
}

void test_that_calls_to_destroy_with_a_copy_does_not_decrease_refcount_for_interned_original()
{
  char *a = "test_that_calls_to_destroy_yada_yada_for_interned_original";

  char *str = intstr_create(a);
  assert_eq(intstr_refcount(str), 1);
  intstr_destroy(a);
  assert_eq(intstr_refcount(str), 1);
  intstr_destroy(str);
}

int main(int argc, char *argv[])
{
  intstr_init();

  test_that_I_can_create_interned_strings_from_strings_in_the_binary();
  test_that_I_can_create_interned_strings_from_strings_on_the_stack();
  test_that_I_can_create_interned_strings_from_strings_on_the_heap();

  test_that_multiple_calls_to_create_returns_the_same_string();
  test_that_create_following_destroy_still_returns_the_same_string();
  puts("1");

  test_that_I_can_call_destroy_with_non_interned_strings();
  puts("2");
  test_that_I_can_call_refcount_with_non_interned_strings();

  test_that_multiple_calls_to_create_increases_refcount();
  puts("3");
  test_that_multiple_calls_to_destroy_decreases_refcount();
  puts("4");
  test_that_calls_to_destroy_with_a_copy_does_not_decrease_refcount_for_interned_original();
  puts("5");

  intstr_done();

  return 0;
}
