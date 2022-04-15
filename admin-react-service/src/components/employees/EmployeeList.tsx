import React  from "react";
import {Datagrid, EditButton, List, ReferenceField, ShowButton, TextField } from "react-admin";
import { AdminListActionToolbar } from "../../common/components/AdminListActionsToolbar";
import { AdminListActionsTop } from "../../common/components/AdminListActionsTop";
import { AdminListProps } from "../../types/common";

export function EmployeeList(props: AdminListProps) {
    return (
        <List {...props}
              pagination={false}
              actions={<AdminListActionsTop/>}
              bulkActionButtons={false}>
            <Datagrid>
                <TextField source="id" sortable={false}/>
                <TextField source="name" sortable={false}/>
                <TextField source="age" sortable={false}/>
                <TextField source="position" sortable={false}/>

                <ReferenceField source="departmentId"
                                reference="department"
                                sortable={false}>
                    <TextField source="name" />
                </ReferenceField>

                <ReferenceField source="organizationId"
                                reference="organization"
                                sortable={false}>
                    <TextField source="name" />
                </ReferenceField>

                <AdminListActionToolbar>
                    {/*<EditButton />*/}
                    <ShowButton />
                </AdminListActionToolbar>
            </Datagrid>
        </List>
    )
};
