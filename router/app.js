// app.js

import router from './routers/router.js';

const { createApp } = Vue

let template = `
<div>
    <router-link />
    <router-view />
</div>
`;

createApp({
    template
})
.use(router)
.mounte('#app');