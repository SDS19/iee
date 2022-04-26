<template>
  <div id="main" style="width: 800px; height: 600px"></div>
</template>

<script>
export default {
  name: "LineChart",
  props: ['windData'],
  methods: {
    charts () {
      let myChart = this.$echarts.init(document.getElementById('main'))

      let a = this.windData
      let time = []
      for (let i = 0; i < a.length; i++) {
        time.push(a[i].time)
      }
      let power = []
      for (let i = 0; i < a.length; i++) {
        power.push(a[i].outPower)
      }

      myChart.setOption({
        title: {
          text: 'Out Power'
        },
        xAxis: {
          type: 'category',
          data: time
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            type: 'line',
            smooth: true,
            data: power,
          }
        ]
      })
    }
  },
  mounted() {
    this.charts()
  }
}
</script>

<style scoped>

</style>