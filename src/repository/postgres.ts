import {Pool} from 'pg'
import config from 'config'

export const pool = new Pool({
    host: config.get("host") as string,
    user: config.get("dbUser") as string,
    password: config.get("dbPassword") as string,
    database: config.get("db") as string,
    port: config.get("dbPort") as number,
})
