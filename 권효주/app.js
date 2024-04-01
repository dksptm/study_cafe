// app.js
import router from './router/router.js';

import HeaderComponent from './layouts/header.js';

const { createApp } = Vue

let template = `
<div class="container">
  <HeaderComponent />
  <hr>
  <router-view />
</div>
`;

createApp({
  template,
  components : {
    HeaderComponent
  }
})
.use(router)
.mount('#app');