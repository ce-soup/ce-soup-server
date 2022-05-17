# 기반 이미지
FROM openjdk:11

# build/libs/*-SANPSHOT.jar를 JAR_FILE 변수에 담음
ARG JAR_FILE=build/libs/*-SNAPSHOT.jar

# 위에서 할당한 변수명을 app.jar 이름으로 복사함, 경로에 추가함
COPY ${JAR_FILE} app.jar

# 해당 컨테이너 시작될때 수행할 명령으로 java를 실행하는 parameter를 담았다.
# 각 원소는 띄어쓰기 기준
ENTRYPOINT ["java", "-jar", "-Dserver.port=${SERVER_PORT}","-Deureka.client.service-url.defaultZone=${EUREKA_CLIENT_SERVICE_URL_DEFAULT_ZONE}", "-Dspring.datasource.url=${DATASOURCE_URL}", "-Dspring.datasource.username=${DATASOURCE_USERNAME}", "-Dspring.datasource.password=${DATASOURCE_PASSWORD}", "-Dspring.jpa.hibernate.ddl-auto=${JPA_HIBERNATE_DDL_AUTO}", "/app.jar"]
