// slot_parent.js

import ChildComponent from './slot_child.js';

let template = `
<div>
    <ChildComponent>
        <template v-slot:title >
            <h1> 팝업 타이틀 </h1>
        </template>
        <p>다른 내용</p>
        <template v-slot:content >
            <p> 팝업 컨텐츠1 </p>
            <p> 팝업 컨텐츠2 </p>
        </template>
        <template v-slot:default >
            <button type="button"> click </button> 
        </template>
    </ChildComponent>
    <hr>
    <ChildComponent>
        <template v-slot:title >
            <h3> Info </h3>
        </template>
        <template v-slot:content >
            <p> Service </p>
            <input type="radio" v-model="select" value="A"> A유형 <br>
            <input type="radio" v-model="select" value="B"> B유형 
        </template>
        <template v-slot:default >
            <button type="button"> 완료 </button> 
        </template>
    </ChildComponent>
</div>
`;

export default {
    template,
    data() {
        return {
            select : ''
        }
    },
    components : {
        ChildComponent
    }
}

