<template>
    <div class="main-header-box" v-show="showMainHeader">
        <div id="main-header">
            <div class="container">
                <div class="container-left">
                    <div class="logo">
                        <img
                            src="https://lf3-cdn-tos.bytescm.com/obj/static/xitu_juejin_web/e08da34488b114bd4c665ba2fa520a31.svg" />
                    </div>
                    <div class="navigation">
                        <router-link class="nav-item" active-class="active" v-for="p in pages" :key="p.path"
                            :to="p.path">{{ p.title }}
                        </router-link>
                    </div>
                </div>
                <div class="container-right">
                    <div class="search-bar">
                        <el-input placeholder="探索稀土掘金">
                            <template #append>
                                <el-button :icon="Search">
                                </el-button>
                            </template>
                        </el-input>
                    </div>
                    <div class="creator-center">
                        <el-dropdown>
                            <el-button type="primary">创作者中心</el-button>
                            <template #dropdown>
                                <el-dropdown-menu>
                                    <el-dropdown-item :icon="EditPen">写文章</el-dropdown-item>
                                    <el-dropdown-item :icon="MagicStick">发沸点</el-dropdown-item>
                                    <el-dropdown-item :icon="Paperclip">写代码</el-dropdown-item>
                                </el-dropdown-menu>
                            </template>
                        </el-dropdown>
                    </div>
                    <div class="user">
                        <el-button type="primary" plain>登录</el-button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { onMounted, reactive, ref, watch } from 'vue';
import { useRouter } from 'vue-router'
import { Search, EditPen, Paperclip, MagicStick } from '@element-plus/icons-vue';
const routes = useRouter().getRoutes()

type Page = {
    title: string,
    path: string
}
const pages: Array<Page> = reactive([]);
const showMainHeader = ref(true)
const currentScrollTop = ref(0)
const throttle = ref(true)
watch(
    () => currentScrollTop.value,
    (newVal, oldVal) => {
        if (throttle.value) {
            throttle.value = false
            showMainHeader.value = newVal < 500 || oldVal > newVal
            setTimeout(() => {
                throttle.value = true
            }, 500);
        }

    }
)
const handleScroll = () => {
    currentScrollTop.value = document.documentElement.scrollTop || document.body.scrollTop;

}
onMounted(() => {
    routes.forEach(route => {
        if (route.meta.showInMainHeader) {
            pages.push({
                title: route.meta.title as string,
                path: route.path
            })
        }
    })
    window.addEventListener('scroll', handleScroll)
})
</script>

<style scoped lang="less">
.main-header-box {
    position: relative;
    top: 0;
    left: 0;
    right: 0;
    height: 60px;

    #main-header {
        position: fixed;
        top: 0;
        left: 0;
        right: 0;
        height: 60px;
        border-bottom: 1px solid #f1f1f1;
        background-color: white;
        z-index: 999;
        display: flex;

        .container {
            margin: auto;
            width: 70%;
            height: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;

            &-left {
                display: flex;
                height: 100%;
                align-items: center;

                .navigation {
                    height: 100%;
                    display: flex;
                    align-items: center;

                    .nav-item {
                        text-align: center;
                        height: 60px;
                        line-height: 60px;
                        text-decoration: none;
                        color: black;
                        margin-left: 20px;

                        &.active,
                        &:hover {
                            color: #1e80ff;
                            border-bottom: 2px solid #1e80ff;
                        }
                    }
                }
            }

            &-right {
                display: flex;

                .search-bar {
                    width: 300px;
                }

                .creator-center {
                    margin: 0 20px;
                }
            }
        }
    }
}
</style>