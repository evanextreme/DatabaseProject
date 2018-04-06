call mvn clean install
call java -jar -Dserver.port=80 target\RetailStore-0.0.1-SNAPSHOT.jar
pause