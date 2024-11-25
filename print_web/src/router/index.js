import { createRouter, createWebHistory } from 'vue-router'
import Docs_PbView from '../views/Publisher/Docs_PbView.vue'
import Tasks_PbView from '../views/Publisher/Tasks_PbView.vue'
import NotFound from '../views/error/NotFound.vue'
import LoginView from '@/views/LoginView.vue'
import RegisterView from '@/views/RegisterView.vue'
import store from '@/store/index'
import Docs_AdView from '@/views/Administrator/Docs_AdView.vue'
import Tasks_AdView from '@/views/Administrator/Tasks_AdView.vue'
import Lists_AdView from '@/views/Administrator/Lists_AdView.vue'
import Departments_AdView from '@/views/Administrator/Departments_AdView.vue'

const routes = [
  {
    path: "/",
    name: "Home",
    redirect: "/publisher/docs/",
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/publisher/docs/",
    name: "docs_p",
    component: Docs_PbView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/publisher/tasks/",
    name: "tasks_p",
    component: Tasks_PbView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/administrator/docs/",
    name: "docs_a",
    component: Docs_AdView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/administrator/tasks/",
    name: "tasks_a",
    component: Tasks_AdView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/administrator/lists/",
    name: "lists_a",
    component: Lists_AdView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/administrator/departments/",
    name: "departments_a",
    component: Departments_AdView,
    meta: {
      requestAuth: true
    }
  },
  {
    path: "/login/",
    name: "loginview",
    component: LoginView,
  },
  {
    path: "/register/",
    name: "registerview",
    component: RegisterView,
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
  },
  {
    path: "/:pathMatch(.*)*",
    redirect: "/404/",
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) =>{
  if(to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "loginview"});
  }else {
    next();
  }
})

export default router
