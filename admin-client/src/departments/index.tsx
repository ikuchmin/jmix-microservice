import { ResourceProps } from "react-admin";
import { DepartmentCreate } from "./DepartmentCreate";
import { DepartmentEdit } from "./DepartmentEdit";
import { DepartmentList } from "./DepartmentList";
import { DepartmentShow } from "./DepartmentShow";

const DepartmentResource: Partial<ResourceProps> = {
    list: DepartmentList,
    create: DepartmentCreate,
    show: DepartmentShow,
    edit: DepartmentEdit,
}

export default DepartmentResource;
