<template>
  <div>
    <el-row v-for="(books, index) in displayBooks" :key="index">
      <el-col v-for="book in books" :span="24 / cols" :key="book.isbn">
        <book-card :book="book" class="card-container"></book-card>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts" setup>
import BookCard from "./components/BookCard.vue";
import useCols from "./business/useCols";
import useDisplayBooks from "./business/useDisplayBooks";
import useBooks from "./business/useBooks";

const books = useBooks();
const cols = useCols();
const displayBooks = useDisplayBooks(books, cols);

</script>
<style scoped>
.card-container {
  margin: 16px;
}
</style>
