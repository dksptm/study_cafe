// app.js
// => Root Component

import RawComponent from './raw.js';
import InputComponent from './input.js';

const { createApp } = Vue

const template = `
<div>
    <!-- 자식 컴포넌트 추가 -->
    <!-- <raw-component /> -->
    <!-- input 컴포넌트 추가 -->
    <input-component />
    <!-- 예제 -->
    <!--<model-component />-->
</div>
`;

createApp({
    template,
    components : {
        RawComponent, // 'raw-componenet' : RawComponent
        InputComponent,
        ModelComponent
    }
})
.mount('#app');