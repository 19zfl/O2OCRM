<template>
    <section>
        <!--工具条-->
        <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
            <el-form :inline="true" :model="filters">
                <el-form-item>
                    <el-input v-model="filters.name" placeholder="部门名称"></el-input>
                </el-form-item>
                <el-form-item>
                    <!--
                        v-for="变量名 in 数据源"  -- 遍历
                        :label :展示的列表
                        :value :获取的值
                    -->
                    <el-select v-model="filters.managerId" clearable placeholder="请选择项目经理">
                        <el-option
                                v-for="item in managers"
                                :label="item.username"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item>
                    <!--
                        v-for="变量名 in 数据源"  -- 遍历
                        :label :展示的列表
                        :value :获取的值,传递到后台的
                    -->
                    <el-select v-model="filters.parentId" clearable placeholder="请选择父级部门">
                        <el-option
                                v-for="item in parentDepts"
                                :label="item.name"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" v-on:click="getdept">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="handleAdd">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>

        <!--列表-->
        <!--		:data :绑定的数据-->
        <!--		v-loading: 页面css样式等东西加载之后加载加载数据-->
        <!--		prop:对应的是:data绑定的对象的属性-->
        <!--		label :描述-->
        <el-table :data="dept" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
                  style="width: 100%;">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column type="index" width="60" label="编号">
            </el-table-column>
            <el-table-column prop="name" label="部门名称" width="120" sortable>
            </el-table-column>
            <el-table-column prop="state" label="状态" width="100" :formatter="formatSex" sortable>
                <template slot-scope="scope">
                    <el-button type="text" size="small"
                               :type="scope.row.state ==0?'danger':'primary'">
                        {{scope.row.state==0?"禁用":"启用"}}
                    </el-button>
                </template>
            </el-table-column>
            <el-table-column prop="sn" label="部门编号" width="100" sortable>
            </el-table-column>
            <el-table-column prop="managerName" label="部门经理" width="100" sortable>
            </el-table-column>
            <el-table-column prop="parentName" label="上级部门" width="100" sortable>
            </el-table-column>
            <el-table-column label="操作" width="150">
                <template slot-scope="scope">
                    <el-button size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!--分页条-->
        <el-col :span="24" class="toolbar">
            <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button>
            <!--			current-change:自带的事件,我当前页发生改变时,就会触发此事件-->
            <el-pagination layout="total, sizes, prev, pager, next, jumper"
                           :page-sizes="[5, 10, 50, 100]"
                           @current-change="handleCurrentChange"
                           @size-change="handleSizeChange"
                           :page-size="size"
                           :total="total"
                           style="float:right;">
            </el-pagination>
        </el-col>

        <!--新增界面-->
        <el-dialog title="操作" :visible.sync="addFormVisible" :close-on-click-modal="false">
            <el-form :model="addForm" label-width="80px" :rules="addFormRules" ref="addForm">
                <el-form-item label="部门名称" prop="name">
                    <el-input v-model="addForm.name" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门编号" prop="sn">
                    <el-input v-model="addForm.sn" auto-complete="off"></el-input>
                </el-form-item>
                <el-form-item label="部门状态" prop="state">
                    <el-switch
                            v-model="state"
                            active-text="启用"
                            inactive-text="禁用">
                    </el-switch>
                </el-form-item>
                <!--
                   v-for="变量名 in 数据源"  -- 遍历
                   :label :展示的列表
                   :value :获取的值,传递到后台的
                 -->
                <el-form-item label="部门经理">
                    <el-select v-model="addForm.managerId" clearable placeholder="请选择">
                        <el-option
                                v-for="item in employees"
                                :label="item.username"
                                :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="上级部门">
                    <!--:options="treeDepts" ：数据源
                    props：属性
                    v-model:前面没有冒号
                    -->
                    <el-cascader
                            v-model="addForm.parentIds"
                            :options="treeDepts"
                            :props="{ checkStrictly: true,value:'id',label:'name' }"
                            clearable></el-cascader>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click.native="addFormVisible = false">取消</el-button>
                <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
            </div>
        </el-dialog>
    </section>
</template>

<script>
    export default {
        data() {
            return {
                state: true,
                treeDepts: [],
                employees: [],
                managers: [],
                parentDepts: [],
                filters: {
                    name: '',
                    managerId: null,
                    parentId: null
                },
                dept: [],
                total: 0, // 分页总条数
                page: 1,  // 页码
                size: 10, // 每页条数
                listLoading: false,
                sels: [],//列表选中列
                addFormVisible: false,//新增界面是否显示
                addLoading: false,
                addFormRules: {
                    name: [
                        /*
                            required(必须): true,
                            trigger（触发条件）: 'blur（光标移开触发）'
                        */
                        {required: true, message: '请输入部门名称', trigger: 'blur'}
                    ],
                    sn: [
                        /*
                            required(必须): true
                            trigger（触发条件）: 'blur（光标移开触发）'：
                       */
                        {required: true, message: '请输入部门编号', trigger: 'blur'}
                    ]
                },
                //新增界面数据
                addForm: {
                    managerId: null,
                    name: '',
                    sn: '',
                    parentIds: []
                }
            }
        },
        methods: {
            //性别显示转换
            //row 当前行row = user  column当前列
            formatSex: function (row, column) {
                return row.state == 1 ? '正常' : row.state == 0 ? '禁用' : '未知';
            },
            //val:点第5页val就是5
            handleCurrentChange(val) {
                //var 就是当前页的数据
                this.page = val;
                this.getdept();
            },
            //val:选择每页多少条就是多少条
            handleSizeChange(val) {
                //var 就是当前页的数据
                this.size = val;
                this.getdept();
            },
            //获取部门列表
            getdept() {
                //封装的数据
                let para = {
                    //page 就是当前页
                    pageNum: this.page,
                    //size 就是每页条数
                    pageSize: this.size,
                    //这个是高级查询的条件
                    keyword: this.filters.name, //部门名称
                    managerId: this.filters.managerId,
                    parentId: this.filters.parentId
                };
                this.listLoading = true;
                //NProgress.start();
                this.$http.post("/system/dept/list", para).then((res) => {
                    console.log(res)
                    //取值
                    this.total = res.data.data.total;
                    this.dept = res.data.data.data;
                    //关闭进度条
                    this.listLoading = false;
                });
            },
            //删除
            handleDel: function (index, row) {
                this.$confirm('确认删除该记录吗?', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {id: row.id};

                    this.$http.delete("/dept/" + row.id).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getdept();
                    });
                }).catch(() => {

                });
            },
            //显示编辑界面
            handleEdit: function (index, row) {
                //显示编辑页面的模态框
                this.addFormVisible = true;
                //Object.assign 是拷贝对象 BeanUtil.copyof()
                this.state = !!row.state
                this.addForm = row;
                //"dirPath":"/3/8/10"
                var dirPath = row.dirPath;
                if (dirPath) {
                    //1.根据/进行分隔
                    var paths = dirPath.split("/"); //["","3","8","10"]
                    //2.截取数组
                    paths = paths.slice(1, paths.length - 1) //["3","8"]
                    //把数组变成数字数组
                    paths = paths.map(Number);
                    //3.赋值截取之后的数组
                    this.addForm.parentIds = paths;//[3,8]
                }
            },
            //显示新增界面
            handleAdd: function () {
                this.addFormVisible = true;
                this.addForm = {
                    name: '',
                    sn: ''

                };
            },
            //新增/修改
            addSubmit: function () {
                //$refs调用js 原生dom元素
                //validate
                this.$refs.addForm.validate((valid) => {
                    if (valid) {
                        this.$confirm('确认提交吗？', '提示', {}).then(() => {
                            //这里才是我们处理数据的地方
                            this.addLoading = true;
                            //Object.assign 复制对象的
                            this.addForm.state = this.state ? 1 : 0
                            //如果没有选择上级部门，清空parentId
                            /*if (!this.addForm.parentIds || this.addForm.parentIds.length === 0){
                                this.addForm.parentId = null;
                            }
*/
                            let para = Object.assign({}, this.addForm);
                            this.$http.post("/dept/saveOrUpdate", para).then((res) => {
                                this.addLoading = false;
                                //elementUi的提示弹框
                                this.$message({
                                    message: res.data.success ? '提交成功' : res.data.message,
                                    type: res.data.success ? 'success' : "error"
                                });
                                //重新set数据
                                this.$refs['addForm'].resetFields();
                                this.addFormVisible = false;
                                this.getdept();
                            });
                        });
                    }
                });
            },
            //复选勾选框的方法
            selsChange: function (sels) {
                this.sels = sels;
            },
            //批量删除
            batchRemove: function () {
                //map:抽取   item => item.id：把id都拿出来  组装成一个数组
                var ids = this.sels.map(item => item.id);
                this.$confirm('确认删除选中记录吗？', '提示', {
                    type: 'warning'
                }).then(() => {
                    this.listLoading = true;
                    //NProgress.start();
                    let para = {ids: ids};
                    this.$http.post("/dept/batchDelete", para).then((res) => {
                        this.listLoading = false;
                        //NProgress.done();
                        this.$message({
                            message: '删除成功',
                            type: 'success'
                        });
                        this.getdept();
                    });
                }).catch(() => {

                });
            },
            findManagers() {
                //发一个axios异步请求
                this.$http.get("/emp/findManagers").then(res => {
                    this.managers = res.data.data;
                })

            },
            findParentDepts() {
                //发一个axios异步请求
                this.$http.get("/dept/findParentDepts").then(res => {
                    this.parentDepts = res.data.data;
                })
            },
            getEmployees() {
                //发一个axios异步请求
                this.$http.get("/emp/findAll").then(res => {
                    console.log(res.data.data)
                    this.employees = res.data.data;
                })
            },
            treeDept() {
                //发一个axios异步请求
                this.$http.get("/dept/treeDept").then(res => {
                    this.treeDepts = res.data.data;
                })
            }

        },

        //就是页面已加载完成就会去触发这个mounted
        mounted() {
            //获取部门列表
            this.getdept();
            //查询员工经理们
            this.findManagers();
            //查询上级部门
            this.findParentDepts();
            //查询所有的员工
            this.getEmployees();
            //查询部门树形结构
            this.treeDept();
        }
    }

</script>

<style scoped>

</style>
