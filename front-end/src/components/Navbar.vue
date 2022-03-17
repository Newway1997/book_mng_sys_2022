<template>
  <el-menu
    class="navbar"
    mode="horizontal"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    :router="true"
  >
    <el-menu-item index="1" route="/">首页</el-menu-item>
    <el-menu-item index="2" route="/register" v-if="!isLogin"
      >注册</el-menu-item
    >
    <el-menu-item index="3" route="/login" v-if="!isLogin">登录</el-menu-item>
    <el-sub-menu index="4" route="/user" v-if="isLogin && user">
      <template #title>用户</template>
      <el-menu-item index="2-1" :route="'/user/' + user.id"
        >个人信息</el-menu-item
      >
      <template v-if="user.type === 0">
        <el-menu-item index="2-2" route="/admin/manageUser">
          用户管理
        </el-menu-item>
        <el-menu-item index="2-3" route="/admin/manageBook">
          书籍管理
        </el-menu-item>
      </template>
      <el-menu-item index="2-4" route="/borrowbook">我的借阅</el-menu-item>
      <el-menu-item index="2-5" route="/updatePass">修改密码</el-menu-item>
      <el-menu-item index="2-6" @click="logout">注销</el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>
<script lang="ts" setup>
import { useUserStore } from "@/stores/user";
import { ref, watch } from "vue";
import * as userApi from "@/api/user";
import { useRouter } from "vue-router";
import type { User } from "@/domain/user";
import { storeToRefs } from "pinia";

const user = ref<User | null>(null);
const store = useUserStore();

const router = useRouter();
const logout = function () {
  store.userLogout();
  user.value = null;
  router.push({
    //你需要接受路由的参数再跳转
    path: "/",
  });
};

const isLogin = storeToRefs(store).isLogin;
watch(
  () => isLogin.value,
  () => {
    if (isLogin.value) {
      checkUser();
    }
  },
  {
    immediate: true,
  }
);
const checkUser = function () {
  //发送get请求
  userApi
    .checkUser()
    .then((res) => {
      user.value = res;
    })
    .catch(() => {
      store.isLogin = false;
    });
};
</script>
<style scoped></style>
