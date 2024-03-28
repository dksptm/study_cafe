// ref_child.js

let template = `
<div>
    <button type="button" @click="childClick()" ref="btn" id="cBtn"> click </button>
    <p style="background-color:skyblue;"> message : {{ msg }} </p>
</div>
`;

export default {
    template,
    data(){
        return {
            msg : ''
        }
    },
    mounted() {
        this.$refs.btn.click();
    },
    methods : {
        childClick() {
            console.log('click 이벤트 발생');
        },
        childFunc() {
            console.log('child method가 실행');
        }
    }
}