<template>
  <div>
    <b-navbar toggleable="md" type="dark" variant="info">

      <b-navbar-toggle target="nav_collapse"></b-navbar-toggle>

      <b-navbar-brand href="#/">
        도서 검색 서비스
      </b-navbar-brand>

      <b-collapse is-nav id="nav_collapse">

        <b-navbar-nav>
          <b-nav-item href="#" @click="movePage('/search')">도서조회</b-nav-item>
          <b-nav-item href="#" @click="movePage('/history')">나의 검색이력</b-nav-item>
          <b-nav-item href="#" >검색어 순위</b-nav-item>
        </b-navbar-nav>

        <b-navbar-nav class="ml-auto">

          <b-nav-item-dropdown right>
            <!-- Using button-content slot -->
            <template slot="button-content">
              <em>{{userName}}</em>
            </template>
            <b-dropdown-item href="#" @click="logout">Logout</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>

      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
  
import { userService } from '@/_services';

export default {
  name: 'top',
  data () {
    return {
      userId: '',
      userName: '',
    }
  },
  methods:{
    logout(){
        userService.logout();
        location.reload(true);
      },
    movePage(path){
      this.$router.push(path)
    }
  },
  created() {
    const user = JSON.parse(localStorage.getItem('user'))
    this.userId = user.userId
    this.userName = user.userName
  }
};
</script>

<style scoped>

</style>
