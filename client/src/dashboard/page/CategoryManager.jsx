import { useEffect, useState } from "react";
import { FaEllipsisV } from "react-icons/fa";
import ReactPaginate from "react-paginate";
import { adminCategoryApi } from "../../api/AdminCategory";

export default function CategoryManager() {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [categoryList, setCategoryList] = useState([]);
  const [openMenu, setOpenMenu] = useState(null);
  const [editingCategory, setEditingCategory] = useState(null);
  const [currentPage, setCurrentPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const itemsPerPage = 5;

  useEffect(() => {
    fetchCategory();
  }, [currentPage]);

  const fetchCategory = async () => {
    try {
      const res = await adminCategoryApi.getAllCategory(
        currentPage,
        itemsPerPage
      );
      // console.log(res?.data?.data);
      setCategoryList(res?.data?.data?.content);
      setTotalPages(res?.data?.data?.totalPages);
    } catch (error) {
      console.error("Error creating category:", error);
      return null;
    }
  };

  const handleSaveCategory = async () => {
    try {
      if (editingCategory) {
        await adminCategoryApi.EditAdminUser(
          editingCategory.id,
          name,
          description
        );
        setEditingCategory(null);
      } else {
        // Nếu không, tạo danh mục mới
        await adminCategoryApi.createAdminCategory(name, description);
      }
      setName("");
      setDescription("");
      fetchCategory();
    } catch (error) {
      console.error("Error saving category:", error);
    }
  };
  const handleEditCategory = (category) => {
    setEditingCategory(category);
    setName(category.name);
    setDescription(category.description);
    setOpenMenu(null);
  };
  const handleCancelEdit = () => {
    setEditingCategory(null);
    setName("");
    setDescription("");
  };

  const handleDeleteCategory = async (id) => {
    try {
      await adminCategoryApi.deleteAdminCategory(id);
      setOpenMenu(null);
      fetchCategory();
    } catch (error) {
      console.error("Error deleting category:", error);
    }
  };

  // const pageCount = Math.ceil(categoryList.length / itemsPerPage);
  const pageCount = totalPages;
  const displayedCategories = categoryList;

  // const displayedCategories = categoryList.slice(
  //   currentPage * itemsPerPage,
  //   (currentPage + 1) * itemsPerPage
  // );

  const handlePageClick = ({ selected }) => {
    setCurrentPage(selected);
  };
  return (
    <div className="max-w-xl mx-auto p-4 border rounded-lg shadow-md bg-white my-5">
      <h2 className="text-lg font-semibold mb-4">Categories</h2>
      <div className="flex gap-2 mb-4 w-full">
        <div className="grid grid-cols-2 grid-rows-2 gap-3 w-full">
          <div className="col-span-2">
            <input
              type="text"
              className="border p-2 rounded-lg w-full"
              placeholder="Name"
              value={name}
              onChange={(e) => setName(e.target.value)}
            />
          </div>
          <div className="col-span-2">
            <input
              type="text"
              className="border p-2 rounded-lg w-full"
              placeholder="Description"
              value={description}
              onChange={(e) => setDescription(e.target.value)}
            />
          </div>
        </div>
        <div className="flex items-center min-w-max">
          <button
            onClick={handleSaveCategory}
            className="bg-green-600 text-white px-4 py-2 rounded-lg"
          >
            {editingCategory ? "Update" : "Save"}
          </button>
          {editingCategory && (
            <button
              onClick={handleCancelEdit}
              className="ml-2 bg-gray-500 text-white px-4 py-2 rounded-lg"
            >
              Cancel
            </button>
          )}
        </div>
      </div>

      <div>
        {displayedCategories.map((cat) => (
          <div
            key={cat.id}
            className="flex justify-between items-center p-2 border rounded-lg mb-2"
          >
            <span>{cat.name}</span>
            <div className="relative">
              <button
                onClick={() => setOpenMenu(openMenu === cat.id ? null : cat.id)}
                className="p-2 rounded-full hover:bg-gray-100"
              >
                <FaEllipsisV />
              </button>
              {openMenu === cat.id && (
                <div className="absolute right-6 top-1 mt-2 w-40 bg-white border rounded-lg shadow-lg">
                  <ul>
                    <li
                      className="px-4 py-2 hover:bg-gray-100 cursor-pointer text-green-600"
                      onClick={() => handleEditCategory(cat)}
                    >
                      Edit
                    </li>
                    <li
                      className="px-4 py-2 hover:bg-red-100 text-red-600 cursor-pointer"
                      onClick={() => handleDeleteCategory(cat.id)}
                    >
                      Delete
                    </li>
                  </ul>
                </div>
              )}
            </div>
          </div>
        ))}
      </div>
      <ReactPaginate
        previousLabel={"← Previous"}
        nextLabel={"Next →"}
        breakLabel={"..."}
        pageCount={pageCount}
        marginPagesDisplayed={1}
        pageRangeDisplayed={3}
        onPageChange={handlePageClick}
        containerClassName="flex justify-center space-x-2 mt-4"
        pageClassName="px-3 py-1 border rounded-lg cursor-pointer hover:bg-gray-200"
        activeClassName="bg-blue-500 text-white"
        previousClassName="px-3 py-1 border rounded-lg cursor-pointer hover:bg-gray-200"
        nextClassName="px-3 py-1 border rounded-lg cursor-pointer hover:bg-gray-200"
      />
    </div>
  );
}
