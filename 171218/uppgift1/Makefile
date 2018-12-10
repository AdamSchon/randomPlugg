all:
	gcc -Wall -ggdb -std=gnu99 driver.c kalloc.c

test-hard:
	gcc -Wall -ggdb -std=gnu99 driver.c kalloc.c
	./a.out

test-soft:
	gcc -Wall -ggdb -std=gnu99 -DNDEBUG -Wno-unused-variable driver.c kalloc.c
	./a.out

memtest:
	gcc -Wall -ggdb -std=gnu99 driver.c kalloc.c
	valgrind --leak-check=full ./a.out

clean:
	rm -f a.out
