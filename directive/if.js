// if.js
let template = `
<div>
    <label>
        <input type="checkbox" v-model="myVisible">표시</label>
    <p v-show="myVisible">show 디렉티브</p>
    <p v-if="myVisible">체크박스가 ON</p>
    <p v-else>체크박스가 OFF</p>
    <hr>
    <label>
        점수 : <input type="text" v-model="score"></label>
    <p v-if="score >= 90">A</p>
    <p v-else-if="score >= 80">B</p>
    <p v-else-if="score >= 70">C</p>
    <p v-else-if="score >= 60">D</p>
    <p v-else>F</p>
    <hr>
    <!-- v-for 과 v-if -->
    <template v-for="dan in 9" :key="dan">
        <div v-if="dan%2 == 0">
            <p v-for="num in 9" :key="num"> 
                {{ dan }} x {{ num }} = {{ dan*num }}
            </p>
            <br>
        </div>
    </template>
</div>
`;

export default {
    template,
    data(){
        return {
            myVisible : false,
            score : 0
        }
    }
}