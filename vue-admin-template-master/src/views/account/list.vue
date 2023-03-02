<template>
    <div class="app-container">
        <div>
            <el-row>
                <el-col :span="8">
                    <el-statistic group-separator="," :precision="2" :value="this.balance" :title="title"></el-statistic>
                </el-col>
                <el-col :span="8">
                    <el-statistic group-separator="," :precision="2" :value="this.income" :title="title1"></el-statistic>
                </el-col>
                <el-col :span="8">
                    <el-statistic group-separator="," :precision="2" :value="this.expense" :title="title2"></el-statistic>
                </el-col>
            </el-row>
        </div>
        <el-divider></el-divider>
        <el-form :inline="true" :model="account" class="demo-form-inline">
            <el-form-item label="收支类型">
                <el-select v-model="account.type" clearable placeholder="收支类型">
                    <el-option :value="0" label="支出"></el-option>
                    <el-option :value="1" label="收入"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="添加时间">
                <el-date-picker
                    v-model="account.startTime"
                    type="date"
                    placeholder="选择开始日期"
                    value-format="yyyy-MM-dd"
                />
                <el-date-picker
                    v-model="account.endTime"
                    type="date"
                    placeholder="选择结束日期"
                    value-format="yyyy-MM-dd"
                />
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="getAccountList()">查询</el-button>
                <el-button type="default" icon="el-icon-refresh"  @click="resetData()">重置</el-button>
                <el-button type="warning" icon="el-icon-circle-plus-outline" @click="dialogFormVisible=true">添加</el-button>
            </el-form-item>
        </el-form>
        <el-dialog title="收支情况" :visible.sync="dialogFormVisible">
            <el-form :model="account">
                <el-form-item label="金额" :label-width="formLabelWidth">
                    <el-input-number v-model="account.money" :precision="2" :step="0.01" :min="0"></el-input-number>
                </el-form-item>
                <el-form-item label="类型" :label-width="formLabelWidth">
                <el-select v-model="account.type" placeholder="请选择类型">
                    <el-option label="收入" value="1"></el-option>
                    <el-option label="支出" value="0"></el-option>
                </el-select>
                </el-form-item>
                <el-form-item label="说明" :label-width="formLabelWidth"> 
                    <el-input
                        placeholder="请输入收支说明"
                        v-model="account.remark"
                        clearable>
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="cancel()">取 消</el-button>
                <el-button type="primary" @click="addAccount()">确 定</el-button>
            </div>
            </el-dialog>
        <el-table
            :data="accountList"
            style="width: 100%">
            <el-table-column
                label="金额"
                width="180"
                align="center">
                <template slot-scope="scope">
                    <i class="el-icon-coin"></i>
                    <span>{{ scope.row.money }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="类型"
                width="120"
                align="center">
                <template slot-scope="scope">
                    <i class="el-icon-discount"></i>
                    <span>{{ (scope.row.type==='0'?'支出':'收入') }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="账户余额"
                width="180"
                align="center">
                <template slot-scope="scope">
                    <i class="el-icon-wallet"></i>
                    <span>{{ scope.row.total }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="说明"
                width="180"
                align="center">
                <template slot-scope="scope">
                    <i class="el-icon-chat-dot-round"></i>
                    <span>{{ scope.row.remark }}</span>
                </template>
            </el-table-column>
            <el-table-column
                label="添加时间"
                width="180"
                align="center">
                <template slot-scope="scope">
                    <i class="el-icon-time"></i>
                    <span>{{ renderTime(scope.row.createTime) }}</span>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                    <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <div class="block" style="width: 650px;margin: 0 auto;padding-top: 20px">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="pageNum"
                :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="this.total">
             </el-pagination>
        </div>
    </div>
</template>
<script>
  import accountApi from '@/api/account'

  export default {
    data() {
      return {
        account: {
            money: 0.00,
            total: 0.00
        },
        accountList: [],
        title: '账户余额',
        title1: '本月收入',
        title2: '本月消费',
        income: 0,
        expense: 0,
        balance: 0,
        dialogFormVisible: false,
        total: 0,
        pageNum: 1,
        pageSize: 10
      }
    },
    created(){
        this.getBalanceShow(),
        this.getAccountList(),
        this.getIndexShow()
    },
    methods: {
        resetData(){
            this.account = {}
            this.getAccountList()
        },
        getAccountList(){
            accountApi.getList(this.account, this.pageNum, this.pageSize).then(res => {
                this.accountList = res.data.accountList
                this.total = res.data.total
            })
        },
        getBalanceShow(){
            accountApi.getBalance().then(res => {
                this.balance = res.data.balance
            })
        },
        renderTime(date) {
            var dateee = new Date(date).toJSON();
            return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
        },
        addAccount() {
            accountApi.addAccount(this.account).then(res=>{
                this.$message({
                        type: 'success',
                        message: '添加成功！'
                })
                this.dialogFormVisible = false,
                this.resetData()
                this.getBalanceShow(),
                this.getIndexShow()
            })
        },
        cancel(){
            this.account = {},
            this.dialogFormVisible = false
        },
        getIndexShow(){
            accountApi.getIncomeAndExpensePerMonth().then(res => {
                this.income = res.data.income
                this.expense = res.data.expense
            })
        },
        handleEdit(){
            accountApi.editAccountLog().then(res => {
                
            })
        },
        handleSizeChange(pageSize){
            this.pageSize = pageSize
            this.getAccountList()
        },
        handleCurrentChange(pageNum){
            this.pageNum = pageNum
            this.getAccountList()
        }
    }
  }
</script>