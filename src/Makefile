IN=tests/in/$(CTG)/test$(TEST).in
OUT=tests/out/$(CTG)/test$(TEST).out
AUX=tests/aux/$(CTG)/test$(TEST).aux

build:
	javac Edge.java Main.java CliqueToSat.java

run_backtracking:
	java Main  $(IN) > $(OUT)

run_reduction:
	java CliqueToSat  $(IN) > $(AUX)

clean:
	rm -rf *.class
