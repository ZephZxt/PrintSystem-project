import { createRouter, createWebHistory } from 'vue-router'
import Docs_PbView from '../views/Publisher/Docs_PbView.vue'
import Tasks_PbView from '../views/Publisher/Tasks_PbView.vue'
import NotFound from '../views/error/NotFound.vue'

const routes = [
  {
    path: "/",
    name: "Home",
    redirect: "/publisher/docs/",
  },
  {
    path: "/publisher/docs/",
    name: "docs_p",
    component: Docs_PbView,
  },
  {
    path: "/publisher/tasks/",
    name: "tasks_p",
    component: Tasks_PbView,
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

export default router
