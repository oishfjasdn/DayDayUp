<template>
  <div class="about">
    <h1>This is an home page</h1>
    <button @click="bbb">asd</button>
  </div>
</template>
<script setup>
const bbb=()=>{
  localStorage.removeItem('token')
}
</script>
<style></style>