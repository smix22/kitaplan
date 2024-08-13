<script setup lang="ts">
import {ref} from "vue";
import {keycloak} from "@/main";

const wochenplaene = ref()

async function _fetch() {
  wochenplaene.value = await (await fetch(`/api/benutzer`)).json();
  console.log(wochenplaene.value)
}

async function _fetchWochenplan() {
  wochenplaene.value = await (await fetch(`/api/public/wochenplan`)).json();
  console.log(wochenplaene.value)
}


async function login() {
  keycloak.login().then(r => {
    console.log(r)
  })
}

async function logout() {
  keycloak.logout().then(r => {
    console.log(r)
  })
}

</script>

<template>

  <button @click="login">Anmelden</button>
  <button @click="logout">Abmelden</button>
  <button @click="_fetchWochenplan">Fetch Wochenplan</button>
  <button @click="_fetch">Fetch Benutzer</button>
</template>

<style scoped>
.btn-bold {
  font-weight: normal;
}

.icn-success {
  color: lightseagreen;
}

.icn-space {
  margin: 0 1rem;
  width: 1rem;
  text-align: center;
}

p {
  margin: 0 1rem;
  padding: 0;
}

h4 {
  margin-bottom: 1rem;
}
</style>