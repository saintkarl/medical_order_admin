mvn install:install-file -DgroupId=org.springmodules -DartifactId=spring-modules-validation -Dversion=0.8 -Dpackaging=jar -Dfile=spring-modules-validation-0.8.jar -DgeneratePom=true
mvn install:install-file -DgroupId=taglibs -DartifactId=string -Dversion=1.1.0 -Dpackaging=jar -Dfile=string-1.1.0.jar -DgeneratePom=true
mvn install:install-file -DgroupId=com.amazonaws -DartifactId=aws-java-sdk -Dversion=1.4.1 -Dpackaging=jar -Dfile=aws-java-sdk-1.4.1.jar -DgeneratePom=true
mvn install:install-file -Dfile=ftp4j-1.6.jar -DgroupId=it.sauronsoftware -DartifactId=ftp4j -Dversion=1.6 -Dpackaging=jar -DgeneratePom=true
mvn install:install-file -Dfile=sqljdbc4-4.0.2206.100.jar -Dpackaging=jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0  -DgeneratePom=true