import { useEffect } from "react";

import { useState } from "react";
import Header from "../components/Header";
import Banner from "../components/Banner";
import MovieList from "../components/MovieList";
import MovieSearch from "../components/MovieSearch";
import http from "../api/http";
import MovieListCus from "../components/MovieListCus";

const fetchMoviesData = async (setTrendingMovies, setTopRatedMovies) => {
  const urls = [
    "https://api.themoviedb.org/3/trending/movie/day?language=vi",
    "https://api.themoviedb.org/3/movie/top_rated?language=vi",
  ];
  const options = {
    method: "GET",
    headers: {
      accept: "application/json",
      Authorization: `Bearer ${import.meta.env.VITE_API_KEY}`,
    },
  };

  try {
    const fetchMovies = async (url) => {
      return await fetch(url, options).then((response) => response.json());
    };

    const response = await Promise.all(urls.map(fetchMovies));
    setTrendingMovies(response[0].results);
    setTopRatedMovies(response[1].results);
  } catch (error) {
    console.error("Error fetching movies:", error);
  }
};

function HomePage() {
  const [trendingMovies, setTrendingMovies] = useState([]);
  const [topRatedMovies, setTopRatedMovies] = useState([]);
  const [recom, setRecom] = useState([]);

  const fetchRecMovies = async () => {
    try {
      const response = await http.get(`connection/python`);
      console.log(response);
      setRecom(response.data.data);
    } catch (error) {
      console.error("Lỗi khi lấy kết quả", error);
    }
  };

  useEffect(() => {
    fetchMoviesData(setTrendingMovies, setTopRatedMovies);
    fetchRecMovies();
  }, []);
  return (
    <>
      <div className="h-full bg-black text-white min-h-screen pb-10 relative">
        {/* <Header onSearch={handleSearch} /> */}
        <Banner />
        <MovieListCus title="Phim Đề xuất" data={recom.slice(0, 10)} />

        <MovieList title="Phim Hot" data={trendingMovies.slice(0, 10)} />
        {/* )} */}

        <MovieList title="Phim đề cử" data={topRatedMovies.slice(0, 10)} />
      </div>
    </>
  );
}

export default HomePage;
