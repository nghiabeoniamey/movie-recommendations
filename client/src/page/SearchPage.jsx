import { useEffect, useState } from "react";
import { useSearchParams } from "react-router-dom";
import MovieSearch from "../components/MovieSearch";

const SearchPage = () => {
  const [searchParams] = useSearchParams();
  const query = searchParams.get("q") || "";
  const [searchResults, setSearchResults] = useState([]);

  useEffect(() => {
    if (!query) return;

    const fetchSearchResults = async () => {
      const url = `https://api.themoviedb.org/3/search/movie?query=${query}&include_adult=false&language=vi&page=1`;
      const options = {
        method: "GET",
        headers: {
          accept: "application/json",
          Authorization: `Bearer ${import.meta.env.VITE_API_KEY}`,
        },
      };

      try {
        const response = await fetch(url, options);
        const data = await response.json();
        setSearchResults(data.results);
      } catch (error) {
        console.error("Error fetching search results:", error);
      }
    };

    fetchSearchResults();
  }, [query]);

  return (
    <div className="h-full bg-black text-white min-h-screen pb-10 relative">
      <h1 className="text-2xl font-bold p-4">Kết quả tìm kiếm cho: {query}</h1>
      <MovieSearch data={searchResults} />
    </div>
  );
};

export default SearchPage;
