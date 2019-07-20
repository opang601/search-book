import axios from 'axios'

export const lnb = {
    namespaced: true,
    state: {
        topMenuList: [],
        menuList: []
    },
    actions: {
        initLnb({ commit }, data) {
            commit('initLnb', data)
        },
    },
    mutations: {
        initLnb(state, data) {
            let key = Object.keys(data)[0]
            state[key] = []
            state[key] = data[key]
        }
    }
}