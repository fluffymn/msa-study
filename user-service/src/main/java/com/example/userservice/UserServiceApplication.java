package com.example.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}


/*
* user-service를 실행하는 방법
* 1. run configurations 에서 실행하기 (2개 이상 실행할 경우 포트번호 설정)
* 2. 터미널에서 아래 명령어 입력
* 	mvn spring-boot:run '-Dspring-boot.run.jvmArguments=-Dserver.port=9003'
* 3. cmd에서 프로젝트 경로로 들어가서 아래 명령어 입력
* 	mvn clean
*   mvn compile package
*   java -jar -Dserver.port=9004 ./target/user-service-0.0.1-SNAPSHOT.jar
* */