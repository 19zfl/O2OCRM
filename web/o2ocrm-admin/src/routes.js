import Login from './views/Login.vue'
import NotFound from './views/404.vue'
import echarts from './views/charts/echarts.vue'
import Home from "./views/Home.vue";
import Department from "./views/org/Department.vue";
import Employee from "./views/org/Employee.vue";
import Shop from "./views/shop/index.vue"
import Shops from  "./views/org/Shops.vue"


let routes = [
    // {
    //     path: '/',
    //     component: Home,
    //     name: '首页',
    //     leaf: true,//只有一个节点
    //     iconCls: 'el-icon-s-home',
    //     children: [
    //         { path: '/echarts', component: echarts, name: '首页'}
    //     ]
    // },
    {
        path: '/',
        component: Home,
        name: '组织机构',
        // leaf: true,//只有一个节点
        iconCls: 'el-icon-s-home',
        children: [
            {path: '/dept', component: Department, name: '部门管理'},
            {path: '/emp', component: Employee, name: '员工管理'},
            {path: '/shop', component: Shops, name: '店铺管理'}
        ]
    },
    {
        //前端访问路径
        path: '/shopSign',
        //对应的组件或者叫模板
        component: Shop,
        //模板展示的名字
        name: 'Shop',
        //hidden 隐藏
        hidden: true
    },
    {
        //前端访问路径
        path: '/login',
        //对应的组件或者叫模板
        component: Login,
        //模板展示的名字
        name: 'Login',
        //hidden 隐藏
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;