// app.js

import router from './router/router.js';

import HeaderComponent from './layouts/header.js';
import FooterComponent from './layouts/footer.js';

const { createApp } = Vue

let template = `
<div>
    <!-- <router-link to="/"> Home link </router-link> -->
    <!-- <router-link v-bind:to="{ name : 'home'}">Home link name</router-link> -->
    <!-- || <router-link :to="{ path : 'postList' }"> 전체조회(10개만) </router-link> -->
    <!-- || <router-link :to="{ path : 'postInsert' }"> 단건등록 </router-link> -->
    <HeaderComponent />
    <router-view :key="$route.fullPath" />
    <FooterComponent />
</div>
`;

createApp({
    template,
    components : {
        HeaderComponent,
        FooterComponent
    }
})
.use(router)
.mount('#app');