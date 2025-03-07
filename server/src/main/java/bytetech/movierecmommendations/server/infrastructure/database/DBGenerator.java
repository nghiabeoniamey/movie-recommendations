package bytetech.movierecmommendations.server.infrastructure.database;

import bytetech.movierecmommendations.server.infrastructure.database.service.DBGenEntityService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DBGenerator {

    @Value("${db.generator.is-generated}")
    private String isGenerated;


    private final DBGenEntityService service;

    public DBGenerator(
            DBGenEntityService service) {
        this.service = service;
    }

    @PostConstruct
    public void init() {
        if ("true".equals(isGenerated)) generateData();
    }

    @PostConstruct
    public void category() {
        if ("true".equals(isGenerated)) generateDataCategory();
    }
    @PostConstruct
    public void Movie() {
        if ("true".equals(isGenerated)) generateDataMovie();
    }
    @PostConstruct
    public void Review() {
        if ("true".equals(isGenerated)) generateDataReview();
    }
    private void generateData() {
        service.generateUser();
    }
    private void generateDataCategory() {
        service.generateCategory();
    }

    private void generateDataMovie() {
        service.generateMovie();
    }

    private void generateDataReview() {
        service.generateReview();
    }
}
