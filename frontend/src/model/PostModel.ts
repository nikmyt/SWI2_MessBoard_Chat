export interface PostModel {
    postId: number;
    title: string;
    text: string;
    tag: string;
    createdAt: Date;
    //updatedAt: Date; //interesting

    user: number; //??? userId or user_id or user ???



    }