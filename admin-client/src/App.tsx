import React from 'react';
import { Admin, fetchUtils, Resource } from 'react-admin';
import departments from "./components/departments";
import employees from "./components/employees";
import organizations from "./components/organizations";
import { ResoursesEnum } from './common/resourses';
import { authProvider } from './providers/auth-provider';
import { getDataProvider } from './providers/data-provider';
import "./style.css";

const httpClient = (url: string, options: any = {}) => {
    if (!options.headers) {
        options.headers = new Headers({ Accept: 'application/json' });
    }

    const token = localStorage.getItem('token');
    options.headers.set('Authorization', `Bearer ${token}`);
    return fetchUtils.fetchJson(url, options);
};

// const options = {
//     headers: new Headers({ Accept: 'application/json' })
// }
//
// if (localStorage.getItem('token')) {
//     const token = localStorage.getItem('token');
//     console.log(token)
//     options.headers.set('Authorization', `Bearer ${token}`);
// }

console.log(localStorage.getItem("token"), 'token pre app');

const dataProvider = getDataProvider();

function App() {
    console.log(localStorage.getItem("token"), 'token app');

    return (
        <Admin dataProvider={dataProvider}
               authProvider={authProvider}>
            <Resource name={ResoursesEnum.employee} {...employees}/>
            <Resource name={ResoursesEnum.department} {...departments}/>
            <Resource name={ResoursesEnum.organization} {...organizations}/>
        </Admin>
      );
}

export default App;
