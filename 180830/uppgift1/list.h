#ifndef __kodprov__
#define __kodprov__

#include <stdbool.h>

typedef struct list list_t;

/// Skapar en tom lista 
list_t *ioopm_list_create(); 

/// Tar bort en lista ur minnet
void ioopm_list_destroy(list_t *list); 

/// Stoppar in ett element FÖRST i listan
void ioopm_list_prepend(list_t *list, int element);

/// Stoppar in ett element SIST i listan
void ioopm_list_append(list_t *list, int element);

/// Tar bort elementet på plats index. Negativa
/// index räknas bakifrån, dvs. -1 är sista elementet.
/// Valida index är [-N,-1] och [0,N) för en lista med N element.
/// \returns true om elementet togs bort.
bool ioopm_list_remove(list_t *list, int index); 

/// Returnerar elementet på plats index. Negativa
/// index räknas bakifrån, dvs. -1 är sista elementet.
/// Valida index är [-N,-1] och [0,N) för en lista med N element.
/// \returns en pekare till int:en i listan -- NULL om den inte fanns
int *ioopm_list_get(list_t *list, int index); 

/// Kontrollerar om en lista innehåller ett viss element
/// \returns true om elementet finns i listan.
bool ioopm_list_contains(list_t *list, int element); 

/// OBS! Implementeras i O(n) tid, inte O(1). 
/// \returns antalet element i listan. 
int ioopm_list_size(list_t *list); 
#endif
