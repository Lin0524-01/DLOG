<template>
    <div class="app-container">
        <div>
            <el-row>
                <el-col :span="12">
                    <el-statistic :value="days" title="与小嘟嘟在一起已经有" suffix="天"></el-statistic>
                </el-col>
                <el-col :span="12">
                    <el-statistic ref="statistic" format="M月D天 HH:mm:ss" :value="date" title="距离两周年还有：" time-indices > </el-statistic>
                </el-col>
            </el-row>
            
            <el-divider></el-divider>
            <el-button type="success" @click="dialogFormVisible = true">添加卡片描述</el-button>
            <el-dialog title="恋人卡片" :visible.sync="dialogFormVisible">
                <el-form :model="loverCard">
                    <el-form-item label="卡片标题" :label-width="formLabelWidth">
                        <el-input v-model="loverCard.cardTitle" autocomplete="off"></el-input>
                    </el-form-item>
                    <el-form-item label="卡片正文" :label-width="formLabelWidth">
                        <el-input 
                            type="textarea" 
                            v-model="loverCard.cardContent" 
                            autocomplete="off"
                            rows="6"></el-input>
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addLoverCard()">确 定</el-button>
                </div>
            </el-dialog>
            <div v-for="card in cardList" style="padding-top: 20px">
                <el-card class="box-card" shadow="hover" body-style="margin: 0 auto">
                    <div slot="header" class="clearfix">
                        <span>{{ card.cardTitle }}</span>
                    </div>
                    <div class="text item">
                        {{ card.cardContent }}
                    </div>
                </el-card>
            </div>
        </div>
    </div>
</template>

<script>
    import loveApi from '@/api/love'

    export default {
        data(){
            return{
                days: 0,
                loverCard: {},
                dialogFormVisible: false,
                cardList: [],
                date: Date.now() + (new Date('2023-10-03 00:00:00') - Date.now())
            }
        },
        created(){
            this.getLoveDay(),
            this.getCardList()
        },
        methods: {
            getLoveDay(){
                loveApi.getLoveDay().then(res => {
                    this.days = res.data.day
                })
            },
            addLoverCard(){
                loveApi.addLoverCard(this.loverCard).then(res => {
                    this.$message({
                        type: 'success',
                        message: '添加成功！'
                    })
                    this.dialogFormVisible = false
                    this.getCardList()
                })
            },
            getCardList(){
                loveApi.getCardList().then(res => {
                    this.cardList = res.data.cardList
                })
            }
        }
    }
</script>

<style>
  .box-card {
    width: 100%;
  }

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
</style>