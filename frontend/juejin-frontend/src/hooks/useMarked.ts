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
    let htmlStr = marked(html)

    // 为所有pre标签添加hljs类名，使其拥有背景色
    return htmlStr.replaceAll('<pre', "<pre class=\"hljs\"")
}