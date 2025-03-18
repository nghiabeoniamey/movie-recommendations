import IconRatingHalf from "../assets/rating-half.png";
import IconRating from "../assets/rating.png";
import ImgMovie from "../assets/temp-1.jpeg";
import IconPlay from "../assets/play-button.png";

const Banner = () => {
  return (
      <div className="md:h-[600px] h-[1000px] w-full bg-banner bg-cover bg-center bg-no-repeat relative mt-[75px]">
        <div className="w-full h-full bg-black/40" />
        <div className="flex flex-col md:flex-row items-center justify-between absolute md:top-1/2 top-10 -translate-x-1/2 left-1/2 md:-translate-y-1/2 w-full">
          <div className="md:w-[50%] w-full">
            <div className="flex flex-col space-y-6 items-start p-10">
              <p className="bg-gradient-to-r from-red-600 to-red-300 py-2 px-6">
                Francis Ford Coppola
              </p>
              <div className="flex flex-col space-y-4">
                <h1 className="text-[40px] font-bold text-white">The Godfather</h1>
                <div className="flex items-center space-x-3">
                  <img src={IconRating} alt="rating" className="w-8 h-8" />
                  <img src={IconRating} alt="rating" className="w-8 h-8" />
                  <img src={IconRating} alt="rating" className="w-8 h-8" />
                  <img src={IconRating} alt="rating" className="w-8 h-8" />
                  <img src={IconRatingHalf} alt="rating" className="w-8 h-8" />
                </div>
                <p className="text-white">
                  The aging patri­arch of an orga­nized crime dynasty trans­fers con­trol of his clan­des­tine empire to his reluc­tant son.

                  The quin­tes­sence of cin­e­ma with this grandiose oper­at­ic fres­co which is still con­sid­ered the defin­i­tive rep­re­sen­ta­tion of the func­tion­ing of Cosa Nos­tra in col­lec­tive memory.
                </p>
              </div>
            </div>
          </div>
          <div className="md:w-[50%] w-full flex items-center justify-center">
            <div className="w-[300px] h-[400px] relative group">
              <button className="w-full h-full absolute top-0 left-0 flex items-center justify-center backdrop-blur-sm opacity-0 group-hover:opacity-100 transition-opacity duration-500 ease-in-out">
                <img src={IconPlay} alt="play" className="w-16 h-16" />
              </button>
              <img
                  src="https://m.media-amazon.com/images/M/MV5BM2MyNjYxNmUtYTAwNi00MTYxLWJmNWYtYzZlODY3ZTk3OTFlXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_.jpg"
                  alt="banner"
                  className="object-cover w-full h-full"
              />
            </div>
          </div>
        </div>
      </div>
  );
};

export default Banner;