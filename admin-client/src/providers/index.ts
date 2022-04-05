import { DataProvider, LegacyDataProvider } from "react-admin";
import { createDataProvider } from "../common/data-provider";
import { ResoursesEnum } from "../common/resourses";
import { DepartmentControllerApi, DepartmentDto, EmployeeControllerApi, EmployeeDto, OrganizationControllerApi, OrganizationDto } from "../rest/gateway";

type ParamsType = DepartmentDto | EmployeeDto | OrganizationDto;

const departmentsApi = new DepartmentControllerApi();
const employeesApi = new EmployeeControllerApi();
const organizationsApi = new OrganizationControllerApi();

const getList = {
    [ResoursesEnum.department]: departmentsApi.findAllDepartments.bind(departmentsApi),
    [ResoursesEnum.employee]: employeesApi.findAllEmployees.bind(employeesApi),
    [ResoursesEnum.organization]: organizationsApi.findAllOrganizations.bind(organizationsApi)
}

const create = {
    [ResoursesEnum.department]: (departmentDto: DepartmentDto) =>
        departmentsApi.addDepartment.call(departmentsApi, {departmentDto}),
    [ResoursesEnum.employee]: (employeeDto: EmployeeDto) =>
        employeesApi.addEmployee.call(employeesApi, {employeeDto}),
    [ResoursesEnum.organization]: (organizationDto: OrganizationDto) =>
        organizationsApi.addOrganization.call(organizationsApi, {organizationDto})
}

const getOne = {
    [ResoursesEnum.department]: (id: number) =>
        departmentsApi.findDepartmentById.call(departmentsApi, {id}),
    [ResoursesEnum.employee]: (id: number) =>
        employeesApi.findEmployeeById.call(employeesApi, {id}),
    [ResoursesEnum.organization]: (id: number) =>
        organizationsApi.findOrganizationById.call(organizationsApi, {id})
}

const getManyReference = {
    [ResoursesEnum.department]: (params: { id: number, target: string }) =>
        employeesApi.findEmployeeByDepartment.call(employeesApi, {departmentId: params?.id}),
    [ResoursesEnum.organization]: (params: { id: number, target: string }) => {
        if (params?.target === "employee_id") {
            return employeesApi.findEmployeeByOrganization.call(employeesApi, {organizationId: params?.id})
        }
        return departmentsApi.findDepartmentByOrganization.call(departmentsApi, {organizationId: params?.id})
    },
    // заглушка
    [ResoursesEnum.employee]: (params: { id: number, target: string }) => employeesApi.findEmployeeByDepartment.call(employeesApi, {departmentId: params?.id}),
}


// const getMany = {
//     [ResoursesEnum.department]: (id: number[]) =>
//         employeesApi.findEmployeeByDepartment.call(employeesApi, {departmentId: params?.id}),
//     [ResoursesEnum.organization]: (id: number[]) => {
//         if (params?.target === "employee_id") {
//             return employeesApi.findEmployeeByOrganization.call(employeesApi, {organizationId: params?.id})
//         }
//         // if (params?.target === "department_id") {
//         return departmentsApi.findDepartmentByOrganization.call(departmentsApi, {organizationId: params?.id})
//         // }
//     },
//
//     [ResoursesEnum.employee]: (id: number[]) =>
//         employeesApi.findEmployeeByDepartment.call(employeesApi, {departmentId: params?.id})
// }

export const dataProvider: DataProvider | LegacyDataProvider = createDataProvider({
    getList: (resources: ResoursesEnum) => getList[resources]()
        .then((data: ParamsType[]) => {
            return {
                data: [...data],
                total: data.length
            }
        }),
    create: (resources: ResoursesEnum, params: { data: ParamsType }) => create[resources](params?.data)
        .then((data: ParamsType) => {
            return { data }
        }),
    getOne: (resources: ResoursesEnum, params: { id: string }) => getOne[resources](+params?.id)
        .then((data: ParamsType) => {
            return { data }
        }),
    getManyReference: (resources: ResoursesEnum, params: { id: number, target: string }) => getManyReference[resources](params)
        .then((data: ParamsType[]) => {
            return {
                data: [...data],
                total: data.length
            }
        }),
    // TODO: getList заменить на getMany
    getMany: (resources: ResoursesEnum, params: { ids: number[]}) => getList[resources]()
        .then((data: ParamsType[]) => {
            return {data}
        })
})
