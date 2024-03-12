/*给Vue实例添加原型属性，所有的Vue实例都拥有了$http属性*/
Vue.prototype.$http = axios;
//axios.defaults.baseURL = "http://api.pethome.com";//换成你自己后台访问根URL
axios.defaults.baseURL = "http://localhost:80";//换成你自己后台访问根URL
//这个参数，设置的是图片查看的全局服务器的地址
Vue.prototype.$fastBaseUrl = "http://47.95.117.210:8888";


/*axios前置拦截器， 只要你发送axios之前，都先执行此箭头函数*/
axios.interceptors.request.use(config => {
    //携带token
    let token = sessionStorage.getItem("token");
    //alert(token)
    if (token) {
        //添加请求头信息
        config.headers['token'] = token;
    }
    return config;
}, error => {
    Promise.reject(error);
});

// 响应拦截器
axios.interceptors.response.use(res => {

    // 请求成功对响应数据做处理
    console.log(location.href)
    if(res && res.data && res.data.code){
        //如果返回的是未登录的1001状态码
        if (res.data.code === "10086"){

            //跳转到登录界面
            location.href = "/login.html";
            return;
        }
    }

    // 该返回的数据则是axios.then(res)中接收的数据
    return res
}, err => {
    // 在请求错误时要做的事儿

    // 该返回的数据则是axios.catch(err)中接收的数据
    return Promise.reject(err)
})



/**
 * js动态获取?后面的参数，并且封装成一个json对象
 * @returns {Object}
 * ?openid=oa9wA0wv4tE-3T_Sz6Pn9-KEyyzo
 */
function getParam(){
    let href = location.href;
    console.log(href)
    var url=location.search;
    console.log(url) //?openid=oa9wA0wv4tE-3T_Sz6Pn9-KEyyzo
    var param = new Object();
    if(url.indexOf("?")!=-1){
        var str = url.substr(1)
        console.log(str) //openid=oa9wA0wv4tE-3T_Sz6Pn9-KEyyzo
        strs = str.split("&");
        console.log(strs);// Array : ["openid=oa9wA0wv4tE-3T_Sz6Pn9-KEyyzo"]

        for(var i=0;i<strs.length;i++){
            let kvs = strs[i].split("=");
            console.log(kvs) // [ "openid", "oa9wA0wv4tE-3T_Sz6Pn9-KEyyzo" ]
            param[kvs[0]]=unescape(kvs[1]);
        }
    }
    console.log(param)// 对象 {"openid":"oa9wA0wv4tE-3T_Sz6Pn9-KEyyzo"}
    return param;
}