<template>
    <CategoryHeader :categories="categories" pageName="articles" />
    <div class="article-list">
        <el-skeleton class="skeleton" v-if="isLoading" animated>
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
        <div v-else v-for="a in articles" :key="a.id">
            <ArticleCard :article="a" />
        </div>
    </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { onMounted, onUnmounted, reactive, ref } from 'vue';
import { useRoute } from 'vue-router';
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
const route = useRoute()
const isLoading = ref(true)
const articles: any = reactive([])
const currentPage = ref(0)
const pageSize = 20;
const getArticles = () => {
    axios.get('/articles/list', {
        params: {
            category: route.params.category,
            page: currentPage.value + 1,
            size: pageSize,
        }
    }).then(res => {
        if (res.data.msg === 'success') {
            articles.push(...res.data.list)
            currentPage.value += 1
            isLoading.value = false
        }
    })
}

const handleScroll = () => {
    // 距顶部
    var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
    // 可视区高度
    var clientHeight = document.documentElement.clientHeight || document.body.clientHeight;
    // 滚动条总高度
    var scrollHeight = document.documentElement.scrollHeight || document.body.scrollHeight;
    // 滚动到底部时加载列表
    if (scrollTop + clientHeight === scrollHeight) {
        getArticles()
    }

}

onMounted(() => {
    getArticles()
    window.addEventListener('scroll', handleScroll)
})

onUnmounted(() => {
    window.removeEventListener('scroll', handleScroll)
})
</script>

<style lang="less" scoped>
.article-list {
    width: 50%;
    margin: 20px auto;
    border-radius: 10px;
    overflow: hidden;

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