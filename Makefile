All: Demo
Demo: ./src/POOArticle.java ./src/POOBoard.java ./src/POODirectory.java ./src/Demo.java ./src/base_file.java
	javac -sourcepath src -d class ./src/Demo.java
run:
	java Demo
