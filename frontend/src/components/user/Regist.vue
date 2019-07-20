<template>
  <div>
    <h2>회원가입 안내</h2>
    <b-form @submit="onSubmit" @reset="onBack" >
      <b-form-group id="input-group-2" label="ID" label-for="input-2">
        <b-form-input
          v-model="form.id"
          required
          placeholder="ID를 적어주세요" maxlength="20"
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-2" label="비밀번호" label-for="input-2">
        <b-form-input
          v-model="form.pwd"
          required
          placeholder="비밀번호" maxlength="20"
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-2" label="비밀번호확인" label-for="input-2">
        <b-form-input
          v-model="form.repwd"
          required
          placeholder="비밀번호 확인" maxlength="20"
        ></b-form-input>
      </b-form-group>
      <b-form-group id="input-group-2" label="이름" label-for="input-2">
        <b-form-input
          v-model="form.name"
          required
          placeholder="이름을 적어주세요" maxlength="20"
        ></b-form-input>
      </b-form-group>

      <b-button type="submit" variant="primary">가입요청</b-button>
      <b-button type="reset" variant="danger">돌아가기</b-button>
    </b-form>
  </div>
</template>

<script>


import axios from 'axios'
import Vue from 'vue'

  export default {
    data() {
      return {
        form: {
          id: '',
          name: '',
          pwd: '',
          repwd: ''
        },
      }
    },
    methods: {
      onSubmit(evt) {
        evt.preventDefault()
        if(confirm('가입 하시겠습니까?')) {
            let params = {}
            params.userId = this.form.id
            params.userName = this.form.name
            params.userPwd = this.form.pwd

            axios.post(process.env.ROOT_API + '/api/user/regist', params)
            .then((response) => {
              alert(response.data)
            })
            .catch((ex) => {
              alert('등록이 실패하였습니다.\n' + ex)
              console.log("error : " + ex)
            })
        }
        
      },
      onBack(evt) {
        evt.preventDefault()
        alert('뒤로가기')
      }
    }
  }
</script>