export class RegisterForm {
    username: string;
    password: string;
    password_hint: string;
    email: string;

    constructor(username: string, password: string, email: string, password_hint: string) {
        this.username = username;
        this.password = password;
        this.password_hint = password_hint;
        this.email = email;
    }
}