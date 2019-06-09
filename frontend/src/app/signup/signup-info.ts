export class SignUpInfo {
    firstName: string;
    username: string;
    lastName: string;
    role: string[];
    password: string;

    constructor(firstName: string, username: string, lastName: string, password: string) {
        this.firstName = firstName;
        this.username = username;
        this.lastName = lastName;
        this.password = password;
        this.role = ['user'];
    }
}
