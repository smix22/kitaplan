<script setup lang="ts">
import {ref} from "vue";
import {keycloak} from "@/main";

const benutzer = ref()
const plaene = ref()
const wochenplan = ref()

async function _fetch() {
  benutzer.value = await (await fetch(`/api/benutzer`)).json();
  console.log(benutzer.value)
}

async function _fetchPlan() {
  plaene.value = await (await fetch(`/api/public/plan`)).json();
  console.log(plaene.value)
}

async function _fetchPlanByWoche(woche: string) {
  wochenplan.value = await (await fetch(`/api/public/plan/${woche}`)).json();
  console.log(wochenplan.value)
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

_fetchPlanByWoche("2024-33")
</script>

<template>
  <div>
    <table style="width: 1000px;">
      <tr>
        <td v-for="(tag) in wochenplan" style="width: 20%; vertical-align: top; padding: 0.5rem;">
          <table v-if="new Date().toDateString() == new Date(tag.datum).toDateString()"
                 style="width: 100%;">
            <tr>
              <td>
                <p style="padding-bottom: 0.5rem;" class="heute"><b>{{ tag.wochentag }},<br>
                  {{ new Date(tag.datum).getDate() }}.
                  {{ new Date(tag.datum).toLocaleString('de-de', {month: 'long'}) }}
                  {{ new Date(tag.datum).toLocaleString('de-de', {year: 'numeric'}) }}</b></p>
              </td>
            </tr>
            <tr style="border: 3px solid forestgreen;">
              <td style="padding: 0.5rem 0;">
                <p class="heute"><b>Öffnungszeiten:<br>{{
                    tag.start
                  }} -
                  {{ tag.ende }} Uhr</b></p>
                <p v-if="tag.wald" style="padding-top: 0.5rem;"><b><i class="fa fa-tree"></i> Waldtag<br>Abfahrt: {{
                    tag.abfahrt
                  }}
                  Uhr</b></p>
              </td>
            </tr>
          </table>
          <table v-else class="nichtheute" style="width: 100%;">
            <tr>
              <td>
                <p style="padding-bottom: 0.5rem;">{{ tag.wochentag }},<br>
                  {{ new Date(tag.datum).getDate() }}.
                  {{ new Date(tag.datum).toLocaleString('de-de', {month: 'long'}) }}
                  {{ new Date(tag.datum).toLocaleString('de-de', {year: 'numeric'}) }}</p>
              </td>
            </tr>
            <tr style="border: 3px solid lightgray;">
              <td style="padding: 0.5rem 0;">
                <p class="nichtheute">
                  Öffnungszeiten:<br>{{ tag.start }} - {{
                    tag.ende
                  }} Uhr</p>
                <p v-if="tag.wald" style="padding-top: 0.5rem;"><b><i class="fa fa-tree"></i> Waldtag<br>Abfahrt: {{
                    tag.abfahrt
                  }}
                  Uhr</b></p>
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </div>

  <div style="height: 10rem;"></div>

  <div>
    <button @click="login">Anmelden</button>
    <button @click="logout">Abmelden</button>
    <button @click="_fetchPlan">Fetch Plan</button>
    <button @click="_fetch">Fetch Benutzer</button>
  </div>
</template>

<style scoped>
.heute {
  color: forestgreen;
}

.nichtheute {
  color: gray;
}

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