<template>
    <CategoryHeader :categories="categories" pageName="articles" />
    <div class="article-list">
        <div class="sort">
            <router-link class="sort-item" v-for="s in sorts" :key="s.index" :to="{ query: { sort: s.index } }"
                :class="{ active: currentSort == s.index }" @click="currentSort = s.index">{{ s.title }}</router-link>
        </div>
        <div v-for="a in articles" :key="a.id">
            <ArticleCard :article="a" />
        </div>
        <el-skeleton class="skeleton" animated>
            <template #template>
                <div style="display: flex;flex-direction: column;justify-content: space-between;">
                    <el-skeleton-item variant="p" style="width: 200px;height: 20px;" />
                    <el-skeleton-item variant="p" style="width: 210px;height: 21px;" />
                    <el-skeleton-item variant="p" style="width: 600px;height: 20px;" />
                    <el-skeleton-item variant="p" style="width: 170px;height: 22px;" />
                </div>
                <el-skeleton-item variant="image" style="width: 200px; height: 150px;" />
            </template>
        </el-skeleton>
    </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { onMounted, onUnmounted, reactive, ref, watch } from 'vue';
import { useRoute } from 'vue-router';
import Debounced from '@/hooks/useDebounced'
import ArticleCard from './components/ArticleCard.vue';
const categories = [
    { category: 'recommended', title: '综合' },
    { category: 'backend', title: '后端' },
    { category: 'frontend', title: '前端' },
    { category: 'android', title: 'Android' },
    { category: 'ios', title: 'iOS' },
    { category: 'ai', title: '人工智能' },
    { category: 'ide', title: '开发工具' },
    { category: 'career', title: '代码人生' },
]
const sorts = [
    { index: 0, title: '推荐' },
    { index: 1, title: '最新' },
    { index: 2, title: '最热' },
]
const route = useRoute()
const articles: any = reactive([])
const currentPage = ref(0)
const pageSize = 10;
const currentSort = ref(0)
const getArticles = () => {
    axios.get('/articles/list', {
        params: {
            category: route.params.category,
            page: currentPage.value + 1,
            size: pageSize,
            sort: currentSort.value
        }
    }).then(res => {
        if (res.data.msg === 'success') {
            articles.push(...res.data.list)
            currentPage.value += 1
        }

    })
}

watch(
    () => route.params.category,
    (newVal, oldVal) => {
        // 当文章分类发生变化时
        if (newVal != oldVal) {
            // 清空原数组
            articles.splice(0, articles.length)
            // 重置当前页为0
            currentPage.value = 0
            // 重置排序规则为0
            currentSort.value = 0
            // 重新获取文章列表
            getArticles()
        }
    })
watch(
    // 当排序规则发生变化时
    () => currentSort.value,
    () => {
        // 清空原数组
        articles.splice(0, articles.length)
        // 重置当前页为0
        currentPage.value = 0
        // 重新获取文章列表
        getArticles()

    })

const handleScroll = () => {
    // 距顶部
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    // 可视区高度
    var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
    // 滚动条总高度
    var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
    // 滚动到底部时加载列表
    if (scrollTop + clientHeight >= scrollHeight - 200) {
        getArticles()
    }
}

const debounced = new Debounced()
const debouncedUse: Function = debounced.use(handleScroll, 500, true)
const func = (...args: any) => {
    debouncedUse(...args)
}
onMounted(() => {
    getArticles()
    window.addEventListener('scroll', func)
})

onUnmounted(() => {
    window.removeEventListener('scroll', func)
})
</script>

<style lang="less" scoped>
.article-list {
    width: 50%;
    margin: 20px auto;
    border-radius: 10px;
    overflow: hidden;

    .sort {
        background-color: white;
        border-bottom: 1px solid #f1f1f1;

        &-item {
            text-align: center;
            height: 45px;
            line-height: 45px;
            text-decoration: none;
            color: black;
            margin-left: 20px;

            &.active {
                color: #1e80ff;
            }
        }
    }

    .skeleton {
        width: 100%;
        padding: 20px;
        display: flex;
        background-color: white;
        box-sizing: border-box;
        justify-content: space-between;
    }
}
</style>