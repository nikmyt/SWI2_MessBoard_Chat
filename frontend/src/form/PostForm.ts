export class PostForm {
    title: string;
    text: string;
    tag: string;

    constructor(title: string, text: string, tag: string) {
        this.title = title;
        this.text = text;
        this.tag = tag;
    }
}