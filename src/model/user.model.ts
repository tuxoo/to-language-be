export enum Role {
    UserRole = "USER",
    AdminRole = "ADMIN"
}

export interface User {
    id?: string,
    name: string,
    loginEmail: string,
    passwordHash: string,
    registeredAt: Date,
    visitedAt: Date,
    role?: Role,
    isEnabled?: Boolean
}

export interface SignUpDTO {
    name: string,
    email: string,
    password: string
}