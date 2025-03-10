import { useState, useEffect } from "react";
import ReactPaginate from "react-paginate";
import Modal from "react-modal";
import { MdEdit, MdDelete } from "react-icons/md";
import { adminMovieApi } from "../../api/AdminMovie";
import MovieModal from "../components/Modal/MovieModal";

Modal.setAppElement("#root");

export default function MovieManager() {
  const [movies, setMovies] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [selectedMovie, setSelectedMovie] = useState(null);

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isCreating, setIsCreating] = useState(false);

  useEffect(() => {
    fetchMovies(1);
  }, []);

  const fetchMovies = async (page) => {
    try {
      const response = await adminMovieApi.getAllMovie(page, 5);
      setMovies(response.data.data.data);
      setTotalPages(response.data.data.totalPages);
    } catch (error) {
      console.error("Error fetching movies:", error);
    }
  };
  const openCreateModal = () => {
    setSelectedMovie({
      title: "",
      description: "",
      author: "",
      actor: "",
      pictureURL: "",
      moviesURL: "",
      year: "",
      categoryIds: [],
    });
    setIsCreating(true);
    setIsModalOpen(true);
  };

  const openEditModal = (movie) => {
    setSelectedMovie(movie);
    setIsCreating(false);
    setIsModalOpen(true);
  };

  const handleDeleteClick = async (id) => {
    if (window.confirm("Are you sure you want to delete this user?")) {
      try {
        // window.alert(id);
        await adminMovieApi.deleteMovie(id);
        fetchMovies(1);
      } catch (error) {
        console.error("Error deleting user:", error);
      }
    }
  };
  const handlePageClick = (event) => {
    fetchMovies(event.selected + 1);
  };

  return (
    <div className="container mx-auto p-4">
      <button
        className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded"
        onClick={() => openCreateModal()}
      >
        Add New
      </button>

      <div className="w-full mt-6 overflow-auto">
        <table className="w-full border border-gray-300 bg-white shadow-lg rounded-lg">
          <thead className="bg-gray-100">
            <tr>
              {[
                "Title",
                "Author",
                "Actor",
                "Year",
                "Categories",
                "Description",
                "Created Date",
                "Actions",
              ].map((header) => (
                <th key={header} className="p-4 text-left border-b">
                  <p className="text-gray-700 font-medium">{header}</p>
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            {movies.map((movie) => (
              <tr key={movie.id} className="hover:bg-gray-50">
                <td className="p-4 border-b flex items-center gap-3">
                  {movie.pictureURL && (
                    <img
                      src={movie.pictureURL}
                      alt={movie.title}
                      className="w-12 h-12 object-cover p-1 border rounded-md"
                    />
                  )}
                  <p className="text-gray-900 font-semibold">{movie.title}</p>
                </td>
                <td className="p-4 border-b text-gray-700">{movie.author}</td>
                <td className="p-4 border-b text-gray-700">{movie.actor}</td>
                <td className="p-4 border-b text-gray-700">{movie.year}</td>
                <td className="p-4 border-b text-gray-700">
                  {movie.categories.map((cat) => cat.name).join(", ")}
                </td>
                <td className="p-4 border-b text-gray-700 truncate max-w-xs">
                  {movie.description}
                </td>
                <td className="p-4 border-b text-gray-700">
                  {new Date(movie.createdDate).toLocaleDateString()}
                </td>
                <td className="border-b text-center gap-2">
                  <button
                    className="p-2 rounded-full hover:bg-gray-200 transition"
                    onClick={() => openEditModal(movie)}
                  >
                    <MdEdit />
                  </button>
                  <button
                    className="p-2 rounded-full hover:bg-red-200 transition text-red-600"
                    onClick={() => handleDeleteClick(movie.id)}
                  >
                    <MdDelete />
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
      <div className="flex justify-center mt-4">
        <ReactPaginate
          previousLabel={"Previous"}
          nextLabel={"Next"}
          breakLabel={"..."}
          pageCount={totalPages}
          marginPagesDisplayed={2}
          pageRangeDisplayed={3}
          onPageChange={handlePageClick}
          containerClassName={"flex list-none"}
          pageClassName={"px-2 py-1 border mx-1 rounded cursor-pointer"}
          activeClassName={"bg-blue-500 text-white"}
          previousClassName={"px-2 py-1 border mx-1 rounded cursor-pointer"}
          nextClassName={"px-2 py-1 border mx-1 rounded cursor-pointer"}
          disabledClassName={"opacity-50 cursor-not-allowed"}
        />
      </div>
      <MovieModal
        isOpen={isModalOpen}
        onClose={() => setIsModalOpen(false)}
        movie={selectedMovie}
        isCreating={isCreating}
        fetchMovies={fetchMovies}
      />
    </div>
  );
}
