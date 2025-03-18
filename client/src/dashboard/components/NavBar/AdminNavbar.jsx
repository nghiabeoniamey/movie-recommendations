import {Avatar, Dropdown, Menu} from "antd";
import {UserOutlined} from "@ant-design/icons";
import {useAuthStore} from "../../../utils/stores/auth.ts";
import {useNavigate} from "react-router-dom";

export default function Navbar() {

    const navigate = useNavigate();

    const userInfo = useAuthStore().user;

    console.log(userInfo);

    const handleLogout = () => {
        navigate("/login");
    };

    const menu = (
        <Menu>
            <Menu.Item key="logout" onClick={handleLogout}>
                Đăng xuất
            </Menu.Item>
        </Menu>
    );

    return (
        <>
            {/* Navbar */}
            <nav
                className="absolute top-0 left-0 w-full z-10 bg-transparent md:flex-row md:flex-nowrap md:justify-start flex items-center p-4">
                <div
                    className="w-full mx-autp items-center flex justify-between md:flex-nowrap flex-wrap md:px-10 px-4">
                    {/* Brand */}
                    <a
                        className="text-violet-700 text-3xl uppercase hidden lg:inline-block font-extrabold"
                        onClick={(e) => e.preventDefault()}
                    >
                        Manager
                    </a>
                    {/* User */}
                    <ul className="flex-col md:flex-row list-none items-center hidden md:flex">
                        <Dropdown overlay={menu} placement="bottomRight" arrow>
                            <div className="flex items-center cursor-pointer">
                                {/* Hiển thị avatar */}
                                {userInfo?.profilePicture ? (
                                    <Avatar src={userInfo.profilePicture} size="large"/>
                                ) : (
                                    <Avatar icon={<UserOutlined/>} size="large"/>
                                )}
                                <span className="ml-2 truncate">
                                    {userInfo?.userName || "Người dùng"}
                                </span>
                            </div>
                        </Dropdown>
                    </ul>
                </div>
            </nav>
            {/* End Navbar */}
        </>
    );
}

