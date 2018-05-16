# 
This a simple project which is used to test greenplum with jdbc connection pool

## How To Config

```
    cd src/main/resource
    # vim application.yml
    # modify spring.datasource.username
             spring.datasource.password
             spring.datasource.url
    # like this
    spring:
        datasource:
            username: user
            password: pwd
            type: com.zaxxer.hikari.HikariDataSource
            url: jdbc:postgresql://xx.xx.xx:5432/demo?useUnicode=true&characterEncoding=UTF-8
            driverClassName: org.postgresql.Driver
```

## How To Build

```
    # you must install maven first
    mvn clean -DskipTests package
    # then in the target directory,you will see jar like this
    # gpdb-jdbc-test-{yyyMMddHH}.jar
```

## How To Run

```
    java -jar gpdb-jdbc-test-{yyyMMddHH}.jar
```

