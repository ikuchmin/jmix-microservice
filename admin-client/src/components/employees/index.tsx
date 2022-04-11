import { ResourceProps } from "react-admin";
import { EmployeeCreate } from "./EmployeeCreate";
import { EmployeeEdit } from "./EmployeeEdit";
import { EmployeeList } from "./EmployeeList";
import { EmployeeShow } from "./EmployeeShow";

const EmployeeResource: Partial<ResourceProps> = {
    list: EmployeeList,
    create: EmployeeCreate,
    show: EmployeeShow,
    edit: EmployeeEdit,
}

export default EmployeeResource;
