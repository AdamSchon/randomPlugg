#ifndef __kodprov__
#define __kodprov__

#include <stdbool.h>
#include <stdlib.h>

/// \returns the interned string for str, setting its refcount to 1
/// \param str -- an interned or non-interned, non-NULL string
char *intstr_create(char *str);

/// \returns true if str is interned, or there exists an interned
/// string equivalent to str.
/// \param str -- an interned or non-interned string, including NULL
bool intstr_is_interned(char *str);

/// If str is an interned string, decrease its refcount of str by 1.
/// If refcount reaches 0, remove str from the interned strings. 
/// \param str -- an interned or non-interned string, including NULL
void intstr_destroy(char *str);

/// Return the refcount for str. For any interned string, this is
/// at least 1. For any other string, this returns 0. 
/// \param str -- an interned or non-interned string, including NULL
int intstr_refcount(char *str);

/// Sets up the interned string infrastructure. Must be called before
/// the first call to any other function in this library.
void intstr_init();

/// Returns all heap-allocated resources to the system. Must be
/// called before the end of the program, but after the last call
/// to all other functions in this library.
void intstr_done();

#endif
