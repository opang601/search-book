import Vue from 'vue'
import VueRouter from 'vue-router'
import App from './App.vue'

// bootstrap-vue plugin
import BootstrapVue from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import { store } from './_store';
import { router } from './_helpers';

// plugin setup
Vue.use(VueRouter)
Vue.use(BootstrapVue)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  store,
  router,
  render: h => h(App)
})
