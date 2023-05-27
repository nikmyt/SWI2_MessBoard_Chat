export class CommentForm {
    text: string;
    userId: number;
    postId: number;
    createdAt: Date;


    constructor(text: string, postId: number) {
        this.text = text;
        this.postId = postId;

        this.createdAt = new Date();

        const id = localStorage.getItem('token');
        this.userId = id ? parseInt(id, 10) : 0;
    }
}