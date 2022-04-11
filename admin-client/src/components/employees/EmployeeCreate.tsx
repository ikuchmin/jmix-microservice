import React from "react";
import { Create, required, SimpleForm, TextInput } from "react-admin";
import { AdminReferenceInput } from "../../common/components/AdminReferenceInput";
import { AdminCreateProps } from "../../types/common";

export const EmployeeCreate = (props: AdminCreateProps) => (
    <Create title="Create a Employee" {...props}>
        <SimpleForm>
            <TextInput source="name" validate={required()}/>
            <TextInput source="age" validate={required()}/>
            <TextInput source="position" validate={required()}/>

            <AdminReferenceInput
                source="departmentId"
                reference="department"
                allowEmpty
                validate={required()}
                perPage={10000}
            />
        </SimpleForm>
    </Create>
);
