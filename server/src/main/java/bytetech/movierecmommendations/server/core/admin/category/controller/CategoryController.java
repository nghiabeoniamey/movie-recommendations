package bytetech.movierecmommendations.server.core.admin.category.controller;

import bytetech.movierecmommendations.server.core.admin.category.model.request.CategoryFilterRequest;
import bytetech.movierecmommendations.server.core.admin.category.service.AdminCategoryService;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Category;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MappingConstant.API_ADMIN_CATEGORY)
public class CategoryController {

    private final AdminCategoryService adminCategoryService;

    public CategoryController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    @GetMapping()
    public ResponseEntity<ResponseObject<Page<Category>>> getAll(final CategoryFilterRequest filterRequest) {
        ResponseObject<Page<Category>> response = adminCategoryService.getAll(filterRequest);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping()
    public ResponseEntity<ResponseObject<Category>> create(@RequestBody Category category) {
        ResponseObject<Category> response = adminCategoryService.create(category);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseObject<Category>> update(
            @PathVariable String id,
            @RequestBody Category category) {
        ResponseObject<Category> response = adminCategoryService.update(id, category);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseObject<?>> delete(@PathVariable("id") String id) {
        ResponseObject<?> response = adminCategoryService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
