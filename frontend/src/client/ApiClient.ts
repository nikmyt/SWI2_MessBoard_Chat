import {CommentModel} from "../model/CommentModel";
import {PostModel} from "../model/PostModel";
import {AuthForm} from "../form/AuthForm";
import {PostForm} from "../form/PostForm";
import {CommentForm} from "../form/CommentForm";
import {RegisterForm} from "../form/RegisterForm";
import {MessageForm} from "../form/MessageForm";
import {MessageRequestForm} from "../form/MessageRequestForm";
import {DestinationForm} from "../form/DestinationForm";
import {JoinDestinationForm} from "../form/JoinDestinationForm";

declare var Promise: any;
//TODO: add es2015 to your --lib option in your TypeScript configuration file (tsconfig.json) to include the Promise constructor in your project.

export class ApiClient{
    //create, read, update, delete, (list)

    public static async getPosts(): Promise<PostModel[]> {
        const response = await fetch('http://localhost:8080/postssort?filter=newest');

        if (response.ok) {
            return await response.json();
        } else {
            const errorResponse = await response.json();
            const errorMessage = errorResponse?.error || 'Failed to fetch posts';
            console.log("Cannot load posts!");
            throw new Error(errorMessage);
        }
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

    public static async updatePost(postId: number, post: PostForm): Promise<any> {
        const token = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8080/editpost/${postId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(post)
        });

        if (response.ok) {
            return await response.text();
        } else {
            const error = await response.text();
            throw new Error(error);
        }
    }

    public static async getComments(postId: number): Promise<CommentForm[]> {
        const response = await fetch('http://localhost:8080/comment/' + postId);
        if(response.ok){
            return await response.json();
        }
        else{
        console.log("Cannot load comments!");
        throw new Error(await response.json());}
    }

    public static async createComment(comment: CommentForm): Promise<any>{
        const token = localStorage.getItem('token');
        const response = await fetch('http://localhost:8080/newcomment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(comment)

        });

        if (!response.ok) {

            const error = await response.text();
            throw new Error(error);
        }
    }

    public static async deleteComment(commentId: number): Promise<void> {
        const token = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8080/deletecomment/${commentId}`, {
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
        const response = await fetch(`http://localhost:8080/editcomment/${commentId}`, {
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

            //26.1 is token just userID all this time... 😑
            localStorage.setItem('token', id);

            //console.log(localStorage.getItem('token'));
            return id;
        } else {
            const error = await response.text();
            throw new Error(error);
        }
    }

    public static async register(registration: RegisterForm){
        console.log(JSON.stringify(registration));
        const response = await fetch( "http://localhost:8080/register", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(registration)

        });
        if (!response.ok) {
            const error = await response.text();
            throw new Error(error);
        }
    }

    public static async getSearchResults(term):Promise<any[]>{
        const response = await fetch('http://localhost:8080/search/'+term);
            if (!response.ok) {
                throw new Error('Failed to fetch search results');

            }

        return await response.json();

    }

    public static async getPostsByUserId(userId) : Promise<any[]>
    {
        const response = await fetch('http://localhost:8080/postsbyuser/'+userId);


        if (!response.ok) {
            throw new Error('Failed to fetch search results');

        }

        return await response.json();
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

    public static async getCommentCount(userId) {
        try {
            const response = await fetch('http://localhost:8080/commentcount/'+userId);
            if (!response.ok) {
                throw new Error('Failed to fetch comment count');
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error fetching comment count:', error);
            throw error;
        }
    }

// Get post count by user ID
    public static async getPostCount(userId: number): Promise<number> {
        try {
            const response = await fetch('http://localhost:8080/postcount/'+userId);
            if (!response.ok) {
                throw new Error('Failed to fetch comment count');
            }
            const data = await response.json();
            return data;
        } catch (error) {
            console.error('Error fetching comment count:', error);
            throw error;
        }
    }

    //chat stuff

    public static async sendMessage(message: MessageForm) {
        const response = await fetch('http://localhost:8080/send', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                //'Authorization': `Bearer ${token}` // cool idea. actually don't
            },
            body: JSON.stringify(message)
        });
        if (!response.ok) {
            console.log("Failed to send message")
            const error = await response.text();
            throw new Error(error);
        }
    }

    //likely correct
    public static async getMessages(messageRequestForm: MessageRequestForm): Promise<MessageForm[]> {
        const response = await fetch('http://localhost:8080/getMessages', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                //'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(messageRequestForm)
        });
        if (response.ok) {
            return await response.json(); //TODO: check what you get back
        } else {
            console.log("Failed to fetch messages")
            const error = await response.text();
            throw new Error(error);
        }
    }

    //saveRoom - use destinationForm. why is this a post, god dammit.
    //no need for return
    public static async saveRoom(destinationForm: DestinationForm) {
        const response = await fetch('http://localhost:8080/saveRoom', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                //'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(destinationForm)
        });
        if (response.ok) {
            return await response.json();
        } else {
            console.log("Failed to fetch messages")
            const error = await response.text();
            throw new Error(error);
        }
    }

    // AddUserToRoom(joinDestinatioform) - also make sure to do this when user makes a room
    //no need for return
    public static async addUserToRoom(joinDestinationForm: JoinDestinationForm) {
        const response = await fetch('http://localhost:8080/AddUserToRoom', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                //'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(joinDestinationForm)
        });
        if (response.ok) {
            return await response.json();
        } else {
            console.log("Failed to fetch messages")
            const error = await response.text();
            throw new Error(error);
        }
    }

    //searchRooms (findByDestinationContainingIgnoreCase)
    public static async searchRooms(searchTerm: string) : Promise<any> {
        const response = await fetch('http://localhost:8080/searchRooms', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                //'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(searchTerm)
        });
        if (response.ok) {
            return await response.json();
        } else {
            console.log("Failed to fetch messages")
            const error = await response.text();
            throw new Error(error);
        }
    }

    // getUserRooms (by userId)
    public static async getUserRooms(userID: number) : Promise<any> {
        const response = await fetch('http://localhost:8080/getUserRooms', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                //'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(userID)
        });
        if (response.ok) {
            return await response.json();
        } else {
            console.log("Failed to fetch messages")
            const error = await response.text();
            throw new Error(error);
        }
    }
}