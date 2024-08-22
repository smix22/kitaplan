<script setup lang="ts">
import {ref} from "vue";
import {keycloak} from "@/main";

const wochenplan = ref()

async function _fetchPlanByDatum() {
  const heute = new Date().getDay()
  if (heute >= 1 && heute <= 5) {
    const datum = new Date().toISOString().substring(0, 10);
    wochenplan.value = await (await fetch(`/api/public/plan/woche/${datum}`)).json();
  } else {
    const naechsteWoche = new Date();
    naechsteWoche.setDate(naechsteWoche.getDate() + 2);
    const datum = naechsteWoche.toISOString().substring(0, 10);
    wochenplan.value = await (await fetch(`/api/public/plan/woche/${datum}`)).json();
  }
}

async function _fetchCurrentPlan() {
  const datum = new Date().toISOString().substring(0, 10);
  wochenplan.value = await (await fetch(`/api/public/plan/woche/datum/${datum}`)).json();
}

async function _fetchPlanByWoche(woche: string) {
  wochenplan.value = await (await fetch(`/api/public/plan/${woche}`)).json();
}

async function login() {
  await keycloak.login()
}

_fetchCurrentPlan()
</script>

<template>
  <div style="height: 1rem;"></div>

  <div class="d-flex-column align-items-start">
    <h3 class="txt-mw px-3">Kila Mooswuffel</h3>
    <h6 class="txt-mw px-3 pb-3">
      {{ new Date(wochenplan[0].datum).getDate() }}.
      - {{ new Date(wochenplan[4].datum).getDate() }}.
      {{ new Date(wochenplan[4].datum).toLocaleString('de-de', {month: 'long'}) }}
      {{ new Date(wochenplan[4].datum).toLocaleString('de-de', {year: 'numeric'}) }}</h6>
    <div>
      <div class="d-flex flex-wrap justify-content-start align-items-baseline">
        <div v-for="(tag) in wochenplan">
          <div v-if="new Date(tag.datum).getDate() >= new Date().getDate()" class="card m-3"
               style="width: 200px;"
               v-bind:style="[new Date().toDateString() == new Date(tag.datum).toDateString() ? {'color': 'forestgreen', 'border': '2px solid forestgreen'} : {'color': 'gray'}]">
            <div class="card-header"
                 v-bind:style="[new Date().toDateString() == new Date(tag.datum).toDateString() ? {'background-color': 'forestgreen', 'color': 'white', 'border-radius': '0'} : {}]">
              <h5 class="my-1"><i v-if="tag.wald && tag.offen" class="fa fa-tree"></i> {{
                  new Date(tag.datum).toLocaleString('de-de', {weekday: 'long'})
                }}</h5>
              <p class="card-title m-0">{{ new Date(tag.datum).getDate() }}.
                {{ new Date(tag.datum).toLocaleString('de-de', {month: 'long'}) }}
                {{ new Date(tag.datum).toLocaleString('de-de', {year: 'numeric'}) }}</p>
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
              <p v-if="tag.kommentar" class="card-text txt-red m-0 pt-3">{{ tag.kommentar }}</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div style="height: 2rem;"></div>

    <div class="d-flex justify-content-start">
      <button v-if="!keycloak.authenticated" @click="login" class="btn btn-link">Anmelden</button>
    </div>
  </div>
</template>

<style scoped>
.txt-mw {
  color: forestgreen;
}

.txt-red {
  color: darkred;
}
</style>