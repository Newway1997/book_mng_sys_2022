import type { Book, BorrowBook, Category } from "@/domain/book";
import request from "@/request";

export function getBooks() {
  return request<Book[]>({
    url: "/api/book/getBooks",
    params: {
      pageSize: 10,
      curPage: 0,
    },
  });
}

export function getBook(params: { isbn: string }) {
  return request<Book>({
    url: "/api/book/getBookDetail",
    params,
  });
}
export function borrowBook(params: { isbn: string }) {
  return request({
    url: "/api/book/borrowBook",
    params,
  });
}
export function getBorrowBooks() {
  return request<BorrowBook[]>({
    url: "/api/book/getBorrowBooks",
  });
}
export function returnBook(params: { id: number; isbn: string }) {
  return request({
    url: "/api/book/returnBook",
    params,
  });
}
export function getCategory() {
  return request<Category[]>({
    url: "/api/book/getCategory",
  });
}
export function enableBook(params: { isbn: string }) {
  return request({
    url: "/api/book/enableBook",
    params,
  });
}

export function disableBook(params: { isbn: string }) {
  return request({
    url: "/api/book/disableBook",
    params,
  });
}
export function updateBook(data, headers) {
  return request({
    method: "POST",
    url: "/api/book/updateBook",
    data,
    headers,
  });
}
export function uploadBook(data, headers) {
  return request({
    method: "POST",
    url: "/api/book/uploadBook",
    data,
    headers,
  });
}
