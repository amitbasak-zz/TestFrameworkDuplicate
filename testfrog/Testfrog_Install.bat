mvn install:install-file -Dfile="%cd%\bin\testfrogforapi-0.0.1-SNAPSHOT-jar-with-dependencies.jar" -DgroupId="com.kroakyhub" -DartifactId=testfrogforapi -Dversion="0.0.1-SNAPSHOT" -Dpackaging=jar
setx DRIVER_HOME "%cd%\drivers"
pause