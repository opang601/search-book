<template>
  <div id="App">
    <b-container fluid class="px-0">
      <router-view></router-view>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  .btn .fa-icon {
    vertical-align: middle;
    margin-right: 0.5rem;
  }
  .btn .fa-icon:last-child {
    margin-right: 0;
  }
</style>
