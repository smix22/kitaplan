<script setup lang="ts">
import { ref } from 'vue'
import { keycloak } from '@/main'

const wochenplan = ref()
let woche = ref()
let anfangDerWoche = ref()
let endeDerWoche = ref()
let aktuelleWoche = ref()
let isAktuelleWoche = ref(true)
let aktuellesDatum = ref()
let isAktuellesDatum = ref(true)

// Bei Seitenaufruf aktuelle Woche laden
getAktuelleWoche()

async function getAktuellesDatum() {
  const datum = new Date()
  aktuellesDatum.value = datum

  const neujahr = new Date(new Date().getFullYear(), 0, 1)
  const tage = Math.floor((datum.getTime() - neujahr.getTime()) / 86_400_000)
  woche.value = Math.floor(tage / 7) + 1
  let tageDiff = 0

  // Am Wochenende nächste Woche laden
  if (new Date().getDay() == 0 || new Date().getDay() == 6) {
    woche.value += 1
    aktuellesDatum.value.setDate(aktuellesDatum.value.getDate() + 7)
    tageDiff += 7
  }

  aktuelleWoche.value = woche.value

  await getPlanByDatum(tageDiff)
  await scrollToHeute(new Date().toDateString())
}

async function getPlanByDatum(tageDiff: number) {
  let datum = new Date()
  datum.setDate(datum.getDate() + tageDiff)

  const neujahr = new Date(datum.getFullYear(), 0, 1)
  const tage = Math.floor((datum.getTime() - neujahr.getTime()) / 86_400_000)
  woche.value = Math.floor(tage / 7) + 1

  const jahr = datum.getFullYear()

  wochenplan.value = await (await fetch(`/api/public/plan/wochen/${jahr}-${woche.value}`)).json()

  anfangDerWoche = wochenplan.value[0].datum
  endeDerWoche = wochenplan.value[4].datum
}

async function getAktuelleWoche() {
  const datum = new Date()
  const neujahr = new Date(new Date().getFullYear(), 0, 1)
  const tage = Math.floor((datum.getTime() - neujahr.getTime()) / 86_400_000)
  woche.value = Math.floor(tage / 7) + 1

  // Am Wochenende nächste Woche laden
  if (new Date().getDay() == 0 || new Date().getDay() == 6) woche.value += 1

  aktuelleWoche.value = woche.value

  await getPlanByWoche(woche.value)
  await scrollToHeute(new Date().toDateString())

  // var heuteNaechsteWoche = new Date();
  // var heuteNaechsteWoche = new Date(heuteNaechsteWoche.getTime() + 7 * 24 * 60 * 60 * 1000);
  // var neujahrNaechsteWoche = new Date(heuteNaechsteWoche.getFullYear(), 0, 1);
  // naechsteWoche.value = Math.floor((heuteNaechsteWoche - neujahrNaechsteWoche) / 86_400_000 / 7) + 1;
}

async function getPlanByWoche(neueWoche: number) {
  isAktuelleWoche.value = neueWoche == aktuelleWoche.value
  woche.value = neueWoche

  const jahr = new Date().getFullYear()

  wochenplan.value = await (await fetch(`/api/public/plan/wochen/${jahr}-${woche.value}`)).json()

  anfangDerWoche = wochenplan.value[0].datum
  endeDerWoche = wochenplan.value[4].datum
}

async function scrollToHeute(datum: string) {
  const element = document.getElementById(datum)

  if (element) {
    const top = element!.offsetTop - 168
    window.scrollTo(0, top)
  } else {
    window.scrollTo(0, 0)
  }
}

async function login() {
  await keycloak.login()
}
</script>

<template>
  <div class="d-flex-column align-items-start">
    <div id="title" class="sticky-top top">
      <div class="py-2"></div>
      <h1 class="txt-moos pt-2"><i class="fa fa-leaf px-1"></i> Kila Mooswuffel</h1>
      <div class="btn-group pt-2 pb-3">
        <button
          :disabled="woche <= aktuelleWoche"
          class="btn btn-sm btn-moos"
          @click="getPlanByWoche(woche - 1)"
        >
          <i class="fa fa-angle-left"></i><i class="fa fa-angle-left"></i>
        </button>
        <button class="btn btn-sm btn-moos px-3">
          {{ new Date(anfangDerWoche).getDate() }}.
          {{
            new Date(anfangDerWoche).getMonth() != new Date(endeDerWoche).getMonth()
              ? new Date(anfangDerWoche).toLocaleString('de-de', { month: 'long' })
              : ''
          }}
          - {{ new Date(endeDerWoche).getDate() }}.
          {{ new Date(endeDerWoche).toLocaleString('de-de', { month: 'short' }) }}
          {{ new Date(endeDerWoche).toLocaleString('de-de', { year: 'numeric' }) }}
        </button>
        <button
          :disabled="woche - aktuelleWoche > 2"
          class="btn btn-sm btn-moos"
          @click="getPlanByWoche(woche + 1)"
        >
          <i class="fa fa-angle-right"></i><i class="fa fa-angle-right"></i>
        </button>
      </div>
    </div>

    <div class="d-flex flex-wrap justify-content-start align-items-baseline crd-gap mx-3">
      <div
        v-for="tag in wochenplan"
        class="d-flex flex-grow-1 flex-sm-grow-0 flex-md-grow-0"
        :id="new Date(tag.datum).toDateString()"
      >
        <div
          class="card flex-grow-1"
          style="width: 200px"
          v-bind:style="[
            new Date().toDateString() == new Date(tag.datum).toDateString()
              ? { color: 'forestgreen', border: '1px solid forestgreen' }
              : { color: 'gray' }
          ]"
        >
          <div
            class="card-header"
            v-bind:style="[
              new Date().toDateString() == new Date(tag.datum).toDateString()
                ? { 'background-color': 'forestgreen', color: 'white', 'border-radius': '0' }
                : {}
            ]"
          >
            <h5 class="my-1">
              <i v-if="tag.wald && tag.offen" class="fa fa-tree"></i>
              {{ new Date(tag.datum).toLocaleString('de-de', { weekday: 'long' }) }}
            </h5>
            <p class="card-title m-0">
              {{ new Date(tag.datum).getDate() }}.
              {{ new Date(tag.datum).toLocaleString('de-de', { month: 'long' }) }}
              {{ new Date(tag.datum).toLocaleString('de-de', { year: 'numeric' }) }}
            </p>
          </div>
          <div
            class="card-body"
            v-bind:style="[
              new Date().toDateString() == new Date(tag.datum).toDateString()
                ? { color: 'forestgreen' }
                : { color: 'gray' }
            ]"
          >
            <p class="card-text m-0"><b>Öffnungszeiten:</b></p>
            <div v-if="tag.offen">
              {{ tag.start.substring(0, 5) }} - {{ tag.ende.substring(0, 5) }} Uhr
              <p v-if="tag.wald" class="card-text m-0 pt-3 pb-0">
                <i class="fa fa-tree"></i>
                <b> Waldtag</b><br />
                Abfahrt: {{ tag.abfahrt.substring(0, 5) }} Uhr
              </p>
            </div>
            <div v-else>
              <p class="card-text m-0">geschlossen</p>
            </div>
            <p v-if="tag.kommentar" class="card-text txt-red m-0 pt-3">
              <i class="fa fa-bell"></i> {{ tag.kommentar }}
            </p>
          </div>
        </div>
      </div>
    </div>

    <div style="height: 3rem"></div>

    <div class="d-flex justify-content-start" id="anmelden">
      <a class="btn btn-sm btn-anmelden mx-3" v-if="!keycloak.authenticated" @click="login"
        >Anmelden</a
      >
    </div>

    <div style="height: 3rem"></div>
  </div>
</template>

<style scoped>
.top {
  background-color: white;
  padding: 1rem 1rem 2rem 1rem;
}
.txt-moos {
  color: forestgreen;
}

.btn-anmelden {
  border: 1px solid darkgoldenrod;
  background-color: white;
  color: darkgoldenrod;
}

.btn-moos {
  background-color: white;
  border: 1px solid forestgreen;
  color: forestgreen;
  font-weight: bolder;
  white-space: nowrap;
}

.crd-gap {
  gap: 2rem;
}

.txt-red {
  color: darkred;
}
</style>
