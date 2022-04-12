import React from 'react';
import { Admin, Resource } from 'react-admin';
import departments from "./components/departments";
import employees from "./components/employees";
import organizations from "./components/organizations";
import { ResoursesEnum } from './common/resourses';
import { authProvider } from './providers/auth-provider';
import { dataProvider } from './providers/data-provider';
import "./style.css";

function App() {
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
