import { DataProvider, LegacyDataProvider } from "react-admin"

export function createDataProvider(
    dataProvider?: Partial<DataProvider | LegacyDataProvider>
): DataProvider | LegacyDataProvider {
    return {
        getList:          (resource: any, params: any) => new Promise(() => { return {data: [], total: 0} }),
        getOne:           (resource: any, params: any) => new Promise(() => { return {data: {}} }),
        getMany:          (resource: any, params: any) => new Promise(() => { return {data: []} }),
        getManyReference: (resource: any, params: any) => new Promise(() => { return {data: [], total: 0} }),
        create:           (resource: any, params: any) => new Promise(() => { return {data: {}} }),
        update:           (resource: any, params: any) => new Promise(() => { return {data: {}} }),
        updateMany:       (resource: any, params: any) => new Promise(() => { return {data: []} }),
        delete:           (resource: any, params: any) => new Promise(() => { return {data: {}} }),
        deleteMany:       (resource: any, params: any) => new Promise(() => { return {data: []} }),
        ...dataProvider,
    }
}
