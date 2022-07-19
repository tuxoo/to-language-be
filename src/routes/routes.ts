import {Express, Request, Response} from "express";
import {signUpHandler, verifyHandler} from "../controller/user.controller";

export default function (app: Express) {

    app.get('/ping', (req: Request, res: Response) => res.send('pong'))

    app.post(`/api/v1/user/sign-up`, signUpHandler)
    app.post(`/api/v1/user/verify`, verifyHandler)
}