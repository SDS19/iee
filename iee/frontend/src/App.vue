<template>
  <el-container id="app">

    <el-header style="position: center">
      <span>
        <span class="mr-10">Date</span>
        <el-date-picker type="daterange" value-format="yyyy-MM-dd"
                        v-model="date" @change="dateChange"
                        start-placeholder="Start date"
                        range-separator="To"
                        end-placeholder="End date">
        </el-date-picker>
      </span>
      <span class="ml-10">
        <el-button type="primary">Import File<i class="el-icon-top"></i></el-button>
      </span>
    </el-header>

    <el-main>
      <div>
        <span id="chart_1" style="width: 1600px; height: 400px"></span>
        <span id="chart_2" style="width: 1600px; height: 400px"></span>
      </div>
    </el-main>

  </el-container>
</template>

<script>
export default {
  name: 'App',
  data() {
    return{
      date:'',
      windData: []
    }
  },
  methods: {
    charts () {
      let chart_1 = this.$echarts.init(document.getElementById('chart_1'))
      let chart_2 = this.$echarts.init(document.getElementById('chart_2'))

      let a = this.windData
      let time = []
      for (let i = 0; i < a.length; i++) {
        time.push(a[i].time)
      }
      let inPower = []
      for (let i = 0; i < a.length; i++) {
        inPower.push(a[i].inPower)
      }
      let outPower = []
      for (let i = 0; i < a.length; i++) {
        outPower.push(a[i].outPower)
      }
      let speed = []
      for (let i = 0; i < a.length; i++) {
        speed.push(a[i].speed)
      }

      chart_1.setOption({
        title: {
          left: 'center',
          text: 'Power'
        },
        tooltip: {
          trigger:'axis'
        },
        legend: {
          data: ['In Power','Out Power'],
          right: 200
        },
        xAxis: {
          name: 'Time',
          type: 'category',
          data: time
        },
        yAxis: {
          name: 'Power (kW)',
          type: 'value'
        },
        dataZoom: [
          {
            show: true,
            realtime: true,
            start: 0,
            end: 100,
            xAxisIndex: [0, 1]
          }
        ],
        series: [
          {
            name: 'In Power',
            type: 'line',
            smooth: true,
            data: inPower,
          },
          {
            name: 'Out Power',
            type: 'line',
            smooth: true,
            data: outPower,
          }
        ]
      })
      chart_2.setOption({
        title: {
          left: 'center',
          text: 'Speed',
        },
        tooltip: {
          trigger:'axis'
        },
        legend: {
          data: ['speed'],
          right: 200
        },
        xAxis: {
          name: 'Time',
          type: 'category',
          data: time
        },
        yAxis: {
          name: 'Speed (m/s)',
          type: 'value'
        },
        series: [
          {
            name: 'speed',
            type: 'line',
            smooth: true,
            data: speed
          }
        ]
      })
    },
    dateChange(){
      fetch("http://localhost:9090/date?startDate="+this.date[0]+"&endDate="+this.date[1]).then(res => res.json()).then(res => {
        this.windData = res
        this.charts()
      })
    }
  },
  created() {
    fetch("http://localhost:9090/date?startDate=2022-02-01&endDate=2022-02-01").then(res => res.json()).then(res => {
      this.windData = res
      this.charts()
    })
  },
  /*mounted() {
    this.charts()
  }*/
}
</script>

<style>

</style>
