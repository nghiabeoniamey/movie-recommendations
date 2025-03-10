import React, { useEffect, useState } from "react";
import { adminCategoryApi } from "../../../api/AdminCategory";
import Select from "react-select";
import { adminMovieApi } from "../../../api/AdminMovie";
import { toast } from "react-toastify";
import Modal from "react-modal";

export default function MovieModal({
  isOpen,
  onClose,
  movie,
  isCreating,
  fetchMovies,
}) {
  const [categoryList, setCategoryList] = useState([]);
  const [selectedCategories, setSelectedCategories] = useState([]);

  const [formData, setFormData] = useState({
    title: "",
    description: "",
    author: "",
    actor: "",
    pictureURL: "",
    moviesURL: "",
    year: "",
    categoryIds: [],
  });
  console.log(movie);
  useEffect(() => {
    fetchCategory();
  }, []);
  useEffect(() => {
    if (!isCreating && movie) {
      setFormData({
        title: movie?.title || "",
        description: movie?.description || "",
        author: movie?.author || "",
        actor: movie?.actor || "",
        pictureURL: movie?.pictureURL || "",
        moviesURL: movie?.moviesURL || "",
        year: movie?.year || "",
        categoryIds: movie?.categoryIds || [],
      });
      setSelectedCategories(
        (movie?.categories || []).map((id) => ({
          value: id,
          label: categoryList.find((c) => c.value === id)?.label || "",
        }))
      );
    } else {
      // Nếu đang ở chế độ tạo mới, reset form về mặc định
      setFormData({
        title: "",
        description: "",
        author: "",
        actor: "",
        pictureURL: "",
        moviesURL: "",
        year: "",
        categoryIds: [],
      });
      setSelectedCategories([]);
    }
  }, [movie, isCreating, categoryList]);

  // get all category
  const fetchCategory = async () => {
    try {
      const res = await adminCategoryApi.getAllCategory(0, 8);
      const categories = res?.data?.data?.content || [];
      // Chuyển đổi categoryList thành format phù hợp cho react-select
      console.log(categories);
      const formattedCategories = categories.map((c) => ({
        value: c.id, // Giá trị thực tế
        label: c.name, // Hiển thị tên category
      }));
      setCategoryList(formattedCategories);
    } catch (error) {
      console.error("Error creating category:", error);
      return null;
    }
  };
  const handleCategoryChange = (selectedOptions) => {
    setSelectedCategories(selectedOptions || []);
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const newMovie = {
        title: formData.title,
        description: formData.description,
        author: formData.author,
        actor: formData.actor,
        pictureURL: formData.pictureURL,
        moviesURL: formData.moviesURL,
        year: formData.year,
        categoryIds: selectedCategories.map((c) => c.value),
      };

      let response;
      if (isCreating) {
        console.log(newMovie);
        response = await adminMovieApi.createMovie(newMovie);
        toast.success("Movie created successfully!");
      } else {
        response = await adminMovieApi.updateMovie(movie?.id, newMovie);
        toast.success("Movie updated successfully!");
      }
      console.log(response);
      // onClose();
      fetchMovies(1);
    } catch (error) {
      console.error("Error submitting movie:", error);
      toast.error("Failed to submit movie");
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onClose}
      className="fixed inset-0 flex items-center justify-center"
      overlayClassName="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center"
    >
      <div className="relative w-full max-w-2xl mx-auto bg-white rounded-lg shadow-lg">
        <button
          onClick={onClose}
          className="absolute top-4 right-4 p-1 text-gray-500 hover:text-gray-700"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth="1.5"
            stroke="currentColor"
            className="size-6"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M6 18L18 6M6 6l12 12"
            />
          </svg>
        </button>
        <div className="rounded-t bg-gray-100 px-4 py-4 flex justify-between">
          <h6 className="text-gray-700 text-xl font-bold">
            {isCreating ? "Create Movie" : "Update Movie"}
          </h6>
        </div>
        <div className="px-6 py-6">
          <form onSubmit={handleSubmit}>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div>
                <label className="block text-gray-600 text-sm font-bold mb-1">
                  Title
                </label>
                <input
                  type="text"
                  name="title"
                  className="border px-3 py-2 rounded w-full"
                  value={formData.title}
                  onChange={handleChange}
                  required
                />
              </div>
              <div>
                <label className="block text-gray-600 text-sm font-bold mb-1">
                  Year
                </label>
                <input
                  type="text"
                  name="year"
                  className="border px-3 py-2 rounded w-full"
                  value={formData.year}
                  onChange={handleChange}
                  required
                />
              </div>
            </div>
            <div className="mt-4">
              <label className="block text-gray-600 text-sm font-bold mb-1">
                Description
              </label>
              <textarea
                name="description"
                className="border px-3 py-2 rounded w-full"
                rows="4"
                value={formData.description}
                onChange={handleChange}
                required
              ></textarea>
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mt-4">
              <div>
                <label className="block text-gray-600 text-sm font-bold mb-1">
                  Author
                </label>
                <input
                  type="text"
                  name="author"
                  className="border px-3 py-2 rounded w-full"
                  value={formData.author}
                  onChange={handleChange}
                />
              </div>
              <div>
                <label className="block text-gray-600 text-sm font-bold mb-1">
                  Actor
                </label>
                <input
                  type="text"
                  name="actor"
                  className="border px-3 py-2 rounded w-full"
                  value={formData.actor}
                  onChange={handleChange}
                />
              </div>
            </div>
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4 mt-4">
              <div>
                <label className="block text-gray-600 text-sm font-bold mb-1">
                  Movie URL
                </label>
                <input
                  type="text"
                  name="moviesURL"
                  className="border px-3 py-2 rounded w-full"
                  value={formData.moviesURL}
                  onChange={handleChange}
                />
              </div>
              <div>
                <label className="block text-gray-600 text-sm font-bold mb-1">
                  Picture URL
                </label>
                <input
                  type="text"
                  name="pictureURL"
                  className="border px-3 py-2 rounded w-full"
                  value={formData.pictureURL}
                  onChange={handleChange}
                />
              </div>
            </div>
            <div className="mt-4">
              <label className="block text-gray-600 text-sm font-bold mb-1">
                Category
              </label>
              <Select
                options={categoryList}
                isMulti
                closeMenuOnSelect={false}
                hideSelectedOptions={false}
                onChange={handleCategoryChange}
                value={selectedCategories}
                className="text-sm"
              />
            </div>
            <div className="mt-6 text-center">
              <button
                type="submit"
                className="bg-blue-500 text-white px-6 py-2 rounded shadow hover:bg-blue-600"
              >
                {isCreating ? "Create Movie" : "Update Movie"}
              </button>
            </div>
          </form>
        </div>
      </div>
    </Modal>
  );
}
