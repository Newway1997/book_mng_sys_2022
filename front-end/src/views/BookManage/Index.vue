<template>
  <div>
    <el-button class="upload-btn" @click="openDialog(0)">上传图书</el-button>
    <el-table :data="books" style="width: 100%">
      <el-table-column prop="isbn" label="ISBN"></el-table-column>
      <el-table-column prop="bookName" label="书名"></el-table-column>
      <el-table-column prop="author" label="作者"></el-table-column>
      <el-table-column prop="publisher" label="出版社"></el-table-column>
      <el-table-column label="修改图书">
        <template v-slot="scope">
          <el-button
            type="primary"
            value="return"
            @click="openDialog(1, scope.row)"
            >修改图书</el-button
          >
        </template>
      </el-table-column>
      <el-table-column label="禁用图书">
        <template v-slot="scope">
          <el-button
            type="danger"
            value="return"
            @click="disableBook(scope.row)"
            v-if="scope.row.status === 1"
            >禁用</el-button
          >
          <el-button
            type="success"
            value="return"
            @click="enableBook(scope.row)"
            v-if="scope.row.status === 0"
            >启用</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <upload-dialog
      v-model:visible="dialogFormVisible"
      :mode="mode"
      :isbn="isbn"
      @upload="onUploaded"
    ></upload-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ElMessage } from "element-plus";
import { ref, onMounted } from "vue";
import * as bookApi from "@/api/books";
import type { Book } from "@/domain/book";
import UploadDialog from "./components/UploadDialog.vue";

const books = ref<Book[]>([]);

const getBooks = () => {
  bookApi.getBooks().then((data) => {
    books.value = data;
  });
};

onMounted(() => {
  getBooks();
});

const enableBook = (book: Book) => {
  bookApi
    .enableBook({
      isbn: book.isbn,
    })
    .then(() => {
      ElMessage.success("启用成功");
      getBooks();
    });
};
const disableBook = (book: Book) => {
  bookApi
    .disableBook({
      isbn: book.isbn,
    })
    .then(() => {
      ElMessage.success("禁用成功");
      getBooks();
    });
};

const dialogFormVisible = ref(false);
const mode = ref<0 | 1>(0);
const isbn = ref<string>();
const openDialog = (targetMode: 0 | 1, book?: Book) => {
  mode.value = targetMode;
  dialogFormVisible.value = true;
  isbn.value = book?.isbn;
};

const onUploaded = () => {
    getBooks();
};
</script>
<style scoped>
.upload-btn {
  float: left;
}
</style>
