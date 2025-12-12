package cn.nihiler.agenticrag;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.nihiler.agenticrag.mapper")
public class AgenticRagApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgenticRagApplication.class, args);
    }

}
