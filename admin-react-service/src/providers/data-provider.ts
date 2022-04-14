import { DataProvider, LegacyDataProvider } from "react-admin";
import { createDataProvider } from "../common/data-provider";
import { ResoursesEnum } from "../common/resourses";
import { DepartmentControllerApi, DepartmentDto, EmployeeControllerApi, EmployeeDto, OrganizationControllerApi, OrganizationDto } from "../rest/gateway";

type ParamsType = DepartmentDto | EmployeeDto | OrganizationDto;

const departmentsApi = new DepartmentControllerApi();
const employeesApi = new EmployeeControllerApi();
const organizationsApi = new OrganizationControllerApi();

function getHeaders() {
    const headers = new Headers({ Accept: 'application/json', "Content-Type": "application/json" });
    const token = localStorage.getItem('token');
    headers.set('Authorization', `Bearer ${token}`);
    return headers;
}

const getList = (resources: ResoursesEnum) => {
    const headers = getHeaders();

    switch(resources) {
        case ResoursesEnum.employee:
            return employeesApi.findAllEmployees.call(employeesApi, {headers});
        case ResoursesEnum.department:
            return departmentsApi.findAllDepartments.call(departmentsApi, {headers});
        case ResoursesEnum.organization:
            return organizationsApi.findAllOrganizations.call(organizationsApi, {headers});
    }
}

const create = (resources: ResoursesEnum, dto: ParamsType) => {
    const headers = getHeaders();

    switch(resources) {
        case ResoursesEnum.employee:
            return employeesApi.addEmployee.call(employeesApi, {employeeDto: dto}, {headers});
        case ResoursesEnum.department:
            return departmentsApi.addDepartment.call(departmentsApi, {departmentDto: dto}, {headers});
        case ResoursesEnum.organization:
            return organizationsApi.addOrganization.call(organizationsApi, {organizationDto: dto}, {headers});
    }
}

const getOne = (resources: ResoursesEnum, id: number) => {
    const headers = getHeaders();

    switch(resources) {
        case ResoursesEnum.employee:
            return employeesApi.findEmployeeById.call(employeesApi, {id}, {headers});
        case ResoursesEnum.department:
            return departmentsApi.findDepartmentById.call(departmentsApi, {id}, {headers});
        case ResoursesEnum.organization:
            return organizationsApi.findOrganizationById.call(organizationsApi, {id}, {headers});
    }
}

const getManyReference = (
    resources: ResoursesEnum,
    params: {
        id: number,
        target: string
    }
) => {
    const headers = getHeaders();

    switch (resources) {
        case ResoursesEnum.department:
            return employeesApi.findEmployeeByDepartment.call(employeesApi, {departmentId: params?.id}, {headers});
        case ResoursesEnum.organization:
            if (params?.target === "employee_id") {
                return employeesApi.findEmployeeByOrganization.call(employeesApi, {organizationId: params?.id}, {headers});
            }
            return departmentsApi.findDepartmentByOrganization.call(departmentsApi, {organizationId: params?.id}, {headers});
        default:
            return Promise.resolve([])
    }
}

export const dataProvider: DataProvider | LegacyDataProvider = createDataProvider({
    getList: (resources: ResoursesEnum) => getList(resources)
        .then((data: ParamsType[]) => {
            return {
                data: [...data],
                total: data.length
            }
        }),
    create: (resources: ResoursesEnum, params: { data: ParamsType }) => create(resources, params?.data)
        .then((data: ParamsType) => {
            return {data}
        }),
    getOne: (resources: ResoursesEnum, params: { id: string }) => getOne(resources, +params?.id)
        .then((data: ParamsType) => {
            return {data}
        }),
    getManyReference: (resources: ResoursesEnum, params: { id: number, target: string }) => getManyReference(resources, params)
        .then((data: ParamsType[]) => {
            return {
                data: [...data],
                total: data.length
            }
        }),
    getMany: (resources: ResoursesEnum, params: { ids: number[] }) => getList(resources)
        .then((data: ParamsType[]) => {
            return {data}
        })
})
