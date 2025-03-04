package bytetech.movierecmommendations.server.core.admin.category.repository;

import bytetech.movierecmommendations.server.core.admin.category.model.request.CategoryFilterRequest;
import bytetech.movierecmommendations.server.entities.main.Category;
import bytetech.movierecmommendations.server.repository.CategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdminCategoryRepository extends CategoryRepository {
    @Query(value = "SELECT DISTINCT c FROM Category c " +
            "LEFT JOIN MovieCategory mc ON c.id = mc.category.id " +
            "LEFT JOIN Movie m ON mc.movie.id = m.id " +
            "WHERE (:#{#condition.movieId} IS NULL OR m.id = :#{#condition.movieId}) " +
            "AND c.deleted = false " +
            "ORDER BY c.createdDate DESC")
    Page<Category> findAllByFilter(@Param("condition") CategoryFilterRequest filterRequest, Pageable pageable);
}
