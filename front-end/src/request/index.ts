// index.ts
import axios from "axios";
import type { AxiosError } from "axios";
import qs from "qs";
import type { AxiosRequestConfig, AxiosResponse } from "axios";
import { errorHandler } from "./errorHandler";
import { startLoading, stopLoading } from "./loading";

const instance = axios.create();
instance.interceptors.request.use(
  (req: AxiosRequestConfig) => {
    if (req.method === "GET" || req.method === "DELETE") {
      req.params = req.data;
      req.paramsSerializer = (params) =>
        qs.stringify(params, { indices: false });
    }
    startLoading();
    return req;
  },
  (err: AxiosError) => {
    if (err.config.showLoading !== false) {
      errorHandler(err);
    }
    stopLoading();
    return Promise.reject(err);
  }
);
instance.interceptors.response.use(
  // 因为我们接口的数据都在res.data下，所以我们直接返回res.data
  (res: AxiosResponse) => {
    stopLoading();
    if (res.data?.meta?.success) {
      return res.data.data;
    } else {
      return Promise.reject(res.data?.meta?.message || "系统异常");
    }
  },
  (err) => {
    stopLoading();
    if (err.config.showLoading !== false) {
      errorHandler(err);
    }
    return Promise.reject(err);
  }
);

export default instance;
