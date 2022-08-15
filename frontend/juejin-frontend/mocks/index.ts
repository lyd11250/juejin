import { mock, Random } from 'mockjs'

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
    // {
    //     message: 'success',
    //     "list|20": [
    //         {
    //             id: "@id()",
    //             date: "@datetime()",
    //             author: "@cname()",
    //             title: "@ctitle(10,25)",
    //             "img|0-1": "@dataImage('200x150')",
    //             abstract: "@csentence(40,45)" + "...",
    //             click: "@integer(1,9999)",
    //             like: "@integer(1,9999)",
    //             comment: "@integer(1,9999)",
    //         }
    //     ]
    // }
)

