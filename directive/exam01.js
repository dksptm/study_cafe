// exam01.js => modelComponent

let template = `
<div>
    <h3> 자기소개 </h3>
    <p>이름입력 : </p>
    <input type="text" v-model.lazy="myName"><br>
    
    <p>좋아하는 색은 </p>
    <label>
    <input type="radio" v-model="myColor" value="red">빨간색<br>
    <input type="radio" v-model="myColor" value="yellow">노란색<br>
    <input type="radio" v-model="myColor" value="green">초록색<br>
    <input type="radio" v-model="myColor" value="blue">파란색<br>
    <input type="radio" v-model="myColor" value="gray">회색<br>
    </label>

    <p>좋아하는 색은 </p>
    <select v-model="mySelectColor" v-bind:disabled="myName.length == 0">
        <option value="red">빨간색</option>
        <option value="yellow">노란색</option>
        <option value="green">초록색</option>
        <option value="blue">파란색</option>
        <option value="gray">회색</option>
    </select>

    <p> 나의 이름은 {{ myName }} <br> 내가 좋아하는 색은 {{ myColor }} 입니다.</p>
    <p> 내가 좋아하는 색은 {{ mySelectColor }} 입니다.</p>

</div>
`;

export default {
    template,
    data(){
        return {
            myName : '',
            myColor : '',
            mySelectColor : ''
        }
    }
}