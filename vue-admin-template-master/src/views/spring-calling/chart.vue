<template>
    <div class="app-container">
        <div id="pie" style="width: 800px;height: 600px; margin: 0 auto"></div>
    </div>
</template>

<script>
    import echarts from 'echarts'
    import springApi from '@/api/spring'
    
    export default {
        data(){
            return{
            }
        },
        created(){
        },
        mounted(){
            this.drawPie()
        },
        methods: {
            async drawPie(){
                let myChart = this.$echarts.init(document.getElementById("pie"))

                let res = await springApi.getPieData();

                let option = {
                    legend: {
                        top: 'bottom'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            mark: { show: true },
                            dataView: { show: true, readOnly: false },
                            restore: { show: true },
                            saveAsImage: { show: true }
                        }
                    },
                    series: [
                        {
                            name: '简历投递状态',
                            type: 'pie',
                            radius: [50, 250],
                            center: ['50%', '50%'],
                            roseType: 'area',
                            itemStyle: {
                                borderRadius: 8
                            },
                            data: [
                                {
                                    name: '未回复',
                                    value: res.data.pieData[0]
                                },
                                {
                                    name: '一面',
                                    value: res.data.pieData[1]
                                },
                                {
                                    name: '二面',
                                    value: res.data.pieData[2]
                                },
                                {
                                    name: '三面',
                                    value: res.data.pieData[3]
                                },
                                {
                                    name: '笔试',
                                    value: res.data.pieData[4]
                                },
                                {
                                    name: 'offer',
                                    value: res.data.pieData[5]
                                },
                                {
                                    name: '已拒绝',
                                    value: res.data.pieData[6]
                                }
                            ]
                        }
                    ]
                };
                myChart.setOption(option)
            }
        }
    }
</script>