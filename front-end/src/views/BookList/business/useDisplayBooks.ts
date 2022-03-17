import type { Book } from "@/domain/book";
import { computed } from "vue";
import type { Ref } from "vue";
export default function useDisplayBooks(books: Ref<Book[]>, cols: Ref<number>) {
  const displayBooks = computed(() => {
    const bookArr: Book[][] = [];
    books.value.forEach((item, index) => {
      const row = Math.floor(index / cols.value);
      if (!bookArr[row]) {
        bookArr[row] = [];
      }
      bookArr[row].push(item);
    });
    return bookArr;
  });
  return displayBooks;
}
