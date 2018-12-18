#pragma once

#include <stdlib.h>
#include <stdbool.h>

#define kalloc(bytes) kalloc_calloc(bytes, __FILE__, __FUNCTION__, __LINE__)
void kalloc_init(unsigned long magic);
void *kalloc_calloc(size_t size_in_bytes, const char *file, const char * func, size_t line);
bool kalloc_free(void *);
bool kalloc_check_integrity(bool verbose);
