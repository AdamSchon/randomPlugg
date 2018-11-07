test:
	gcc -g -Wall interned.c driver.c
	./a.out

memtest:
	gcc -g -Wall interned.c driver.c
	valgrind --leak-check=full ./a.out
