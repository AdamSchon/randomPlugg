all:
	gcc -Wall -ggdb driver.c kalloc.c

test-hard:
	gcc -Wall -ggdb driver.c kalloc.c
	./a.out

test-soft:
	gcc -Wall -ggdb -DNDEBUG -Wno-unused-variable driver.c kalloc.c
	./a.out

memtest:
	gcc -Wall -ggdb driver.c kalloc.c
	valgrind --leak-check=full ./a.out
