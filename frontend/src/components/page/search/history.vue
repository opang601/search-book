<template>
  <div>
    <h2>나의 검색이력</h2>
    <table class="table table-type1 table-align-left">
        <thead role="rowgroup" class="">
            <tr role="row">
              <th role="columnheader" scope="col" aria-colindex="1" class="">No</th>
              <th role="columnheader" scope="col" aria-colindex="2" class="">검색어</th>
              <th role="columnheader" scope="col" aria-colindex="3" class="">검색일시</th>
            </tr>
          </thead>
        <colgroup>
          <col width="">
          <col width="">
          <col width="">
        </colgroup>
        <tbody>
          <tr v-for="(item,index) in searchList" :key="index">
            <td>{{index+1}}</td>
            <td>{{item.searchKeyword}}</td>
            <td>{{formatDate(item.regDt)}}</td>
          </tr>
        </tbody>
      </table>
    </div>

</template>

<script>
import axios from 'axios'
import Vue from 'vue'
import moment from 'moment'

  export default {
    data() {
      return {
        searchList: [],
      }
    },
    methods: {
      formatDate(timezone){
        return moment(timezone).format("YYYY-MM-DD HH:mm:ss");
      }
    },
    computed: {
     
    },
   created() {
     //회원 검색내역 조회
     axios.get(process.env.ROOT_API + '/api/search/history')
        .then((response) => {
          this.searchList =  response.data.data.searchList
        })
        .catch((ex) => {
          console.log("error : " + ex)
        })
   }
    
  }
</script>