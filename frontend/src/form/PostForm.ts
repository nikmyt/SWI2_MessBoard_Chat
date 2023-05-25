export class PostForm {
    postId: number;
    title: string;
    text: string;
    tag: string;
    createdAt: Date;
    user: number;
    constructor(title: string, text: string, tag: string) {
        this.title = title;
        this.text = text;
        this.tag = tag;
        this.createdAt= new Date();
        //this.user = user; //????
        const id = localStorage.getItem('token');

        this.user = id ? parseInt(id, 10) : 0; //TODO: well ok, but make it so you can't post if you aren't logged in

    }
}