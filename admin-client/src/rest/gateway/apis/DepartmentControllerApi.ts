/* tslint:disable */
/* eslint-disable */
/**
 * Gateway Service API
 * API for Gateway
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as runtime from '../runtime';
import {
    DepartmentDto,
    DepartmentDtoFromJSON,
    DepartmentDtoToJSON,
} from '../models';

export interface AddDepartmentRequest {
    departmentDto: DepartmentDto;
}

export interface FindDepartmentByIdRequest {
    id: number;
}

export interface FindDepartmentByOrganizationRequest {
    organizationId: number;
}

export interface FindDepartmentByOrganizationWithEmployeesRequest {
    organizationId: number;
}

/**
 * 
 */
export class DepartmentControllerApi extends runtime.BaseAPI {

    /**
     * Add department
     */
    async addDepartmentRaw(requestParameters: AddDepartmentRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<DepartmentDto>> {
        if (requestParameters.departmentDto === null || requestParameters.departmentDto === undefined) {
            throw new runtime.RequiredError('departmentDto','Required parameter requestParameters.departmentDto was null or undefined when calling addDepartment.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/department/`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: DepartmentDtoToJSON(requestParameters.departmentDto),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => DepartmentDtoFromJSON(jsonValue));
    }

    /**
     * Add department
     */
    async addDepartment(requestParameters: AddDepartmentRequest, initOverrides?: RequestInit): Promise<DepartmentDto> {
        const response = await this.addDepartmentRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Dind all departments
     */
    async findAllDepartmentsRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<DepartmentDto>>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/department/`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(DepartmentDtoFromJSON));
    }

    /**
     * Dind all departments
     */
    async findAllDepartments(initOverrides?: RequestInit): Promise<Array<DepartmentDto>> {
        const response = await this.findAllDepartmentsRaw(initOverrides);
        return await response.value();
    }

    /**
     * Find department by Id
     */
    async findDepartmentByIdRaw(requestParameters: FindDepartmentByIdRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<DepartmentDto>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling findDepartmentById.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/department/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => DepartmentDtoFromJSON(jsonValue));
    }

    /**
     * Find department by Id
     */
    async findDepartmentById(requestParameters: FindDepartmentByIdRequest, initOverrides?: RequestInit): Promise<DepartmentDto> {
        const response = await this.findDepartmentByIdRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Find department by organization
     */
    async findDepartmentByOrganizationRaw(requestParameters: FindDepartmentByOrganizationRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<DepartmentDto>>> {
        if (requestParameters.organizationId === null || requestParameters.organizationId === undefined) {
            throw new runtime.RequiredError('organizationId','Required parameter requestParameters.organizationId was null or undefined when calling findDepartmentByOrganization.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/department/organization/{organizationId}`.replace(`{${"organizationId"}}`, encodeURIComponent(String(requestParameters.organizationId))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(DepartmentDtoFromJSON));
    }

    /**
     * Find department by organization
     */
    async findDepartmentByOrganization(requestParameters: FindDepartmentByOrganizationRequest, initOverrides?: RequestInit): Promise<Array<DepartmentDto>> {
        const response = await this.findDepartmentByOrganizationRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Find department by organization Id with employees
     */
    async findDepartmentByOrganizationWithEmployeesRaw(requestParameters: FindDepartmentByOrganizationWithEmployeesRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<DepartmentDto>>> {
        if (requestParameters.organizationId === null || requestParameters.organizationId === undefined) {
            throw new runtime.RequiredError('organizationId','Required parameter requestParameters.organizationId was null or undefined when calling findDepartmentByOrganizationWithEmployees.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/department/organization/{organizationId}/with-employees`.replace(`{${"organizationId"}}`, encodeURIComponent(String(requestParameters.organizationId))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(DepartmentDtoFromJSON));
    }

    /**
     * Find department by organization Id with employees
     */
    async findDepartmentByOrganizationWithEmployees(requestParameters: FindDepartmentByOrganizationWithEmployeesRequest, initOverrides?: RequestInit): Promise<Array<DepartmentDto>> {
        const response = await this.findDepartmentByOrganizationWithEmployeesRaw(requestParameters, initOverrides);
        return await response.value();
    }

}
