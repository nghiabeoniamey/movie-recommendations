package bytetech.movierecmommendations.server.core.admin.category.controller;

import bytetech.movierecmommendations.server.core.admin.category.model.request.CategoryFilterRequest;
import bytetech.movierecmommendations.server.core.admin.category.service.AdminCategoryService;
import bytetech.movierecmommendations.server.core.common.base.ResponseObject;
import bytetech.movierecmommendations.server.entities.main.Category;
import bytetech.movierecmommendations.server.infrastructure.constants.module.MappingConstant;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MappingConstant.API_ADMIN_CATEGORY)
public class CategoryController {

    private final AdminCategoryService adminCategoryService;

    public CategoryController(AdminCategoryService adminCategoryService) {
        this.adminCategoryService = adminCategoryService;
    }

    @PostMapping("/list")
    public ResponseEntity<ResponseObject<Page<Category>>> getAll(
            @RequestBody CategoryFilterRequest filterRequest,
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size) {

        ResponseObject<Page<Category>> response = adminCategoryService.getAll(filterRequest, page, size);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseObject<Category>> create(@RequestBody Category category) {
        ResponseObject<Category> response = adminCategoryService.create(category);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseObject<Category>> update(
            @PathVariable String id,
            @RequestBody Category category) {
        ResponseObject<Category> response = adminCategoryService.update(id, category);
        return ResponseEntity.status(response.getStatus()).body(response);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject<?>> delete(@PathVariable("id") String id) {
        ResponseObject<?> response = adminCategoryService.delete(id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
