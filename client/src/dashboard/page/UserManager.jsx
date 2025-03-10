import { useEffect, useState } from "react";
import CardTable from "../components/Card/CardTable";

import ReactPaginate from "react-paginate";
import { adminUserApi } from "../../api/AdminUser";
import UserModal from "../components/Modal/UserModal";
import { MdDelete, MdEdit } from "react-icons/md";

// components

export default function UserManager() {
  const [isOpen, setIsOpen] = useState(false);

  const [users, setUsers] = useState([]);
  const [totalPages, setTotalPages] = useState(1);

  const [selectedUser, setSelectedUser] = useState(null);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isCreating, setIsCreating] = useState(false);

  useEffect(() => {
    fetchUsers(1);
  }, []);

  const fetchUsers = async (page) => {
    try {
      const response = await adminUserApi.getAllUser(page, 5);
      console.log(response.data);
      setUsers(response.data.data.data);
      setTotalPages(response.data.data.totalPages);
    } catch (error) {
      console.error("Error fetching users:", error);
    }
  };
  const handleDeleteClick = async (userId) => {
    if (window.confirm("Are you sure you want to delete this user?")) {
      try {
        window.alert(userId);
        // await adminUserApi.deleteAdminUser(userId);
        // fetchUsers(1);
      } catch (error) {
        console.error("Error deleting user:", error);
      }
    }
  };

  const openCreateModal = () => {
    setSelectedUser({
      userName: "",
      password: "",
      email: "",
      role: 0,
      profilePicture: null,
    });
    setIsCreating(true);
    setIsModalOpen(true);
  };

  const openEditModal = (user) => {
    setSelectedUser(user);
    setIsCreating(false);
    setIsModalOpen(true);
  };

  const handlePageClick = (event) => {
    fetchUsers(event.selected + 1);
  };

  return (
    <>
      <div className=" mt-4">
        {/* Button */}
        <div>
          <button
            className="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 m-3 rounded"
            onClick={openCreateModal}
          >
            Add New
          </button>
        </div>

        <div className="w-full px-4">
          <div className="relative flex flex-col min-w-0 break-words w-full mb-6 shadow-lg rounded bg-white">
            <div className="w-full mt-6 overflow-auto">
              <table className="w-full border border-gray-300 bg-white shadow-lg rounded-lg">
                <thead className="bg-gray-100">
                  <tr>
                    {["Username", "Email", "Image", "Role", ""].map(
                      (header) => (
                        <th key={header} className="p-4 text-left border-b">
                          <p className="text-gray-700 font-medium">{header}</p>
                        </th>
                      )
                    )}
                  </tr>
                </thead>
                <tbody>
                  {users.map((user) => (
                    <tr key={user.id} className="hover:bg-gray-50">
                      <td className="p-4 border-b text-gray-900 font-semibold flex items-center gap-3">
                        {user.name}
                      </td>
                      <td className="p-4 border-b text-gray-700 font-medium">
                        {user.email}
                      </td>
                      <td className="p-4 border-b">
                        {user.profilePicture ? (
                          <img
                            src={user.profilePicture}
                            alt="Profile"
                            className="w-10 h-10 object-cover rounded-full border"
                          />
                        ) : (
                          <span className="text-gray-400">No Image</span>
                        )}
                      </td>
                      <td className="p-4 border-b text-gray-700 font-medium">
                        <span
                          className={`px-2 py-1 rounded-md text-xs font-semibold ${
                            user.role === 0
                              ? "bg-blue-100 text-blue-700"
                              : "bg-orange-100 text-orange-700"
                          }`}
                        >
                          {user.role === 0 ? "Admin" : "User"}
                        </span>
                      </td>
                      <td className="p-4 border-b text-center flex gap-2">
                        <button
                          className="p-2 rounded-full hover:bg-gray-200 transition"
                          onClick={() => openEditModal(user)}
                        >
                          <MdEdit />
                        </button>
                        <button
                          className="p-2 rounded-full hover:bg-red-200 transition text-red-600"
                          onClick={() => handleDeleteClick(user.id)}
                        >
                          <MdDelete />
                        </button>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div className="flex justify-center mt-4">
          <ReactPaginate
            previousLabel={"Previous"}
            nextLabel={"Next"}
            breakLabel={"..."}
            pageCount={totalPages}
            marginPagesDisplayed={2}
            pageRangeDisplayed={3}
            onPageChange={handlePageClick}
            containerClassName={"flex list-none"}
            pageClassName={"px-2 py-1 border mx-1 rounded cursor-pointer"}
            activeClassName={"bg-blue-500 text-white"}
            previousClassName={"px-2 py-1 border mx-1 rounded cursor-pointer"}
            nextClassName={"px-2 py-1 border mx-1 rounded cursor-pointer"}
            disabledClassName={"opacity-50 cursor-not-allowed"}
          />
        </div>
        {/* {isOpen && <ModalExample isOpen={isOpen} setIsOpen={setIsOpen} />} */}
        <UserModal
          isOpen={isModalOpen}
          setIsOpen={setIsOpen}
          onClose={() => setIsModalOpen(false)}
          user={selectedUser}
          isCreating={isCreating}
        />
        {/* <UserModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)} onSubmit={handleSubmit} user={selectedUser} isCreating={isCreating} /> */}
      </div>
    </>
  );
}
