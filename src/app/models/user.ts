import { Roles } from "./roles";

export interface User {
    id: string,
    userName: string,
    password: string,
    roles: Array<Roles>,
    lastLogin: Date,
    active: boolean,
    createdBy:string,
    modifiedBy:string
}