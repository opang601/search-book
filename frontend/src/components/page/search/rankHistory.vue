<template>
  <div>
    <h2>검색어 순위</h2>
    <table class="table table-type1 table-align-left">
        <thead role="rowgroup" class="">
            <tr role="row">
              <th role="columnheader" scope="col" aria-colindex="1" class="">No</th>
              <th role="columnheader" scope="col" aria-colindex="2" class="">검색어</th>
              <th role="columnheader" scope="col" aria-colindex="3" class="">검색횟수</th>
            </tr>
          </thead>
        <colgroup>
          <col width="">
          <col width="">
          <col width="">
        </colgroup>
        <tbody>
           <tr v-for="(value, name, index) in searchList" :key="name">
            <td>{{index+1}}</td>
            <td>{{name}}</td>
            <td>{{value}}</td>
          </tr>
        </tbody>
      </table>
    </div>

</template>

<script>
import axios from 'axios'
import Vue from 'vue'
import moment from 'moment'

Vue.filter('formatDate', function(value) {
  if (value) {
    return moment(String(value)).format('YYYY/MM/DD hh:mm')
  }
});

  export default {
    data() {
      return {
        searchList: [],
      }
    },
    methods: {
     
    },
    computed: {
      
    },
   created() {
     axios.get(process.env.ROOT_API + '/api/search/rankHistory')
        .then((response) => {
          this.searchList =  response.data.data
        })
        .catch((ex) => {
          console.log("error : " + ex)
        })
   }
    
  }
</script>