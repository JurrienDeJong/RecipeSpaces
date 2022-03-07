package nl.bioinf.recipespaces.recipespaces;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@SpringBootApplication
public class RecipeSpacesApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecipeSpacesApplication.class, args);
    }

}
