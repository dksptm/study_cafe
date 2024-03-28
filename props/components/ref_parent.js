// ref_parent.js
import ChildComponent from './ref_child.js';

let template = `
<div>
    <ChildComponent ref="child" />
    <button type="button" @click="changeChildData"> Change Child Data </button>
    <button type="button" @click="clickChildEvent"> Child Click Event </button>
    <button type="button" @click="executeChildFunc"> Child Method Excute </button>
</div>
`;

export default {
    template,
    methods : {
        changeChildData() {
            //console.log(this.$refs);
            //console.log(this.$refs.child);
            this.$refs.child.msg = 'Data Update';
        },
        clickChildEvent() {
            //let childCom = this.$refs.child;
            //let childBtn = childCom.$refs.btn;
            //console.log(childBtn);
            //childBtn.click();
            this.$refs.child.$refs.btn.click();
        },
        executeChildFunc() {
            this.$refs.child.childFunc();
        }
    },
    components : {
        ChildComponent
    }
}