<template>
  <div>
    <h2>도서조회</h2>
    <b-input-group class="mb-1" prepend="검색어" style="width:700px;">
      <b-form-input v-model="fieldList.searchKeyword" 
                    @keyup.enter.native="searchKeyword(1)"></b-form-input>
      <b-input-group-append>
        <b-button size="sm" text="Button" variant="success" @click="searchKeyword(1)">조회</b-button>
      </b-input-group-append>
    </b-input-group>
    
    <table id="my-table" aria-busy="false" aria-colcount="3" class="table b-table table-sm">
        <thead role="rowgroup" class="">
          <tr role="row">
            <th role="columnheader" scope="col" aria-colindex="1" class="">No</th>
            <th role="columnheader" scope="col" aria-colindex="2" class="">도서명</th>
            <th role="columnheader" scope="col" aria-colindex="3" class="">저자</th>
          </tr>
        </thead>
        <tbody role="rowgroup" class="">

          <tr v-if="searchList.length < 1" aria-rowindex="1" role="row" class="" >
            <td role="cell" aria-colindex="1" class="">조회된 데이터가 없습니다.</td>
          </tr>
          <tr v-else aria-rowindex="1" role="row" class="" v-for="(item,index) in searchList" :key="index">
            <td role="cell" aria-colindex="1" class="">{{((currentPage-1)*perPage)+index+1}}</td>
            <td role="cell" aria-colindex="2" class="">{{item.title}}</td>
            <td role="cell" aria-colindex="3" class="">{{item.authors}}</td>
          </tr>
        </tbody>
    </table>
    <b-pagination align="center" size="sm" 
      v-model="currentPage"
      :total-rows="rows"
      :per-page="perPage"
      :limit="limit"
    />
  </div>
</template>

<script>
import axios from 'axios'

  export default {
    data() {
      return {
        fieldList:{searchKeyword:''},
        rows: 0,
        perPage: 10,
        currentPage: 1,
        limit: 10,
        searchList: [],
      }
    },
    methods: {
      fullName(value) {
        return `${value.first} ${value.last}`
      },

      //상품 조회
      searchKeyword(page) {
        if(!this.fieldList.searchKeyword){
          alert('검색어를 입력해주세요.')
          return false
        }
        this.searchList = []
        this.currentPage = page
        //form 데이터 기입
        let formParams = {}
        formParams.page = page
        formParams.pageSize =  this.perPage
        formParams.searchKeyword = this.fieldList.searchKeyword

        axios.post(process.env.ROOT_API + '/api/search/book', formParams)
        .then((response) => {
          let result =  response.data.data
          console.log(result.body.meta)
          this.rows = result.body.meta.total_count
          this.searchList = result.body.documents
        })
        .catch((ex) => {
          console.log("error : " + ex)
        })
      },

    },
    computed: {
      
    },
    watch: {
    currentPage: function (newVal, oldVal) {
      this.searchKeyword(this.currentPage)
    },
    
  },
  }
</script>