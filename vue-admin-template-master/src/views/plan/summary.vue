<template>
    <div class="app-container">
        <el-row>
          <el-col :span="12">
            <el-statistic ref="statistic" :value="this.endDays" title="今年还剩" suffix="天"></el-statistic>
          </el-col>
          <el-col :span="8">
            <el-statistic ref="statistic" :value="this.totalDays" title="今年一共自律" suffix="天"></el-statistic>
          </el-col>
        </el-row>
        <el-divider></el-divider>
        <el-form :inline="true" :model="record" class="demo-form-inline">
            <el-form-item label="选择记录日期起始">
                <el-date-picker
                    v-model="record.startTime"
                    type="date"
                    placeholder="选择开始日期">
                </el-date-picker>
                <el-date-picker
                    v-model="record.endTime"
                    type="date"
                    placeholder="选择结束日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="getRecordList()">查询</el-button>
                <el-button type="default" icon="el-icon-refresh"  @click="resetData()">重置</el-button>
                <el-button type="warning" icon="el-icon-circle-plus-outline" @click="dialogFormVisible=true">添加</el-button>
            </el-form-item>
        </el-form>
        <el-table
            :data="recordList"
            style="width: 100%">
                <el-table-column
                    label="记录日期"
                    width="180"
                    align="center">
                    <template slot-scope="scope">
                        {{ formatDate(scope.row.recordDate) }}
                    </template>
                </el-table-column>
                <el-table-column
                    label="步数"
                    width="120"
                    align="center">
                    <template slot-scope="scope">
                        {{ scope.row.steps }}
                    </template>
                </el-table-column>
                <el-table-column
                    label="天气"
                    width="120"
                    align="center">
                    <template slot-scope="scope">
                        {{ scope.row.weather }}
                    </template>
                </el-table-column>
                <el-table-column
                    label="心情"
                    width="120"
                    align="center">
                    <template slot-scope="scope">
                        {{ scope.row.mood }}
                    </template>
                </el-table-column>
                <el-table-column width="200px" label="计划完成情况" align="center">
                    <template slot-scope="scope">
                        <el-progress 
                            type="circle" 
                            :percentage="scope.row.planProcess"
                            width="80"></el-progress>
                    </template>
                </el-table-column>
                <el-table-column width="300px" label="操作" align="center">
                    <template slot-scope="scope">
                        <el-button
                            size="mini"
                            type="primary"
                            @click="findRecordById(scope.row.id)">
                            查看
                        </el-button>
                        <el-button 
                            size="mini"
                            type="warning"
                            @click="findRecordToChangeScore(scope.row.id)">
                            评分
                        </el-button>
                        <el-button 
                            size="mini"
                            type="info"
                            @click="syncPlan(scope.row.id)">
                            同步
                        </el-button>
                        <el-button 
                            size="mini"
                            type="danger"
                            @click="deleteRecord(scope.row.id)">
                            删除
                        </el-button>
                    </template>
                </el-table-column>
        </el-table>

        <el-dialog title="添加今日总结" :visible.sync="dialogFormVisible">
            <el-form :inline="true" :model="record">
                <el-form-item label="今日步数">
                    <el-input v-model="record.steps" placeholder="请输入今日步数"></el-input>
                </el-form-item>
                <el-form-item label="今日天气">
                    <el-select v-model="record.weather" placeholder="请选择今日天气">
                        <el-option label="晴天" :value="0"></el-option>
                        <el-option label="阴天" :value="1"></el-option>
                        <el-option label="多云" :value="2"></el-option>
                        <el-option label="雨天" :value="3"></el-option>
                        <el-option label="雪天" :value="4"></el-option>
                        <el-option label="霾天" :value="5"></el-option>
                        <el-option label="浮尘" :value="6"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="今日心情">
                    <el-select v-model="record.mood" placeholder="请选择今日的心情">
                        <el-option label="愉悦" :value="0"></el-option>
                        <el-option label="焦虑" :value="1"></el-option>
                        <el-option label="沮丧" :value="2"></el-option>
                        <el-option label="生气" :value="3"></el-option>
                    </el-select>
                </el-form-item>
                <br>
                <el-form-item label="今日日记">
                    <el-input
                        type="textarea"
                        :rows="8"
                        show-word-limit
                        maxlength="500"
                        resize="both"
                        placeholder="请输入今日总结"
                        v-model="record.note"
                    >
                    </el-input>
                </el-form-item>
                <br>
                <el-button @click="cancel()">取 消</el-button>
                <el-button type="primary" @click="addRecord()">确 定</el-button>
            </el-form>
        </el-dialog>

        <el-drawer
            :visible.sync="drawer"
            direction="rtl"
            size="50%"
            :title="formatDate(this.record.recordDate) + '日记录'">
            <el-table
                :data="planList"
                border
                style="width: 100%"
                >
                <el-table-column
                    prop="planName"
                    label="计划名称"
                    width="200"
                    align="center">
                </el-table-column>
                <el-table-column
                    prop="planTime"
                    label="计划时间"
                    width="200"
                    align="center">
                </el-table-column>
                <el-table-column
                    prop="planStatus"
                    label="计划状态"
                    width="200"
                    align="center">
                </el-table-column>
            </el-table>

            <div style="padding: 30px 30px">             
                <el-descriptions title="今日收支情况" direction="vertical" :column="2" border>
                    <el-descriptions-item label="收入">{{ this.recordAccountVo.income }}</el-descriptions-item>
                    <el-descriptions-item label="支出">{{ this.recordAccountVo.expense }}</el-descriptions-item>
                </el-descriptions>
            </div>

            <div style="width: 150px; height: 50px; margin: 0 auto;">
                <p>今日评分</p>
                <el-rate
                    v-model="this.record.score"
                    disabled
                    show-score
                    text-color="#ff9900"
                    score-template="{value}"
                    allow-half>
                </el-rate>
            </div>

            <div style="padding: 30px 70px;">
                <el-card class="box-card">
                    <div slot="header" class="clearfix">
                        <span>今日日记</span>
                    </div>
                    <div class="text item">
                        {{ record.note }}
                    </div>
                </el-card>
            </div>
        </el-drawer>

        <div class="block" style="width: 500px;margin: 0 auto;padding-top: 20px">
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
    import recordApi from '@/api/record'
    export default {
        data() {
            return {
                recordList: [],
                record: {
                    id: 0,
                    recordDate: '',
                    steps: 0,
                    weather: '',
                    mood: '',
                    planProcess: 0.0,
                    scope: 0
                },
                drawer: false,
                planList: [],
                recordAccountVo: {
                    recordDate: null,
                    income: 0,
                    expense: 0
                },
                endDays: 0,
                totalDays: 0,
                dialogFormVisible: false,
                total: 0,
                pageNum: 1,
                pageSize: 10
            }
        },
        created(){
            this.getRecordList()
            this.getTotalDays()
            this.resetData()
        },
        methods: {
            getRecordList(){
                recordApi.getRecordList(this.record, this.pageNum, this.pageSize).then(res => {
                    this.recordList = res.data.recordList
                    this.total = res.data.total
                    this.recordList.map((item => {
                        if(item.weather === '0'){
                            item.weather = '晴天'
                        }else if(item.weather === '1'){
                            item.weather = '阴天'
                        }else if(item.weather === '2'){
                            item.weather = '多云'
                        }else if(item.weather === '3'){
                            item.weather = '雨天'
                        }else if(item.weather === '4'){
                            item.weather = '雪天'
                        }else if(item.weather === '5'){
                            item.weather = '霾天'
                        }else if(item.weather === '6'){
                            item.weather = '浮尘'
                        }
                        if(item.mood === '0'){
                            item.mood = '愉悦'
                        }else if(item.mood === '1'){
                            item.mood = '焦虑'
                        }else if(item.mood === '2'){
                            item.mood = '沮丧'
                        }else if(item.mood === '3'){
                            item.mood = '生气'
                        }
                    }))
                })
            },
            formatDate(date){
                const newDate = new Date(date)
                const year = newDate.getFullYear()
                let month = newDate.getMonth() + 1
                month = month < 10 ? ('0' + month) : month
                let day = newDate.getDate()
                day = day < 10 ? ('0' + day) : day
                return year + '-' + month + '-' + day
            },
            resetData(){
                this.record = {}
                this.getRecordList()
                this.getTotalDays()
            },
            findRecordById(id){
                this.drawer = true
                recordApi.getRecordById(id).then(res => {
                    this.record = res.data.record
                    this.planList = res.data.planList
                    this.planList.map((item => {
                        if(item.planStatus === '0'){
                            item.planStatus = '未完成'
                        }else if(item.planStatus === '1'){
                            item.planStatus = '已完成'
                        }
                    }))
                    recordApi.getIncomeAndExpenseToday(this.record).then(res =>{
                        this.recordAccountVo = res.data.recordAccountVo
                    })
                })
            },
            findRecordToChangeScore(id){
                recordApi.getRecordById(id).then(res => {
                    this.record = res.data.record
                    recordApi.changeScore(this.record).then(res => {
                        this.$message({
                            type: 'success',
                            message: '评分成功'
                        })
                    })
                })
            },
            deleteRecord(id){
                recordApi.deleteRecord(id).then(res => {
                    this.$message({
                        type: 'success',
                        message: '删除成功'
                    })
                    this.getRecordList()
                })
            },
            getTotalDays(){
                recordApi.getTotalDaysCurrentYear().then(res => {
                    this.totalDays = res.data.totalDays
                    this.endDays = res.data.endDays
                })
            },
            addRecord(){
                recordApi.addRecord(this.record).then(res => {
                    this.$message({
                        type: 'success',
                        message: '添加成功！'
                    })
                    this.resetData()
                    this.dialogFormVisible = false
                })
            },
            cancel(){
                this.record = {},
                this.dialogFormVisible = false
            },
            syncPlan(id){
                recordApi.getRecordById(id).then(res => {
                    this.record = res.data.record
                    recordApi.syncPlanToRecord(this.record).then(res => {
                        this.$message({
                            type: 'success',
                            message: '同步成功！'
                        })
                        this.resetData()
                    })
                })
            },
            handleSizeChange(pageSize){
                this.pageSize = pageSize
                this.getRecordList()
            },
            handleCurrentChange(pageNum){
                this.pageNum = pageNum
                this.getRecordList()
            }
        }
    }
</script>

<style>
  .text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    width: 480px;
  }
</style>