export default [
    {
        path: '/',
        redirect: '/articles'
    },
    {
        path: '/articles',
        meta: {
            title: '文章',
            showInMainHeader: true,
        },
        redirect: '/articles/recommended',
        children: [
            {
                name: 'articles',
                path: ':category',
                component: () => import('@/views/articles/index.vue'),
            },
            {
                name: 'articleDetail',
                path: 'detail/:id',
                component: () => import('@/views/article/index.vue'),
            }
        ]
    }
]