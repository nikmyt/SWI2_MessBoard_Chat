export interface PostModel {
    postId: number;
    title: string;
    text: string;
    tag: string;
    createdAt: Date;
    //updatedAt: Date; //interesting
    BoardUser: {
        user: number; //??? userId or user_id or user ???
        username: string;
    };

    //TODO: mystery if i should put comments here. i don't think so, what if there's many comments and we show it on front page, commentless? better to call a function
}