import React, {useEffect, useState} from "react";
import teamImage from "../assets/img/team-1-800x800.jpg";
import {useAuthStore} from "../utils/stores/auth";
import {AdminUserApi} from "../api/AdminUser";
import {toast} from "react-toastify";

export default function Profile() {
    const authStore = useAuthStore();
    const {id: userId} = authStore.user;
    const [user, setUser] = useState(null);
    const [formData, setFormData] = useState({
        name: "",
        email: "",
        password: "",
        role: '0',
        deleted: 'false',
        profilePicture: "",
    });

    useEffect(() => {
        if (userId) {
            AdminUserApi.GetUserById(userId)
                .then((res) => {
                    setUser(res.data);
                    setFormData({
                        name: res.data.name || "",
                        email: res.data.email || "",
                        password: res.data.password || "",
                        role: res.data.role || '0',
                        deleted: `${res.data.deleted}` || 'false',
                        profilePicture: res.data.profilePicture || "",
                    });
                })
                .catch((error) => {
                    console.error("Error fetching profile:", error);
                });
        }
    }, [userId]);

    const handleChange = (e) => {
        const {name, value, type, checked} = e.target;
        setFormData((prev) => ({
            ...prev,
            [name]: type === "checkbox" ? checked : value,
        }));
    };

    const handleSubmit = () => {
        AdminUserApi.UpdateUser(userId, formData)
            .then(() => {
                toast.success("Successfully updated profile");
            })
            .catch((err) => {
                console.error("Update failed:", err);
                toast.error("Update failed");
            });
    };

    return (
        <>
            <main className="profile-page">
                <section className="relative block h-[500px]">
                    <div
                        className="absolute top-0 w-full h-full bg-center bg-cover"
                        style={{
                            backgroundImage: `url(${formData.profilePicture || teamImage})`,
                        }}
                    >
            <span
                id="blackOverlay"
                className="w-full h-full absolute opacity-50 bg-black"
            ></span>
                    </div>
                </section>
                <section className="relative py-16 bg-blueGray-200">
                    <div className="container mx-auto px-4">
                        <div
                            className="relative flex flex-col min-w-0 break-words bg-white w-full mb-6 shadow-xl rounded-lg -mt-64">
                            <div className="px-6">
                                <div className="flex flex-wrap justify-center relative mt-4">
                                    <div className="w-full lg:w-3/12 px-4 flex justify-center">
                                        <img
                                            alt="..."
                                            src={formData.profilePicture || teamImage}
                                            className="shadow-xl rounded-full h-auto align-middle border-none"
                                        />
                                    </div>
                                </div>

                                <div className="text-center mt-12">
                                    <h3 className="text-4xl font-semibold leading-normal mb-2 text-blueGray-700 mb-2">
                                        Profile Settings
                                    </h3>
                                </div>

                                <div className="px-4 py-6">
                                    <div className="mb-4">
                                        <label className="block font-semibold">Name:</label>
                                        <input
                                            name="name"
                                            type="text"
                                            value={formData.name}
                                            onChange={handleChange}
                                            className="border p-2 w-full rounded"
                                        />
                                    </div>

                                    <div className="mb-4">
                                        <label className="block font-semibold">Email:</label>
                                        <input
                                            name="email"
                                            type="email"
                                            value={formData.email}
                                            onChange={handleChange}
                                            className="border p-2 w-full rounded"
                                        />
                                    </div>

                                    <div className="mb-4">
                                        <label className="block font-semibold">Password:</label>
                                        <input
                                            name="password"
                                            type="password"
                                            value={formData.password}
                                            onChange={handleChange}
                                            className="border p-2 w-full rounded"
                                        />
                                    </div>

                                    <div className="mb-4">
                                        <label className="block font-semibold">Role:</label>
                                        <select
                                            name="role"
                                            value={formData.role}
                                            onChange={handleChange}
                                            className="border p-2 w-full rounded"
                                        >
                                            <option value={'0'} disabled={true}>Admin</option>
                                            <option value={'1'}>User</option>
                                        </select>
                                    </div>

                                    <div className="mb-4">
                                        <label className="block font-semibold">Status:</label>
                                        <select
                                            name="deleted"
                                            value={formData.deleted ? "true" : "false"}
                                            onChange={(e) =>
                                                handleChange({
                                                    target: {
                                                        name: "deleted",
                                                        value: e.target.value === "true",
                                                    },
                                                })
                                            }
                                            className="border p-2 w-full rounded"
                                        >
                                            <option value="true">Active</option>
                                            <option value="false">Inactive</option>
                                        </select>
                                    </div>

                                    <div className="mb-4">
                                        <label className="block font-semibold">Profile Picture URL:</label>
                                        <input
                                            name="profilePicture"
                                            type="text"
                                            value={formData.profilePicture}
                                            onChange={handleChange}
                                            className="border p-2 w-full rounded"
                                        />
                                    </div>

                                    <button
                                        onClick={handleSubmit}
                                        className="bg-blue-500 text-white font-bold py-2 px-6 rounded hover:bg-blue-600"
                                    >
                                        Save Changes
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </main>
        </>
    );
}
