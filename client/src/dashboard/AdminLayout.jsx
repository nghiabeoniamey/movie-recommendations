import { Outlet } from "react-router-dom";
import HeaderStats from "./components/Header/HeaderStats";
import AdminNavbar from "./components/NavBar/AdminNavbar";
import Sidebar from "./components/Sidebar/Sidebar";
import FooterAdmin from "./components/Footer/FooterAdmin";

export default function AdminLayout() {
  return (
    <>
      <Sidebar />
      <div className="relative md:ml-64 bg-blueGray-100">
        <AdminNavbar />
        <HeaderStats />
        <div className="px-4 md:px-10 mx-auto w-full ">
          <Outlet />

          <FooterAdmin />
        </div>
      </div>
    </>
  );
}
