// myName.js

// Child.
let ChildComponent = {
    template : `
        <div>
            <p> 내 이름은 {{ name }} 입니다. </p>
            <p> 변경된 이름 </p>
            <input type="text" v-model.lazy="updateName" @keyup.enter="updateMyName()">
        </div>
    `,
    data(){
        return {
            updateName : ''
        }
    },
    methods : {
        updateMyName() {
            this.$emit('update-name', this.updateName, '업데이트 진행');
        }
    },
    props : ['name']
};

// Parent.
export default {
    template : `
    <div>
        <ChildComponent v-bind:name="first" v-on:update-name="getName" />
        <ChildComponent v-bind:name="second" />
    </div>
    `,
    data(){
        return {
            first : 'Hong',
            second : 'Kang'
        }
    },
    methods : {
        getName(data, msg){
            console.log(data, msg);
            this.first = data;
        }
    },
    components : {
        ChildComponent
    }
}