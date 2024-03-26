// app.js
// => Root Component

import RawComponent from './raw.js';

const { createApp } = Vue

const template = `
<div>
    <!-- 자식 컴포넌트 추가 -->
    <raw-component/>
</div>
`;

createApp({
    template,
    components : {
        RawComponent, // 'raw-componenet' : RawComponent
    }
})
.mount('#app');