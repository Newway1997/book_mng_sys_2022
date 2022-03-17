<template>
  <div class="update-pass-container">
    <el-form :model="ruleForm" ref="formRef" label-width="100px" :rules="rules">
      <el-form-item label="旧密码" prop="oldPass">
        <el-input
          type="password"
          placeholder="旧密码"
          name="oldPass"
          v-model="ruleForm.oldPass"
          show-password
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item label="新密码" prop="newPass">
        <el-input
          type="password"
          placeholder="密码"
          name="newPass"
          v-model="ruleForm.newPass"
          show-password
          clearable
        ></el-input>
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          value="update"
          @click="update"
          :loading="isLoading"
          >确认</el-button
        >
      </el-form-item>
    </el-form>
  </div>
</template>

<script lang="ts" setup>
import { ElMessage } from "element-plus";
import type { FormInstance } from "element-plus";
import { ref } from "vue";
import * as userApi from "@/api/user";
import { useRouter } from "vue-router";
import { useUserStore } from "@/stores/user";

const isLoading = ref(false);
const ruleForm = ref({
  oldPass: "",
  newPass: "",
});
const rules = {
  oldPass: [{ required: true, message: "请输入旧密码", trigger: "blur" }],
  newPass: [{ required: true, message: "请输入新密码", trigger: "blur" }],
};
const formRef = ref<FormInstance>();
const router = useRouter();
const store = useUserStore();
const update = () => {
  formRef.value?.validate((valid: boolean) => {
    if (valid) {
      isLoading.value = true;
      userApi
        .updatePass({
          oldPass: ruleForm.value.oldPass,
          newPass: ruleForm.value.newPass,
        })
        .then(async () => {
          ElMessage.success("修改成功");
          await store.userLogout(false);
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
      ElMessage.error("请输入旧密码和新密码");
    }
  });
};
</script>
<style scoped>
.el-form {
  width: 360px;
  text-align: left;
}

.update-pass-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
}
</style>
