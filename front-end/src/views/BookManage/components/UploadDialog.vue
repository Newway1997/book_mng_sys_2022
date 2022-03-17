<template>
  <el-dialog title="书籍" v-model="dialogFormVisible">
    <el-form
      :model="form"
      enctype="multipart/form-data"
      ref="formRef"
      :rules="rules"
    >
      <el-form-item label="ISBN" prop="isbn" :label-width="formLabelWidth">
        <el-input v-model="form.isbn" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item
        label="书籍名称"
        prop="bookName"
        :label-width="formLabelWidth"
      >
        <el-input v-model="form.bookName" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item
        label="类别"
        prop="categoryId"
        :label-width="formLabelWidth"
      >
        <el-select
          v-model="form.categoryId"
          name="categoryId"
          placeholder="请选择活动区域"
        >
          <el-option
            v-for="category in categorys"
            :label="category.categoryName"
            :value="category.id"
            :key="category.id"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="作者" prop="author" :label-width="formLabelWidth">
        <el-input v-model="form.author" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item
        label="出版社"
        prop="publisher"
        :label-width="formLabelWidth"
      >
        <el-input v-model="form.publisher" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item
        label="出版时间"
        prop="publishDate"
        :label-width="formLabelWidth"
      >
        <el-date-picker
          v-model="form.publishDate"
          type="date"
          placeholder="选择日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="单价" prop="unitPrice" :label-width="formLabelWidth">
        <el-input v-model="form.unitPrice" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="图片" prop="img" :label-width="formLabelWidth">
        <!-- <el-input type="file" v-model="form.imgUrl" @change="setImg($event)" autocomplete="off"></el-input> -->
        <input type="file" @change="setImg($event)" />
      </el-form-item>

      <el-form-item label="简介" prop="summary" :label-width="formLabelWidth">
        <el-input v-model="form.summary" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="数量" prop="allNum" :label-width="formLabelWidth">
        <el-input v-model="form.allNum" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="标签" prop="tags" :label-width="formLabelWidth">
        <el-input v-model="form.tags" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="upload">确 定</el-button>
      </div>
    </template>
  </el-dialog>
</template>
<script lang="ts" setup>
import type { Category } from "@/domain/book";
import { ElMessage, type FormInstance } from "element-plus";
import { computed, onMounted, ref, watch } from "vue";
import * as bookApi from "@/api/books";

const props = defineProps<{
  mode: 1 | 0;
  visible: boolean;
  isbn?: string;
}>();
const formLabelWidth = ref("120px");

const form = ref({
  isbn: "",
  categoryId: "",
  bookName: "",
  author: "",
  publisher: "",
  publishDate: "",
  unitPrice: "",
  img: "",
  summary: "",
  allNum: 0,
  tags: "",
});

const rules = {
  isbn: [{ required: true, message: "请输入ISBN", trigger: "blur" }],
  categoryId: [{ required: true, message: "请输入类别", trigger: "blur" }],
  bookName: [{ required: true, message: "请输入书名", trigger: "blur" }],
  author: [{ required: true, message: "请输入作者", trigger: "blur" }],
  publisher: [{ required: true, message: "请输入出版社", trigger: "blur" }],
  publishDate: [{ required: true, message: "请输入出版日期", trigger: "blur" }],
  unitPrice: [{ required: true, message: "请输入单价", trigger: "blur" }],
  img: [{ required: true, message: "请输入图片", trigger: "blur" }],
  summary: [{ required: true, message: "请输入简介", trigger: "blur" }],
  allNum: [{ required: true, message: "请输入数量", trigger: "blur" }],
};

const setImg = (event: Event) => {
  form.value.img = (event?.target as HTMLInputElement)
    ?.files?.[0] as any as string; // get input file object
};

const mode = ref<0 | 1>(0);
const formRef = ref<FormInstance>();
const originIsbn = ref("");
const emit = defineEmits<{
  (event: "update:visible", val: boolean): void;
  (event: "upload"): void;
}>();
const dialogFormVisible = computed({
  get() {
    return props.visible;
  },
  set(val: boolean) {
    emit("update:visible", val);
  },
});
watch(
  () => props.visible,
  () => {
    if (!props.visible) {
      return;
    }
    if (props.mode === 0) {
      onUpload();
    } else {
      if (props.isbn) {
        onUpdate(props.isbn);
      }
    }
  }
);
const onUpload = () => {
  originIsbn.value = "";
  rules.img[0].required = true;
  (Object.keys(form.value) as Array<keyof typeof form.value>).forEach((key) => {
    if (key === "allNum") {
      form.value.allNum = 0;
    } else {
      form.value[key] = "";
    }
  });
};
const onUpdate = (isbn: string) => {
  rules.img[0].required = false;
  originIsbn.value = isbn;
  bookApi.getBook({ isbn: originIsbn.value }).then((data) => {
    Object.assign(form.value, data);
  });
};

const upload = () => {
  formRef.value?.validate((valid: boolean) => {
    if (valid) {
      form.value.publishDate = new Date(
        form.value.publishDate
      ) as any as string;
      const formData = new FormData();
      formData.append("img", form.value.img);
      for (let key in form.value) {
        formData.append(
          key,
          form.value[key as keyof typeof form.value] as string
        );
      }

      let uploadMethod;
      if (mode.value === 0) {
        uploadMethod = bookApi.uploadBook;
      } else {
        uploadMethod = bookApi.updateBook;
        formData.append("originIsbn", originIsbn.value);
      }

      uploadMethod(formData, { "Content-Type": "multipart/form-data" }).then(
        () => {
          dialogFormVisible.value = false;
          ElMessage({
            message: mode.value === 0 ? "添加成功" : "修改成功",
            type: "success",
          });
          emit("upload");
        }
      );
    }
  });
};

const categorys = ref<Category[]>([]);
const getCategory = () => {
  bookApi.getCategory().then((data) => {
    categorys.value = data;
  });
};
onMounted(() => {
  getCategory();
});
</script>
<style lang=""></style>
