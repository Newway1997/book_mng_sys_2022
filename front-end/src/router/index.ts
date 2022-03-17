import { useUserStore } from "./../stores/user";
import { createRouter, createWebHistory } from "vue-router";
import BooksList from "@/views/BookList/Index.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "BooksList",
      component: BooksList,
    },
    {
      path: "/login",
      name: "Login",
      component: () => import("@/views/Login.vue"),
    },
    {
      path: "/register",
      name: "Register",
      component: () => import("@/views/Register.vue"),
    },
    {
      path: "/user/:id",
      name: "UserInfo",
      component: () => import("@/views/ReaderInfo.vue"),
      meta: {
        needLogin: true,
      },
    },
    {
      path: "/book/:isbn",
      name: "BookInfo",
      component: () => import("@/views/BookInfo/Index.vue"),
    },
    {
      path: "/borrowbook",
      name: "BorrowBook",
      component: () => import("@/views/BorrowBook.vue"),
      meta: {
        needLogin: true,
      },
    },
    {
      path: "/updatePass",
      name: "UpdatePass",
      component: () => import("@/views/UpdatePass.vue"),
      meta: {
        needLogin: true,
      },
    },
    {
      path: "/admin/manageUser",
      name: "UserList",
      component: () => import("@/views/UserList.vue"),
      meta: {
        needLogin: true,
      },
    },
    {
      path: "/admin/manageBook",
      name: "BookManage",
      component: () => import("@/views/BookManage/Index.vue"),
      meta: {
        needLogin: true,
      },
    },
  ],
});

router.beforeEach((to, from, next) => {
  const store = useUserStore();
  if (to.meta.needLogin && !store.isLogin) {
    next("/login");
  } else {
    next();
  }
});
export default router;
