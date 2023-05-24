import {CommentModel} from "../model/CommentModel";
import {PostModel} from "../model/PostModel";
import {AuthForm} from "../form/AuthForm";
import {PostForm} from "../form/PostForm";

declare var Promise: any;
//TODO: add es2015 to your --lib option in your TypeScript configuration file (tsconfig.json) to include the Promise constructor in your project.

export class ApiClient{
    //well, this isn't axios 🥴
    //create, read, update, delete, (list)

    public static async getComments(postId: number): Promise<CommentModel[]> {
        //TODO: hook up to correct GET
        //TODO: actually use the method!
        const response = await fetch('http://localhost:8080/comment/' + postId);
        if(response.ok){
            return await response.json();
        }
        console.log("Cannot load comments!");
        throw new Error(await response.json());
    }

    public static async getPosts(): Promise<PostModel[]> {
        const response = await fetch('http://localhost:8080/posts');
        if(response.ok){
            return await response.json();
        }
        console.log("Cannot load posts!");
        throw new Error(await response.json());
    }

    public static async getPost(postId: number): Promise<PostModel> {
        const response = await fetch(`http://localhost:8080/posts/` + postId); //or ${postId}
        if(response.ok){
            return await response.json();
        }
        console.log("Cannot load post!");
        throw new Error(await response.json());
    }

    public static async createPost(post: PostForm): Promise<PostModel> {
        //const token = localStorage.getItem('token');
        //could re-authenticate here but that's a little silly, no?
        const response = await fetch('http://localhost:8080/posts', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                //'Authorization': `Bearer ${token}` // cool idea. actually don't
            },
            body: JSON.stringify(post)
        });
        if (response.ok) {
            return await response.json();
        } else {
            const error = await response.text();
            throw new Error(error);
        }
    }


    public static async authenticate(credentials: AuthForm): Promise<string> {
        const response = await fetch('http://localhost:8080/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        });
        if (response.ok) {
            const token = await response.text();
            localStorage.setItem('token', token); // store the token in localStorage
            return token;
        } else {
            const error = await response.text();
            throw new Error(error);
        }
    }
}