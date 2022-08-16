<template>
    <div id="main-container-box">
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
const handleAnchorClick = (index: number) => {
    window.scrollTo(0, catalog[index].offsetTop - 70)
    activeIndex.value = index
}

function scrollListener() {
    console.log(window.scrollY);

    catalog.forEach(c => {
        if (c.offsetTop <= window.scrollY + 70) {
            activeIndex.value = catalog.indexOf(c)
        }
    })
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
    window.addEventListener('scroll', scrollListener)
})

onBeforeUnmount(() => {
    window.removeEventListener('scroll', scrollListener)
})
</script>

<style lang="less" scoped>
#main-container-box {
    display: flex;
    justify-content: center;
    width: 100vw;
    margin-top: 20px;

    #article {
        width: 40%;
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