<template>
    <CategoryHeader :categories="categories" pageName="articles" />
    <div class="article-list" v-infinite-scroll="getArticles">
        <el-skeleton v-if="isLoading" animated style="padding:20px;display: flex;background-color: white;">
            <template #template>
                <div style="width:100%;display: flex;flex-direction: column;justify-content: space-between;">
                    <el-skeleton-item variant="p" style="width: 200px;height: 20px;" />
                    <el-skeleton-item variant="p" style="width: 210px;height: 21px;" />
                    <el-skeleton-item variant="p" style="width: 600px;height: 20px;" />
                    <el-skeleton-item variant="p" style="width: 170px;height: 22px;" />
                </div>
                <el-skeleton-item variant="image" style="width: 200px; height: 150px;flex-shrink: 0;" />
            </template>
        </el-skeleton>
        <div v-else v-for="a in articles" :key="a.id">
            <ArticleCard :article="a" />
        </div>
    </div>
</template>

<script setup lang="ts">
import axios from 'axios';
import { reactive, ref } from 'vue';
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
const getArticles = () => {
    axios.get('/articles', {
        params: {
            category: route.params.category,
            page: currentPage.value + 1,
            size: 20,
        }
    }).then(res => {

        if (res.data.message === 'success') {
            articles.push(...res.data.list)
            currentPage.value++
            isLoading.value = false
        }
    })
}
</script>

<style lang="less" scoped>
.article-list {
    width: 50%;
    margin: 20px auto;
    border-radius: 10px;
    overflow: hidden;
}
</style>