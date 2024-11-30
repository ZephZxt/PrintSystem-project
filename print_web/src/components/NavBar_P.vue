<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">
    <router-link class="navbar-brand" :to="{name: 'Home'}">Priting System</router-link>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link :class="route_name == 'docs_p'? 'nav-link active' : 'nav-link'" :to="{name: 'docs_p'}">印刷单据</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'tasks_p'? 'nav-link active' : 'nav-link'" :to="{name: 'tasks_p'}">印刷任务</router-link>
        </li>
      </ul>
      <ul class="navbar-nav" v-if="$store.state.user.is_login">
        <li class="username-container">
          <span class="username">{{ $store.state.user.username }}</span>
        </li>
        <li class="button-container">
          <router-link class="btn btn-outline-light" :to="{name: 'loginview'}" @click="logout">退出</router-link>
        </li>
      </ul>
      <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
        <li class="nav-item">
          <router-link class="nav-link " :to="{name: 'loginview'}" role="button">
            登录
          </router-link>
        </li>
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'registerview'}" role="button">
            注册
          </router-link>
        </li>
      </ul>
    </div>
  </div>
</nav>
</template>
<script>
import { useStore } from 'vuex'
import { useRoute } from 'vue-router'
import { computed } from 'vue'

export default {
    setup() {
        const store = useStore()
        const route = useRoute()
        let route_name = computed(() => route.name)
        const logout = () => {
          store.dispatch("logout")
        }

        return {
            route_name,
            logout,
        }
    }
}
</script>
<style scoped>
.username {
  font-size: 18px;
  color: #fff;
  margin-right: 10px;
}
.username-container {
  margin-top: 6px;
  margin-right: 10px
}

</style>
