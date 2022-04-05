import React from "react";
import {ReferenceField, Show, SimpleShowLayout, TextField } from "react-admin";
import { AdminShowProps } from "../types/common";

export function EmployeeShow(props: AdminShowProps) {
    return (
        <Show {...props}>
            <SimpleShowLayout>
                <TextField source="id" />
                <TextField source="name" />
                <TextField source="age" />
                <TextField source="position" />

                <ReferenceField source="departmentId" reference="department">
                    <TextField source="name" />
                </ReferenceField>

                <ReferenceField source="organizationId" reference="organization">
                    <TextField source="name" />
                </ReferenceField>
            </SimpleShowLayout>
        </Show>
    )
}
