<template>
    <div class="app-container">
        <el-form :inline="true" :model="fortune" class="demo-form-inline">
            <el-form-item label="运势时间">
                <el-date-picker
                    v-model="fortune.startTime"
                    type="date"
                    placeholder="选择开始日期">
                </el-date-picker>
                <el-date-picker
                    v-model="fortune.endTime"
                    type="date"
                    placeholder="选择结束日期">
                </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="getFortuneList()">查询</el-button>
                <el-button type="default" icon="el-icon-refresh" @click="resetData()">重置</el-button>
                <el-button type="success" @click="dialogFormVisible = true">添加运势</el-button>
            </el-form-item>
        </el-form>

        <div class="block" style="padding-top: 20px">
            <el-timeline v-for="item in this.fortuneList" :key="item.id">
                <el-timeline-item 
                    :timestamp="formatDate(item.fortuneDate)" 
                    placement="top"
                    type="primary">
                        <el-card
                            shadow="hover"
                        >
                            {{ item.fortuneContent }}
                        </el-card>
                </el-timeline-item>
            </el-timeline>
        </div>

        <el-dialog title="双子座运势" :visible.sync="dialogFormVisible">
            <el-form :model="fortune">
                <el-form-item label="运势日期" :label-width="formLabelWidth">
                    <el-date-picker
                        v-model="fortune.fortuneDate"
                        type="date"
                        placeholder="选择日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="运势内容" :label-width="formLabelWidth">
                    <el-input
                        type="textarea"
                        :rows="6"
                        placeholder="请输入运势"
                        v-model="fortune.fortuneContent">
                    </el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = false">取 消</el-button>
                <el-button type="primary" @click="addFortune()">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import fortuneApi from '@/api/fortune'
    export default {
        data() {
            return {
                fortune: {},
                dialogFormVisible: false,
                fortuneList: []
            }
        },
        created(){
            this.getFortuneList()
        },
        methods: {
            resetData(){
                this.getFortuneList(),
                this.fortune = {}
            },
            getFortuneList(){ 
                fortuneApi.getFortuneList(this.fortune).then(res => {
                    this.fortuneList = res.data.fortuneList
                })
            },
            addFortune(){
                fortuneApi.addFortune(this.fortune).then(res => {
                    this.$message({
                        type: 'success',
                        message: '添加成功！'
                    })
                    this.dialogFormVisible = false
                    this.getFortuneList()
                    this.fortune = {}
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
        }
    }
</script>