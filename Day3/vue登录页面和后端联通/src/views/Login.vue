<template>
    <div class="auth-container">
        <div v-if="isLogin" class="login-form">
            <h2>登录</h2>
            <form @submit.prevent="handleLogin">
                <div class="form-group">
                    <label for="username">用户名：</label>
                    <input type="text" id="username" v-model="loginForm.userName" required />
                </div>
                <div class="form-group">
                    <label for="password">密码：</label>
                    <input type="password" id="password" v-model="loginForm.password" required />
                </div>
                <button type="submit">登录</button>
                <button type="button" @click="toggleForm">去注册</button>
            </form>
        </div>

        <div v-else class="register-form">
            <h2>注册</h2>
            <form @submit.prevent="handleRegister">
                <div class="form-group">
                    <label for="registerUsername">用户名：</label>
                    <input type="text" id="registerUsername" v-model="registerForm.userName" required />
                </div>
                <div class="form-group">
                    <label for="registerPassword">密码：</label>
                    <input type="password" id="registerPassword" v-model="registerForm.password" required />
                </div>
                <button type="submit">注册</button>
                <button type="button" @click="toggleForm">返回登录</button>
            </form>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import router from '@/router';

// 定义响应式数据
const isLogin = ref(true);
const loginForm = ref({ userName: '', password: '' });
const registerForm = ref({ userName: '', password: '' });

// 切换表单类型
const toggleForm = () => {
    isLogin.value = !isLogin.value;
};

// 处理登录请求
const handleLogin = async () => {

    try {
        const response = await axios.post('http://localhost:8080/userlogin', loginForm.value);
        localStorage.setItem('token', response.data.data.token)
        router.push('/home')
    } catch (error) {
        alert("登陆失败，用户名或密码错误或用户不存在")
    }
};

// 处理注册请求
const handleRegister = async () => {
    try {
        const response = await axios.post('http://localhost:8080/useregist', registerForm.value);
        if (response.data.code == 400) {
            alert('用户已存在')
        }
        else {
            alert('注册成功，亲爱的用户'+registerForm.value.userName)
            localStorage.setItem('token', response.data.data.token)
            router.push('/home')
            // 可以在这里重定向到登录界面或者显示成功消息
        }
    } catch (error) {

        alert('注册出问题了，请检查一下')
    }
};
</script>

<style scoped>
.auth-container,
.login-form,
.register-form {
    max-width: 400px;
    margin: auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
}

.form-group {
    margin-bottom: 15px;
}

label {
    display: block;
    margin-bottom: 5px;
}

input {
    width: 100%;
    padding: 8px;
    box-sizing: border-box;
}

button {
    margin-right: 10px;
}
</style>