
CC=gcc
CFLAGS=-Wall -std=c11 -ggdb

all: test

a.out: driver.c interned.c
	$(CC) $(CFLAGS) interned.c driver.c

test: a.out
	./a.out

memtest: a.out
	valgrind --leak-check=full ./a.out

clean:
	rm -f *.o a.out
