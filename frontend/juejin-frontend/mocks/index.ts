import { mock, Random } from 'mockjs'
import articleMock from './article'

mock(
    /\/articles\/detail/,
    'get',
    options => {
        let code = 0;
        let url = options.url;
        let data = {
            id: url.split('id=')[1],
            title: Random.ctitle(),
            author: Random.cname(),
            authorHead: Random.dataImage('50x50'),
            date: Random.date('yyyy-MM-dd'),
            content: articleMock,
            click: Random.integer(1, 9999),
            like: Random.integer(1, 9999),
            comment: Random.integer(1, 9999),

        }
        return {
            message: null,
            code,
            data
        }
    }
)
mock(
    /\/articles/,
    'get',
    (options) => {
        let url = options.url
        let page = parseInt(url.split('page=')[1].split('&')[0])
        let size = parseInt(url.split('size=')[1])
        let list = []
        for (let i = 0; i < size; i++) {
            list.push(
                {
                    id: Random.guid(),
                    title: Random.ctitle(),
                    author: Random.cname(),
                    date: Random.date('yyyy-MM-dd'),
                    abstract: Random.csentence(40, 50),
                    pic: Random.boolean() ? Random.dataImage('200x150') : undefined,
                    click: Random.integer(1, 9999),
                    like: Random.integer(1, 9999),
                    comment: Random.integer(1, 9999),
                }
            )
        }

        return {
            message: 'success',
            page,
            size,
            list
        }

    }
)