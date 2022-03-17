import type { AxiosRequestConfig, AxiosResponse } from "axios";

declare module "axios" {
  interface AxiosInstance {
    <T>(config: AxiosRequestConfig): Promise<T>;
  }
  interface AxiosRequestConfig {
    showLoading?: boolean;
    showError?: boolean;
  }
}
