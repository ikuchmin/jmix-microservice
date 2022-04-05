import React, { memo } from "react";
import {CreateButton, TopToolbar } from "react-admin";

export const AdminListActionsTop = memo(() => (
    <TopToolbar>
        <CreateButton/>
    </TopToolbar>
));
