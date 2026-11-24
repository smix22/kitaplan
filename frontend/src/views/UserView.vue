<script setup lang="ts">
import { ref } from 'vue'
import { keycloak } from '@/main'

const wochenplan = ref()
let anfangDerWoche = ref()
let endeDerWoche = ref()
const aktuellesDatum = ref()
const neuesDatum = ref()
const aktuelleWoche = ref(0)

aktuellesDatum.value = new Date()
if (aktuellesDatum.value.getDay() == 0) {
  aktuellesDatum.value.setDate(aktuellesDatum.value.getDate() + 1)
  aktuellesDatum.value = new Date(aktuellesDatum.value) }
if (aktuellesDatum.value.getDay() == 6) {
  aktuellesDatum.value.setDate(aktuellesDatum.value.getDate() + 2)
  aktuellesDatum.value = new Date(aktuellesDatum.value) }

neuesDatum.value = aktuellesDatum.value

getWochenplan(aktuelleWoche.value)

async function getWochenplan(wochenWechsel: number) {
  await getPlanByDatum(wochenWechsel)
  await scrollToHeute(new Date().toDateString())
}

async function getPlanByDatum(wochenWechsel: number) {
  aktuelleWoche.value += wochenWechsel
  neuesDatum.value.setDate(neuesDatum.value.getDate() + (wochenWechsel * 7))
  neuesDatum.value = new Date(neuesDatum.value)

  const datum = neuesDatum.value.toISOString().substring(0, 10)

  wochenplan.value = await (await fetch(`/api/public/plan/woche/${datum}`)).json()

  anfangDerWoche = wochenplan.value[0].datum
  endeDerWoche = wochenplan.value[4].datum
}

async function scrollToHeute(datum: string) {
  const element = document.getElementById(datum)

  if (element) {
    const top = element!.offsetTop - 254
    window.scrollTo(0, top)
  } else {
    window.scrollTo(0, 0)
  }
}

async function reload() {
  location.reload()
}

async function login() {
  await keycloak.login()
}
</script>

<template>
  <div class="d-flex-column align-items-start">
    <div id="title" class="sticky-top top">
      <div class="d-flex img-logo py-2">
        <img src="../assets/logo-mooswuffel.png" alt="Logo Kinderladen Mooswuffel" id="img-logo-size" @click="reload()">
      </div>
      <div class="btn-date-group btn-group pt-3 pb-2">
        <button
          :disabled="aktuelleWoche <= 0"
          class="btn btn-md btn-moos"
          @click="getWochenplan(-1)"
          v-bind:style="[aktuelleWoche <= 0 ? { color: 'lightgray', borderTop: '1px solid lightgray', borderRight: '1px solid forestgreen', borderBottom: '1px solid lightgray', borderLeft: '1px solid lightgray' } : { color: 'white', backgroundColor: 'forestgreen' }]"
        >&larr;
        </button>
        <button class="btn btn-md btn-moos px-3 btn-date">
          {{ new Date(anfangDerWoche).getDate() }}.
          {{
            new Date(anfangDerWoche).getMonth() != new Date(endeDerWoche).getMonth()
              ? new Date(anfangDerWoche).toLocaleString('de-de', { month: 'short' })
              : ''
          }}
          - {{ new Date(endeDerWoche).getDate() }}.
          {{ new Date(endeDerWoche).toLocaleString('de-de', { month: 'short' }) }}
          {{ new Date(endeDerWoche).toLocaleString('de-de', { year: 'numeric' }) }}
        </button>
        <button
          :disabled="aktuelleWoche > 2"
          class="btn btn-md btn-moos"
          @click="getWochenplan(1)"
          v-bind:style="[aktuelleWoche > 2 ? { color: 'lightgray', borderTop: '1px solid lightgray', borderRight: '1px solid lightgray', borderBottom: '1px solid lightgray', borderLeft: '1px solid forestgreen' } : { color: 'white', backgroundColor: 'forestgreen' }]"
        >&rarr;
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
@media screen and (max-width: 480px) {
  .btn-date-group {
    display: flex;
  }

  .btn-date {
    width: 100%;
  }

  .img-logo {
    justify-content: center;
  }

  #img-logo-size {
    width: 40%;
  }
}

@media screen and (min-width: 481px) {
  .img-logo {
    justify-content: start;
  }

  #img-logo-size {
    width: 100px;
  }
}

.top {
  background-color: white;
  padding: 2rem 1rem 2rem 1rem;
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
