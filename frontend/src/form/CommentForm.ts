export class CommentForm {
    text: string;
    user: number;
    postId: number;
    createdAt: Date;

    constructor(text: string, postId: number) {
        this.text = text;
        this.postId = postId;

        this.createdAt = new Date();

        const id = localStorage.getItem('token');
        this.user = id ? parseInt(id, 10) : 0;
    }
}