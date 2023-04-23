import {CommentModel} from "../model/CommentModel";
import {PostModel} from "../model/PostModel";
import {NewPostModel} from "../model/NewPostModel";
declare var Promise: any;
//TODO: add es2015 to your --lib option in your TypeScript configuration file (tsconfig.json) to include the Promise constructor in your project.

export class ApiClient{
    //well, this isn't axios 🥴
    //create, read, update, delete, (list)

    public static async getComments(postId: number): Promise<CommentModel[]> {
        //TODO: hook up to correct GET
        //TODO: actually use the method!
        const response = await fetch(`http://localhost:8080/posts/${postId}/comments`);
        if(response.ok){
            return await response.json();
        }
        throw new Error(await response.json());
        console.log("Cannot load comments!");
    }

    public static async getPosts(): Promise<PostModel[]> {
        const response = await fetch('http://localhost:8080/posts/');
        if(response.ok){
            return await response.json();
        }
        throw new Error(await response.json());
        console.log("Cannot load posts!");
    }

    public static async getPost(postId: number): Promise<PostModel> {
        const response = await fetch(`http://localhost:8080/posts/` + postId); //or ${postId}
        if(response.ok){
            return await response.json();
        }
        throw new Error(await response.json());
        console.log("Cannot load post!");
    }

    public static async createPost(newPost: NewPostModel):Promise<NewPostModel>{
        //take note of date:2. that's the first. also month? yes, 0 is 1
        //TODO: NewPostModel has to be same as PostModel...? fix pls
        //const post: PostModel = {name: newPost.title, text: newPost.text} as PostModel; //ah so i need all of that.
        const response = await fetch("http://localhost:8080/posts",
            {
                method: "POST",
                body: JSON.stringify(newPost),
                headers: {
                    "Content-Type": "application/json",
                    "Accept": "application/json"
                }
                //this is messed. probably why it doesn't work
            });
        if(response.ok){
            return await response.json();
        }
        throw new Error(await response.json());
        console.log("Couldn't post comment! Try again!");
    }

    //get user? filter by?
}