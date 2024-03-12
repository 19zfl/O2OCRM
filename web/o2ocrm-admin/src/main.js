/**======================================================================================
 * 内容说明： 导入vue和elementui相关
 ======================================================================================*/
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
/**======================================================================================
 * 内容说明： 导入router路由相关
 ======================================================================================*/
import VueRouter from 'vue-router'
import store from './vuex/store'
import Vuex from 'vuex'
import routes from './routes'

/**======================================================================================
 * 内容说明： 导入axios相关
 ======================================================================================*/
import axios from 'axios'

/**======================================================================================
 * 内容说明： 使用相关组件
 ======================================================================================*/
Vue.use(ElementUI)
Vue.use(VueRouter)
Vue.use(Vuex)
/**======================================================================================
 * 内容说明： 配置axios的全局基本路径全局属性配置
 * 注意如果你springboot后端端口不是8080，要改
 ======================================================================================*/
axios.defaults.baseURL = 'http://localhost:8080/'
Vue.prototype.$http = axios
/**======================================================================================
 * 内容说明： 路由配置
 ======================================================================================*/
const router = new VueRouter({
    routes
})

//拦截器配置
//1-请求拦截器配置
//在你每次发请求之前帮你做的事情 ，请求头中携带token
/*
axios.interceptors.request.use(config => {
    // 设置请求头
    config.headers['token'] = sessionStorage.getItem("token");
    return config;
}, error => {
    return Promise.reject(error)
});
*/
// 响应拦截器
/*
axios.interceptors.response.use(res => {
    // 请求成功对响应数据做处理
    console.log(location.href)
    if (res && res.data && res.data.code) {
        //如果返回的是未登录的1001状态码
        if (res.data.code === "6666") {
            if (location.href.indexOf("login") === -1) {
                ElementUI.Message({
                    type: 'error',
                    message: res.data.message
                });
            }
            //跳转到登录页面
            router.push({path: '/login'});
            return Promise.reject(res.data.message);
        }
    }
    // 该返回的数据则是axios.then(res)中接收的数据
    return res
}, err => {
    // 在请求错误时要做的事儿
    // 该返回的数据则是axios.catch(err)中接收的数据
    return Promise.reject(err)
})
*/

/**======================================================================================
 * 内容说明： 路由前置拦截器，用作登录检查
 ======================================================================================*/
/*
router.beforeEach((to, from, next) => {
    //NProgress.start();
    if (to.path === '/login') {
        sessionStorage.removeItem('userInfo');
        sessionStorage.removeItem('token');
    }
    //从会话缓存中拿到token  //shopZhuCe
    let token = sessionStorage.getItem('token');
    //true     true
    if (!token && to.path !== '/login' && to.path !== '/shopZhuCe') {
        //跳转到登录页面
        next({path: '/login'})
    } else {
        //放行
        next()
    }
})
*/
/**======================================================================================
 * 内容说明： 创建Vue对象，挂载 #app元素 ， 见Vue.app
 ======================================================================================*/
new Vue({
    //el: '#app',
    //template: '<App/>',
    router,
    store,
    //components: { App }
    render: h => h(App)
}).$mount('#app')