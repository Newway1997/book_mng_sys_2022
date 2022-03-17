<template>
  <div class="register-container">
    <el-form :model="ruleForm" ref="formRef" label-width="100px" :rules="rules">
      <el-form-item label="账号" prop="username">
        <el-input
          placeholder="账号"
          name="username"
          v-model="ruleForm.username"
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input
          type="password"
          placeholder="密码"
          name="password"
          v-model="ruleForm.password"
          show-password
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          value="register"
          @click="register"
          :loading="isLoading"
          >注册</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import * as userApi from "@/api/user";
import { ElMessage } from "element-plus";
import { useRouter } from "vue-router";

const isLoading = ref(false);
const ruleForm = ref({
  username: "",
  password: "",
});
const rules = ref({
  username: [{ required: true, message: "请输入账号", trigger: "blur" }],
  password: [{ required: true, message: "请输入密码", trigger: "blur" }],
});
const formRef = ref();
const router = useRouter();

const register = () => {
  formRef.value.validate((valid: boolean) => {
    if (valid) {
      isLoading.value = true;
      userApi
        .register({
          username: ruleForm.value.username,
          password: ruleForm.value.password,
        })
        .then(() => {
          ElMessage.success("注册成功");
          setTimeout(() => {
            //跳转
            router.push({
              path: "/",
            });
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

.register-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
