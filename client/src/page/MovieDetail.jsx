import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import ReactPlayer from "react-player";
import {
  FaStar,
  FaStarHalfAlt,
  FaClock,
  FaImdb,
  FaTheaterMasks,
  FaCalendarAlt,
  FaGlobe,
} from "react-icons/fa";

const comments = [
  {
    id: 1,
    name: "Arlene",
    date: "12/06/2020",
    text: "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo con",
    avatar: "https://randomuser.me/api/portraits/men/32.jpg",
    likes: 10,
  },
  {
    id: 2,
    name: "Arlene",
    date: "12/06/2020",
    text: "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo con",
    avatar: "https://randomuser.me/api/portraits/women/44.jpg",
    likes: 10,
  },
];

const MovieDetail = () => {
  const { id } = useParams();
  const [movie, setMovie] = useState(null);
  const [trailerUrl, setTrailerUrl] = useState("");

  useEffect(() => {
    const fetchMovie = async () => {
      try {
        const response = await fetch(
          `https://api.themoviedb.org/3/movie/${id}?language=en-US`,
          {
            headers: {
              Authorization: `Bearer ${import.meta.env.VITE_API_KEY}`,
              "Content-Type": "application/json;charset=utf-8",
            },
          }
        );

        const data = await response.json();
        setMovie(data);
      } catch (error) {
        console.error("Error fetching movie details:", error);
      }
    };

    fetchMovie();
  }, [id]);

  useEffect(() => {
    const fetchTrailer = async () => {
      try {
        const response = await fetch(
          `https://api.themoviedb.org/3/movie/${id}/videos?language=en-US`,
          {
            headers: {
              Authorization: `Bearer ${import.meta.env.VITE_API_KEY}`,
              "Content-Type": "application/json;charset=utf-8",
            },
          }
        );
        const data = await response.json();
        if (data.results.length > 0) {
          setTrailerUrl(
            `https://www.youtube.com/watch?v=${data.results[0].key}`
          );
        }
      } catch (error) {
        console.error("Error fetching trailer:", error);
      }
    };

    fetchTrailer();
  }, [id]);

  const renderStars = (rating) => {
    if (!rating) return null;
    const stars = [];
    const fullStars = Math.floor(rating / 2); // TMDB dùng thang 10, đổi thành 5 sao
    const hasHalfStar = rating % 2 >= 1;

    for (let i = 0; i < fullStars; i++) {
      stars.push(<FaStar key={`star-${i}`} className="text-orange-500" />);
    }
    if (hasHalfStar) {
      stars.push(<FaStarHalfAlt key="half-star" className="text-orange-500" />);
    }
    return stars;
  };

  if (!movie) return <div>Loading...</div>;

  return (
    <div className="h-full bg-black text-white min-h-screen pb-10 relative ">
      <div className="px-5 flex justify-center">
        {trailerUrl && (
          <div className="mb-6 w-full max-w-5xl">
            <ReactPlayer
              url={trailerUrl}
              controls
              width="100%"
              height="500px"
            />
          </div>
        )}
      </div>

      {/* <div className="min-h-screen bg-gradient-to-br from-gray-900 to-gray-800 py-12 px-4 sm:px-6 lg:px-8"> */}
      <div className="max-w-7xl mx-auto my-8">
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8">
          {/* Movie Poster */}
          <div className="relative group overflow-hidden rounded-lg shadow-2xl transition-transform duration-300 hover:scale-[1.02] flex justify-center">
            <img
              src={`https://image.tmdb.org/t/p/w500${movie.poster_path}`}
              alt={movie.title}
              className=" w-xl h-auto max-h-[400px] object-cover "
              onError={(e) => {
                e.target.src =
                  "https://via.placeholder.com/500x750?text=No+Image";
              }}
            />
            <div className="absolute inset-0 bg-gradient-to-t from-black/70 to-transparent opacity-0 group-hover:opacity-100 transition-opacity duration-300"></div>
          </div>

          {/* Movie Details */}
          <div className="space-y-6">
            <div>
              <h1 className="text-5xl font-bold text-white mb-2 tracking-tight">
                {movie.title}
              </h1>
              <p className="text-xl text-gray-400">
                {new Date(movie.release_date).getFullYear()}
              </p>
            </div>

            <div className="flex flex-wrap gap-2">
              {movie.genres?.map((genre) => (
                <span
                  key={genre.id}
                  className="px-4 py-1 bg-white/10 text-white rounded-full hover:bg-white/20 transition-colors duration-200 cursor-pointer"
                >
                  {genre.name}
                </span>
              ))}
            </div>

            <div className="flex items-center space-x-6">
              <div className="flex items-center">
                {renderStars(movie.vote_average)}
                <span className="ml-2 text-white">
                  {movie.vote_average?.toFixed(1)}/10
                </span>
              </div>
              <div className="flex items-center text-white">
                <FaClock className="mr-2" />
                {Math.floor(movie.runtime / 60)}h {movie.runtime % 60}m
              </div>
            </div>

            <p className="text-gray-300 text-lg leading-relaxed">
              {movie.overview}
            </p>

            <div className="grid grid-cols-2 gap-4 bg-white/5 p-6 rounded-lg">
              <div className="flex items-center space-x-2 text-gray-300">
                <FaTheaterMasks />
                <span>Director: Updating...</span>{" "}
                {/* Fetch from API if needed */}
              </div>
              <div className="flex items-center space-x-2 text-gray-300">
                <FaCalendarAlt />
                <span>Release: {movie.release_date}</span>
              </div>
              <div className="flex items-center space-x-2 text-gray-300">
                <FaGlobe />
                <span>Language: {movie.original_language.toUpperCase()}</span>
              </div>
              <div className="flex items-center space-x-2 text-gray-300">
                <FaImdb />
                <span>
                  Production: {movie.production_companies?.[0]?.name || "N/A"}
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
      {/* </div> */}

      <div className="max-w-7xl mx-auto my-8 text-2xl font-bold ">
        <span>You may so like </span>
      </div>

      <div className="max-w-4xl mx-auto bg-black text-white p-6 rounded-lg">
        <h2 className="text-xl font-semibold mb-4">Comments</h2>

        {/* Input box */}
        <div className="flex items-center gap-3 mb-6">
          <img
            src="https://randomuser.me/api/portraits/men/15.jpg"
            alt="User"
            className="w-12 h-12 rounded-full"
          />
          <input
            type="text"
            placeholder="Write your comments here....."
            className="flex-1 bg-gray-800 text-white px-4 py-2 rounded-lg focus:outline-none"
          />
        </div>

        {/* Comments list */}
        {comments.map((comment) => (
          <div key={comment.id} className="flex gap-4 mb-6">
            <img
              src={comment.avatar}
              alt={comment.name}
              className="w-12 h-12 rounded-full"
            />
            <div>
              <p className="font-semibold">{comment.name}</p>
              <p className="text-gray-400 text-sm">{comment.date}</p>
              <p className="mt-1">{comment.text}</p>
              <div className="flex items-center gap-4 mt-2 text-gray-400 text-sm">
                {/* <button className="flex items-center gap-1 hover:text-gray-200">
                  👍 {comment.likes}
                </button>
                <button className="flex items-center gap-1 hover:text-gray-200">
                  👎
                </button> */}
                <button className="hover:text-gray-200 px-7">Reply</button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default MovieDetail;
