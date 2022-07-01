import {pool} from "./postgres";
import {User} from "../model/user.model";

const userTable = "\"user\""

export async function save(user: User) {
    const query = `INSERT INTO ${userTable} (name, login_email, password_hash, registered_at, visited_at, role) VALUES ($1, $2, $3, $4, $5, $6)`
    try {
        await pool.query(
            query,
            [user.name, user.loginEmail, user.passwordHash, user.registeredAt, user.visitedAt, user.role]
        )
    } catch (error) {
        console.error("all bad")
    }
}