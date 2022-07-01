import bcrypt from "bcrypt";
import {findByEmail, save} from "../repository/user.repository";
import {SignUpDTO, User, Role, VerifyDTO} from "../model/user.model";
import config from "config";


export async function signUp(signUpDTO: SignUpDTO) {
    const crypto = require('crypto');
    const hasher = crypto.createHash('sha1');
    hasher.update(signUpDTO.password)
    const passwordHash = hasher.digest('hex')

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

export async function verifyUser(verifyDTO: VerifyDTO) {

    const user = await findByEmail(verifyDTO.email, false)
}