Java version: 17

To compile and generate executable jar file:
cd Melisa-Cagilgan/Source
javac *.java
jar -cvfM ../EncounterParser.jar *.class ./META-INF

To run
cd ..
java -jar EncounterParser.jar