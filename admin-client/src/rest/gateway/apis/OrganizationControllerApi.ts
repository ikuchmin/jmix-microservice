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
    OrganizationDto,
    OrganizationDtoFromJSON,
    OrganizationDtoToJSON,
} from '../models';

export interface AddOrganizationRequest {
    organizationDto: OrganizationDto;
}

export interface FindOrganizationByIdRequest {
    id: number;
}

export interface FindOrganizationByIdWithDepartmentsRequest {
    id: number;
}

export interface FindOrganizationByIdWithDepartmentsAndEmployeesRequest {
    id: number;
}

export interface FindOrganizationByIdWithEmployeesRequest {
    id: number;
}

/**
 * 
 */
export class OrganizationControllerApi extends runtime.BaseAPI {

    /**
     * Add organization
     */
    async addOrganizationRaw(requestParameters: AddOrganizationRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<OrganizationDto>> {
        if (requestParameters.organizationDto === null || requestParameters.organizationDto === undefined) {
            throw new runtime.RequiredError('organizationDto','Required parameter requestParameters.organizationDto was null or undefined when calling addOrganization.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/organization/`,
            method: 'POST',
            headers: headerParameters,
            query: queryParameters,
            body: OrganizationDtoToJSON(requestParameters.organizationDto),
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => OrganizationDtoFromJSON(jsonValue));
    }

    /**
     * Add organization
     */
    async addOrganization(requestParameters: AddOrganizationRequest, initOverrides?: RequestInit): Promise<OrganizationDto> {
        const response = await this.addOrganizationRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Find all organizations
     */
    async findAllOrganizationsRaw(initOverrides?: RequestInit): Promise<runtime.ApiResponse<Array<OrganizationDto>>> {
        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/organization/`,
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => jsonValue.map(OrganizationDtoFromJSON));
    }

    /**
     * Find all organizations
     */
    async findAllOrganizations(initOverrides?: RequestInit): Promise<Array<OrganizationDto>> {
        const response = await this.findAllOrganizationsRaw(initOverrides);
        return await response.value();
    }

    /**
     * Find organization by Id
     */
    async findOrganizationByIdRaw(requestParameters: FindOrganizationByIdRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<OrganizationDto>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling findOrganizationById.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/organization/{id}`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => OrganizationDtoFromJSON(jsonValue));
    }

    /**
     * Find organization by Id
     */
    async findOrganizationById(requestParameters: FindOrganizationByIdRequest, initOverrides?: RequestInit): Promise<OrganizationDto> {
        const response = await this.findOrganizationByIdRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Find organization by Id with departments
     */
    async findOrganizationByIdWithDepartmentsRaw(requestParameters: FindOrganizationByIdWithDepartmentsRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<OrganizationDto>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling findOrganizationByIdWithDepartments.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/organization/{id}/with-departments`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => OrganizationDtoFromJSON(jsonValue));
    }

    /**
     * Find organization by Id with departments
     */
    async findOrganizationByIdWithDepartments(requestParameters: FindOrganizationByIdWithDepartmentsRequest, initOverrides?: RequestInit): Promise<OrganizationDto> {
        const response = await this.findOrganizationByIdWithDepartmentsRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Find Organization by Id with departments and employees
     */
    async findOrganizationByIdWithDepartmentsAndEmployeesRaw(requestParameters: FindOrganizationByIdWithDepartmentsAndEmployeesRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<OrganizationDto>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling findOrganizationByIdWithDepartmentsAndEmployees.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/organization/{id}/with-departments-and-employees`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => OrganizationDtoFromJSON(jsonValue));
    }

    /**
     * Find Organization by Id with departments and employees
     */
    async findOrganizationByIdWithDepartmentsAndEmployees(requestParameters: FindOrganizationByIdWithDepartmentsAndEmployeesRequest, initOverrides?: RequestInit): Promise<OrganizationDto> {
        const response = await this.findOrganizationByIdWithDepartmentsAndEmployeesRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Find organization by Id with employees
     */
    async findOrganizationByIdWithEmployeesRaw(requestParameters: FindOrganizationByIdWithEmployeesRequest, initOverrides?: RequestInit): Promise<runtime.ApiResponse<OrganizationDto>> {
        if (requestParameters.id === null || requestParameters.id === undefined) {
            throw new runtime.RequiredError('id','Required parameter requestParameters.id was null or undefined when calling findOrganizationByIdWithEmployees.');
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/organization/{id}/with-employees`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters.id))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => OrganizationDtoFromJSON(jsonValue));
    }

    /**
     * Find organization by Id with employees
     */
    async findOrganizationByIdWithEmployees(requestParameters: FindOrganizationByIdWithEmployeesRequest, initOverrides?: RequestInit): Promise<OrganizationDto> {
        const response = await this.findOrganizationByIdWithEmployeesRaw(requestParameters, initOverrides);
        return await response.value();
    }

}
