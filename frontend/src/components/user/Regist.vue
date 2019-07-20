<template>
  <div>
    <h2>회원가입 안내</h2>
    <b-form @submit="onSubmit" @reset="onBack" >
      <b-form-group id="input-group-2" label="ID" label-for="input-2">
        <b-form-input
          v-model="form.userId"
          required
          placeholder="ID를 적어주세요" maxlength="20"
        ></b-form-input>
        <b-button variant="outline-primary" @click="idCheckProc">ID 중복확인</b-button>
        중복확인 여부 : {{idCheck.checkNm}}
      </b-form-group>
      <b-form-group id="input-group-2" label="비밀번호" label-for="input-2">
        <b-form-input
          v-model="form.userPwd"
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
          v-model="form.userName"
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
          userId: '',
          userPwd: '',
          repwd: '',
          userName: '',
        },
        idCheck: {
          check : false,
          checkNm : '미확인'
        }
      }
    },
    methods: {
      initForm(){
        this.form = {
          userId: '',
          userPwd: '',
          repwd: '',
          userName: '',
        }
      },
      onSubmit(evt) {
        evt.preventDefault()
        if(confirm('가입 하시겠습니까?')) {
            if(this.form.userPwd != this.form.repwd){
              alert('비밀번호와 비밀번호 확인 문자열이 다릅니다.\n다시 확인해주세요.')
              return false
            }
            if(!this.idCheck.check){
              alert('ID 중복확인을 해주세요.')
              return false
            }
            axios.post(process.env.ROOT_API + '/api/user/regist', this.form)
            .then((response) => {
              alert(response.data.message)
              this.initForm();
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
      },
      idCheckProc(){
        if(!this.form.userId){
            alert('ID에 값이 없습니다.')
            return false
          }
        axios.post(process.env.ROOT_API + '/api/user/idCheck', this.form)
            .then((response) => {
              if(response.data.resultCode == '0'){
                this.idCheck.check = true
                this.idCheck.checkNm = '확인완료'
              }else{
                this.idCheck.check = false
                this.idCheck.checkNm = '중복ID존재'
              }
            })
            .catch((ex) => {
              alert('ID 중복확인이 실패하였습니다.\n' + ex)
              console.log("error : " + ex)
            })
      },
    },
    watch: {
      'form.userId': function (newVal, oldVal) {
        this.idCheck.check = false
        this.idCheck.checkNm = '미확인'
      }
    },
  }
</script>