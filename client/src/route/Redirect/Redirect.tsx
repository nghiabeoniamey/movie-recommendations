import {useLocation, useNavigate} from "react-router-dom";
import {useAuthStore} from "../../utils/stores/auth";
import {useEffect} from "react";
import {getUserInformation} from "../../utils/token.helper";
import {ROLES} from "../../utils/constants/role";
import {ROUTES_CONSTANTS} from "../../utils/constants/path";
import {toast} from "react-toastify";

const RedirectPage: React.FC = () => {

    const location = useLocation();
    const navigate = useNavigate();
    const authStore = useAuthStore();

    const queryParams = new URLSearchParams(location.search);
    const state = queryParams.get("state");
    const error = queryParams.get("error");

    useEffect(() => {
        if (state) {
            const decodedState = atob(state);

            const {accessToken, refreshToken} = JSON.parse(decodedState);

            const user = getUserInformation(accessToken);

            authStore.login({
                user,
                accessToken,
                refreshToken,
            });

            const userRole = user.roleCode;

            switch (userRole) {
                case ROLES.ADMIN:
                    navigate(ROUTES_CONSTANTS.ADMIN.path);
                    break;
                case ROLES.USER:
                    navigate(ROUTES_CONSTANTS.CLIENT.path);
                    break;
                default:
                    navigate(ROUTES_CONSTANTS.AUTHENTICATION.path);
                    break;
            }
            return;
        }

        if (error) {
            toast.warning(error);
            authStore.logout();
        }

        const timeout = setTimeout(() => {
            navigate(ROUTES_CONSTANTS.AUTHENTICATION.path);
        }, 4000);

        return () => clearTimeout(timeout);
    }, [state]);

    return (
        <div className="d-flex justify-center items-center h-[100vh] bg-white">
            <p className="text-2xl text-violet-700 font-bold">Redirecting...</p>
        </div>
    );
}

export default RedirectPage;
