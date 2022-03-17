<template>
  <el-table :data="borrowBook" style="width: 100%">
    <el-table-column prop="isbn" label="ISBN" width="180"></el-table-column>
    <el-table-column
      prop="borrowDate"
      label="借阅日期"
      width="180"
      :formatter="dateFormatter"
    ></el-table-column>
    <el-table-column
      prop="returnDate"
      label="归还日期"
      :formatter="dateFormatter"
    ></el-table-column>
    <el-table-column label="归还图书">
      <template v-slot="scope">
        <el-button type="primary" value="return" @click="returnBook(scope.row)"
          >归还</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

<script lang="ts" setup>
import { onMounted, ref } from "vue";
import * as bookApi from "@/api/books";
import { ElMessage } from "element-plus";
import type { BorrowBook } from "@/domain/book";

const borrowBook = ref<BorrowBook[]>([]);
const getBorrowBook = () => {
  bookApi.getBorrowBooks().then((data) => {
    borrowBook.value = data;
  });
};
const dateFormatter = (row: any, col: any, value: string | number | Date) => {
  return new Date(value).toLocaleDateString();
};

const returnBook = (params: { id: number; isbn: string }) => {
  let id = params.id;
  let isbn = params.isbn;
  bookApi.returnBook({ id: id, isbn: isbn }).then(() => {
    ElMessage.success("归还成功");
    setTimeout(() => getBorrowBook(), 1000);
  });
};
onMounted(() => {
  getBorrowBook();
});
</script>

<style scoped></style>
