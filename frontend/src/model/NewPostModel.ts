export class NewPostModel{
    //TODO: finish this
    //what should we put in? username and date too? yeah
    constructor(public title: string, public text: string | null) {
        this.title = title;
        this.text = text;
    }
}