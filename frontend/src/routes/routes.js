import Vue from 'vue';
import Router from 'vue-router';
import axios from 'axios'
import { userService } from '../_services';

import index from '@/components/page/index';
import intro from '@/components/page/intro';
import login from '@/components/page/user/login';
import regist from '@/components/page/user/regist';
import bookSearch from '@/components/page/search/bookSearch';
import history from '@/components/page/search/history';

Vue.use(Router);

const routes = [
    {
        path: '/',
        name: 'index',
        component: index,
        children: [
          {
            path: '/',
            name: 'intro',
            component: intro,
          },
          {
            path: '/search',
            name: 'bookSearch',
            component: bookSearch,
          },
          {
            path: '/history',
            name: 'history',
            component: history,
          },
        ],
      },
    {
        path: '/user/login',
        component: login,
    },
    {
        path: '/user/regist',
        component: regist,
    },
]

export const router = new Router({
    routes: routes,
});


//라우터 마다 인증 체크 추가
router.beforeEach((to, from, next) => {
    // redirect to login page if not logged in and trying to access a restricted page
    const publicPages = ['/user/login','/user/regist'];
    const authRequired = !publicPages.includes(to.path);
    const loggedIn = localStorage.getItem('user');

    //로그인 인증 체크
    if (authRequired && !loggedIn) {
        return next('/user/login');
    }
    //토큰만료 체크
    userService.expireCheck();
    //현재URL을 헤더에 담아서 전송
    next();
})


//토큰 인증 만료 처리 Interceptor추가.
axios.interceptors.response.use(function(response) {
    return response;
}, function(error) {
    if (401 === error.response.status) {
        alert('인증시간이 만료되었습니다. 다시 로그인 해주세요.')
        
        userService.logout();
        location.reload(true);
    }
});

//만료시간 체크해서 갱신주기가 지나면 Token 갱신 요청
// 토큰 갱신 처리 Interceptor추가.
axios.interceptors.request.use(
    reqConfig => {
        //토큰만료 체크
        userService.expireCheck();
        return reqConfig;
    },
    err => Promise.reject(err)
);