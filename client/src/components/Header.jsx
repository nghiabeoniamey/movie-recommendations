import PropTypes from "prop-types";
import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { AuthContext } from "../context/AuthContext";

import teamImage from "../assets/img/team-1-800x800.jpg";

const Header = ({ onSearch }) => {
  const { isAuthenticated, logout, role } = useContext(AuthContext);
  const [search, setSearch] = useState("");

  const [showDropdown, setShowDropdown] = useState(false);

  const navigate = useNavigate();

  // const handleSearch = () => {
  //   if (search.trim() !== "") {
  //     navigate(`/search?q=${search.trim()}`);
  //   }
  // };
  const handleSearch = () => {
    if (search.trim() !== "") {
      navigate(`/search?q=${search.trim()}`);
    }
  };

  return (
    <div className="p-4 flex justify-between fixed top-0 left-0 w-full z-[9999] bg-black">
      <div className="flex items-center gap-8">
        <h1 className="text-[30px] uppercase text-red-700 font-bold">Movie</h1>
        <nav className="hidden md:flex items-center space-x-5">
          <a href="/" className="hover:text-red-700 text-white">
            Home
          </a>
          <a href="/all" className="hover:text-red-700 text-white">
            All
          </a>
        </nav>
      </div>

      <div className="flex w-full max-w-md rounded bg-white">
        <input
          type="search"
          name="search"
          placeholder="Search..."
          className="w-full border-none bg-transparent px-4 py-1 text-gray-400 outline-none focus:outline-none"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
        />

        {/* <button type="submit" className="p-2" onClick={() => onSearch(search)}> */}
        <button type="submit" className="p-2" onClick={handleSearch}>
          <svg className="fill-black h-6 w-6" viewBox="0 0 56.966 56.966">
            <path
              d="M55.146,51.887L41.588,37.786c3.486-4.144,5.396-9.358,5.396-14.786c0-12.682-10.318-23-23-23s-23,10.318-23,23
      s10.318,23,23,23c4.761,0,9.298-1.436,13.177-4.162l13.661,14.208c0.571,0.593,1.339,0.92,2.162,0.92
      c0.779,0,1.518-0.297,2.079-0.837C56.255,54.982,56.293,53.08,55.146,51.887z M23.984,6c9.374,0,17,7.626,17,17
      s-7.626,17-17,17s-17-7.626-17-17S14.61,6,23.984,6z"
            />
          </svg>
        </button>
      </div>
      {isAuthenticated ? (
        <div className="relative">
          <button
            className="flex items-center text-white px-3 py-1 rounded-lg"
            onClick={() => setShowDropdown(!showDropdown)}
          >
            <img
              src={teamImage}
              alt="avatar"
              className="h-8 w-8 rounded-full object-cover mr-2"
            />
          </button>

          {showDropdown && (
            <div className="absolute right-0 mt-2 w-48 bg-white rounded-lg shadow-lg overflow-hidden z-50">
              <Link
                to="/profile"
                className="block px-4 py-2 text-gray-700 hover:bg-gray-100"
              >
                Profile
              </Link>
              {role == "ADMIN" && (
                <Link
                  to="/admin"
                  className="block px-4 py-2 text-gray-700 hover:bg-gray-100"
                >
                  Dashboard
                </Link>
              )}
              <Link
                to="/"
                className="block px-4 py-2 text-gray-700 hover:bg-gray-100"
              >
                History
              </Link>
              <button
                onClick={logout}
                className="block w-full text-left px-4 py-2 text-gray-700 hover:bg-gray-100"
              >
                Logout
              </button>
            </div>
          )}
        </div>
      ) : (
        <Link to="/login" className="text-white px-3 py-1 rounded-lg">
          Login
        </Link>
      )}
    </div>
  );
};

Header.propTypes = {
  onSearch: PropTypes.func.isRequired,
};

export default Header;
