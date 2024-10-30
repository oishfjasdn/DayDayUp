import { createRouter, createWebHistory } from 'vue-router'
import Home from '@/views/Home.vue'
import Login from '@/views/Login.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: Login
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: { requiresAuth: true }
    }
  ]
})
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('token');
  if (to.matched.some(record => record.meta.requiresAuth)) {
    // 如果目标路由需要认证并且没有token
    if (!token) {
      // 重定向到登录页面
      next({
        path: '/',
      });
    } else {
      // 如果有token，允许访问
      next();
    }
  } else {
    // 不需要认证的路由直接放行
    next();
  }
});

export default router
