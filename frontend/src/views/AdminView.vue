<script setup lang="ts">
import { keycloak } from '@/main'
import { reactive, ref } from 'vue'

const wochenplan = ref()
const wochenplaene = ref()
let selectWoche = ref('')
const tagOffen = ref([''])
const tagStart = ref([''])
const tagEnde = ref([''])
const tagWald = ref([''])
const tagAbfahrt = ref([''])
const tagKommentar = ref([''])
const successPlanUpdate = ref(false)

async function _fetchCurrentPlan() {
  const datum = new Date().toISOString().substring(0, 10)
  wochenplan.value = await (await fetch(`/api/public/plan/woche/${datum}`)).json()
  selectWoche.value = wochenplan.value[0].woche
}

async function _fetchPlanByWeek(woche: string) {
  wochenplan.value = await (await fetch(`/api/public/plan/wochen/${woche}`)).json()
}

async function _fetchAllPlans() {
  wochenplaene.value = await (await fetch(`/api/public/plaene`)).json()
}

async function updatePlan(id: number, index: number) {
  let update: boolean = true
  if (!tagStart.value[index] || !tagEnde.value[index] || !tagAbfahrt.value[index]) update = false

  if (update) {
    await fetch(`/api/public/plan/tag/${id}`, {
      method: 'PUT',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        id: id,
        wald: tagWald.value[index],
        offen: tagOffen.value[index],
        start: tagStart.value[index],
        ende: tagEnde.value[index],
        abfahrt: tagAbfahrt.value[index],
        kommentar: tagKommentar.value[index]
      })
    }).then((response) => {
      if (response.ok) {
        successPlanUpdate.value = true
        setTimeout(() => successPlanUpdate.value = false, 2000)
      }
    })
  } else {
    await _fetchPlanByWeek(selectWoche.value)
  }
}

async function logout() {
  keycloak.logout().then(r => {
    console.log(r)
  })
}

_fetchAllPlans()
_fetchCurrentPlan()
</script>

<template>
  <div class="d-flex-column align-items-start">
    <div id="title" class="sticky-top p-3" style="background-color: white;">
      <div class="py-2"></div>
      <h1 class="txt-admin pt-3">Plan bearbeiten</h1>
      <div class="form-group row pt-2 pb-3">
        <div class="col-auto d-flex align-items-center">
          <select class="form-select" v-model="selectWoche"
                  @change="_fetchPlanByWeek(selectWoche)">
            <option value="" selected disabled>Woche auswählen</option>
            <option v-for="option in wochenplaene" :value="option.woche">
              {{ new Date(option.datum).getDate() }}.
              {{ new Date(option.datum).toLocaleString('de-de', { month: 'long' }) }}
              {{ new Date(option.datum).toLocaleString('de-de', { year: 'numeric' }) }}
              (KW {{ option.woche.substring(5, 7) }})
            </option>
          </select>
          <i v-if="successPlanUpdate" class="fa fa-check txt-mw" style="margin-left: 1rem;"></i>
        </div>
      </div>
    </div>

    <div>
      <div class="d-flex flex-wrap justify-content-start">
        <div v-for="(tag, index) in wochenplan"
             class="d-flex flex-grow-1 flex-sm-grow-0 flex-md-grow-0">
          <div class="card m-3 flex-grow-1"
               style="width: 200px;"
               v-bind:style="[new Date().toDateString() == new Date(tag.datum).toDateString() ? {'color': 'forestgreen', 'border': '1px solid forestgreen'} : {'color': 'gray'}]">
            <div class="card-header"
                 v-bind:style="[new Date().toDateString() == new Date(tag.datum).toDateString() ? {'background-color': 'forestgreen', 'color': 'white', 'border-radius': '0'} : {}]">
              <h5 class="my-1">{{
                  new Date(tag.datum).toLocaleString('de-de', { weekday: 'long' })
                }}</h5>
              <p class="card-title m-0">{{ new Date(tag.datum).getDate() }}.
                {{ new Date(tag.datum).toLocaleString('de-de', { month: 'long' }) }}
                {{ new Date(tag.datum).toLocaleString('de-de', { year: 'numeric' }) }}</p>
            </div>
            <div class="card-body"
                 v-bind:style="[new Date().toDateString() == new Date(tag.datum).toDateString() ? {'color': 'forestgreen'} : {'color': 'gray'}]">
              <p class="card-text m-0">
                <input type="checkbox" :name="tagOffen[index]=tag.offen" v-model="tag.offen"
                       :disabled="new Date(tag.datum + 'T16:30:00') < new Date()"
                       @change="updatePlan(tag.id, index)"> <b>Öffnungszeiten</b></p>
              <div v-if="tag.offen">
                <div class="input-group input-group-sm pt-2">
                  <span class="input-group-text" style="width: 40px;">von</span>
                  <input v-model="tag.start" class="form-control" required type="time"
                         :name="tagStart[index]=tag.start" @blur="updatePlan(tag.id, index)"
                         :disabled="new Date(tag.datum + 'T16:30:00') < new Date()"
                         v-bind:style="[!tagStart[index] ? {'border': '1px solid red'} : {}]">
                </div>
                <div class="input-group input-group-sm pt-2 pb-1">
                  <span class="input-group-text" style="width: 40px;">bis</span>
                  <input v-model="tag.ende" class="form-control" required type="time"
                         :name="tagEnde[index]=tag.ende" @blur="updatePlan(tag.id, index)"
                         :disabled="new Date(tag.datum + 'T16:30:00') < new Date()"
                         v-bind:style="[!tagEnde[index] ? {'border': '1px solid red'} : {}]">
                </div>
                <p class="m-0 pt-3">
                  <input type="checkbox" :name="tagWald[index]=tag.wald" v-model="tag.wald"
                         :disabled="new Date(tag.datum + 'T16:30:00') < new Date()"
                         @change="updatePlan(tag.id, index)"> <i class="fa fa-tree"></i>
                  <b> Waldtag</b>
                </p>
                <div v-if="tag.wald" class="input-group input-group-sm pt-2">
                  <span class="input-group-text">Abfahrt</span>
                  <input type="time" class="form-control" :name="tagAbfahrt[index]=tag.abfahrt"
                         v-model="tag.abfahrt" required @blur="updatePlan(tag.id, index)"
                         :disabled="new Date(tag.datum + 'T16:30:00') < new Date()"
                         v-bind:style="[!tagAbfahrt[index] ? {'border': '1px solid red'} : {}]">
                </div>
                <div v-else>
                  <input type="hidden" :name="tagAbfahrt[index]=tag.abfahrt"
                         v-model="tag.abfahrt">
                </div>
              </div>
              <p class="card-text m-0 pt-3 pb-2"><b>Kommentar:</b></p>
              <div class="input-group input-group-sm pb-1">
                <input type="text" class="form-control" :name="tagKommentar[index]=tag.kommentar"
                       v-model="tag.kommentar" @blur="updatePlan(tag.id, index)"
                       :disabled="new Date(tag.datum + 'T16:30:00') < new Date()">
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <div style="height: 2rem;"></div>

    <div class="d-flex justify-content-start">
      <a class="btn btn-sm btn-anmelden mx-3" v-if="keycloak.authenticated"
         @click="logout">Abmelden</a>
    </div>

    <div style="height: 3rem;"></div>
  </div>
</template>

<style scoped>
.txt-mw {
  color: forestgreen;
}

.txt-admin {
  color: darkgoldenrod;
}

.btn-anmelden {
  border: 1px solid darkgoldenrod;
  background-color: white;
  color: darkgoldenrod;
}
</style>