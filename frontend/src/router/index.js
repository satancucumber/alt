import Vue from 'vue'
import VueRouter from 'vue-router'

import Home from '../components/HomeView.vue'


Vue.use(VueRouter)

const router = new VueRouter({
    mode: 'history',
    routes: [
        {
            path: '/',
            name: 'home',
            component: Home
        },
        {
            path: '/literal',
            name: constructor,
            component: () => import('../components/LiteralView.vue'),
        },
    ]
})
export default router