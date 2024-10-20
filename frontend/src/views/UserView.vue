<script setup lang="ts">
import { ref } from 'vue'
import { keycloak } from '@/main'

const wochenplan = ref()
let anfangDerWoche = ref()
let endeDerWoche = ref()
let isAktuell = ref(true)
let woche = ref()

async function getPlanByWoche(woche: number, aktuelleWoche: boolean) {
  isAktuell.value = aktuelleWoche
  const jahr = new Date().getFullYear()
  wochenplan.value = await (await fetch(`/api/public/plan/wochen/${jahr}-${woche}`)).json()
  anfangDerWoche = wochenplan.value[0].datum
  endeDerWoche = wochenplan.value[4].datum
}

async function login() {
  await keycloak.login()
}

async function getWoche() {
  const heute = new Date().getTime()
  const neujahr = new Date(new Date().getFullYear(), 0, 1).getTime()
  let tage = Math.floor((heute - neujahr) / 86_400_000)
  woche.value = Math.floor(tage / 7) + 1
}

getWoche()

if (new Date().getDay() >= 1 && new Date().getDay() <= 5) getPlanByWoche(woche.value, true)
else getPlanByWoche(woche.value + 1, false)

// _fetchPlanByDatum(true, new Date().getDate())
</script>

<template>
  <div class="d-flex-column align-items-start">
    <div id="title" class="sticky-top p-3" style="background-color: white;">
      <div class="py-2"></div>
      <h1 class="txt-mw pt-3">Kila Mooswuffel</h1>
      <!--      <h6 class="txt-mw pb-2">-->
      <!--        <i class="fa fa-calendar px-1"></i>-->
      <!--        {{ new Date(anfangDerWoche).getDate() }}.-->
      <!--        {{ new Date(anfangDerWoche).getMonth() != new Date(endeDerWoche).getMonth() ? new Date(anfangDerWoche).toLocaleString('de-de', { month: 'long' }) : ''-->
      <!--        }} - -->
      <!--        {{ new Date(endeDerWoche).getDate() }}.-->
      <!--        {{ new Date(endeDerWoche).toLocaleString('de-de', { month: 'long' }) }}-->
      <!--        {{ new Date(endeDerWoche).toLocaleString('de-de', { year: 'numeric' }) }}-->
      <!--      </h6>-->
      <div class="btn-group pt-2 pb-3">
        <a class="btn btn-sm" @click="getPlanByWoche(woche, true)"
           v-bind:style="isAktuell ? 'background-color: forestgreen; color: white;' : 'background-color: white; border: 1px solid forestgreen; color: forestgreen;'">Aktuelle
          Woche</a>
        <a class="btn btn-sm" @click="getPlanByWoche(woche + 1, false)"
           v-bind:style="!isAktuell ? 'background-color: forestgreen; color: white;' : 'background-color: white; border: 1px solid forestgreen; color: forestgreen;'">Nächste
          Woche</a>
      </div>
<!--      <div style="height: 1rem;"></div>-->
    </div>

    <div>
      <div class="d-flex flex-wrap justify-content-start"><!--align-items-baseline-->
        <div v-for="(tag) in wochenplan" class="d-flex flex-grow-1 flex-sm-grow-0 flex-md-grow-0">
          <div class="card m-3 flex-grow-1"
               style="width: 200px;"
               v-bind:style="[new Date().toDateString() == new Date(tag.datum).toDateString() ? {'color': 'forestgreen', 'border': '1px solid forestgreen'} : {'color': 'gray'}]">
            <div class="card-header"
                 v-bind:style="[new Date().toDateString() == new Date(tag.datum).toDateString() ? {'background-color': 'forestgreen', 'color': 'white', 'border-radius': '0'} : {}]">
              <h5 class="my-1"><i v-if="tag.wald && tag.offen" class="fa fa-tree"></i> {{
                  new Date(tag.datum).toLocaleString('de-de', { weekday: 'long' })
                }}</h5>
              <p class="card-title m-0">{{ new Date(tag.datum).getDate() }}.
                {{ new Date(tag.datum).toLocaleString('de-de', { month: 'long' }) }}
                {{ new Date(tag.datum).toLocaleString('de-de', { year: 'numeric' }) }}</p>
            </div>
            <div class="card-body"
                 v-bind:style="[new Date().toDateString() == new Date(tag.datum).toDateString() ? {'color': 'forestgreen'} : {'color': 'gray'}]">
              <p class="card-text m-0"><b>Öffnungszeiten:</b></p>
              <div v-if="tag.offen">
                {{ tag.start.substring(0, 5) }} - {{ tag.ende.substring(0, 5) }} Uhr
                <p v-if="tag.wald" class="card-text m-0 pt-3 pb-0">
                  <i class="fa fa-tree"></i>
                  <b> Waldtag</b><br>
                  Abfahrt: {{ tag.abfahrt.substring(0, 5) }} Uhr</p>
              </div>
              <div v-else>
                <p class="card-text m-0">geschlossen</p></div>
              <p v-if="tag.kommentar" class="card-text txt-red m-0 pt-3"><i class="fa fa-bell"></i>
                {{ tag.kommentar }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div style="height: 2rem;"></div>

    <div class="d-flex justify-content-start">
      <a class="btn btn-sm btn-anmelden mx-3" v-if="!keycloak.authenticated"
         @click="login">Anmelden</a>
    </div>

    <div style="height: 3rem;"></div>
  </div>
</template>

<style scoped>
.txt-mw {
  color: forestgreen;
}

.btn-anmelden {
  border: 1px solid darkgoldenrod;
  background-color: white;
  color: darkgoldenrod;
}

.txt-red {
  color: darkred;
}
</style>