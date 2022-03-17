import { useUserStore } from "./../../../stores/user";
import type { Book } from "@/domain/book";
import { ref, onMounted, watch } from "vue";
import * as booksApi from "@/api/books";

export default function useBooks() {
  const books = ref<Book[]>([]);

  const getBooks = function () {
    booksApi.getBooks().then((res) => {
      books.value = res;
    });
  };
  onMounted(() => {
    getBooks();
  });
  const store = useUserStore();
  watch(
    () => store.isLogin,
    () => {
      getBooks();
    }
  );
  return books;
}
