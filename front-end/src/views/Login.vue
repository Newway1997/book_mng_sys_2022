<template>
  <div class="login-container">
    <el-form :model="form" ref="formRef" label-width="100px" :rules="rules">
      <el-form-item label="账号" prop="username">
        <el-input
          placeholder="账号"
          name="username"
          v-model="form.username"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          placeholder="密码"
          name="password"
          v-model="form.password"
          show-password
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          value="login"
          @click="login"
          :loading="isLoading"
          >登录</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { ElMessage } from "element-plus";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const isLoading = ref(false);
const form = ref({
  username: "",
  password: "",
});

const rules = ref({
  username: [{ required: true, message: "请输入账号", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
});

const formRef = ref();
const router = useRouter();
const store = useUserStore();
const login = () => {
  formRef.value.validate((valid: boolean) => {
    if (valid) {
      isLoading.value = true;
      store
        .userLogin({
          username: form.value.username,
          password: form.value.password,
        })
        .then(() => {
          setTimeout(() => {
            router.push("/");
          }, 1000);
        })
        .finally(() => {
          isLoading.value = false;
        });
    } else {
      ElMessage.error("请输入账号和密码");
    }
  });
};
</script>
<style scoped>
.el-form {
  width: 360px;
  text-align: left;
}

.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
