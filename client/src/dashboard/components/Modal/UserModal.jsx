import Modal from "react-modal";
import { useEffect, useState } from "react";
import FileUpload from "./FileUpload";
import teamImage from "../../../assets/img/team-1-800x800.jpg";
import { adminUserApi } from "../../../api/AdminUser";

Modal.setAppElement("#root");

export default function UserModal({ isOpen, onClose, user, isCreating }) {
  const [localUser, setLocalUser] = useState(
    user || {
      name: "",
      password: "",
      email: "",
      role: 0,
      profilePicture: null,
    }
  );
  // Lưu danh sách ảnh cũ (nếu có)
  const [existingImages, setExistingImages] = useState([]);

  useEffect(() => {
    console.log(user);
    setLocalUser(
      user || {
        name: "",
        password: "",
        email: "",
        role: 0,
        profilePicture: null,
      }
    );
    setExistingImages(user?.profilePicture ? [user?.profilePicture] : []);
  }, [user]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setLocalUser({ ...localUser, [name]: value });
  };

  const handleFiles = (files) => {
    setLocalUser({
      ...localUser,
      profilePicture: files.length ? files[0] : null,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      console.log(localUser);
      // const formData = new FormData();
      // formData.append("name", localUser.name);
      // formData.append("email", localUser.email);
      // formData.append("role", localUser.role);
      // if (localUser.profilePicture) {
      //   formData.append("profilePicture", localUser.profilePicture);
      // }

      // if (isCreating) {
      //   await adminUserApi.createUser(formData);
      // } else {
      //   await adminUserApi.updateUser(user.id, formData);
      // }

      // setIsOpen(false);
    } catch (error) {
      console.error("Error saving user:", error);
    }
  };

  return (
    <Modal
      isOpen={isOpen}
      onRequestClose={onClose}
      className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50"
      overlayClassName="fixed inset-0 bg-black bg-opacity-50"
    >
      <div className="relative bg-white p-6 rounded-xl shadow-xl w-[400px]">
        {/* Nút đóng */}
        <button
          onClick={onClose}
          className="absolute top-4 right-4 p-1 text-gray-500 hover:text-gray-700"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            fill="none"
            viewBox="0 0 24 24"
            strokeWidth="1.5"
            stroke="currentColor"
            className="size-6"
          >
            <path
              strokeLinecap="round"
              strokeLinejoin="round"
              d="M6 18L18 6M6 6l12 12"
            />
          </svg>
        </button>

        {/* Tiêu đề */}
        <h2 className="text-2xl font-semibold text-gray-800 mb-4 text-center">
          {isCreating ? "Create User" : "Edit User"}
        </h2>

        {/* Form */}
        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Name
            </label>
            <input
              type="text"
              name="name"
              value={localUser.name}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 rounded-lg focus:ring focus:ring-blue-300 outline-none"
              required
            />
          </div>
          {/* {user && ( */}
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Password
            </label>
            <input
              type="text"
              name="password"
              value={localUser.password}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 rounded-lg focus:ring focus:ring-blue-300 outline-none"
              required
            />
          </div>
          {/* )} */}
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Email
            </label>
            <input
              type="email"
              name="email"
              value={localUser.email}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 rounded-lg focus:ring focus:ring-blue-300 outline-none"
              required
            />
          </div>
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Role
            </label>
            <select
              name="role"
              value={localUser.role}
              onChange={handleChange}
              className="w-full p-2 border border-gray-300 rounded-lg focus:ring focus:ring-blue-300 outline-none"
              required
            >
              <option value="0">Admin</option>
              <option value="1">User</option>
            </select>
          </div>

          <FileUpload
            mode={isCreating ? "create" : "edit"}
            defaultFiles={existingImages}
            onFilesChange={handleFiles}
          />

          {/* Nút submit */}
          <button
            type="submit"
            className="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 transition"
          >
            {isCreating ? "Create" : "Save"}
          </button>
        </form>
      </div>
    </Modal>
  );
}
