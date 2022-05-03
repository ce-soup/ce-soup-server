# soup service server

### 1. docker-compose 실행

- docker-compose 명령어를 통해 MySQL과 Redis 컨테이너를 실행해요.

```shell
docker-compose up -d
```

### 2. spring boot 실행

- spring boot에 실행 인자를 넣어 실행해요.

```shell
--server.port={PORT}
--spring.datasource.url={DATASOURCE_URL}
--spring.datasource.username={DATASOURCE_USERNAME}
--spring.datasource.password={DATASOURCE_PASSWORD}
--spring.jpa.hibernate.ddl-auto={JPA_HIBERNATE_DDL_AUTO}
--spring.jpa.properties.hibernate.show_sql={JPA_PROPERTIES_SHOW_SQL}
--spring.jpa.properties.hibernate.format_sql={JPA_PROPERTIES_FORMAT_SQL}
--spring.logging.level.org.hibernate.type.descriptor.sql={LOGGING_LEVEL_ORG_HIBERNATE_TYPE_DESCRIPTOR_SQL}
--logging.level.org.hibernate.type.descriptor.sql={LOGGIN_LEBEL_ORG_HIBERNATE_TYPE_DESCRIPTOR_SQL}
```
