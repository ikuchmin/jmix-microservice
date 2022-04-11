import {AutocompleteInput, Edit, ReferenceInput, required, SelectInput, SimpleForm, TextInput } from "react-admin";
import { AdminReferenceInput, AdminRefetenceAutocompleteInput } from "../../common/components/AdminReferenceInput";
import { AdminToolbar } from "../../common/components/AdminToolbar";
import { AdminEditProps } from "../../types/common";

export const EmployeeEdit = (props: AdminEditProps) => (
    <Edit {...props}>
        <SimpleForm toolbar={<AdminToolbar/>}
                    warnWhenUnsavedChanges>
            <TextInput disabled source="id" fullWidth/>
            <TextInput source="name" validate={required()} fullWidth/>
            <TextInput source="age" validate={required()} fullWidth/>
            <TextInput source="position" validate={required()} fullWidth/>

            <AdminRefetenceAutocompleteInput
                source="departmentId"
                reference="department"
                allowEmpty
                validate={required()}
                perPage={10000}
            />
        </SimpleForm>
    </Edit>
);
