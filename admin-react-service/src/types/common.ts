import {CreateProps, EditProps, ListProps, ShowProps } from "react-admin";

export type ChildrenType = {
    children?: React.ReactNode
}

export type AdminShowProps = ShowProps & ChildrenType;
export type AdminListProps = ListProps & ChildrenType;
export type AdminEditProps = EditProps & ChildrenType;
export type AdminCreateProps = CreateProps & ChildrenType;
