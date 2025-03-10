import { Star } from "lucide-react";
import React, { useState, useEffect } from "react";
import { FiSearch, FiFilter, FiDownload } from "react-icons/fi";
import { adminReviewApi } from "../../api/AdminReview";
import AsyncCreatableSelect from "react-select/async-creatable";
import { adminUserApi } from "../../api/AdminUser";
import ReactPaginate from "react-paginate";
// import { format } from "date-fns";

const ReviewManager = () => {
  const [reviews, setReviews] = useState([]);
  const [users, setUsers] = useState({});
  const [totalPages, setTotalPages] = useState(1);
  const [currentPage, setCurrentPage] = useState(0);

  useEffect(() => {
    fetchReviews(1);
  }, []);

  const fetchReviews = async (page) => {
    try {
      const response = await adminReviewApi.getAllReview(page, 6);
      // console.log(response.data);
      setReviews(response.data.data.data);
      setTotalPages(response.data.data.totalPages);
    } catch (error) {
      console.error("Error fetching review:", error);
    }
  };
  const fetchUserById = async (id) => {
    if (users[id]) return; // Tránh gọi lại API nếu đã có thông tin user

    try {
      const response = await adminUserApi.getUserById(id);
      setUsers((prev) => ({ ...prev, [id]: response.data.data })); // Lưu vào state
    } catch (error) {
      console.error("Error fetching user:", error);
    }
  };
  useEffect(() => {
    reviews.forEach((review) => fetchUserById(review.userId));
  }, [reviews]);

  const handleDeleteReview = async (reviewId) => {
    if (!window.confirm("Are you sure you want to delete this review?")) return;
    try {
      await adminReviewApi.deleteAdminReview(reviewId);
      fetchReviews(1);
      setReviews((prevReviews) =>
        prevReviews.filter((review) => review.id !== reviewId)
      );
      console.log("Review deleted successfully");
    } catch (error) {
      console.error("Error deleting review:", error);
    }
  };

  // const handleFilterChange = (e) => {
  //   const { name, value } = e.target;
  //   setFilters((prev) => ({ ...prev, [name]: value }));
  // };

  // const handleSearch = () => {

  // };

  const handlePageClick = (selectedPage) => {
    const newPage = selectedPage.selected + 1;
    setCurrentPage(selectedPage.selected);
    fetchReviews(newPage);
  };

  const handleStatusChange = (reviewId, newStatus) => {};

  const handleDelete = (reviewId) => {};

  return (
    <div className="min-h-screen bg-gray-100 p-4">
      <div className="max-w-7xl mx-auto">
        <h1 className="text-3xl font-bold text-gray-800 mb-8">
          Movie Review Management
        </h1>

        <div className="grid grid-cols-1 md:grid-cols-4 gap-4 mb-8">
          <div className="bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow">
            <h3 className="text-lg font-semibold text-gray-700">
              Total Reviews
            </h3>
            <p className="text-3xl font-bold text-blue-600">10</p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow">
            <h3 className="text-lg font-semibold text-gray-700">
              Pending Reviews
            </h3>
            <p className="text-3xl font-bold text-yellow-600">
              {/* {reviews.filter((r) => r.status === "pending").length} */}10
            </p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow">
            <h3 className="text-lg font-semibold text-gray-700">
              Reported Reviews
            </h3>
            <p className="text-3xl font-bold text-red-600">
              {reviews.filter((r) => r.reported).length}
            </p>
          </div>
          <div className="bg-white p-6 rounded-lg shadow-lg hover:shadow-xl transition-shadow">
            <h3 className="text-lg font-semibold text-gray-700">
              Average Rating
            </h3>
            <p className="text-3xl font-bold text-green-600">
              {(
                reviews.reduce((acc, curr) => acc + curr.rating, 0) /
                  reviews.length || 0
              ).toFixed(1)}
            </p>
          </div>
        </div>

        <div className="bg-white p-6 rounded-lg shadow-lg mb-8">
          <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Movie Title
              </label>
              <input
                type="text"
                name="movieTitle"
                // value={filters.movieTitle}
                // onChange={handleFilterChange}
                className="w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
                placeholder="Search by movie title"
              />
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Category
              </label>
              <select
                name="category"
                // value={filters.category}
                // onChange={handleFilterChange}
                className="w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              >
                <option value="">All Categories</option>
                <option value="action">Action</option>
                <option value="drama">Drama</option>
                <option value="comedy">Comedy</option>
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Status
              </label>
              <select
                name="status"
                // value={filters.status}
                // onChange={handleFilterChange}
                className="w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              >
                <option value="all">All Status</option>
                <option value="pending">Pending</option>
                <option value="approved">Approved</option>
                <option value="rejected">Rejected</option>
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Report Status
              </label>
              <select
                name="reportStatus"
                // value={filters.reportStatus}
                // onChange={handleFilterChange}
                className="w-full p-2 border rounded-md focus:ring-2 focus:ring-blue-500 focus:border-blue-500"
              >
                <option value="all">All Reviews</option>
                <option value="reported">Reported Only</option>
                <option value="not_reported">Not Reported</option>
              </select>
            </div>
          </div>
          <div className="mt-4 flex justify-end space-x-2">
            <button
              // onClick={handleSearch}
              className="px-4 py-2 bg-blue-600 text-white rounded-md hover:bg-blue-700 flex items-center transform hover:scale-105 transition-transform"
            >
              <FiSearch className="mr-2" /> Search
            </button>
            <button className="px-4 py-2 bg-gray-600 text-white rounded-md hover:bg-gray-700 flex items-center transform hover:scale-105 transition-transform">
              <FiDownload className="mr-2" /> Export
            </button>
          </div>
        </div>
        <div className="grid grid-cols-3 gap-3">
          {reviews.map((review) => {
            const user = users[review.userId];
            return (
              <div
                key={review.id}
                className="max-w-md p-4 bg-white shadow-md rounded-2xl border"
              >
                <div className="flex items-center gap-4">
                  <img
                    src={
                      user?.avatar ||
                      `https://randomuser.me/api/portraits/men/${Math.floor(
                        Math.random() * 100
                      )}.jpg`
                    }
                    alt="User Avatar"
                    className="w-12 h-12 rounded-full"
                  />
                  <div>
                    <h3 className="font-semibold text-lg">
                      {user?.name || "Loading..."}
                    </h3>
                  </div>
                </div>
                <div className="mt-3 flex items-center gap-1">
                  {[...Array(parseInt(review.rating))].map((_, i) => (
                    <Star
                      key={i}
                      className="w-4 h-4 text-yellow-500 fill-yellow-500"
                    />
                  ))}
                  {[...Array(5 - parseInt(review.rating))].map((_, i) => (
                    <Star key={i} className="w-4 h-4 text-gray-300" />
                  ))}
                  <span className="text-gray-500 text-sm ml-2">
                    {new Date(
                      parseInt(review.createdDate)
                    ).toLocaleDateString()}
                  </span>
                </div>
                <p className="text-gray-700 mt-2 text-sm">{review.comment}</p>
                <div className="mt-3 flex gap-2">
                  <button className="px-4 py-1.5 text-sm border rounded-lg bg-gray-100 hover:bg-gray-200">
                    Public Comment
                  </button>
                  <button
                    className="px-4 py-1.5 text-sm border rounded-lg bg-red-500 text-white hover:bg-red-600"
                    onClick={() => handleDeleteReview(review.id)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            );
          })}
        </div>
        <div className="flex justify-center mt-6">
          <ReactPaginate
            previousLabel={"← Previous"}
            nextLabel={"Next →"}
            breakLabel={"..."}
            pageCount={totalPages}
            marginPagesDisplayed={2}
            pageRangeDisplayed={3}
            onPageChange={handlePageClick}
            containerClassName={"flex list-none gap-2"}
            pageClassName={
              "px-4 py-2 border rounded-lg cursor-pointer hover:bg-gray-200"
            }
            activeClassName={"bg-blue-500 text-white"}
            previousClassName={
              "px-4 py-2 border rounded-lg cursor-pointer hover:bg-gray-200"
            }
            nextClassName={
              "px-4 py-2 border rounded-lg cursor-pointer hover:bg-gray-200"
            }
            disabledClassName={"opacity-50 cursor-not-allowed"}
            forcePage={currentPage}
          />
        </div>
      </div>
    </div>
  );
};

export default ReviewManager;
