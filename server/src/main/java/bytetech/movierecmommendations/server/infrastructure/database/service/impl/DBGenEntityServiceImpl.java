package bytetech.movierecmommendations.server.infrastructure.database.service.impl;

import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import bytetech.movierecmommendations.server.infrastructure.database.repository.DBGenUserRepository;
import bytetech.movierecmommendations.server.infrastructure.database.service.DBGenEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class DBGenEntityServiceImpl implements DBGenEntityService {

    List<User> users = List.of(
            new User("Trịnh Hiếu Nghĩa", "0849070512", "nghiabe.dev@gmail.com", "12345678", "adress1", null, RoleConstant.ADMIN),
            new User("Nguyễn Phúc Công", "0849070513", "abc@gmail.com", "12345678", "adress1", null, RoleConstant.ADMIN),
            new User("Hoàng Huy Hiệu", "0849070514", "xyz@gmail.com", "12345678", "adress1", null, RoleConstant.ADMIN)
            );

    private final DBGenUserRepository userRepository;

    public DBGenEntityServiceImpl(DBGenUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void generateUser() {
        userRepository.saveAll(users);
    }
}
