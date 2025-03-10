import React, { useEffect } from "react";
import { useNavigate, useSearchParams } from "react-router-dom";
import authApi from "../../api/auth";
import { toast } from "react-toastify";

const VerifyRedirect = () => {
  const [searchParams] = useSearchParams();
  const navigate = useNavigate();
  const token = searchParams.get("token");

  useEffect(() => {
    if (!token) return;
    const verifyEmail = async () => {
      try {
        const res = await authApi.verify(token);
        if (res.status === 200) {
          toast.success(res.data.message, {
            autoClose: 1500,
          });
          // navigate("/dashboard");
        } else if (res.status === 400) {
          toast.error(res.data.message, {
            autoClose: 1500,
          });
        }
        setTimeout(() => navigate("/login"), 2000);
      } catch (error) {
        toast.error("Xác thực thất bại hoặc token không hợp lệ!", {
          autoClose: 1500,
        });
        setTimeout(() => navigate("/login"), 2000);
      }
    };

    verifyEmail();
  }, [token]);

  return (
    <div className="flex justify-center items-center h-screen">
      <div className="relative inline-flex">
        <div className="w-8 h-8 bg-blue-500 rounded-full"></div>
        <div className="w-8 h-8 bg-blue-500 rounded-full absolute top-0 left-0 animate-ping"></div>
        <div className="w-8 h-8 bg-blue-500 rounded-full absolute top-0 left-0 animate-pulse"></div>
      </div>
    </div>
  );
};

export default VerifyRedirect;
