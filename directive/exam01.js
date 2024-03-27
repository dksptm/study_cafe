// exam01.js => modelComponent

let template = `
<div>
    <p>이름입력 : </p>
    <input type="text" v-model.lazy="myName"><br>
    <p>좋아하는 색 선택(하나만)</p>
    <input type="radio" v-model="myColor" value="red">빨간색<br>
    <input type="radio" v-model="myColor" value="green">초록색<br>
    <input type="radio" v-model="myColor" value="blue">파란색<br>
    <input type="radio" v-model="myColor" value="yellow">노란색<br>
    <input type="radio" v-model="myColor" value="gray">회색<br>
    <p>나의 이름은 {{ myName }}이고 </p>
    <p>좋아하는 색은 {{ myColor }}입니다.</p>
</div>
`;

export default {
    template,
    date(){
        return {
            myName : '',
            myColor : ''
        }
    }
}