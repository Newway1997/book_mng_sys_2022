import { defineStore } from "pinia";
import * as userApi from "@/api/user";
import { ElMessage } from "element-plus";

export const useUserStore = defineStore({
  id: "user",
  state: () => ({
    isLogin: false,
  }),
  actions: {
    async userLogin({
      username,
      password,
    }: {
      username: string;
      password: string;
    }) {
      await userApi.login({
        username,
        password,
      });
      ElMessage.success("登陆成功");
      sessionStorage.setItem("isLogin", String(true));
      this.isLogin = true;
    },
    async userLogout(feedback = true) {
      await userApi.logout();
      feedback && ElMessage.success("注销成功");
      sessionStorage.setItem("isLogin", String(false));
      this.isLogin = false;
    },
  },
});
