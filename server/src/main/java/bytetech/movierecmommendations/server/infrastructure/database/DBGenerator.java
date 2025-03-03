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


    private void generateData() {
//        service....
    }

}
