import {useEffect, useState} from "react";
import {useLocation, useNavigate} from "react-router-dom";
import {UserMovieApi} from "../api/UserMovie.ts";
import testImage from "../assets/img/testimg.jpg";

const HistoryPage = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const queryParams = new URLSearchParams(location.search);

    const [movies, setMovies] = useState([]);

    useEffect(() => {
        const fetchMovies = async () => {
            try {
                const response = await UserMovieApi.GetMovieWatchHistory();
                setMovies(response.data || []);
            } catch (error) {
                console.error("Lỗi khi lấy kết quả tìm kiếm:", error);
            }
        };

        fetchMovies().then();
    }, []);

    const handleMovieClick = (movieId) => {
        navigate(`/detail/${movieId}`);
    };

    return (
        <div className="min-h-screen bg-black text-white pb-10 pt-20">
            <div className="my-10 px-10 max-w-full">

                <div className="grid grid-cols-7 md:grid-cols-5 lg:grid-cols-7 gap-4 mt-6">
                    {movies.length > 0 ? (
                        movies.map((movie) => (
                            <div
                                key={movie.id}
                                className="bg-cover bg-no-repeat bg-center w-[200px] h-[300px] relative hover:scale-110 transition-transform duration-500 ease-in-out cursor-pointer"
                                style={{
                                    backgroundImage: `url(${movie.pictureURL || testImage})`,
                                }}
                                onClick={() => navigate(`/detail/${movie.id}`)}
                            >
                                <div className="bg-black w-full h-full opacity-40 absolute top-0 left-0 z-0"/>
                                <div className="relative p-4 flex flex-col items-center justify-end h-full">
                                    <h3 className="text-md uppercase text-center">{movie.title}</h3>
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

export default HistoryPage;
