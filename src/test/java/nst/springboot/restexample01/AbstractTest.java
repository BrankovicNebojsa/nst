package nst.springboot.restexample01;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@EnableAutoConfiguration(exclude = LiquibaseAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
public abstract class AbstractTest {
}
