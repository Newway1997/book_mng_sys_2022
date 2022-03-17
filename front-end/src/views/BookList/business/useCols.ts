import { onBeforeUnmount, onMounted, ref } from "vue";

export default function useCols() {
  const cols = ref();

  const onResize = () => {
    const width = window.innerWidth;
    if (width < 425) {
      cols.value = 2;
    } else if (width < 768) {
      cols.value = 3;
    } else if (width < 1024) {
      cols.value = 4;
    } else {
      cols.value = 6;
    }
  };
  onMounted(() => {
    onResize();
    window.addEventListener("resize", onResize);
  });
  onBeforeUnmount(() => {
    window.removeEventListener("resize", onResize);
  });
  return cols;
}
