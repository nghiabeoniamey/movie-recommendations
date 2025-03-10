import { Star } from "lucide-react";

const ReviewCard = () => {
  return (
    <div className="max-w-md p-4 bg-white shadow-md rounded-2xl border">
      <div className="flex items-center gap-4">
        <img
          src="https://via.placeholder.com/50" // Thay bằng ảnh thực tế
          alt="User Avatar"
          className="w-12 h-12 rounded-full"
        />
        <div>
          <h3 className="font-semibold text-lg">Towhidur Rahman</h3>
          <p className="text-gray-600 text-sm">
            Total Spend: <span className="font-semibold">$200</span>
          </p>
          <p className="text-gray-600 text-sm">
            Total Review: <span className="font-semibold">14</span>
          </p>
        </div>
      </div>
      <div className="mt-3 flex items-center gap-1">
        {[...Array(4)].map((_, i) => (
          <Star key={i} className="w-4 h-4 text-yellow-500 fill-yellow-500" />
        ))}
        <Star className="w-4 h-4 text-gray-300" />
        <span className="text-gray-500 text-sm ml-2">24-10-2022</span>
      </div>
      <p className="text-gray-700 mt-2 text-sm">
        My first and only mala ordered on Etsy, and I'm beyond delighted! I
        requested a custom mala based on two stones I was called to invite
        together in this kind of creation. The fun and genuine joy I invite
        together in this kind of creation.
      </p>
      <div className="mt-3 flex gap-2">
        <button className="px-4 py-1.5 text-sm border rounded-lg bg-gray-100 hover:bg-gray-200">
          Public Comment
        </button>
        <button className="px-4 py-1.5 text-sm border rounded-lg bg-gray-100 hover:bg-gray-200">
          Direct Message
        </button>
      </div>
    </div>
  );
};

export default ReviewCard;
