<template>
    <div class="app-container">
        <el-button type="success" @click="dialogFormVisible = true">添加</el-button>

        <template>
            <el-table
                :data="weekList"
                stripe
                style="width: 100%">
                <el-table-column
                    prop="week"
                    label="周数"
                    align="center"
                >
                </el-table-column>
                <el-table-column
                    prop="startDate"
                    label="开始日期"
                    align="center"
                >
                </el-table-column>
                <el-table-column
                    prop="endDate"
                    label="结束日期"
                    align="center"
                >
                </el-table-column>
                <el-table-column
                    label="操作"
                    align="center">
                        <template slot-scope="scope">
                            <el-button type="primary" size="mini" @click="getWeekPlanListById(scope.row.id)">查看</el-button>
                        </template>
                </el-table-column>
            </el-table>
        </template>
        <el-dialog title="添加周计划" :visible.sync="dialogFormVisible">
            <el-form :model="weekPlan">
                <el-form-item label="计划名称" :label-width="formLabelWidth">
                    <el-input v-model="weekPlan.weekPlanName" clearable ></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addWeekPlan()">确 定</el-button>
            </div>
        </el-dialog>
        <el-drawer
            title="本周计划"
            :visible.sync="dialogTableVisible"
            direction="rtl"
            size="50%">
            <el-table :data="weekPlanList">
                <el-table-column property="weekPlanName" label="计划名称" align="center"></el-table-column>
                <el-table-column label="计划进度" align="center">
                    <template slot-scope="scope">
                        <el-progress 
                            :percentage="scope.row.progress"
                            color="#007175"
                            stroke-width="10"></el-progress>
                    </template>
                </el-table-column>
                <el-table-column
                    label="操作"
                    align="center">
                        <template slot-scope="scope">
                            <el-button type="danger" size="mini" @click="removeWeekPlanById(scope.row.id)">删除</el-button>
                        </template>
                </el-table-column>
            </el-table>
        </el-drawer>
    </div>
</template>

<script>
    import weekApi from '@/api/week'
    export default {
        data() {
            return {
                dialogFormVisible: false,
                weekPlan: {
                    id: '',
                    weekPlanName: '',
                    process: 0
                },
                weekList: [],
                weekPlanList: [],
                dialogTableVisible: false,
                percentage: 20
            }
        },
        created(){
            this.getWeekList()
        },
        methods: {
            addWeekPlan(){
                weekApi.addWeekPlan(this.weekPlan).then(res => {
                    this.$message({
                        type: 'success',
                        message: '添加成功'
                    })
                    this.dialogFormVisible = false
                    this.weekPlan = {}
                })
            },
            resetData(){
                this.weekPlan = {}
            },
            getWeekList(){
                weekApi.getWeekList().then(res => {
                    this.weekList = res.data.weekList
                    this.weekList.map(week => {
                        week.startDate = this.formatDate(week.startDate)
                        week.endDate = this.formatDate(week.endDate)
                    })
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
            getWeekPlanListById(id){
                weekApi.getWeekPlanListById(id).then(res => {
                    this.dialogTableVisible = true
                    this.weekPlanList = res.data.weekPlanList
                })
                this.dialogTableVisible = false
            },
            removeWeekPlanById(id){
                weekApi.deleteWeekPlanById(id).then(res => {
                    this.$message({
                        type: 'success',
                        message: '删除成功！'
                    })
                })
            }
        }
    }
</script>