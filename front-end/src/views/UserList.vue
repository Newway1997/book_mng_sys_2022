<template>
  <div>
    <el-table :data="users" style="width: 100%">
      <el-table-column prop="id" label="id"></el-table-column>
      <el-table-column prop="username" label="读者账号"></el-table-column>
      <el-table-column prop="name" label="读者姓名"></el-table-column>
      <el-table-column
        prop="sex"
        label="读者性别"
        :formatter="sexFormatter"
      ></el-table-column>
      <el-table-column prop="phone" label="读者电话"></el-table-column>
      <el-table-column prop="regdate" label="注册时间"></el-table-column>
      <el-table-column label="禁用用户">
        <template v-slot="scope">
          <el-button
            type="danger"
            value="return"
            @click="disableUser(scope.row)"
            v-if="scope.row.status === 1"
            >禁用</el-button
          >
          <el-button
            type="success"
            value="return"
            @click="enableUser(scope.row)"
            v-if="scope.row.status === 0"
            >启用</el-button
          >
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script lang="ts" setup>
import { onMounted, ref } from "vue";
import * as userApi from "@/api/user";
import type { User } from "@/domain/reader";
import { ElMessage } from "element-plus";
const users = ref<User[]>([]);

const getUsers = function () {
  userApi.getUsers({ curPage: 0, pageSize: 24 }).then((data) => {
    users.value = data;
  });
};
onMounted(() => {
  getUsers();
});

const sexFormatter = (row: any, col: any, value: number) => {
  return value === 1 ? "男" : "女";
};

const enableUser = function (user: User) {
  userApi
    .enableUser({
      id: user.id,
    })
    .then(() => {
      ElMessage.success("启用成功");
      getUsers();
    });
};

const disableUser = function (user: User) {
  userApi
    .disableUser({
      id: user.id,
    })
    .then(() => {
      ElMessage.success("禁用成功");
      getUsers();
    });
};
</script>

<style scoped></style>
