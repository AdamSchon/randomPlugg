#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include "list.h"

/// Elts must be at least 4
#define Elts 16

#define assert_eq(v, expected)                 \
  do { \
    int a = (v); int b = (expected);                                    \
    if (a != b) printf("Assertion failed: %d != %d \t\t\t\t | %s:%d (%s)\n", a, b, __FILE__, __LINE__, __FUNCTION__); } while (0);


void test_forward_iteration()
{
  puts("TEST1");
  list_t *list = ioopm_list_create();
  int expected[Elts];

  for (int i = 0; i < Elts; ++i)
    {
      int v = i;
      expected[i] = v;
      ioopm_list_append(list, v);
    }

  for (int i = 0; i < Elts; ++i)
    {
      int v = *ioopm_list_get(list, i);
      /// If this test failed, then either your append is not
      /// really appending, or your get does not go forward in the
      /// list correctly, or both.
      assert_eq(v, expected[i]);
    }
  printf("TEST2");
  ioopm_list_destroy(list);
}

void test_backward_iteration()
{
  list_t *list = ioopm_list_create();
  int expected[Elts];

  for (int i = 0; i < Elts; ++i)
    {
      int v = i;
      expected[i] = v;
      ioopm_list_prepend(list, v);
    }

  for (int i = 0; i < Elts; ++i)
    {
      int v = *ioopm_list_get(list, -(i + 1));
      /// If this test failed, then either your prepend is not
      /// really prepending, or your get does not go backwards in
      /// the list correctly, or both.
      assert_eq(v, expected[i]);
    }

  ioopm_list_destroy(list);
}

/// This test creates a list ...210012... and then reads from both
/// ends and checks that the values obtained are the same, e.g.,
/// (2,2), (1,1), (0,0) in the case above.
void test_pallindrome()
{
  list_t *list = ioopm_list_create();

  for (int i = 0; i < Elts / 2; ++i)
    {
      int v = i * 2;
      ioopm_list_prepend(list, v);
      ioopm_list_append(list, v);
    }

  for (int i = 0; i < Elts; ++i)
    {
      int v1 = *ioopm_list_get(list, i);
      int v2 = *ioopm_list_get(list, -(i + 1));
      /// This is integration of append, prepend, and forward and
      /// backward iteration at the same time. If the two tests
      /// above succeed, this should not fail --- unless there are
      /// bugs that only surface in the combination of e.g.
      /// forward + prepend, etc.
      assert_eq(v1, v2);
    }

  ioopm_list_destroy(list);
}

/// This test creates a list 01234..., then removes every second
/// element, leaving, 024... It also tests that removes of invalid
/// indices are ignored.
void test_remove()
{
  list_t *list = ioopm_list_create();

  for (int i = 0; i < Elts; ++i)
    {
      ioopm_list_append(list, i);
    }

  for (int i = 1; i < Elts / 2 + 1; ++i)
    {
      ioopm_list_remove(list, i);
    }

  for (int i = 0; i < Elts / 2; ++i)
    {
      int v1 = *ioopm_list_get(list, i);
      /// If this test fails, then remove has destroyed a next
      /// link somwhere
      assert_eq(v1, i * 2);
    }

  for (int i = -1; i >= -(Elts / 2); --i)
    {
      int v1 = *ioopm_list_get(list, i);
      /// If this test fails, then remove has destroyed a prev
      /// link somwhere
      assert_eq(v1, Elts + (2 * i));
    }

  /// If this test fails, then remove did not catch an invalid
  /// index (larger than the list)
  assert_eq(ioopm_list_remove(list, Elts), false);
  /// If this test fails, then remove did not catch an invalid
  /// index (larger than the list, in the negative)
  assert_eq(ioopm_list_remove(list, -(Elts + 1)), false);

  ioopm_list_destroy(list);
}

/// This test creates a bunch of lists with a combination of
/// append, prepend and remove and check that their sizes are
/// correct.
void test_size()
{
  list_t *list = NULL;

  list = ioopm_list_create();
  for (int i = 0; i < Elts; ++i) ioopm_list_append(list, i);
  /// If this test fails, then size does not count nodes correctly
  /// (are you counting the sentinel?)
  assert_eq(ioopm_list_size(list), Elts);
  ioopm_list_destroy(list);

  list = ioopm_list_create();
  for (int i = 0; i < Elts; ++i) ioopm_list_prepend(list, i);
  /// If this test fails, then size does not count nodes correctly
  /// (are you counting the sentinel?)
  assert_eq(ioopm_list_size(list), Elts);
  ioopm_list_destroy(list);

  list = ioopm_list_create();
  for (int i = 0; i < Elts; ++i) { ioopm_list_append(list, i); ioopm_list_prepend(list, i); }
  for (int i = 0; i < Elts; ++i) { ioopm_list_remove(list, i); }
  /// If this test fails (but not the two above), then remove is likely broken.
  assert_eq(ioopm_list_size(list), Elts);
  ioopm_list_destroy(list);
}

/// This test checks that contains work and that remove does not break the list.
void test_contains1()
{
  list_t *list = ioopm_list_create();
  for (int i = 0; i < Elts; ++i) ioopm_list_append(list, i);
  assert_eq(ioopm_list_contains(list, 0), true);
  if (4 < Elts) assert_eq(ioopm_list_contains(list, 4), true);
  if (4 < Elts) assert_eq(ioopm_list_contains(list, 9), true);
  assert_eq(ioopm_list_contains(list, Elts), false);
  ioopm_list_remove(list, 0);
  assert_eq(ioopm_list_contains(list, 0), false);
  ioopm_list_destroy(list);
}

/// This test checks that contains work and that remove does not break the list.
void test_contains2()
{
  list_t *list = ioopm_list_create();
  for (int i = 0; i < Elts; ++i) ioopm_list_append(list, i);
  assert_eq(ioopm_list_contains(list, 0), true);
  if (4 < Elts) assert_eq(ioopm_list_contains(list, 4), true);
  if (4 < Elts) assert_eq(ioopm_list_contains(list, 9), true);
  assert_eq(ioopm_list_contains(list, Elts), false);
  ioopm_list_append(list, Elts);
  assert_eq(ioopm_list_contains(list, Elts), true);
  ioopm_list_remove(list, 0);
  assert_eq(ioopm_list_contains(list, 0), false);
  ioopm_list_destroy(list);
}

int main(void)
{
  printf("HEJ\n");
  test_forward_iteration();
  test_backward_iteration();
  test_pallindrome();
  test_remove();
  test_contains1();
  test_contains2();
  test_size();

  puts("If you see this message, and no error messages above -- all tests passed!");

  return 0;
}
