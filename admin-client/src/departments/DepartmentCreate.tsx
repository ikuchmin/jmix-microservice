import React from "react";
import { Create, required, SimpleForm, TextInput } from "react-admin";
import { AdminReferenceInput } from "../common/components/AdminReferenceInput";
import { AdminCreateProps } from "../types/common";

export const DepartmentCreate = (props: AdminCreateProps) => (
    <Create title="Create a Department" {...props}>
        <SimpleForm>
            <TextInput source="name" validate={required()}/>

            <AdminReferenceInput
                source="organizationId"
                reference="organization"
                allowEmpty
                validate={required()}
                perPage={10000}
            />
        </SimpleForm>
    </Create>
);
