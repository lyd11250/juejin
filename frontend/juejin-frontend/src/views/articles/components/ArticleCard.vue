<template>
    <router-link class="article-card" :to="{
        name: 'articleDetail',
        params: {
            id: article.id
        }
    }">
        <div class="article-card-left" :style="{
            height: (article.abstract ? 150 : 80) + 'px'
        }">
            <div class="author-and-date">
                <span class="author">{{ article.author }}</span>
                <el-divider direction="vertical" />
                <span class="date">{{ relative(article.date) }}</span>
            </div>
            <div class="title">{{ article.title }}</div>
            <div class="abstract" v-if="article.abstract">{{ article.abstract }}</div>
            <div class="data">
                <span class="click">
                    <el-icon>
                        <View />
                    </el-icon>
                    {{ article.click }}
                </span>
                <span class="like">
                    <el-icon>
                        <Share />
                    </el-icon>
                    {{ article.like }}
                </span>
                <span class="comment">
                    <el-icon>
                        <ChatLineSquare />
                    </el-icon>
                    {{ article.comment }}
                </span>
            </div>
        </div>
        <div class="article-card-right" v-if="article.pic">
            <img :src="article.pic">
        </div>

    </router-link>
</template>

<script setup lang="ts">
import { View, Share, ChatLineSquare } from '@element-plus/icons-vue'
import relative from '@/hooks/useRelativeTime'
type Props = {
    article: any,
}
defineProps<Props>()

</script>

<style scoped lang="less">
.article-card {
    text-decoration: none;
    display: flex;
    padding: 20px;
    border-bottom: 1px solid #e5e6eb;
    background-color: white;
    color: #86909c;

    &:hover {
        background-color: #fafafa;
    }

    &-left {
        display: flex;
        flex-direction: column;
        justify-content: space-between;
        flex-grow: 1;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;


        .title {
            color: black;
            font-weight: 900;
            font-size: 1.1rem;
        }

        .abstract {
            text-overflow: ellipsis;
            overflow: hidden;
        }

        .data {
            .like {
                margin: 0 20px;
            }
        }
    }

    &-right {
        position: relative;
        flex: 0 0 auto;
        margin-left: 20px;
    }
}
</style>