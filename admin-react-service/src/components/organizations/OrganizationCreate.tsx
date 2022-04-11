import React from "react";
import { Create, required, SimpleForm, TextInput } from "react-admin";
import { AdminCreateProps } from "../../types/common";

export const OrganizationCreate = (props: AdminCreateProps) => (
    <Create title="Create a Organization" {...props}>
        <SimpleForm>
            <TextInput source="name" validate={required()}/>
            <TextInput source="address" validate={required()}/>
        </SimpleForm>
    </Create>
);
