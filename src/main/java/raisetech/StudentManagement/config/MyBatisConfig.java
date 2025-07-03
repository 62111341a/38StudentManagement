package raisetech.StudentManagement.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("raisetech.StudentManagement.repository")
public class MyBatisConfig {
}
