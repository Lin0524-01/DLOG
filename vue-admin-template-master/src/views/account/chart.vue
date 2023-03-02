<template>
    <div class="app-container">
        <div style="margin: 0 auto">
            <div id="pie" style="width: 500px;height: 400px; display: inline-block;"></div>
            <div id="pie1" style="width: 500px;height: 400px; display: inline-block"></div>
        </div>
        <div id="line" style="width: 1000px;height: 400px; margin: 0 auto;"></div>
        <div id="line1" style="width: 1000px;height: 400px; margin: 0 auto;"></div>
    </div>
</template>

<script>
    import echarts from 'echarts'
    import accountApi from '@/api/account'
    
    export default {
        data(){
            return{
                moneyList: [],
                dateList: [],
                chart: null,
                expenseTypeAndMoneyList: []
            }
        },
        created(){
        },
        mounted(){
            this.drawLine(),
            this.drawPie(),
            this.drawPie1(),
            this.drawLine1()
        },
        methods: {
            async drawLine(){
                let myChart = this.$echarts.init(document.getElementById("line"))

                let res = await accountApi.getLineData()

                let option = {
                    title: {
                        text: '本月余额宝收益折线图',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    xAxis: {
                        type: 'category',
                        name: '日期',
                        data: res.data.dateList,
                        axisTick: {
                            alignWithLabel: true
                        }
                    },
                    yAxis: {
                        type: 'value',
                        name: '金额'
                    },
                    series: [
                        {
                            data: res.data.moneyList,
                            type: 'line',
                            smooth: true
                        }
                    ]
                };

                myChart.setOption(option)
            },
            async drawPie(){
                let myChart = this.$echarts.init(document.getElementById("pie"))

                let res = await accountApi.getIncomeAndExpensePerMonth()

                let option = {
                    title: {
                        text: '本月收支饼图',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                            name: 'Access From',
                            type: 'pie',
                            radius: '50%',
                            data: [
                                { value: res.data.income, name: '收入' },
                                { value: res.data.expense, name: '支出' },
                            ],
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                myChart.setOption(option)
            },
            async drawPie1(){
                let myChart = this.$echarts.init(document.getElementById("pie1"))

                let res = await accountApi.getExpensePieCurrentMonth()
                this.expenseTypeAndMoneyList = res.data.expenseTypeAndMoneyList
                let expenseData = {
                    name: '',
                    value: ''
                }
                let dataList = []
                for(let i = 0; i < this.expenseTypeAndMoneyList.length; i++){
                    expenseData.name = this.expenseTypeAndMoneyList[i].remark
                    expenseData.value = this.expenseTypeAndMoneyList[i].money
                    dataList.push(expenseData)
                    expenseData = {}
                }

                let option = {
                    title: {
                        text: '本月消费类型及金额饼图',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        top: '5%',
                        left: 'right'
                    },
                    series: [
                        {
                            name: '本月消费类型情况统计',
                            type: 'pie',
                            radius: ['50%', '70%'],
                            avoidLabelOverlap: false,
                            itemStyle: {
                                borderRadius: 10,
                                borderColor: '#fff',
                                borderWidth: 2
                            },
                            label: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                label: {
                                    show: true,
                                    fontSize: 20,
                                }
                            },
                            labelLine: {
                                show: false
                            },
                            data: dataList
                        }
                    ]
                };
                myChart.setOption(option)
            },
            async drawLine1(){
                let myChart = this.$echarts.init(document.getElementById("line1"))

                let res = await accountApi.getCurrentIncomeAndExpenseMoney()

                let option = {
                    title: {
                        text: '本月收支折线图',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['收入', '支出'],
                        left: 'left'
                    },
                    xAxis: {
                        type: 'category',
                        data: res.data.monthDateList
                    },
                    yAxis: {
                        type: 'value',
                    },
                    series: [
                        {
                            name: '收入',
                            type: 'line',
                            data: res.data.incomeList
                        },
                        {
                            name: '支出',
                            type: 'line',
                            data: res.data.expenseList,
                        },
                    ]
                };
                myChart.setOption(option)
            }
        }
    }
</script>

<style>
    #pie {
        padding-left: 100px;
    }

    #pie1 {
        padding-right: 50px;
    }
</style>