

export class NewPostModel{
    //TODO: finish this
    //what should we put in? username and date too? yeah


    constructor(public title: string, public text: string | null, public tag: string) {
        this.title = title;
        this.text = text;
        this.tag=tag

    }
}