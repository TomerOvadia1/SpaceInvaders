# id: 313382442
# user: ovadiat4
compile: bin
	find src -name "*.java" > sources.txt
	javac -d bin -cp biuoop-1.4.jar @sources.txt
	rm sources.txt
run:
	java -cp biuoop-1.4.jar:bin:resources game.Ass7Game 
jar:
	jar cfm space-invaders.jar Manifest.mf -C bin . -C resources .
bin:
	mkdir bin
