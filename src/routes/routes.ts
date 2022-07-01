import {Express, Request, Response} from "express";
import {signUpHandler, verifyHandler} from "../controller/user.controller";

export default function (app: Express) {

    app.get('/ping', (req: Request, res: Response) => res.send('pong'))

    app.post(`/sign-up`, signUpHandler)
    app.post(`/verify`, verifyHandler)
}