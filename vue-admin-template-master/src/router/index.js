import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },

  {
    path: '/plan',
    component: Layout,
    redirect: '/plan/table',
    name: '每日计划',
    meta: {title: '每日计划', icon: 'example'},
    children: [
      {
        path: '/table',
        name: 'table',
        component: () => import('@/views/plan/list'),
        meta: { title: '计划列表', icon: 'table' }
      },
      {
        path: '/summary',
        name: 'summary',
        component: () => import('@/views/plan/summary'),
        meta: { title: '每日总结', icon: 'eye' }
      },
      {
        path: '/week',
        name: 'week',
        component: () => import('@/views/plan/week'),
        meta: { title: '每周计划', icon: 'nested' }
      },
      {
        path: '/fortune',
        name: 'fortune',
        component: () => import('@/views/plan/fortune'),
        meta: { title: '星座运势', icon: 'form' }
      },
      {
        path: '/save',
        name: 'save',
        component: () => import('@/views/plan/chart'),
        meta: { title: '图表统计', icon: 'tree' }
      }
    ]
  },
  {
    path: '/idea',
    component: Layout,
    children: [
      {
        path: 'idea',
        name: 'idea',
        component: () => import('@/views/idea/list'),
        meta: { title: '想法屋', icon: 'example'}
      }
    ]
  },
  {
    path: '/account',
    component: Layout,
    redirect: '/account/table',
    name: 'account',
    meta: { title: '个人账户', icon: 'example' },
    children: [
      {
        path: 'table',
        name: 'table',
        component: () => import('@/views/account/list'),
        meta: { title: '消费记录', icon: 'table' }
      },
      {
        path: 'chart',
        name: 'chart',
        component: () => import('@/views/account/chart'),
        meta: { title: '图表统计', icon: 'tree' }
      }
    ]
  },
  {
    path: '/spring-calling',
    component: Layout,
    redirect: '/spring-calling/table',
    name: 'Example',
    meta: { title: '春招实录', icon: 'table' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/spring-calling/list'),
        meta: { title: '投递列表', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/spring-calling/chart'),
        meta: { title: '图表统计', icon: 'tree' }
      }
    ]
  },
  {
    path: '/love',
    component: Layout,
    children: [
      {
        path: 'love',
        name: 'love',
        component: () => import('@/views/love/list'),
        meta: { title: '恋爱日记', icon: 'table'}
      }
    ]
  },

  {
    path: '/nba',
    component: Layout,
    redirect: '/nba/team',
    name: 'NBA',
    meta: {title: 'NBA', icon: 'table'},
    children: [
      {
        path: 'team',
        name: 'team',
        component: () => import('@/views/nba/team'),
        meta: { title: '球队', icon: 'table'}
      },
      {
        path: 'process',
        name: 'process',
        component: () => import('@/views/nba/process'),
        meta: { title: '球员', icon: 'tree'}
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://blog.csdn.net/Zach1Lavine?spm=1000.2115.3001.5343',
        meta: { title: '博客地址', icon: 'link' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
