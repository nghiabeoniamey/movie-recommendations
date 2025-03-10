import http from "./http";

const URL_CategoryList = "admin/category/list";
const URL_CategoryCreate = "admin/category/create";
const URL_CategoryDelete = "admin/category/delete";
const URL_CategoryPut = "admin/category/update";

export const adminCategoryApi = {
  getAllCategory(page, size) {
    return http.post(`${URL_CategoryList}?page=${page}&size=${size}`, {});
  },
  createAdminCategory(name, description) {
    return http.post(`${URL_CategoryCreate}`, {
      name: name,
      description: description,
    });
  },
  deleteAdminCategory(id) {
    return http.delete(`${URL_CategoryDelete}/${id}`);
  },
  EditAdminUser(id, name, description) {
    return http.put(`${URL_CategoryPut}/${id}`, {
      name: name,
      description: description,
    });
  },
};
