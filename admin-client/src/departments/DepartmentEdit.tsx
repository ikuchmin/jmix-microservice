import { Edit, required, SimpleForm, TextInput } from "react-admin";
import { AdminRefetenceAutocompleteInput } from "../common/components/AdminReferenceInput";
import { AdminToolbar } from "../common/components/AdminToolbar";
import { AdminEditProps } from "../types/common";

export const DepartmentEdit = (props: AdminEditProps) => (
    <Edit {...props}>
        <SimpleForm toolbar={<AdminToolbar/>}
                    warnWhenUnsavedChanges>
            <TextInput disabled source="id" fullWidth/>
            <TextInput source="name" validate={required()} fullWidth/>

            <AdminRefetenceAutocompleteInput
                source="organizationId"
                reference="organization"
                allowEmpty
                validate={required()}
                perPage={10000}
            />
        </SimpleForm>
    </Edit>
);
