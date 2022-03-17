import { ElLoading } from "element-plus";
import type { LoadingInstance } from "element-plus/es/components/loading/src/loading";

let loading: LoadingInstance | null = null;
let count = 0;
let delayControl: any = null;
export function startLoading() {
  clearTimeout(delayControl);
  if (count < 1) {
    loading = ElLoading.service();
  }
  count++;
}

export function stopLoading() {
  count--;
  clearTimeout(delayControl);
  delayControl = setTimeout(() => {
    if (count < 1 && loading) {
      loading.close();
      loading = null;
    }
  }, 300);
}
