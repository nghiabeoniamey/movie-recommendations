import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import ReactPlayer from "react-player";
import {FaCalendarAlt, FaTheaterMasks} from "react-icons/fa";
import testImage from "../assets/img/testimg.jpg";
import {TestReview} from "../components/TestReview.jsx";
import {UserMovieApi} from "../api/UserMovie.ts";

const MovieDetailDemo = () => {
    const {id} = useParams();
    const [movie, setMovie] = useState(null);

    useEffect(() => {
        const fetchMovie = async () => {
            try {
                const response = await UserMovieApi.GetMovieById(id);
                console.log(response.data);
                setMovie(response.data);
            } catch (error) {
                console.error("Lỗi khi lấy thông tin phim:", error);
            }
        };

        fetchMovie();
    }, [id]);

    if (!movie) return <div>Loading...</div>;

    return (
        <div className="h-full bg-black text-white min-h-screen pb-10 relative">
            {/* Hiển thị video */}
            <div className="px-5 flex justify-center">
                {movie.movie && (
                    <div className="mb-6 w-full max-w-5xl">
                        <ReactPlayer
                            url={movie.movie}
                            controls
                            width="100%"
                            height="500px"
                        />
                    </div>
                )}
            </div>

            {/* Thông tin chi tiết phim */}
            <div className="max-w-7xl mx-auto my-8">
                <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
                    {/* Movie Poster */}
                    <div
                        className="relative group overflow-hidden rounded-lg shadow-2xl transition-transform duration-300 hover:scale-[1.02] flex justify-center">
                        <img
                            src={movie.pictureURL || testImage}
                            alt={movie.title}
                            className="w-xl h-auto max-h-[400px] object-cover"
                            onError={(e) => {
                                e.target.onerror = null;
                                e.target.src = testImage;
                            }}
                        />
                    </div>

                    {/* Movie Details */}
                    <div className="space-y-6">
                        <h1 className="text-5xl font-bold text-white mb-2">
                            {movie.title}
                        </h1>
                        <p className="text-xl text-gray-400">{movie.year}</p>

                        <div className="flex flex-wrap gap-2">
                            {movie.categories?.map((category) => (
                                <span
                                    key={category.id} // Thêm key duy nhất ở đây
                                    className="px-4 py-1 bg-white/10 text-white rounded-full"
                                >
                                    {category.name}
                                </span>
                            ))}
                        </div>

                        <p className="text-gray-300 text-lg leading-relaxed">
                            {movie.description}
                        </p>

                        <div className="grid grid-cols-2 gap-4 bg-white/5 p-6 rounded-lg">
                            <div className="flex items-center space-x-2 text-gray-300">
                                <FaTheaterMasks/>
                                <span>Author: {movie.author}</span>
                            </div>
                            <div className="flex items-center space-x-2 text-gray-300">
                                <FaCalendarAlt/>
                                <span>Release: {movie.year}</span>
                            </div>
                            <div className="flex col-span-2 items-center space-x-2 text-gray-300">
                                <FaTheaterMasks/>
                                <span>Actor: {movie.actor}</span>
                            </div>
                        </div>
                    </div>
                </div>
                {/*<div className="max-w-7xl mx-auto my-8 text-2xl font-bold ">*/}
                {/*    <span>You may so like </span>*/}
                {/*</div>*/}

                {/* Review */}
                <TestReview movieId={id}/>
            </div>
        </div>
    );
};

export default MovieDetailDemo;
