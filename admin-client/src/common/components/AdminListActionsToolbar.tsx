import React, {Children, cloneElement, JSXElementConstructor, ReactElement } from "react";
import { ChildrenType } from "../../types/common";
import styles from "./common.module.scss";

type AdminListActionToolbarProps = {
    children: any,
    [x: string]: any;
}

export const AdminListActionToolbar = ({childern, ...props}: AdminListActionToolbarProps) => {
    return (
        <div className={styles.toolbar}>
            {Children.map(props.children, button => cloneElement(button, props))}
        </div>
    );
};
