<template>
  <div class="content">
    <div class="wrapper-login">
        <form @submit.prevent="handleSubmit">
          <div class="form-login">
            <div class="group">
              <label for="ad-id">AD ID</label>
              <b-form-input type="text" id="ad-id" v-model="username" name="username" ></b-form-input>
              <!-- <input type="text" v-model="username" name="username" class="form-control" :class="{ 'is-invalid': submitted && !username }" />
              <div v-show="submitted && !username" class="invalid-feedback">Username is required</div> -->

            </div>
            <div class="group">
              <label for="ad-pw">PASSWORD</label>
              <b-form-input id="ad-pw" type="password" v-model="password" name="password" @keyup.enter.native="handleSubmit()"></b-form-input>
              <!-- <input type="password" v-model="password" name="password" class="form-control" :class="{ 'is-invalid': submitted && !password }" />
              <div v-show="submitted && !password" class="invalid-feedback">Password is required</div> -->


              <b-button id="loginPopover" variant="reset" class="popover">Using slots</b-button>
              <b-popover target="loginPopover" triggers="hover focus">
                AD 비밀번호는 PC 비밀번호와 동일합니다.
              </b-popover>            
            </div>
          </div>

           <div class="btn-area">
            <b-button size="lg" variant="custom-3" :disabled="loggingIn" @click="handleSubmit()">LOGIN</b-button>
          </div>

          <div class="noti">
          <dt>※ 주의사항</dt>
          <dd>
            비밀번호는 관리 소홀, 부정 사용에 의한 피해가 발생하지 않도록 철저히 관리 해야합니다.<br>
            (비밀번호가 노출되었거나 노출이 의심되는 경우, 즉시 변경하시기 바랍니다.)
          </dd>
        </div>

        </form>
      </div>
  </div>
</template>

<script>
export default {  
  data () {
        return {
            username: '',
            password: '',
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
            if(!this.username && !this.password){
              alert('AD ID와 비밀번호를 정확하게 입력해주세요.')
              return false
            } 
            if(!this.username ){
              alert('AD ID를 정확하게 입력해주세요.')
              return false
            } 
            if(!this.password){
              alert('비밀번호를 정확하게 입력해주세요.')
              return false
            } 
            this.submitted = true;
            const { username, password } = this;
            const { dispatch } = this.$store;
            if (username && password) {
                dispatch('authentication/login', { username, password });
            }
        }
    }
}


</script>