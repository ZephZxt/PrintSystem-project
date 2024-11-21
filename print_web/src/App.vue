<template>
  <NavBar_P/>
  <router-view/>
</template>
<script>
import NavBar_P from './components/NavBar_P.vue'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap/dist/js/bootstrap.min.js"
export default{
  components: {
    NavBar_P,
  }
}
</script>

<style>
body {
  background-image: url(@/assets/background.png);
  background-size: cover;
}
</style>
