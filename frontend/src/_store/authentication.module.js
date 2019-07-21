import { userService } from '../_services';
import { router } from '../_helpers';
import axios from 'axios'

const user = JSON.parse(localStorage.getItem('user'));

const enhanceAccessToeken = () => {
    if(user != null && user != ''){
        const accessToken = user.token
        if (!accessToken) return
        axios.defaults.headers.common['Authorization'] = accessToken;
    }
}

//화면 Refresh 이후엔 Vuex State가 초기화 되므로 Local Storage에서 가져온 Token으로 다시 세팅.
enhanceAccessToeken();


const initialState = user
    ? { status: { loggedIn: true }, user }
    : { status: {}, user: null };

export const authentication = {
    namespaced: true,
    state: initialState,
    actions: {
        login({ dispatch, commit }, { userId, userPwd }) {
            commit('loginRequest', { userId });

            userService.login(userId, userPwd)
                .then(
                    user => {
                        commit('loginSuccess', user);
                        router.push('/');
                    },
                    error => {
                        let errorMsg = '';
                        if (error.status === 401) {
                            errorMsg = '아이디 또는 비밀번호를 정확하게 입력해주세요.'
                        }else{
                            errorMsg = '로그인 중 문제가 발생하였습니다.'
                        }
                        commit('loginFailure', error);
                        dispatch('alert/error', error, { root: true });
                        alert(errorMsg);
                    }
                );
        },
        logout({ commit }) {
            userService.logout();
            commit('logout');
        }
    },
    mutations: {
        loginRequest(state, user) {
            state.status = { loggingIn: true };
            state.user = user;
        },
        loginSuccess(state, user) {
            state.status = { loggedIn: true };
            state.user = user;
        },
        loginFailure(state) {
            state.status = {};
            state.user = null;
        },
        logout(state) {
            state.status = {};
            state.user = null;
        }
    }
}
