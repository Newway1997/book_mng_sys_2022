import type { Reader } from "@/domain/reader";
import type { User } from "@/domain/user";
import request from "@/request";

export function checkUser() {
  return request<User>({
    url: "/api/user/checkUser",
  });
}

export function logout() {
  return request<void>({
    url: "/api/user/logout",
  });
}
export function login(data: { username: string; password: string }) {
  return request({
    url: `/api/user/login`,
    data,
    method: "POST",
  });
}

export function register(data: { username: string; password: string }) {
  return request({
    url: `/api/user/register`,
    data,
    method: "POST",
  });
}

export function getReaderInfo() {
  return request<Reader>({
    url: `/api/reader/getReaderInfo`,
    method: "GET",
  });
}

export function updateReaderInfo(data: Reader) {
  return request({
    url: `/api/reader/updateReaderInfo`,
    method: "POST",
    data,
  });
}

export function getUsers(params: { curPage: number; pageSize: number }) {
  return request<User[]>({
    url: "/api/reader/getAllReaders",
    method: "GET",
    params,
  });
}

export function enableUser(params: { id: number }) {
  return request({
    url: "/api/user/enableUser",
    method: "GET",
    params,
  });
}
export function disableUser(params: { id: number }) {
  return request({
    url: "/api/user/disableUser",
    method: "GET",
    params,
  });
}
export function updatePass(data: { oldPass: string; newPass: string }) {
  return request({
    url: "/api/user/updatePass",
    method: "POST",
    data,
  });
}
