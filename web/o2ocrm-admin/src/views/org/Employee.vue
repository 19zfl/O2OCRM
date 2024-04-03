<template>
  <section>
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input placeholder="员工名称" v-model="filters.name">
            <el-button slot="append" icon="el-icon-search" v-on:click="getAllEmpInfoListByPageList"></el-button>
          </el-input>
        </el-form-item>
        <el-form-item>
          <el-button @click="handleAdd" type="success" plain>新增</el-button>
          <el-button @click="getAllEmpInfoListByPageList" type="primary" plain>刷新页面</el-button>
        </el-form-item>
      </el-form>
    </el-col>
    <el-table
        v-loading="loading"
        :data="tableData"
        style="width: 100%"
        :row-class-name="tableRowClassName"
        @selection-change="handleSelectionChange"
        highlight-current-row>
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column
          type="index"
          label="编号">
      </el-table-column>
      <el-table-column
          prop="username"
          label="姓名"
          sortable>
      </el-table-column>
      <el-table-column
          prop="email"
          label="邮箱"
          sortable>
      </el-table-column>
      <el-table-column
          prop="phone"
          label="电话"
          sortable>
      </el-table-column>
      <el-table-column
          prop="age"
          label="年龄"
          sortable>
      </el-table-column>
      <el-table-column
          prop="state"
          label="状态"
          sortable>
        <template slot-scope="scope">{{scope.row.state==1?'待审核':scope.row.state==2?'审核通过':scope.row.state==3?'激活成功':scope.row.state==4?'审核驳回':scope.row.state==0?'账号异常':scope.row.state==5?'禁用':'未知'}}</template>
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="170">
        <template slot-scope="scope">
          <el-row>
            <!-- 点击查看详情 -->
            <el-popover
                placement="right"
                width="400"
                trigger="click">
              <el-form :model="gridData" label-width="auto">
                <el-form-item label="姓名">
                  <el-input type="text" v-model="gridData.username" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="邮箱">
                  <el-input type="text" v-model="gridData.email" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="电话">
                  <el-input type="text" v-model="gridData.phone" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="年龄">
                  <el-input type="text" v-model="gridData.age" disabled="disabled"></el-input>
                </el-form-item>
                <el-form-item label="状态">
                  <el-input type="text" v-model="gridData.state==1?'待审核':gridData.state==2?'审核通过':gridData.state==3?'激活成功':gridData.state==4?'审核驳回':gridData.state==0?'账号异常':gridData.state==5?'禁用':'未知'" disabled="disabled"></el-input>
                </el-form-item>
              </el-form>
              <el-button @click="handleClick(scope.row)" icon="el-icon-search" circle slot="reference"></el-button>
            </el-popover>
            &nbsp;&nbsp;
            <el-button @click="handleEdit(scope.row)" type="primary" icon="el-icon-edit" circle></el-button>
            <el-button @click="handleDelete(scope.row)" type="danger" icon="el-icon-delete" circle></el-button>
          </el-row>
        </template>
      </el-table-column>
    </el-table>
    <el-col :span="24" class="toolbar">
      <!-- 批量删除按钮 -->
      <el-button type="danger" @click="batchRemove">批量删除</el-button>
      <!-- 分页条 -->
      <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :page-size="size"
          :page-sizes="[5, 10, 20, 50]"
          layout="total, sizes, prev, pager, next, jumper"
          :total=total
          style="float: right">
      </el-pagination>
    </el-col>
    <!-- 新增编辑的dialog模态框 -->
    <el-dialog
        title="操作"
        :visible.sync="dialogVisible"
        :close-on-click-modal="true"
        width="30%">
      <el-form :model="addData" label-width="auto">
        <el-form-item label="姓名">
          <el-input type="text" v-model="addData.username" ></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input type="text" v-model="addData.email"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input type="text" v-model="addData.phone"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input type="text" v-model="addData.age"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="addData.state" placeholder="请选择">
            <el-option
                v-for="item in selectState"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="editEmpInfo">确 定</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],  // 表格数据集合
      loading: true,
      total: 0,   // 总条数
      pageSize: 10,  // 页码
      pageNum: 1,   // 每页条数
      selectedIds: [], // 批量删除的ids组数
      gridData: [], // 查看某行数据的集合
      dialogVisible: false, // 新增编辑的dialog模态框弹出开关
      addData: [], // 新增编辑参数
      selectState:[   // 编辑模态框下拉菜单
        {value: 1, label: '待审核'},
        {value: 2, label: '审核通过'},
        {value: 3, label: '激活成功'},
        {value: 4, label: '审核驳回'},
        {value: 0, label: '账号异常'},
        {value: 5, label: '禁用'}
      ],
      filters: {  // 高级查询参数
        name: ''
      },
    }
  },
  methods: {
    /* 给表格着色 */
    tableRowClassName({row, rowIndex}) {
      if (rowIndex %2 != 0) {
        return 'success-row'
      } else {
        return 'warning-row'
      }
    },
    /* 修改页码 */
    handleSizeChange(val) {
      this.pageSize = val
      this.getAllEmpInfoListByPageList()
    },
    /* 修改每页条数 */
    handleCurrentChange(val) {
      this.pageNum = val
      this.getAllEmpInfoListByPageList()
    },
    /* 批量删除多选框 */
    handleSelectionChange(val) {
      this.selectedIds = val
    },
    /* 查看模态框 */
    handleClick(row) {
      this.gridData = row
    },
    /* 编辑模态框 */
    handleEdit(row) {
      this.dialogVisible = true
      this.addData = row
    },
    handleAdd() {
      this.dialogVisible = true
      this.addData = Object.assign({}, {})
    },
    /* 获取员工所有数据 */
    getAllEmpInfoListByPageList() {
      this.loading = true
      let param = {
        pageNum: this.pageNum,
        pageSize: this.pageSize,
        name: this.filters.name
      }
      this.$http.post("/system/emp/list", param).then(res => {
        this.tableData = res.data.data.list
        this.total = res.data.data.total
        this.loading = false
      })
    },
    /* 删除 */
    handleDelete(row) {
      /* 确认进行操作，避免误点击 */
      this.$confirm('此操作将永久删除该条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.delete("/system/emp/" + row.id).then(res => {
          this.getAllEmpInfoListByPageList()
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    /* 批量删除 */
    batchRemove() {
      let param = {ids:[]}
      for (let i = 0; i <= this.selectedIds.length -1; i++) {
        param.ids.push(this.selectedIds[i].id)
      }
      /* 确认进行操作，避免误点击 */
      this.$confirm('此操作将永久删除这多条数据, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$http.post("/system/emp/del/batch/", param).then(res => {
          this.getAllEmpInfoListByPageList()
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除！'
        });
      });
    },
    /* 新增和编辑 */
    editEmpInfo() {
      this.$http.post("/system/emp/edit", this.addData).then(res => {
        this.dialogVisible = false
        this.getAllEmpInfoListByPageList()
        this.$message({
          type: 'success',
          message: '操作成功!'
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作！'
        });
      })
    },
  },
  /* 钩子函数 */
  mounted() {
    /* 获取员工所有数据 */
    this.getAllEmpInfoListByPageList();
  }
}
</script>

<style>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>