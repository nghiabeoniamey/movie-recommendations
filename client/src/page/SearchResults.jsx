import { useEffect, useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import http from "../api/http";
import testImage from "../assets/img/testimg.jpg";

const SearchResults = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const queryParams = new URLSearchParams(location.search);
  const searchQuery = queryParams.get("q") || "";

  const [movies, setMovies] = useState([]);
  useEffect(() => {
    const fetchMovies = async () => {
      try {
        const response = await http.get(
          `/user/movie/search?keyword=${searchQuery}`
        );
        console.log(response);
        setMovies(response.data || []);
      } catch (error) {
        console.error("Lỗi khi lấy kết quả tìm kiếm:", error);
      }
    };

    if (searchQuery) fetchMovies();
  }, [searchQuery]);

  const handleMovieClick = (movieId) => {
    navigate(`/detail/${movieId}`);
  };

  return (
    <div className="min-h-screen bg-black text-white pb-10 pt-20">
      <div className="my-10 px-10 max-w-full">
        <h2 className="text-xl uppercase mb-4 mt-10">
          Search Results for "{searchQuery}"
        </h2>

        <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-5 gap-4 mt-6">
          {movies.length > 0 ? (
            movies.map((movie) => (
              <div
                key={movie.id}
                onClick={() => handleMovieClick(movie.id)}
                className="bg-cover bg-no-repeat bg-center w-[200px] h-[300px] relative hover:scale-110 transition-transform duration-500 ease-in-out cursor-pointer"
                style={{
                  backgroundImage: `url(${movie.picture})`,
                }}
              >
                <div className="bg-black w-full h-full opacity-40 absolute top-0 left-0 z-0" />
                <div className="relative p-4 flex flex-col items-center justify-end h-full">
                  <h3 className="text-md uppercase">{movie.title}</h3>
                </div>
              </div>
            ))
          ) : (
            <p className="text-gray-400">Không tìm thấy phim nào.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default SearchResults;
