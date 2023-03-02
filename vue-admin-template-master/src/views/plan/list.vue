<template>
    <div class="app-container">
        <div>
            <span>{{ this.text }}</span>
            <el-divider></el-divider>
        </div>
        <el-form :inline="true" :model="list" class="demo-form-inline">
            <el-form-item label="计划名称">
                <el-input v-model="plan.planName" placeholder="请输入计划名称"></el-input>
            </el-form-item>
            <el-form-item label="计划状态">
                <el-select v-model="plan.planStatus" placeholder="计划状态">
                <el-option label="未完成" value="0"></el-option>
                <el-option label="已完成" value="1"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="getPlanList()">查询</el-button>
                <el-button type="default" icon="el-icon-refresh" @click="resetData()">重置</el-button>
                <el-button type="warning" icon="el-icon-circle-plus-outline" @click="dialogFormVisible=true">添加</el-button>
            </el-form-item>
        </el-form>
        <el-dialog title="添加计划" :visible.sync="dialogFormVisible">
          <el-form :model="plan">
            <el-form-item label="计划名称" :label-width="formLabelWidth">
              <el-select v-model="plan.planName" placeholder="请选择计划">
                <el-option
                  v-for="item in weekPlanList"
                  :key="item.id"
                  :label="item.weekPlanName"
                  :value="item.weekPlanName">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="计划时间" :label-width="formLabelWidth">
              <el-time-picker
                is-range
                v-model="plan.rangeOfPlanTime"
                range-separator="至"
                start-placeholder="开始时间"
                end-placeholder="结束时间"
                placeholder="选择时间范围">
              </el-time-picker>
            </el-form-item>
            <el-form-item label="优先级" :label-width="formLabelWidth">
              <el-select v-model="plan.priority" placeholder="请选择计划优先级">
                <el-option label="极为重要" value="0"></el-option>
                <el-option label="重要" value="1"></el-option>
                <el-option label="一般" value="2"></el-option>
                <el-option label="不重要" value="3"></el-option>
              </el-select>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addStudyPlan()">确 定</el-button>
          </div>
        </el-dialog>
        <el-table
          :data="planList"
          style="width: 100%">
          <el-table-column
            label="计划名称"
            width="180"
            align="center">
            <template slot-scope="scope">
              <i class="el-icon-c-scale-to-original"></i>
              <span>{{ scope.row.planName }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="计划时间"
            width="180"
            align="center">
            <template slot-scope="scope">
              <i class="el-icon-alarm-clock"></i>
              <span>{{ scope.row.planTime }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="优先级"
            width="180"
            align="center">
            <template slot-scope="scope">
              <i class="el-icon-bell"></i>
              <span>{{ scope.row.priority }}</span>
            </template>
          </el-table-column>
          <el-table-column
            label="计划状态"
            width="180"
            align="center">
            <template slot-scope="scope">
              <i class="el-icon-s-flag"></i>
              <span>{{ (scope.row.planStatus==='0'?'未完成':'已完成') }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作" align="center">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="primary"
                @click="finishPlan(scope.row.id)"
                >完成</el-button>
              <el-button
                size="mini"
                type="success"
                @click="findPlanById(scope.row.id)"
                >查看</el-button>
              <el-button
                size="mini"
                @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button
                size="mini"
                type="danger"
                @click="deletePlan(scope.row.id)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-drawer
          title="计划完成情况"
          :visible.sync="drawerShow"
          direction="rtl"
          size="70%"
          :before-close="resetData"
          >
          <el-steps 
            space="500px" 
            :active="0" 
            align-center>
            <el-step title="进行中" :description="renderTime(plan.createTime)" status="process"></el-step>
            <el-step title="已完成" :description="renderTime(plan.finishTime)" :status="this.finishStatus"></el-step>
          </el-steps>
        </el-drawer>
    </div>
</template>

<script>
  import planApi from '@/api/plan'
  import weekApi from '@/api/week'
  export default {
    data() {
      return {
        dialogFormVisible: false,
        plan: {},
        planList: null,
        drawerShow: false,
        finishStatus: "wait",
        deleteDialogVisible: false,
        value1: 3,
        text: '',
        weekPlanList: []
      }
    },
    created(){
      this.getPlanList(),
      this.getRandomOne(),
      this.getWeekPlanList()
    },
    methods: {
      resetData(){
        this.plan = {},
        this.drawerShow = false,
        this.getPlanList(),
        this.getRandomOne()
      },
      addStudyPlan(){
        planApi.addPlan(this.plan).then(res => {
          this.$message({
              type: 'success',
              message: '添加成功！'
          })
          this.dialogFormVisible = false,
          this.resetData()
        })
      },
      getPlanList(){
        planApi.getPlanList(this.plan).then(res => {
          this.planList = res.data.planList
          this.planList.map((item)=>{
            if(item.priority === '0'){
              item.priority = '极为重要'
            }else if(item.priority === '1'){
              item.priority = '重要'
            }else if(item.priority === '2'){
              item.priority = '一般'
            }else {
              item.priority = '不重要'
            }
          })
        })
      },
      finishPlan(id){
        planApi.changePlanStatusOfFinished(id).then(res => {
          this.getPlanList()
        })
      },
      findPlanById(id){
        this.drawerShow = true
        planApi.findPlanById(id).then(res => {
          this.plan = res.data.plan
          if (this.plan.finishTime !== null){
            this.finishStatus = "success"
          }else{
            this.finishStatus = "wait"
          }
        })
      },
      renderTime(date) {
          var dateee = new Date(date).toJSON();
          return new Date(+new Date(dateee) + 8 * 3600 * 1000).toISOString().replace(/T/g, ' ').replace(/\.[\d]{3}Z/, '') 
      },
      deletePlan(id){
        planApi.deletePlanById(id).then(res => {
          this.getPlanList()
        })
      },
      getRandomOne(){
        planApi.getRandomOne().then(res => {
          this.text = res.data.text
        })
      },
      getWeekPlanList(){
        weekApi.getWeekPlanList().then(res => {
          this.weekPlanList = res.data.weekPlanList
        })
      }
    }
  }
</script>