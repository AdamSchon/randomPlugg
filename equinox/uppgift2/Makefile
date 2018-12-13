compile:
	mkdir -p classes
	javac -d classes ASTFixed.java YourCode.java Driver.java

run:
	@java -cp classes Driver

test: compile
	@echo "Running tests and saving result in output.txt"
	@java -cp classes Driver 2> /dev/null 1> output.txt || (echo "\n\nChange Driver.java line 12 before running tests!\n\n" && exit 1)
	@echo "Comparing output.txt with expected_output.txt -- (Printing only differences, your output left, expected output right.)\n"
	@diff -b -i -y --suppress-common-lines output.txt expected_output.txt && echo "Complete match -- tests PASS"

clean:
	rm -rf classes output.txt
