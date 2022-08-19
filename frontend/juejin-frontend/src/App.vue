<template>
  <MainHeader />
  <router-view />
  <el-backtop :right="100" :bottom="100" />
</template>

<script setup lang="ts">
import MainHeader from '@/components/MainHeader.vue'
</script>

<style lang="less" scoped>
</style>