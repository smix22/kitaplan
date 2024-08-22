import './assets/main.css'

import {createApp} from 'vue'
import App from './App.vue'
import router from './router'

import Keycloak from 'keycloak-js';

export const keycloak = new Keycloak({
  url: 'http://localhost:9999',
  realm: 'kita-realm',
  clientId: 'frontend'
});


try {
  const authenticated = await keycloak.init({
    onLoad: 'check-sso'
  });
  console.log(`User is ${authenticated ? 'authenticated' : 'not authenticated'}`);
  // console.log(keycloak.token);
} catch (error) {
  console.error('Failed to initialize adapter:', error);
}

const _fetch = window.fetch;

window.fetch = (input: RequestInfo | URL, init?: RequestInit | undefined) => {
  const token = keycloak.token;

  if (!token) {
    return _fetch(input, init)
  }

  return _fetch(input, {
    ...init, headers: {
      ...init?.headers,
      "Authorization": `Bearer ${token}`
    }
  })

}

const app = createApp(App)

app.use(router)

app.mount('#app')
