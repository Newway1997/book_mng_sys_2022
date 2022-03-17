<template>
  <el-row>
    <el-col :span="4" :offset="3">
      <el-card :body-style="{ padding: '0px' }">
        <img
          v-if="book.imgUrl"
          :src="baseUrl + book.imgUrl"
          class="book-image"
        />
        <div class="card-bottom">
          <span>{{ book.bookName }}</span>
          <div class="book-descriptions clearfix">
            <span class="book-publish">{{ book.publisher }}</span>
          </div>
        </div>
      </el-card>
    </el-col>
    <el-col :span="10" :offset="3">
      <ul class="book-info">
        <li class="book-name">
          <label>书名</label><span>{{ book.bookName }}</span>
        </li>
        <li>
          <label>作者</label><span>{{ book.author }}</span>
        </li>
        <li>
          <label>出版社</label><span>{{ book.publisher }}</span>
        </li>
        <li>
          <label>价格</label><span>{{ book.unitPrice }}</span>
        </li>
        <li class="book-sumary">
          <label>简介</label><span>{{ book.summary }}</span>
        </li>
        <li>
          <label>余量</label><span>{{ book.lastNum }}</span>
        </li>
        <li>
          <el-button
            type="primary"
            @click="borrowBook"
            :disabled="book.lastNum < 1"
            >借阅</el-button
          >
        </li>
      </ul>
    </el-col>
  </el-row>
</template>

<script lang="ts" setup>
import * as bookApi from "@/api/books";
import { BASE_URL } from "@/config/constant";
import { useUserStore } from "@/stores/user";
import { ElMessage } from "element-plus";
import { useRoute, useRouter } from "vue-router";
import { useBook } from "./business";

const baseUrl = BASE_URL + "/img/";
const book = useBook();
const router = useRouter();
const route = useRoute();
const store = useUserStore();
const borrowBook = () => {
  if (!store.isLogin) {
    router.push("/login");
    return;
  }
  bookApi.borrowBook({ isbn: route.params.isbn as string }).then(() => {
    ElMessage.success("借阅成功");
    setTimeout(() => {
      router.push({
        //你需要接受路由的参数再跳转
        path: "/",
      });
    }, 1000);
  });
};
</script>

<style scoped>
.el-row {
  margin-top: 100px;
}
.card-bottom {
  padding: 14px;
  text-align: left;
}
.book-publish {
  font-size: 13px;
  color: #999;
}

.book-descriptions {
  margin-top: 13px;
  line-height: 12px;
}

.book-image {
  width: 100%;
  display: block;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

ul {
  list-style: none;
  text-align: center;
}

ul.book-info li {
  margin: 10px;
  min-height: 30px;
  line-height: 30px;
}
li label {
  float: left;
  font-weight: bold;
}
.book-sumary span {
  text-align: left;
}
.book-name {
  font-size: 32px;
  font-weight: bold;
}
</style>
