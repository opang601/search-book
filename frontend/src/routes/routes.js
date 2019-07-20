import Vue from 'vue';
import Router from 'vue-router';
import axios from 'axios'
import JWTDecode from 'jwt-decode';
import { userService } from '../_services';

import mainSearch from '../components/search/mainSearch.vue'
import login from '../components/user/Login.vue'
import regist from '../components/user/Regist.vue'


Vue.use(Router);

const routes = [
    {
        path: '/login',
        component: login,
        // redirect: '/search/main',
    },
    {
        path: '/search',
        component: mainSearch,
        // redirect: '/search/main',
    },
    {
        path: '/regist',
        component: regist,
        // redirect: '/search/main',
    },
   
]



export const router = new Router({
    routes: routes,
    linkActiveClass: 'nav-item active'
});


//라우터 마다 인증 체크 추가
// router.beforeEach((to, from, next) => {
//     // redirect to login page if not logged in and trying to access a restricted page
//     const publicPages = ['/admin/login'];
//     const authRequired = !publicPages.includes(to.path);
//     const loggedIn = localStorage.getItem('user');

//     //로그인 인증 체크
//     if (authRequired && !loggedIn) {
//         return next('/admin/login');
//     }
//     //토큰만료 체크
//     userService.expireCheck();
//     //현재URL을 헤더에 담아서 전송
//     next();
// })


//토큰 인증 만료 처리 Interceptor추가.
// axios.interceptors.response.use(function(response) {
//     router.app.$store._vm._data.$$state.variable.dimFlag = false;
//     return response;
// }, function(error) {
//     if (401 === error.response.status) {
//         if (4 === error.response.data.resultCode) {
//             alert('해당 메뉴의 권한이 없습니다. 관리자에게 문의해주세요.');
//         } else {
//             alert('인증시간이 만료되었습니다. 다시 로그인 해주세요.')
//         }
//         userService.logout();
//         location.reload(true);
//     }
// });

//만료시간 체크해서 갱신주기가 지나면 Token 갱신 요청
//토큰 갱신 처리 Interceptor추가.
// axios.interceptors.request.use(
//     reqConfig => {
//         router.app.$store._vm._data.$$state.variable.dimFlag = true;
//         //토큰만료 체크
//         userService.expireCheck();
//         return reqConfig;
//     },
//     err => Promise.reject(err)
// );