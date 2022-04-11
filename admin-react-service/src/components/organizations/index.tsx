import { ResourceProps } from "react-admin";
import { OrganizationCreate } from "./OrganizationCreate";
import { OrganizationEdit } from "./OrganizationEdit";
import { OrganizationList } from "./OrganizationList";
import { OrganizationShow } from "./OrganizationShow";

const OrganizationResource: Partial<ResourceProps> = {
    list: OrganizationList,
    create: OrganizationCreate,
    show: OrganizationShow,
    edit: OrganizationEdit,
}

export default OrganizationResource;
