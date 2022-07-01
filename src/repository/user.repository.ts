import {pool} from "./postgres";
import {User} from "../model/user.model";
import {QueryResult} from "pg";

const userTable = "\"user\""

export async function save(user: User) {
    const query = `INSERT INTO ${userTable} (name, login_email, password_hash, registered_at, visited_at, role)
                   VALUES ($1, $2, $3, $4, $5, $6)`
    try {
        await pool.query(
            query,
            [user.name, user.loginEmail, user.passwordHash, user.registeredAt, user.visitedAt, user.role]
        )
    } catch (error) {
        console.error("all bad")
    }
}

export async function findByEmail(email: string, isEnabled: Boolean) {
    const query = `SELECT id, name, login_email, registered_at, visited_at, role, is_enabled
                   FROM ${userTable}
                   WHERE login_email = $1
                     AND is_enabled = $2`

    try {
        const result: QueryResult = await pool.query(
            query,
            [email, isEnabled]
        )

        return result.rows[0] as User

    } catch (error) {
        console.error("all bad")
    }
}