import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/global.css';
import * as echarts from 'echarts'
import App from './App.vue'
import request from "@/assets/axios";

Vue.config.productionTip = false
Vue.use(ElementUI);
Vue.prototype.$echarts = echarts//将echarts作为全局变量供全局使用
Vue.prototype.$request = request

new Vue({
  render: h => h(App),
}).$mount('#app')
