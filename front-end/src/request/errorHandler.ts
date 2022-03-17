import { ElMessage } from "element-plus";
import type { AxiosError } from "axios";
import { useRouter } from "vue-router";
export function errorHandler(error: AxiosError) {
  if (error.response?.status === 401) {
    const router = useRouter();
    router?.push("/login");
    return;
  }
  ElMessage.error({
    message: String(error.message || error || "系统异常"),
  });
}
