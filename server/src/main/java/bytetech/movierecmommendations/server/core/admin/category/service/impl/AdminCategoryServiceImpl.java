package bytetech.movierecmommendations.server.core.admin.category.service.impl;

import bytetech.movierecmommendations.server.core.admin.category.model.request.CategoryFilterRequest;
import bytetech.movierecmommendations.server.core.admin.category.repository.AdminCategoryRepository;
import bytetech.movierecmommendations.server.core.admin.category.service.AdminCategoryService;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {

    private final AdminCategoryRepository adminCategoryRepository;

    public AdminCategoryServiceImpl(AdminCategoryRepository adminCategoryRepository) {
        this.adminCategoryRepository = adminCategoryRepository;
    }

    @Override
    public ResponseObject<Page<Category>> getAll(CategoryFilterRequest filterRequest, int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Category> categories = adminCategoryRepository.findAllByFilter(filterRequest, pageable);
        return new ResponseObject<>(categories, HttpStatus.OK, "Lấy danh sách danh mục thành công");
    }

    @Override
    public ResponseObject<Category> create(Category category) {
        Category savedCategory = adminCategoryRepository.save(category);
        return new ResponseObject<>(savedCategory, HttpStatus.CREATED, "Thêm danh mục thành công");
    }


    @Override
    public Category findById(String id) {
        Optional<Category> category = adminCategoryRepository.findById(id);
        return category.orElse(null);
    }

    @Override
    public ResponseObject update(String id, Category category) {
        Optional<Category> existingCategory = adminCategoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category updatedCategory = existingCategory.get();
            updatedCategory.setName(category.getName());
            updatedCategory.setDescription(category.getDescription());
            adminCategoryRepository.save(updatedCategory);
            return new ResponseObject<>(updatedCategory, HttpStatus.OK, "Cập nhật danh mục thành công");
        }
        return new ResponseObject<>(null, HttpStatus.NOT_FOUND, "Danh mục không tồn tại");
    }

    @Override
    public ResponseObject<?> delete(String id) {
        Optional<Category> existingCategory = adminCategoryRepository.findById(id);
        if (existingCategory.isPresent()) {
            Category category = existingCategory.get();
            category.setDeleted(true);
            adminCategoryRepository.save(category);

            return new ResponseObject<>(true, HttpStatus.OK, "Xóa danh mục thành công");
        }
        return new ResponseObject<>(false, HttpStatus.NOT_FOUND, "Danh mục không tồn tại");
    }


}
