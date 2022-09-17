Java version: 17

To compile and generate executable jar file:
cd hw1/Source
javac *.java
jar -cvfM ../EncounterManager.jar *.class ./META-INF

To run
cd ..
java -jar EncounterManager.jar
