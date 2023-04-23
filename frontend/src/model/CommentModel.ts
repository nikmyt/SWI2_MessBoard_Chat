import {PostModel} from "./PostModel";
import {UserModel} from "./UserModel";

export interface CommentModel{
    comment_id : number;
    postId : PostModel;
    user : UserModel;
    text : string;
    createdAt : Date;
}