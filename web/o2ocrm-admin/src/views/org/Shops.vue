<script>
export default {
  data() {
    return {
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      shopList:[],
      loading: true,
    }
  },
  methods:{
    getAllShopInfo() {
      this.$http.get("/system/shop/all").then(res=>{
        this.loading = false
        this.$message({
          message: '资源请求成功！',
          type: 'success'
        });
        this.shopList = res.data.data
      }, err => {
        this.$message.error(err.message);
      })
    },
  },
  mounted(){
    this.getAllShopInfo()
  }
}
</script>

<template>
  <section>
    <el-table
        :data="shopList"
        style="width: 100%"
        highlight-current-row
        v-loading="loading">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column
          type="index"
          label="编号">
      </el-table-column>
      <el-table-column
          type="logo"
          label="头像"
          width="60px">
        <el-avatar size="large" :src="url"></el-avatar>
      </el-table-column>
      <el-table-column
          prop="name"
          label="姓名"
          sortable>
      </el-table-column>
      <el-table-column
          prop="tel"
          label="电话"
          sortable>
      </el-table-column>
      <el-table-column
          prop="registerTime"
          label="注册时间"
          sortable>
      </el-table-column>
      <el-table-column
          prop="state"
          label="状态"
          sortable>
        <template slot-scope="scope">{{scope.row.state}}</template>
      </el-table-column>
      <el-table-column
          prop="address"
          label="地址"
          sortable>
      </el-table-column>
      <el-table-column
          prop="adminId"
          label="店长"
          sortable>
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="220">
        <el-button-group>
          <el-button type="primary" icon="el-icon-edit"></el-button>
          <el-button type="primary" icon="el-icon-rank"></el-button>
          <el-button type="primary" icon="el-icon-delete"></el-button>
        </el-button-group>
      </el-table-column>
    </el-table>
  </section>
</template>