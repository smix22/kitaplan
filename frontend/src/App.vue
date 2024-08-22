<script setup lang="ts">
import UserView from "@/views/UserView.vue";
import {keycloak} from "@/main";
import AdminView from "@/views/AdminView.vue";
</script>

<template>
  <admin-view v-if="keycloak.authenticated"></admin-view>
  <user-view v-if="!keycloak.authenticated"></user-view>
  <!--  <button v-if="keycloak.authenticated" @click="logout">Abmelden</button>-->
</template>

<style scoped>
</style>
