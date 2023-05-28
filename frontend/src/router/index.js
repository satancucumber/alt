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
            path: '/plot',
            component: () => import('../components/PlotView.vue'),
        },
        {
            path: '/literal',
            component: () => import('../components/LiteralFormView.vue'),
        },
        {
            path: '/formula',
            component: () => import('../components/FormulaFormView.vue'),
        },
        {
            path: '/text',
            component: () => import('../components/TextFormView.vue'),
        },
        {
            path: '/img',
            component: () => import('../components/ImgFormView.vue'),
        },
        {
            path: '/canvas',
            component: () => import('../components/CanvasView.vue'),
        },
        {
            path: '/input',
            component: () => import('../components/PlotInputView.vue'),
        },
        {
            path: '/evidence',
            component: () => import('../components/EvidenceView.vue'),
        },
    ]
})
export default router