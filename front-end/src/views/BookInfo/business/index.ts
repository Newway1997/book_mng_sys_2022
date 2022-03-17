import { useRoute } from "vue-router";
import * as bookApi from "@/api/books";
import { onMounted, ref } from "vue";
import type { Book } from "@/domain/book";

export function useBook() {
  const book = ref<Book | Record<string, never>>({});

  const route = useRoute();

  const getBook = () => {
    bookApi
      .getBook({
        isbn: route.params.isbn as string,
      })
      .then((data) => {
        book.value = data;
      });
  };
  onMounted(() => {
    getBook();
  });
  return book;
}
