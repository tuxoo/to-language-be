import {Request, Response} from "express";
import {signUp} from "../service/user.service";
import {SignUpDTO} from "../model/user.model";

export async function signUpHandler(req: Request, res: Response) {
    try {
        const signUpDTO = req.body as SignUpDTO

        await signUp(signUpDTO)

        return res.sendStatus(201)
    } catch (e) {
        return res.status(409)
    }
}