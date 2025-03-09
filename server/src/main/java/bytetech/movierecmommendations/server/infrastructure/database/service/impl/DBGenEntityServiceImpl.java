package bytetech.movierecmommendations.server.infrastructure.database.service.impl;

import bytetech.movierecmommendations.server.entities.main.Category;
import bytetech.movierecmommendations.server.entities.main.Movie;
import bytetech.movierecmommendations.server.entities.main.MovieCategory;
import bytetech.movierecmommendations.server.entities.main.Reviewer;
import bytetech.movierecmommendations.server.entities.main.User;
import bytetech.movierecmommendations.server.infrastructure.constants.module.RoleConstant;
import bytetech.movierecmommendations.server.infrastructure.database.repository.DBGenCategoryRepository;
import bytetech.movierecmommendations.server.infrastructure.database.repository.DBGenMovieCategoryRepository;
import bytetech.movierecmommendations.server.infrastructure.database.repository.DBGenMovieRepository;
import bytetech.movierecmommendations.server.infrastructure.database.repository.DBGenReviewRepository;
import bytetech.movierecmommendations.server.infrastructure.database.repository.DBGenUserRepository;
import bytetech.movierecmommendations.server.infrastructure.database.service.DBGenEntityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
public class DBGenEntityServiceImpl implements DBGenEntityService {

    private final DBGenUserRepository userRepository;
    private final DBGenCategoryRepository categoryRepository;
    private final DBGenMovieRepository movieRepository;
    private final DBGenReviewRepository reviewRepository;
    private final DBGenMovieCategoryRepository movieCategoryRepository;

    public DBGenEntityServiceImpl(DBGenUserRepository userRepository, DBGenCategoryRepository categoryRepository,
                                  DBGenMovieRepository movieRepository, DBGenReviewRepository reviewRepository, DBGenMovieCategoryRepository movieCategoryRepository) {
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.movieRepository = movieRepository;
        this.reviewRepository = reviewRepository;
        this.movieCategoryRepository = movieCategoryRepository;
    }

    @Override
    public void generateUser() {
        if (userRepository.count() == 0) {
            List<User> users = List.of(
                    new User("Nguyễn Văn A", "0849070511", "vana@gmail.com", "12345678", "Hà Nội", null, RoleConstant.USER),
                    new User("Trần Văn B", "0849070512", "vanb@gmail.com", "12345678", "TP.HCM", null, RoleConstant.USER),
                    new User("Lê Thị C", "0849070513", "letc@gmail.com", "12345678", "Đà Nẵng", null, RoleConstant.USER),
                    new User("Phạm Văn D", "0849070514", "pvd@gmail.com", "12345678", "Hải Phòng", null, RoleConstant.USER),
                    new User("Hoàng Huy Hiệu", "0849070515", "hieuhoang@gmail.com", "12345678", "Nha Trang", null, RoleConstant.ADMIN),
                    new User("Trịnh Hiếu Nghĩa", "0849070516", "nghiabe.dev@gmail.com", "12345678", "Hà Nội", null, RoleConstant.ADMIN),
                    new User("Nguyễn Phúc Công", "0849070517", "abc@gmail.com", "12345678", "Huế", null, RoleConstant.ADMIN),
                    new User("Lý Văn E", "0849070518", "lyve@gmail.com", "12345678", "Vũng Tàu", null, RoleConstant.USER),
                    new User("Đặng Thị F", "0849070519", "dtf@gmail.com", "12345678", "Hải Dương", null, RoleConstant.USER),
                    new User("Vũ Minh G", "0849070520", "vmg@gmail.com", "12345678", "Cần Thơ", null, RoleConstant.USER),
                    new User("Phan Văn H", "0849070521", "pvh@gmail.com", "12345678", "Bắc Giang", null, RoleConstant.USER),
                    new User("Nguyễn Văn I", "0849070522", "nvi@gmail.com", "12345678", "Hòa Bình", null, RoleConstant.USER),
                    new User("Lê Thị J", "0849070523", "ltj@gmail.com", "12345678", "Quảng Ninh", null, RoleConstant.USER),
                    new User("Trần Văn K", "0849070524", "tvk@gmail.com", "12345678", "Đồng Nai", null, RoleConstant.USER),
                    new User("Phạm Văn L", "0849070525", "pvl@gmail.com", "12345678", "Lâm Đồng", null, RoleConstant.USER),
                    new User("Lê Văn M", "0849070526", "lvm@gmail.com", "12345678", "Bến Tre", null, RoleConstant.USER),
                    new User("Đinh Văn N", "0849070527", "dvn@gmail.com", "12345678", "Bình Định", null, RoleConstant.USER),
                    new User("Nguyễn Thị O", "0849070528", "nto@gmail.com", "12345678", "Phú Yên", null, RoleConstant.USER),
                    new User("Hoàng Văn P", "0849070529", "hvp@gmail.com", "12345678", "Tây Ninh", null, RoleConstant.USER),
                    new User("Trần Văn Q", "0849070530", "tvq@gmail.com", "12345678", "An Giang", null, RoleConstant.USER)

            );
            userRepository.saveAll(users);
        }
    }

    @Override
    public void generateCategory() {
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    new Category("Action", "Hành động"),
                    new Category("Comedy", "Hài hước"),
                    new Category("Drama", "Chính kịch"),
                    new Category("Horror", "Kinh dị"),
                    new Category("Sci-Fi", "Khoa học viễn tưởng"),
                    new Category("Romance", "Lãng mạn"),
                    new Category("Fantasy", "Giả tưởng"),
                    new Category("Thriller", "Giật gân"),
                    new Category("Mystery", "Bí ẩn"),
                    new Category("Animation", "Hoạt hình")

            );
            categoryRepository.saveAll(categories);
        }
    }

    @Override
    public void generateMovie() {
        if (movieRepository.count() == 0) {
            List<Movie> movies = IntStream.rangeClosed(1, 40)
                    .mapToObj(i -> new Movie(
                            "Movie " + i,
                            "Description of movie " + i,
                            "Author " + i,
                            "Actor " + i,
                            2000 + (i % 24),
                            "pic" + i + ".jpg",
                            "movie" + i + ".mp4"
                    ))
                    .collect(Collectors.toList());
            movieRepository.saveAll(movies);
        }
    }

    @Override
    public void generateReview() {
        List<User> users = userRepository.findAll();
        List<Movie> movies = movieRepository.findAll();

        if (users.isEmpty() || movies.isEmpty()) {
            log.error("Không thể tạo review vì user hoặc movie chưa có trong database.");
            return;
        }

        if (reviewRepository.count() == 0) {
            List<Reviewer> reviews = IntStream.rangeClosed(1, 70)
                    .mapToObj(i -> new Reviewer(
                            users.get(new Random().nextInt(users.size())),
                            movies.get(new Random().nextInt(movies.size())),
                            "Review content " + i,
                            1 + new Random().nextInt(5)
                    ))
                    .collect(Collectors.toList());
            reviewRepository.saveAll(reviews);
        }
    }

    @Override
    public void generateMovieCategory() {
        List<Movie> movies = movieRepository.findAll();
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty() || movies.isEmpty()) {
            log.error("Không thể tạo review vì user hoặc movie chưa có trong database.");
            return;
        }

        if (movieCategoryRepository.count() == 0) {
            List<MovieCategory> movieCategories = IntStream.rangeClosed(1, 70)
                    .mapToObj(i -> new MovieCategory(
                            movies.get(new Random().nextInt(movies.size())),
                            categories.get(new Random().nextInt(categories.size()))
                    ))
                    .collect(Collectors.toList());
            movieCategoryRepository.saveAll(movieCategories);
        }
    }


}