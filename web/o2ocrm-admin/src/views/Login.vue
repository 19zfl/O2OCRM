<template>
  <el-form :model="ruleForm2" :rules="rules2" ref="ruleForm2" label-position="left" label-width="0px" class="demo-ruleForm login-container">
    <h3 class="title">系统登录</h3>
    <el-form-item prop="account">
      <el-input type="text" v-model="ruleForm2.account" auto-complete="off" placeholder="账号"></el-input>
    </el-form-item>
    <el-form-item prop="checkPass">
      <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off" placeholder="密码"></el-input>
    </el-form-item>
    <el-checkbox v-model="checked" checked class="remember">记住密码</el-checkbox>
    <el-form-item style="width:100%;">
      <el-button type="primary" style="width:47%;" @click.native.prevent="handleSubmit2" :loading="logining">登录</el-button>
      <el-button type="success" style="width:47%;" @click.native.prevent="shopZhuce" :loading="logining">店铺入驻</el-button>
      <!--<el-button @click.native.prevent="handleReset2">重置</el-button>-->
    </el-form-item>
  </el-form>
</template>

<script>
  import { requestLogin } from '../api/api';
  //import NProgress from 'nprogress'
  export default {
    data() {
      return {
        logining: false,
        ruleForm2: {
          account: '13971733039',
          checkPass: '123456'
        },
        rules2: {
          account: [
            { required: true, message: '请输入账号', trigger: 'blur' },
            //{ validator: validaePass }
          ],
          checkPass: [
            { required: true, message: '请输入密码', trigger: 'blur' },
            //{ validator: validaePass2 }
          ]
        },
        checked: true
      };
    },
    methods: {
      handleReset2() {
        this.$refs.ruleForm2.resetFields();
      },
      shopZhuce(){
        //跳转到指定的路由url地址
        this.$router.push({ path: '/shopZhuCe' });
      },
      handleSubmit2(ev) {
        //跳转页面
      /*  this.$router.push({ path: '/echarts' });
        return;*/
        this.$refs.ruleForm2.validate((valid) => {
          if (valid) {
            // 加载动画
            this.logining = true;
            // 封装请求参数
            let loginParams = {
              username: this.ruleForm2.account,
              password: this.ruleForm2.checkPass
            };
            // 发送登录请求
            this.$http.post("/system/login",loginParams).then(res => {
              console.log(res) //打印看一下怎么取值
              // 关闭加载动画
              this.logining = false;
              //解构表达式
              let { message, code, success } = res.data;
              if (success) {
                //拿到对象
                let userInfo = res.data.data;
                sessionStorage.setItem('userInfo', JSON.stringify(userInfo));
                sessionStorage.setItem('token', userInfo.token);
                //跳转到首页面
                this.$router.push({ path: '/echarts' });
              } else {
                this.$message({
                  message: message,
                  type: 'error'
                });
              }
            });
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      }
    }
  }

</script>

<style lang="scss" scoped>
  .login-container {
    /*box-shadow: 0 0px 8px 0 rgba(0, 0, 0, 0.06), 0 1px 0px 0 rgba(0, 0, 0, 0.02);*/
    -webkit-border-radius: 5px;
    border-radius: 5px;
    -moz-border-radius: 5px;
    background-clip: padding-box;
    margin: 180px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
    .title {
      margin: 0px auto 40px auto;
      text-align: center;
      color: #505458;
    }
    .remember {
      margin: 0px 0px 35px 0px;
    }
  }
</style>
