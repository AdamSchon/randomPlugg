test:
	gcc -std=gnu11 -Wall -ggdb tree.c list.c main.c
	./a.out < test.txt
	@echo "Jämför utskriften med facit från kodprovstexten"


memtest: 
	gcc -std=gnu11 -Wall -ggdb tree.c list.c main.c
	valgrind --leak-check=full ./a.out < test.txt
	@echo "Jämför utskriften med facit från kodprovstexten"

clean:
	rm -f *.o a.out
