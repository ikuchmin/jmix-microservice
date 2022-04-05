import React  from "react";
import {Datagrid, EditButton, List, Pagination, ReferenceField, ShowButton, TextField } from "react-admin";
import { AdminListActionToolbar } from "../common/components/AdminListActionsToolbar";
import { AdminListActionsTop } from "../common/components/AdminListActionsTop";
import { AdminListProps } from "../types/common";


export function DepartmentList(props: AdminListProps) {
    return (
        <List {...props}
              pagination={false}
              actions={<AdminListActionsTop/>}
              bulkActionButtons={false}>
            <Datagrid>
                <TextField source="id" sortable={false}/>
                <TextField source="name" sortable={false}/>
                <ReferenceField source="organizationId" reference="organization" sortable={false}>
                    <TextField source="name" />
                </ReferenceField>
                <AdminListActionToolbar>
                    <EditButton />
                    <ShowButton />
                </AdminListActionToolbar>
            </Datagrid>
        </List>
    )
};
