// app.js

import router from './router/router.js';

const { createApp } = Vue

let template = `
<div>
    <router-link to="/"> Home link </router-link>
    <!-- <router-link v-bind:to="{ name : 'home'}">Home link name</router-link> -->
    || <router-link :to="{ path : 'postList' }"> 전체조회(10개만) </router-link>
    || <router-link to="/postInfo"> 단건조회(id:1) </router-link>
    <router-view :key="$route.fullPath" />
</div>
`;

createApp({
    template
})
.use(router)
.mount('#app');