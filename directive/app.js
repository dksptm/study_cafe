// app.js
// => Root Component

import RawComponent from './raw.js';
import InputComponent from './input.js';
import ModelComponent from './exam01.js';
import ListComponent from './list.js';

const { createApp } = Vue

const template = `
<div>
    <!-- 자식 컴포넌트 추가 -->
    <!-- <raw-component /> -->
    <!-- input 컴포넌트 추가 -->
    <!--<input-component /> -->
    <!-- 예제 -->
    <!--<Model-component />-->
    <ListComponent />
</div>
`;

createApp({
    template,
    components : {
        RawComponent, // 'raw-componenet' : RawComponent
        InputComponent,
        ModelComponent,
        ListComponent
    }
})
.mount('#app');