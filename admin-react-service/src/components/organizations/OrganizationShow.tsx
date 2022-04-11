import React from "react";
import {Datagrid, ReferenceManyField, Show, ShowButton, SimpleShowLayout, Tab, TabbedShowLayout, TextField } from "react-admin";
import { AdminListActionToolbar } from "../../common/components/AdminListActionsToolbar";
import { AdminShowProps } from "../../types/common";

export function OrganizationShow(props: AdminShowProps) {
    return (
        <Show {...props}>
            <TabbedShowLayout>
                <Tab label="summary">
                    <TextField source="id" />
                    <TextField source="name" />
                    <TextField source="address" />
                </Tab>
                <Tab label="departments">
                    <ReferenceManyField
                        addLabel={false}
                        reference="organization"
                        target="department_id"
                    >
                        <Datagrid>
                            <TextField source="id" sortable={false}/>
                            <TextField source="name" sortable={false}/>
                            <AdminListActionToolbar>
                                <ShowButton />
                            </AdminListActionToolbar>
                        </Datagrid>
                    </ReferenceManyField>
                </Tab>
                <Tab label="employees">
                    <ReferenceManyField
                        addLabel={false}
                        reference="organization"
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
