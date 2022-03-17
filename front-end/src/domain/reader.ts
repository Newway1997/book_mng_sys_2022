
export interface Reader {
    id:      number;
    name:    string;
    age:     number;
    sex:     number;
    phone:   string;
    regdate: number;
    user:    User;
    level:   Level;
}

export interface Level {
    id:       number;
    maxBrNum: number;
    maxBrDay: number;
}

export interface User {
    id:       number;
    username: string;
    password: string;
    type:     number;
    status:   number;
}
