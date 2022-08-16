import { marked } from 'marked'
import hljs from 'highlight.js'
import 'highlight.js/styles/github-dark.css'

const render = new marked.Renderer()
marked.setOptions({
    renderer: render,
    gfm: true,
    pedantic: false,
    sanitize: false,
    highlight: (code, lang) => hljs.highlight(code, { language: lang }).value
})

export default (html: string) => {
    return marked(html)
}