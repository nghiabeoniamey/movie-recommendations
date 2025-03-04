package bytetech.movierecmommendations.server.core.admin.category.service;

import bytetech.movierecmommendations.server.core.admin.category.model.request.CategoryFilterRequest;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Category;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface AdminCategoryService {

    ResponseObject<Page<Category>> getAll(CategoryFilterRequest filterRequest, int page, int size);

    ResponseObject<Category> create(Category category);

    Category findById(String id);

    ResponseObject update(String id, Category category);

    ResponseObject<?> delete(String id);

}
