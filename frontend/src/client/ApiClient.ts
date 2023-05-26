import {CommentModel} from "../model/CommentModel";
import {PostModel} from "../model/PostModel";
import {AuthForm} from "../form/AuthForm";
import {PostForm} from "../form/PostForm";
import {CommentForm} from "../form/CommentForm";

declare var Promise: any;
//TODO: add es2015 to your --lib option in your TypeScript configuration file (tsconfig.json) to include the Promise constructor in your project.

export class ApiClient{
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
        const response = await fetch('http://localhost:8080/postssort?filter=newest');
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

    public static async createPost(post: PostForm) {
        //const token = localStorage.getItem('token');
        //could re-authenticate here but that's a little silly, no? it'd be extra safe tho.
        const response = await fetch('http://localhost:8080/new_post', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                //'Authorization': `Bearer ${token}` // cool idea. actually don't
            },
            body: JSON.stringify(post)
        });
        if (!response.ok) {

            const error = await response.text();
            throw new Error(error);
        }
    }

    public static async deletePost(postId: number): Promise<void> {
        const token = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8080/deletepost/${postId}`, {

            method: 'DELETE',
            headers: {
            'Content-Type': 'application/json',
                'Authorization': "Bearer ${token}"
        }
    });

        if (!response.ok) {
            const error = await response.text();
            throw new Error(error);
        }
}

    public static async updatePost(postId: number, post: PostForm): Promise<PostModel> {
        const token = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8080/posts/${postId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
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

    public static async deleteComment(commentId: number): Promise<void> {
        const token = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8080/comments/${commentId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        });

        if (!response.ok) {
            const error = await response.text();
            throw new Error(error);
        }
    }

    public static async updateComment(commentId: number, comment: CommentForm): Promise<CommentModel> {
        const token = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8080/comments/${commentId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(comment)
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
            const token = await response.json();

            const id = token.userId;

            localStorage.setItem('token', id);
            //console.log(localStorage.getItem('token'));
            return id;
        } else {
            const error = await response.text();
            throw new Error(error);
        }
    }

    //---------------FILTER ZONE--------------------
    //["Newest", "Oldest", "Tags A-Z", "Tags Z-A", "Title A-Z", "Title Z-A"],

    public static async getPostsByNewest(): Promise<PostModel[]> {
        const response = await fetch(`http://localhost:8080/postssort?filter=newest`);
        if (response.ok) {
            return await response.json();
        }
        console.log("Cannot load posts with filter: Newest!");
        throw new Error(await response.json());
    }

    public static async getPostsByOldest(): Promise<PostModel[]> {
        const response = await fetch(`http://localhost:8080/postssort?filter=oldest`);
        if (response.ok) {
            return await response.json();
        }
        console.log("Cannot load posts with filter: Oldest!");
        throw new Error(await response.json());
    }

    public static async getPostsByTagsAZ(): Promise<PostModel[]> {
        const response = await fetch(`http://localhost:8080/postssort?filter=tagsAZ`);
        if (response.ok) {
            return await response.json();
        }
        console.log("Cannot load posts with filter: Tags A-Z!");
        throw new Error(await response.json());
    }

    public static async getPostsByTagsZA(): Promise<PostModel[]> {
        const response = await fetch(`http://localhost:8080/postssort?filter=tagsZA`);
        if (response.ok) {
            return await response.json();
        }
        console.log("Cannot load posts with filter: Tags Z-A!");
        throw new Error(await response.json());
    }

    public static async getPostsByTitleAZ(): Promise<PostModel[]> {
        const response = await fetch(`http://localhost:8080/postssort?filter=titleAZ`);
        if (response.ok) {
            return await response.json();
        }
        console.log("Cannot load posts with filter: Title A-Z!");
        throw new Error(await response.json());
    }

    public static async getPostsByTitleZA(): Promise<PostModel[]> {
        const response = await fetch(`http://localhost:8080/postssort?filter=titleZA`);
        if (response.ok) {
            return await response.json();
        }
        console.log("Cannot load posts with filter: Title Z-A!");
        throw new Error(await response.json());
    }
}