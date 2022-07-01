import bcrypt from "bcrypt";
import {save} from "../repository/user.repository";
import {SignUpDTO, User, Role} from "../model/user.model";
import config from "config";


export async function signUp(signUpDTO: SignUpDTO) {
    const salt = await bcrypt.genSalt(config.get("saltWorkFactor"));
    const passwordHash = await bcrypt.hash(signUpDTO.password, salt)

    const user: User = {
        name: signUpDTO.name,
        loginEmail: signUpDTO.email,
        passwordHash: passwordHash,
        registeredAt: new Date(),
        visitedAt: new Date(),
        role: Role.UserRole
    }

    await save(user)
}