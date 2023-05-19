import Vue from 'vue'
import VueRouter from 'vue-router'
import ScreenPage from '../views/ScreenPage.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: "/screen"
  },
  {
    path: '/screen',
    name: 'ScreenPage',
    component: ScreenPage
  },
  {
    path: '/sellerPage',
    name: 'sellerPage',
    component: () => import(/* webpackChunkName: "about" */ '../views/SellerPage.vue')
  },
  {
    path: '/trendPage',
    name: 'TrendPage',
    component: () => import(/* webpackChunkName: "about" */ '../views/TrendPage.vue')
  },
  {
    path: '/mapPage',
    name: 'mapPage',
    component: () => import(/* webpackChunkName: "about" */ '../views/MapPage.vue')
  },
  {
    path: '/rankPage',
    name: 'rankPage',
    component: () => import(/* webpackChunkName: "about" */ '../views/RankPage.vue')
  },
  {
    path: '/hotPage',
    name: 'hotPage',
    component: () => import(/* webpackChunkName: "about" */ '../views/HotPage.vue')
  },
  {
    path: '/stockPage',
    name: 'stockPage',
    component: () => import(/* webpackChunkName: "about" */ '../views/StockPage.vue')
  },
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
