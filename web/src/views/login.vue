<template>
  <a-row class="login" type="flex" justify="center" align="middle">
    <a-col :span="8" class="login-main">
      <h1 class="login-title"><bank-two-tone />&nbsp;12306售票系统</h1>
      <a-form :model="loginForm" name="basic" autocomplete="off">
        <a-form-item
            label=""
            name="mobile"
            :rules="[{ required: true, message: '请输入手机号!' }]"
        >
          <a-input v-model:value="loginForm.mobile" placeholder="手机号" size="large"/>
        </a-form-item>

        <a-form-item
            label=""
            name="code"
            :rules="[{ required: true, message: '请输入验证码!' }]"
        >
          <a-input v-model:value="loginForm.code" placeholder="验证码" size="large">
            <template #addonAfter>
              <a-button type="link" @click="sendCode">获取验证码</a-button>
            </template>
          </a-input>
        </a-form-item>

        <a-form-item>
          <a-button type="primary" block size="large" @click="login">登录</a-button>
        </a-form-item>
      </a-form>
    </a-col>
  </a-row>
</template>

<script>
import { defineComponent, reactive } from 'vue';
import axios from 'axios';
import { notification } from 'ant-design-vue';
import { useRouter } from 'vue-router'
import store from "@/store";

export default defineComponent({
  name: "login-view",
  setup() {
    const router = useRouter();

    const loginForm = reactive({
      mobile: '18877776666',
      code: '',
    });

    const sendCode = () => {
      axios.post("/member/member/send-code", { mobile: loginForm.mobile })
          .then(response => {
            if (response.data.success) {
              notification.success({ description: '发送验证码成功！' });
              loginForm.code = "8888";
            } else {
              notification.error({ description: response.data.message });
            }
          });
    };

    const login = () => {
      axios.post("/member/member/login", loginForm)
          .then((response) => {
            if (response.data.success) {
              notification.success({ description: '登录成功！' });
              router.push("/welcome");
              store.commit("setMember", response.data.content);
            } else {
              notification.error({ description: response.data.message });
            }
          })
    };

    return {
      loginForm,
      sendCode,
      login
    };
  },
});
</script>

<style scoped>
.login {
  height: 100vh;
}

.login-main {
  padding: 40px;
  border-radius: 12px;
  background-color: #f7f7f7;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.login-title {
  text-align: center;
  font-size: 28px;
  font-weight: bold;
  margin-bottom: 24px;
  color: #1890ff;
}

a-button[type="link"] {
  padding: 0;
  margin-left: 8px;
}

a-input {
  margin-bottom: 16px;
}

a-button {
  margin-top: 12px;
}

.login-main h1 {
  font-size: 28px;
}
</style>
