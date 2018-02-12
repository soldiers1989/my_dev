FROM 10.5.3.199:5000/java:8
ENV LC_ALL en_US.UTF-8
WORKDIR /usr/local/
ADD target/*.jar .
CMD java -jar -Xms256m -Xmx512m *.jar --spring.profiles.active=test
