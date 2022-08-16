<template>
    <div class="main-container-box">
        <div class="main-container-box-left">
            <div id="article">
                <div class="article-info">
                    <div class="title">{{ article.data.title }}</div>
                    <div class="author-date">
                        <div class="author-date-left">
                            <div class="author-head"><img :src="article.data.authorHead"></div>
                            <div class="author-name-date">
                                <div class="author-name">{{ article.data.author }}</div>
                                <div class="publish-date">{{ article.data.date }}</div>
                            </div>
                        </div>
                        <div class="author-date-right">
                            <el-button class="subscribe" type="primary" plain size="large">关注</el-button>
                        </div>
                    </div>
                </div>
                <div ref="articleRef" id="article-content" v-html="article.data.content"></div>
            </div>
            <div class="relate-list">
                <div class="relate-list-header">相关推荐</div>
                <ArticleCard v-for="r in relates" :key="r.id" :detail="false" :article="r" />
            </div>
        </div>
        <div class="catalog-box">
            <div class="catalog">
                <div class="catalog-title">目录</div>
                <hr>
                <div class="anchor-list">
                    <a class="anchor-list-item" v-for="(c, index) in catalog" :key="index"
                        :class="{ active: activeIndex === index }" @click="handleAnchorClick(index)"
                        :style="{ 'padding-left': 10 + c.level * 20 + 'px' }">
                        {{ c.label }}
                    </a>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { nextTick, onMounted, onBeforeUnmount, reactive, ref } from 'vue';
import { useRoute } from 'vue-router'
import mark from '@/hooks/useMarked'
import ArticleCard from '../articles/components/ArticleCard.vue';
import Debounced from '@/hooks/useDebounced'
const route = useRoute()

const article: any = reactive({
    data: {
        id: route.params.id
    }
});

type Catalog = {
    level: number,
    label: string,
    offsetTop: number
}
const activeIndex = ref(-1)
const articleRef = ref()
const catalog: Catalog[] = reactive([])
const createCatalog = () => {
    let titleNodes: HTMLElement[] = articleRef.value.querySelectorAll('h2,h3')
    titleNodes.forEach(n => {
        catalog.push({
            level: parseInt(n.tagName.charAt(1)) - 2,
            label: n.id,
            offsetTop: n.offsetTop
        })
    })
}
const handleCatalogScroll = () => {
    catalog.forEach(c => {
        if (c.offsetTop <= window.scrollY + 70) {
            activeIndex.value = catalog.indexOf(c)
        }
    })
}
const handleAnchorClick = (index: number) => {
    window.scrollTo(0, catalog[index].offsetTop - 70)
    activeIndex.value = index
}

const relates: any = reactive([])
const currentPage = ref(0)
const pageSize = 10
const getRelates = () => {
    axios.get('/articles/relate', {
        params: {
            id: article.id,
            page: currentPage.value + 1,
            size: pageSize
        }
    }).then(res => {
        if (res.data.msg === 'success') {
            relates.push(...res.data.list)
            currentPage.value += 1
        }
    })
}

function scrollListener() {
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
    var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
    if (scrollTop + clientHeight === scrollHeight) {
        getRelates()
    }
}
const debounced = new Debounced()
const debouncedUse: Function = debounced.use(scrollListener, 500, true)
const func = (...args: any) => {
    debouncedUse(...args)
}

onMounted(() => {
    axios.get('/articles/detail', {
        params: {
            id: article.data.id
        }
    }).then(res => {
        if (res.data.code === 0) {
            article.data = res.data.data
            article.data.content = mark(article.data.content)
            nextTick(() => {
                createCatalog();
            })
        } else {
            console.log(res.data.message);
        }
    })
    getRelates()
    window.addEventListener('scroll', func)
    window.addEventListener('scroll', handleCatalogScroll)
})

onBeforeUnmount(() => {
    window.removeEventListener('scroll', func)
    window.removeEventListener('scroll', handleCatalogScroll)
})
</script>

<style lang="less" scoped>
.main-container-box {
    display: flex;
    justify-content: center;
    width: 100vw;
    margin-top: 20px;

    &-left {
        width: 40%;

        #article {
            background-color: white;
            padding: 20px;
            border-radius: 10px;

            .article-info {
                .title {
                    font-weight: 900;
                    font-size: 2rem;
                    margin-bottom: 20px;
                }

                .author-date {
                    display: flex;
                    align-items: center;
                    justify-content: space-between;

                    &-left {
                        display: flex;
                        height: 50px;

                        .author-head {
                            width: 50px;
                            height: 50px;
                            border-radius: 50%;
                            overflow: hidden;
                        }

                        .author-name-date {
                            display: flex;
                            flex-direction: column;
                            justify-content: space-around;
                            margin-left: 10px;
                        }
                    }
                }
            }

        }

        .relate-list {
            margin-top: 20px;
            border-radius: 10px;
            overflow: hidden;
            background-color: #fff;

            &-header {
                padding: 20px;
                font-size: 2rem;
                font-weight: 900;
                border-bottom: 1px solid #e5e6eb;
            }
        }
    }


    .catalog-box {
        position: relative;
        left: 20px;
        width: 300px;

        .catalog {
            position: fixed;
            background-color: white;
            display: flex;
            flex-direction: column;
            padding: 20px;
            border-radius: 10px;
            width: 300px;
            max-height: calc(100vh - 140px);
            overflow-y: auto;

            &-title {
                font-size: 2rem;
                font-weight: 900;
            }

            .anchor-list {
                display: flex;
                flex-direction: column;

                &-item {
                    text-decoration: none;
                    color: black;
                    height: 2rem;
                    line-height: 2rem;
                    white-space: nowrap;
                    text-overflow: ellipsis;
                    overflow: hidden;

                    &:hover,
                    &.active {
                        cursor: pointer;
                        background-color: #ecf5ff;
                    }
                }
            }
        }
    }
}
</style>