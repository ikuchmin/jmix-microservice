import { Edit, required, SimpleForm, TextInput } from "react-admin";
import { AdminToolbar } from "../common/components/AdminToolbar";
import { AdminEditProps } from "../types/common";

export const OrganizationEdit = (props: AdminEditProps) => (
    <Edit {...props}>
        <SimpleForm toolbar={<AdminToolbar/>}
                    warnWhenUnsavedChanges>
            <TextInput disabled source="id" fullWidth/>
            <TextInput source="name" validate={required()} fullWidth/>
            <TextInput source="address" validate={required()} fullWidth/>
        </SimpleForm>
    </Edit>
);
