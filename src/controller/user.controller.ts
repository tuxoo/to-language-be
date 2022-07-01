import {Request, Response} from "express";
import {signUp, verifyUser} from "../service/user.service";
import {SignUpDTO, VerifyDTO} from "../model/user.model";

export async function signUpHandler(req: Request, res: Response) {
    try {
        const signUpDTO = req.body as SignUpDTO

        await signUp(signUpDTO)

        return res.sendStatus(201)
    } catch (e) {
        return res.status(409)
    }
}

export async function verifyHandler(req: Request, res: Response) {
    try {
        const verifyDTO = req.body as VerifyDTO

        await verifyUser(verifyDTO)
    } catch (e) {
        return res.status(409)
    }
}