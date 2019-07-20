export const variable = {
    namespaced: true,
    state: {
        keepAlive: false,
        storeObject: {},
        storeList: [],
        dimFlag: false,
    },
    actions: {
        setObject({ commit }, data) {
            commit('reqSetObject', data);
        },
        initObject({ commit }) {
            commit('reqInitObject');
        },
        setList({ commit }, data) {
            commit('reqSetList', data);
        },
        initList({ commit }) {
            commit('reqInitList');
        },
    },
    mutations: {
        reqSetData(state, data) {
            state.storeObject = data
        },
        reqInitData(state) {
            state.storeObject = {}
        },
        reqSetList(state, data) {
            state.storeList = data
        },
        reqInitList(state) {
            state.storeList = []
        },
    }
}