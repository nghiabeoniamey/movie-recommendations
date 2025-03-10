import { Link } from "react-router-dom";
import { Globe } from "lucide-react";

const Footer = () => {
  return (
    <div className="bg-black text-white px-4">
      <div className="max-w-5xl mx-auto">
        {/* Netflix Branding */}
        <div className="mb-6 text-left">
          <h2 className="text-2xl font-bold">Movie</h2>
          <p className="text-gray-400">© Movie VietNam</p>
        </div>

        {/* Footer Grid */}
        <div className="grid grid-cols-3 md:grid-cols-3 sm:grid-cols-2 gap-x-12 gap-y-6 text-lg">
          {/* EXPLORE */}
          <div className="flex flex-col space-y-3">
            <h3 className="text-gray-400">EXPLORE</h3>
            <Link to="/" className="hover:underline">
              Help Center
            </Link>
            <Link to="/" className="hover:underline">
              Account
            </Link>
            <Link to="/" className="hover:underline">
              Ways to Watch
            </Link>
            <Link to="/" className="hover:underline">
              Only on Netflix
            </Link>
          </div>

          {/* LEGAL */}
          <div className="flex flex-col space-y-3">
            <h3 className="text-gray-400">LEGAL</h3>
            <Link to="/" className="hover:underline">
              Cookie Preferences
            </Link>
            <Link to="/" className="hover:underline">
              Privacy Policy
            </Link>
            <Link to="/" className="hover:underline">
              Terms of Use
            </Link>
            <Link to="/" className="hover:underline">
              Gift Card Terms
            </Link>
            <Link to="/" className="hover:underline">
              Legal Notices
            </Link>
            <Link to="/" className="hover:underline">
              Corporate Information
            </Link>
          </div>

          {/* SUPPORT */}
          <div className="flex flex-col space-y-3">
            <h3 className="text-gray-400">SUPPORT</h3>
            <Link to="/" className="hover:underline">
              FAQ
            </Link>
            <Link to="/" className="hover:underline">
              Speed Test
            </Link>
            <Link to="/" className="hover:underline">
              Contact Us
            </Link>
            <Link to="/" className="hover:underline">
              Jobs
            </Link>
            <Link to="/" className="hover:underline">
              Media Center
            </Link>
            <Link to="/" className="hover:underline">
              Investor Relations
            </Link>
          </div>
        </div>

        {/* Language Selector */}
        <div className="mt-6">
          <button className="flex items-center space-x-2 px-4 py-2 border border-gray-500 rounded text-gray-300">
            <Globe size={20} />
            <span>English</span>
          </button>
        </div>
      </div>
    </div>
  );
};

export default Footer;
