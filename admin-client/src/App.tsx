import React from 'react';
import { Admin, Resource } from 'react-admin';
import { authProvider } from './authProvider';
import departments from "./departments";
import employees from "./employees";
import organizations from "./organizations";
import { ResoursesEnum } from './common/resourses';
import { dataProvider } from './providers';
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
