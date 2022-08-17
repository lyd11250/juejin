<template>
    <div id="category-header-box">
        <div id="category-header">
            <div class="nav-list">
                <router-link class="nav-list-item" active-class="active" v-for="c in categories" :key="c.category" :to="{
                    name: pageName,
                    params: {
                        category: c.category
                    }
                }">
                    {{ c.title }}</router-link>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
type Category = {
    category: string,
    title: string
}
type Props = {
    pageName: string,
    categories: Array<Category>
}
defineProps<Props>()

</script>

<style scoped lang="less">
#category-header-box {
    position: relative;
    top: 0;
    left: 0;
    right: 0;
    height: 45px;

    #category-header {
        position: fixed;
        left: 0;
        right: 0;
        border-bottom: 1px solid #f1f1f1;
        background-color: white;
        z-index: 10;

        .nav-list {
            flex-shrink: 0;
            width: 70%;
            display: flex;
            justify-content: center;
            align-items: center;

            &-item {
                text-align: center;
                height: 45px;
                line-height: 45px;
                text-decoration: none;
                color: black;
                margin-left: 20px;
                font-size: 14px;

                &:hover,
                &.active {
                    color: #1e80ff;
                }
            }
        }
    }
}
</style>