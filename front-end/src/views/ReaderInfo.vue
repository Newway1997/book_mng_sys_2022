<template>
  <div class="login-container">
    <div>
      <el-form ref="form" label-width="80px" :disabled="!updateMod">
        <el-form-item label="姓名" prop="name">
          <el-input
            placeholder="姓名"
            name="name"
            v-model="reader.name"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input
            placeholder="年龄"
            name="age"
            v-model="reader.age"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-select v-model="reader.sex" name="sex" placeholder="性别">
            <el-option label="男" :value="1"></el-option>
            <el-option label="女" :value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input
            placeholder="电话"
            name="phone"
            v-model="reader.phone"
            clearable
          ></el-input>
        </el-form-item>
      </el-form>
      <el-button
        type="primary"
        v-if="!updateMod"
        @click="updateMod = !updateMod"
      >
        修改
      </el-button>
      <div v-else>
        <el-button type="primary" @click="onSubmit">确定</el-button>
        <el-button @click="updateMod = !updateMod">取消</el-button>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { onMounted, ref } from "vue";
import * as userApi from "@/api/user";
import { ElMessage } from "element-plus";
import type { Reader } from "@/domain/reader";

const reader = ref<Reader | Record<string, never>>({});
const updateMod = ref(false);

const onSubmit = () => {
  userApi.updateReaderInfo(reader.value as Reader).then(() => {
    ElMessage.success("修改成功");
  });
  updateMod.value = !updateMod.value;
};


const getReaderInfo = () => {
  userApi.getReaderInfo().then((res) => {
    reader.value = res;
  });
};
onMounted(() => {
  getReaderInfo();
});
</script>

<style scoped>
.login-container {
  margin: 100px;
  display: flex;
  justify-content: center;
}
.el-select {
  display: block;
}
</style>
