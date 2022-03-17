<template>
    <el-card class="card" :body-style="{ padding: '0px' }">
      <img v-if="book.imgUrl" :src="baseUrl + book.imgUrl" class="card-image" />
      <div class="card-bottom">
        <span>{{ book.bookName }}</span>
        <div class="book-descriptions clearfix">
          <span class="book-publish">{{ book.publisher }}</span>
          <el-button
            type="text"
            class="card-button"
            @click="routeToBook(book.isbn)"
          >
            详情
          </el-button>
        </div>
      </div>
    </el-card>
</template>
<script lang="ts" setup>
import { BASE_URL } from "@/config/constant";
import type { Book } from "@/domain/book";
import { useRouter } from "vue-router";

const baseUrl = BASE_URL + "/img/";
const props = defineProps<{
  book: Book;
}>();
const router = useRouter();
const routeToBook = (isbn: string) => {
  router.push({
    //你需要接受路由的参数再跳
    path: "/book/" + isbn,
  });
};
</script>
<style>
.book-publish {
  font-size: 13px;
  color: #999;
}

.book-descriptions {
  margin-top: 13px;
  line-height: 12px;
}

.card-button {
  padding: 0;
  float: right;
}

.card-image {
  width: 100%;
  display: block;
}

.clearfix::before,
.clearfix::after {
  display: table;
  content: "";
  clear: both;
}
.card-bottom {
  padding: 14px;
  text-align: left;
}
</style>
