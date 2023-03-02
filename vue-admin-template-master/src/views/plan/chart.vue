<template>
    <div class="app-container">
        <div id="line" style="width: 1000px;height: 400px; margin: 0 auto;"></div>
    </div>
</template>

<script>
    import echarts from 'echarts'
    import recordApi from '@/api/record'
    
    export default {
        data(){
            return{
            }
        },
        created(){
        },
        mounted(){
            this.drawLine()
        },
        methods: {
            async drawLine(){
                let myChart = this.$echarts.init(document.getElementById("line"))

                let res = await recordApi.getCurrentMonthPlanAchieveStatus()

                let option = {
                    title: {
                        text: '本月任务完成情况',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['今日计划总数', '今日计划完成数'],
                        left: 'left'
                    },
                    xAxis: {
                        type: 'category',
                        data: res.data.dateList
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '今日计划总数',
                            type: 'line',
                            data: res.data.planSummaryList,
                            smooth: true, //默认是false,判断折线连线是平滑的还是折线
                            itemStyle: {
                                normal: {
                                    color: "#c8161d", //改变折线点的颜色
                                    lineStyle: {
                                        color: "#108b96", //改变折线颜色
                                    },
                                },
                            },
                        },
                        {
                            name: '今日计划完成数',
                            type: 'line',
                            data: res.data.planAchieveList,
                            smooth: true,
                            itemStyle: {
                                normal: {
                                    color: "#8aabcc", //改变折线点的颜色
                                    lineStyle: {
                                        color: "#b83570", //改变折线颜色
                                    },
                                },
                            },
                        },
                    ]
                };
                myChart.setOption(option)
            }
        }
    }
</script>