// input.js
let template = `
<div>
    <input type="text" v-model="myName"><br>
    <p> 나는 {{ myName }} 입니다.</p>
    <input type="number" v-model="myNum"><br>
    <p> 계산결과 : {{ myNum + 1000 }} </p>
    <textarea v-model.lazy="myComment" />
    <div>{{ myComment }}</div>
    <select v-model="mySelect" multiple>
        <option>선택1</option>
        <option>선택2</option>
        <option>선택3</option>
    </select><br>
    <!-- v-model directive : checked attribute-->
    <!-- checkbox 하나만 -->
    <input type="checkbox" v-model="myCheck" true-value="확인완료" false-value="미확인">확인여부<br>
    <p> {{ myCheck }} </p>
    <!-- checkbox 여러개-->
    <input type="checkbox" v-model="myHobbies" value="독서">독서<br>
    <input type="checkbox" v-model="myHobbies" value="여행">여행<br>
    <input type="checkbox" v-model="myHobbies" value="운동">운동<br>
    <p> 취미 : {{ myHobbies }} </p>

    <!-- 예제 -->
    <input type="checkbox" v-model="isAgree">약관에 동의합니다.<br>
    <button type="button" v-bind:disabled="!isAgree">회원가입</button><br>

    <!-- radio -->
    <input type="radio" v-model="myGender" value="남자">남자
    <input type="radio" v-model="myGender" value="여자">여자<br>
    <p> 성별 : {{ myGender }} </p>

</div>
`;

export default {
    template,
    data(){
        return {
            myName : '',
            myNum : 0,
            myComment : '',
            mySelect : [],
            myCheck : false,
            myHobbies : [],
            isAgree : false,
            myGender : ''
        }
    }
}