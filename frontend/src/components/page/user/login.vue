<template>
    <div>
        <h2>도서 검색</h2>
        <form @submit.prevent="handleSubmit">
          <div>
            <div class="group">
              <label for="ID">ID</label>
              <b-form-input type="text" v-model="userId" name="userId" placeholder="회원ID" maxlength="20" ></b-form-input>
            </div>
            <div>
              <label for="PASSWORD">PASSWORD</label>
              <b-form-input type="password" v-model="userPwd" name="userPwd" placeholder="비밀번호" maxlength="20" @keyup.enter.native="handleSubmit()"></b-form-input>
            </div>
          </div>
           <div class="btn-area">
            <b-button variant="primary"  @click="handleSubmit()">LOGIN</b-button>
            <b-button variant="primary"  @click="goRegist()">회원가입</b-button>
          </div>

        </form>
    </div>
</template>

<script>
export default {  
  data () {
        return {
            userId: '',
            userPwd: '',
            submitted: false
        }
    },
    computed: {
        loggingIn () {
            return this.$store.state.authentication.status.loggingIn;
        }
    },
    created () {
        // reset login status
        this.$store.dispatch('authentication/logout');
    },
    methods: {
        handleSubmit () {
            if(!this.userId && !this.userPwd){
              alert('ID와 비밀번호를 정확하게 입력해주세요.')
              return false
            } 
            if(!this.userId ){
              alert('ID를 정확하게 입력해주세요.')
              return false
            } 
            if(!this.userPwd){
              alert('비밀번호를 정확하게 입력해주세요.')
              return false
            } 
            this.submitted = true;
            const { userId, userPwd } = this;
            const { dispatch } = this.$store;
            if (userId && userPwd) {
                dispatch('authentication/login', { userId, userPwd });
            }
        },
        goRegist(){
          this.$router.push('regist')
        }
    }
}


</script>