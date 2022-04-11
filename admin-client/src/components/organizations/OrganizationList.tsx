import React, { memo } from "react";
import {Datagrid, EditButton, List, ShowButton, TextField } from "react-admin";
import { AdminListActionToolbar } from "../../common/components/AdminListActionsToolbar";
import { AdminListActionsTop } from "../../common/components/AdminListActionsTop";
import { AdminListProps } from "../../types/common";

export function OrganizationList(props: AdminListProps) {
    return (
        <List {...props}
              pagination={false}
              actions={<AdminListActionsTop/>}
              bulkActionButtons={false}>
            <Datagrid>
                <TextField source="id" sortable={false}/>
                <TextField source="name" sortable={false}/>
                <TextField source="address" sortable={false}/>
                <AdminListActionToolbar>
                    <EditButton />
                    <ShowButton />
                </AdminListActionToolbar>
            </Datagrid>
        </List>
    )
};
