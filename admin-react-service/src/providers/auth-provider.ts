import { AuthProvider, LegacyAuthProvider } from "react-admin";
import { REST_BASE_PATH_2 } from "../common/rest-info";

export const authProvider: AuthProvider | LegacyAuthProvider | undefined = {
    login: async ({ username, password }) => {
        const response = await fetch(`${REST_BASE_PATH_2}/auth/realms/master/protocol/openid-connect/token`, {
            method: 'POST',
            headers: {
                'Accept': '*/*',
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `client_id=admin-react&password=${password}&username=${username}&grant_type=password`
        })

        const result = JSON.parse(await response.text());
        if ("access_token" in result) {
            localStorage.removeItem('not_authenticated')
            localStorage.setItem('token', result['access_token']);
            localStorage.setItem('login', username);
            localStorage.setItem('user', username);
            return Promise.resolve();
        }

        localStorage.setItem('not_authenticated', 'true');
        return Promise.reject()
    },
    logout: () => {
        localStorage.setItem('not_authenticated', 'true');
        // localStorage.removeItem('role');
        localStorage.removeItem('login');
        localStorage.removeItem('user');
        localStorage.removeItem('token');
        return Promise.resolve();
    },
    checkError: ({ status }) => {
        return status === 401 || status === 403
            ? Promise.reject()
            : Promise.resolve();
    },
    checkAuth: () => {
        return localStorage.getItem('not_authenticated')
            ? Promise.reject()
            : Promise.resolve();
    },
    getPermissions: () => {
        const role = localStorage.getItem('role');
        return Promise.resolve(role);
    },
    // getIdentity: () => {
    //     return Promise.resolve({
    //         // id: localStorage.getItem('login'),
    //         fullName: localStorage.getItem('user'),
    //         token: localStorage.getItem('token'),
    //         role: localStorage.getItem('role')
    //     });
    // },
}
