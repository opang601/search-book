import axios from 'axios'
import JWTDecode from 'jwt-decode';

export const userService = {
    login,
    logout,
    refresh,
    expireCheck
};

function login(username, password) {
    let formData = new FormData();
    formData.append('userId',username)
    formData.append('password',password)

    const requestOptions = {
        method: 'POST',
        // headers: { 'Content-Type': 'application/json' },
        // body: JSON.stringify({ username, password })
        body: formData
    };
    
    var loginUrl = process.env.ROOT_API +'/auth/login'
    return fetch(loginUrl, requestOptions)
        .then(handleResponse)
        .then(user => {
            if (user.data.token) {
                localStorage.setItem('user', JSON.stringify(user.data));
                //Axios 요청 시 기본적으로 Header에 Auth 정보 전달.
                axios.defaults.headers.common['Authorization'] = user.data.token
            }

            return user;
        });
}

function logout() {
    localStorage.removeItem('user');
}

function refresh() {
    const requestOptions = {
        method: 'POST',
        headers: { 
                    'Content-Type': 'application/json' ,
                    'Authorization': axios.defaults.headers.common['Authorization'] ,
                 }        
    };
    
    var refreshUrl = process.env.ROOT_API +'/auth/refresh'
    return fetch(refreshUrl, requestOptions)
        .then(handleResponse)
        .then(user => {
            if (user.data.token) {
                localStorage.setItem('user', JSON.stringify(user.data));
                //Axios 요청 시 기본적으로 Header에 Auth 정보 전달.
                axios.defaults.headers.common['Authorization'] = user.data.token
            }

            return user;
        });
}

function expireCheck(){
    var userInfo = JSON.parse(localStorage.getItem('user'))
    
    if(userInfo){
        var token = userInfo.token
        //현재시간
        var currentTime = Math.floor(Date.now() / 1000);
        //Token 만료시간
        var expireTime = JWTDecode(token).exp;
        //만료까지 남은시간
        var remainTime = expireTime - currentTime;
        //Refresh 주기 설정(단위:초) 현재 : 30분
        var refreshInterval = 60 * 30
        // console.log('토큰 만료까지 남은 시간 : ' + remainTime + '초');
        //남은시간이 0보다 크고 Interval보단 적을 시에 Token 갱신 요청
        if (remainTime > 0 && remainTime < refreshInterval) {
            this.refresh();
        }
        if(remainTime < 0){//Token 만료시 Logout 요청 후 페이지 변환
            this.logout();
            location.reload(true);
        }
    }    
}

function handleResponse(response) {
    
    return response.text().then(text => {
        const data = text && JSON.parse(text);
        if (!response.ok) {
            let error = (data && data.message) || response.statusText;

            if (response.status === 401) {
                // auto logout if 401 response returned from api
                logout();
                location.reload(true);
            }

            return Promise.reject(response);
        }
        return data;
    });
}