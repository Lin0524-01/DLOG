<template>
    <div class="app-container">
        <!-- 选择器 用于选择赛季 -->
        <template>
            <span style="font-size: 16px;padding-right: 10px;">赛季切换</span>
            <el-select 
                v-model="season" 
                placeholder="请选择"
            >
                <el-option
                    v-for="item in seasonList"
                    :key="item.id"
                    :label="item.seasonName"
                    :value="item.id">
                </el-option>
            </el-select>
        </template>

        <!-- 走马灯  展示当前分区的球队名称和球队LOGO -->
        <el-collapse v-model="teamPartitionList">
            <el-collapse-item :title="teamPartitionList[0]" name="1" style="font-size: 14px;">
                <el-carousel height="200px">
                    <el-carousel-item v-for="item in teamInParition1" :key="item">
                        <!-- <h3 style="padding-left: 30px" class="small">{{ item.teamName }}</h3>
                        <div style="width: 100px;height: 100px; margin: 0 auto">
                            <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;">
                        </div> -->
                        <!--卡片填充走马灯 用于展示-->
                        <el-card class="box-card" style="width: 100%; height: 200px">
                            <span style="font-size: 18px">{{item.teamName}}</span>
                            <div class="text item" style="width: 100px;height: 100px; margin: 0 auto">
                                <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;"/>
                            </div>
                        </el-card>
                    </el-carousel-item>
                </el-carousel>
                <!--当前分区球队列表 展示球队城市和球队名称 以及按钮操作查看当前分区排行 球队数据雷达图 球员信息等-->
                <el-table
                    :data="teamInParition1"
                    style="width: 100%">
                        <el-table-column
                            prop="teamCity"
                            label="球队城市"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamName"
                            label="球队名称"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            prop="teamCoach"
                            label="球队教练"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            prop="teamRoom"
                            label="球馆名称"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            label="操作"
                            align="center">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" @click="getRanking(scope.row.id)">排行</el-button>
                                <el-button type="success" size="mini" @click="getTeamPlayerList(scope.row.id)">球员</el-button>
                            </template>
                        </el-table-column>
                </el-table>
            </el-collapse-item>
            <el-collapse-item :title="teamPartitionList[1]" name="2">
                <el-carousel height="200px">
                    <el-carousel-item v-for="item in teamInParition2" :key="item">
                        <!-- <h3 style="padding-left: 30px" class="small">{{ item.teamName }}</h3>
                        <div style="width: 100px;height: 100px; margin: 0 auto">
                            <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;">
                        </div> -->
                        <el-card class="box-card" style="width: 100%; height: 200px">
                            <span style="font-size: 18px">{{item.teamName}}</span>
                            <div class="text item" style="width: 100px;height: 100px; margin: 0 auto">
                                <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;"/>
                            </div>
                        </el-card>
                    </el-carousel-item>
                </el-carousel>
                <el-table
                    :data="teamInParition2"
                    style="width: 100%">
                        <el-table-column
                            prop="teamCity"
                            label="球队城市"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamName"
                            label="球队名称"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamCoach"
                            label="球队教练"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            prop="teamRoom"
                            label="球馆名称"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            label="操作"
                            align="center">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" @click="getRanking(scope.row.id)">排行</el-button>
                                <el-button type="success" size="mini" @click="getTeamPlayerList(scope.row.id)">球员</el-button>
                            </template>
                        </el-table-column>
                </el-table>
            </el-collapse-item>
            <el-collapse-item :title="teamPartitionList[2]" name="3">
                <el-carousel height="200px">
                    <el-carousel-item v-for="item in teamInParition3" :key="item">
                        <!-- <h3 style="padding-left: 30px" class="small">{{ item.teamName }}</h3>
                        <div style="width: 100px;height: 100px; margin: 0 auto">
                            <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;">
                        </div> -->
                        <el-card class="box-card" style="width: 100%; height: 200px">
                            <span style="font-size: 18px">{{item.teamName}}</span>
                            <div class="text item" style="width: 100px;height: 100px; margin: 0 auto">
                                <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;"/>
                            </div>
                        </el-card>
                    </el-carousel-item>
                </el-carousel>
                <el-table
                    :data="teamInParition3"
                    style="width: 100%">
                        <el-table-column
                            prop="teamCity"
                            label="球队城市"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamName"
                            label="球队名称"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamCoach"
                            label="球队教练"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            prop="teamRoom"
                            label="球馆名称"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            label="操作"
                            align="center">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" @click="getRanking(scope.row.id)">排行</el-button>
                                <el-button type="success" size="mini" @click="getTeamPlayerList(scope.row.id)">球员</el-button>
                            </template>
                        </el-table-column>
                </el-table>
            </el-collapse-item>
            <el-collapse-item :title="teamPartitionList[3]" name="4">
                <el-carousel height="200px">
                    <el-carousel-item v-for="item in teamInParition4" :key="item">
                        <!-- <h3 style="padding-left: 30px" class="small">{{ item.teamName }}</h3>
                        <div style="width: 100px;height: 100px; margin: 0 auto">
                            <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;">
                        </div> -->
                        <el-card class="box-card" style="width: 100%; height: 200px">
                            <span style="font-size: 18px">{{item.teamName}}</span>
                            <div class="text item" style="width: 100px;height: 100px; margin: 0 auto">
                                <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;"/>
                            </div>
                        </el-card>
                    </el-carousel-item>
                </el-carousel>
                <el-table
                    :data="teamInParition4"
                    style="width: 100%">
                        <el-table-column
                            prop="teamCity"
                            label="球队城市"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamName"
                            label="球队名称"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamCoach"
                            label="球队教练"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            prop="teamRoom"
                            label="球馆名称"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            label="操作"
                            align="center">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" @click="getRanking(scope.row.id)">排行</el-button>
                                <el-button type="success" size="mini" @click="getTeamPlayerList(scope.row.id)">球员</el-button>
                            </template>
                        </el-table-column>
                </el-table>
            </el-collapse-item>
            <el-collapse-item :title="teamPartitionList[4]" name="5">
                <el-carousel height="200px">
                    <el-carousel-item v-for="item in teamInParition5" :key="item">
                        <!-- <h3 style="padding-left: 30px" class="small">{{ item.teamName }}</h3>
                        <div style="width: 100px;height: 100px; margin: 0 auto">
                            <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;">
                        </div> -->
                        <el-card class="box-card" style="width: 100%; height: 200px">
                            <span style="font-size: 18px">{{item.teamName}}</span>
                            <div class="text item" style="width: 100px;height: 100px; margin: 0 auto">
                                <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;"/>
                            </div>
                        </el-card>
                    </el-carousel-item>
                </el-carousel>
                <el-table
                    :data="teamInParition5"
                    style="width: 100%">
                        <el-table-column
                            prop="teamCity"
                            label="球队城市"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamName"
                            label="球队名称"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamCoach"
                            label="球队教练"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            prop="teamRoom"
                            label="球馆名称"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            label="操作"
                            align="center">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" @click="getRanking(scope.row.id)">排行</el-button>
                                <el-button type="success" size="mini" @click="getTeamPlayerList(scope.row.id)">球员</el-button>
                            </template>
                        </el-table-column>
                </el-table>
            </el-collapse-item>
            <el-collapse-item :title="teamPartitionList[5]" name="6">
                <el-carousel height="200px">
                    <el-carousel-item v-for="item in teamInParition6" :key="item">
                        <!-- <h3 style="padding-left: 30px" class="small">{{ item.teamName }}</h3>
                        <div style="width: 100px;height: 100px; margin: 0 auto">
                            <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;">
                        </div> -->
                        <el-card class="box-card" style="width: 100%; height: 200px">
                            <span style="font-size: 18px">{{item.teamName}}</span>
                            <div class="text item" style="width: 100px;height: 100px; margin: 0 auto">
                                <img :src="item.teamLogo" alt="#" style="width:100px;height: 100px;"/>
                            </div>
                        </el-card>
                    </el-carousel-item>
                </el-carousel>
                <el-table
                    :data="teamInParition6"
                    style="width: 100%">
                        <el-table-column
                            prop="teamCity"
                            label="球队城市"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamName"
                            label="球队名称"
                            align="center"
                        >
                        </el-table-column>
                        <el-table-column
                            prop="teamCoach"
                            label="球队教练"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            prop="teamRoom"
                            label="球馆名称"
                            align="center"
                        ></el-table-column>
                        <el-table-column
                            label="操作"
                            align="center">
                            <template slot-scope="scope">
                                <el-button type="primary" size="mini" @click="getRanking(scope.row.id)">排行</el-button>
                                <el-button type="success" size="mini" @click="getTeamPlayerList(scope.row.id)">球员</el-button>
                            </template>
                        </el-table-column>
                </el-table>
            </el-collapse-item>
        </el-collapse>
        <el-drawer
            title="球员信息"
            :visible.sync="playerShow"
            direction="rtl"
            size="55%">
            <el-table
                :data="playerList"
                style="width: 100%">
                <el-table-column type="expand">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <div>
                                <img :src="props.row.playerAvatar" :alt="props.row.playerName">
                            </div>
                            <el-form-item label="身高">
                                <span>{{ props.row.playerHeight}}</span>
                            </el-form-item>
                            <el-form-item label="体重">
                                <span>{{ props.row.playerWeight}}</span>
                            </el-form-item>
                            <el-form-item label="生日">
                                <span>{{ props.row.playerBirth }}</span>
                            </el-form-item>
                            <el-form-item label="国籍">
                                <span>{{ props.row.playerCountry }}</span>
                            </el-form-item>
                            <el-form-item label="学校">
                                <span>{{ props.row.playerSchool }}</span>
                            </el-form-item>
                            <el-form-item label="选秀排名">
                                <span>{{ props.row.playerDraft }}</span>
                            </el-form-item>
                            <el-form-item label="赛季薪资">
                                <span>{{ (props.row.playerSalary===null?'':props.row.playerSalary + '万美元') }}</span>
                            </el-form-item>
                            <el-form-item label="合同">
                                <span>{{ props.row.playerContract }}</span>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <el-table-column
                    label="姓名"
                    prop="playerName">
                </el-table-column>
                <el-table-column
                    label="年龄"
                    prop="playerAge">
                </el-table-column>
                <el-table-column
                    label="号码"
                    prop="playerNumber">
                </el-table-column>
                <el-table-column
                    label="位置"
                    prop="playerRole">
                </el-table-column>
            </el-table>
        </el-drawer>
        <el-drawer
            :visible.sync="teamShow"
            direction="rtl"
            size="50%">
            <el-card class="box-card" style="width: 630px">
                <div slot="header" class="clearfix">
                    <div style="padding-bottom: 20px">
                        <span>{{teamData.teamName}}</span>
                    </div>
                   
                    <el-descriptions border direction="vertical" :column="6" :size="small">
                        <el-descriptions-item label="球队logo" labelStyle="text-align: center;width: 80px">
                            <img :src="teamData.teamLogo" alt="老鹰logo" style="width: 60px; height: 60px;">
                        </el-descriptions-item>
                        <el-descriptions-item label="球队城市" labelStyle="text-align: center">
                            <div style="text-align: center">{{teamData.teamCity}}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="分区排名" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.zone + '第' + teamData.zoneRanking}}</div> 
                        </el-descriptions-item>
                        <el-descriptions-item label="比赛场次" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.games }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="得分排行" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.scoreRanking }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="助攻排行" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.assistRanking }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="篮板排行" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.backboardRanking }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="失分排行" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.competitorScoreRanking }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="场均得分" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.scorePerGame }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="场均篮板" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.backboardPerGame }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="场均助攻" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.assistPerGame }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="场均抢断" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.stealPerGame }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="场均盖帽" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.blockPerGame }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="场均失误" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.mistakePerGame }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="场均犯规" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.foulPerGame }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="命中率" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.hitRate }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="三分命中率" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.threeHitRate }}</div>
                        </el-descriptions-item>
                        <el-descriptions-item label="罚球命中率" labelStyle="text-align: center">
                            <div style="text-align: center">{{ teamData.freeHitRate}}</div>
                        </el-descriptions-item>
                    </el-descriptions>
                </div>
            </el-card>
            <div id="radar" style="width: 500px;height: 500px;margin: 0 auto;padding-top: 20px;"></div>
        </el-drawer>
    </div>
</template>

<script>
    import teamApi from '@/api/team'
    import echarts from 'echarts'
    export default {
        data() {
            return {
                teamList: [],
                team: {
                    id: 0
                },
                teamPartitionList: [],
                teamInParition1: [],
                teamInParition2: [],
                teamInParition3: [],
                teamInParition4: [],
                teamInParition5: [],
                teamInParition6: [],
                teamData: {},
                teamShow: false,
                playerShow: false,
                playerList: [],
                seasonList: [],
                season: {
                    id: '',
                    seasonName: ''
                }
            }
        },
        created(){
            this.getTeamList(),
            this.getSeasonList()
        },
        mounted(){
            this.getRanking(id)
        },
        methods: {
            getTeamList(){
                teamApi.getTeamList(this.team).then(res => {
                    this.teamList = res.data.teamList
                    this.teamPartitionList = res.data.teamPartitionList

                    let flag = 0
                    // 遍历teamList，将同一分区的球队塞入一个数组里
                    this.teamList.map(item => {
                        flag += 1
                        if(flag <= 15){
                            if(flag % 3 === 1){
                                this.teamInParition1.push(item)
                            }else if(flag % 3 === 2){
                                this.teamInParition2.push(item)
                            }else if(flag % 3 === 0){
                                this.teamInParition3.push(item)
                            }
                        }else {
                            if(flag % 3 === 1){
                                this.teamInParition4.push(item)
                            }else if(flag % 3 === 2){
                                this.teamInParition5.push(item)
                            }else if(flag % 3 === 0){
                                this.teamInParition6.push(item)
                            }
                        }
                    })
                })
            },
            // getTeamSeason(id){
            //     this.teamShow = true
            //     teamApi.getTeamSeason(id).then(res => {
            //         this.teamData = res.data.teamData
            //         if(this.teamData.zone === '0'){
            //             this.teamData.zone = '西部'
            //         }else{
            //             this.teamData.zone = '东部'
            //         }
            //     })
            // },
            async getRanking(id){
                this.teamShow = true
                let myChart = this.$echarts.init(document.getElementById("radar"))

                let res = await teamApi.getTeamSeason(id)
                this.teamData = res.data.teamData
                if(this.teamData.zone === '0'){
                    this.teamData.zone = '西部'
                }else{
                    this.teamData.zone = '东部'
                }

                let option = {
                    title: {
                        text: this.teamData.teamName + '数据雷达图'
                    },
                    radar: {
                        center: ['50%', '50%'],
                        splitNumber: 2,
                        // shape: 'circle',
                        indicator: [
                            { name: '场均得分', max: 118.8 },
                            { name: '场均助攻', max: 29.1 },
                            { name: '场均篮板', max: 49.3 },
                            { name: '场均抢断', max: 9.3 },
                            { name: '场均盖帽', max: 7.0 }
                        ]
                    },
                    series: [
                        {
                            type: 'radar',
                            data: [
                                {
                                    value: [this.teamData.scorePerGame, this.teamData.assistPerGame, 
                                    this.teamData.backboardPerGame, this.teamData.stealPerGame, this.teamData.blockPerGame]
                                },
                            ]
                        }
                    ]
                };

                myChart.setOption(option)
            },
            getTeamPlayerList(id){
                this.playerShow = true
                teamApi.getTeamPlayerList(id).then(res => {
                    this.playerList = res.data.playerList
                })
                this.playerList = []
            },
            getSeasonList(){
                teamApi.getSeasonList().then(res => {
                    this.seasonList = res.data.seasonList
                })
            }
        }
    }
</script>

<style>
  .el-carousel__item:nth-child(2n) {
     background-color: #99a9bf;
  }
  
  .el-carousel__item:nth-child(2n+1) {
     background-color: #d3dce6;
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

  .box-card {
    width: 480px;
  }

  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
    padding-left: 50px;
  }
</style>