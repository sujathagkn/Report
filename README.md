# Email send service

## Problem :

Email sender servoce abstracting between mail gun and send grid servers.


#How to run: 
Open as gradle project, from Gradle view window, application -> bootrun to start the service
or use the below CLIs.


Environment : Java 8

### Run local instance
`./gradlew clean bootRun`

Spring Boot will run locally on http://localhost:9002/


### Build .war file
`./gradlew clean assemble`

## Generate report

swagger url : http://localhost:8080/swagger-ui.html

Get end point : http://localhost:8080/processReport?inputFile=<txt path>&outputDir=<dir with write permissions>
eg: http://localhost:8080/processReport?inputFile=/Git/rep1/input.txt&outputDir=/Git

It generates 2 files

1. output.csv - Dialy summary report
2. prodTransaction.csv - Transaction total of each unique product




