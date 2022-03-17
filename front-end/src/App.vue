<script setup lang="ts">
import { RouterView } from "vue-router";
import Navbar from "@/components/Navbar.vue";
import { useUserStore } from "./stores/user";
import { onMounted } from "vue";

const store = useUserStore();
onMounted(() => {
  store.isLogin = sessionStorage.getItem("isLogin") === "true";
});
</script>

<template>
  <el-container id="app">
    <el-header>
      <Navbar />
    </el-header>
    <el-main>
      <router-view />
    </el-main>
  </el-container>
</template>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  height: 100%;
}
body {
  margin: 0;
  padding: 0;
  height: 100vh;
}
body .el-header {
  padding: 0;
}
</style>
