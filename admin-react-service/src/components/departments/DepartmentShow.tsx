import React from "react";
import {Datagrid, ReferenceField, ReferenceManyField, Show,
    ShowButton, SimpleShowLayout, Tab, TabbedShowLayout, TextField } from "react-admin";
import { AdminListActionToolbar } from "../../common/components/AdminListActionsToolbar";
import { AdminShowProps } from "../../types/common";

export function DepartmentShow(props: AdminShowProps) {
    return (
        <Show {...props}>
            <TabbedShowLayout>
                <Tab label="summary">
                    <TextField source="id" />
                    <TextField source="name" />

                    <ReferenceField source="organizationId" reference="organization">
                        <TextField source="name" />
                    </ReferenceField>
                </Tab>
                <Tab label="employees">
                    <ReferenceManyField
                        addLabel={false}
                        reference="employee"
                        target="employee_id"
                    >
                        <Datagrid>
                            <TextField source="id" sortable={false}/>
                            <TextField source="name" sortable={false}/>
                            <TextField source="age" sortable={false}/>
                            <TextField source="position" sortable={false}/>
                            <AdminListActionToolbar>
                                <ShowButton />
                            </AdminListActionToolbar>
                        </Datagrid>
                    </ReferenceManyField>
                </Tab>
            </TabbedShowLayout>
        </Show>
    )
}
