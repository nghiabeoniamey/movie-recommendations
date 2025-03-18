import {useEffect, useState} from "react";
import Banner from "../components/Banner";
import MovieList from "../components/MovieList";
import MovieListCus from "../components/MovieListCus";
import {UserMovieApi} from "../api/UserMovie.ts";

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
    const [movieRecommendation, setMovieRecommendation] = useState([]);

    const fetchRecMovies = async () => {
        try {
            const response = await UserMovieApi.GetMovieRecommendations();
            console.log("response + {}", response.data);
            setMovieRecommendation(response.data);
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
                <Banner/>
                <MovieListCus title="Recommendation Movie" data={movieRecommendation}/>

                <MovieList title="Hot Movie" data={trendingMovies.slice(0, 10)}/>
                {/* )} */}

                <MovieList title="Social Movie" data={topRatedMovies.slice(0, 10)}/>
            </div>
        </>
    );
}

export default HomePage;
