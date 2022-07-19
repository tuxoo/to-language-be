import {findByEmail, save, updateById} from "../repository/user.repository";
import {Role, SignUpDTO, User, VerifyDTO} from "../model/user.model";
import crypto from "crypto";
import c from "config";


export async function signUp(signUpDTO: SignUpDTO) {
    try {
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
    } catch (error) {
        throw new Error("")
    }
}

export async function verifyUser(verifyDTO: VerifyDTO) {
    try {
        const user = await findByEmail(verifyDTO.email, false)

        const crypto = require('crypto');
        const hasher = crypto.createHash('sha1');
        hasher.update(user?.name)

        if (verifyDTO.checkCode != hasher.digest('hex')) {
            console.error("illegal check code")
        }

        await updateById(user?.id!!)
    } catch (error) {
        throw new Error("")
    }
}