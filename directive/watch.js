// watch.js
let template = `
<div>
    공급가액 : <input type="number" v-model="price">원
    <!-- computed -->
    <p> 최종 소비자가 : {{ taxIncluded }} </p> 
    <!-- methods -->
    <p> 최종 소비자가 : {{ getFinalPrice() }} </p>
    <!-- why? -->
    <!-- getFinalPrice() 은 2번 사용 / taxIncluded 은 1번만 사용 -->
    <p> 공급가액 50% 할인 : {{ taxIncluded * 0.5 }} </p>
    <p> 공급가액 50% 할인 : {{ getFinalPrice() * 0.5 }} </p>
    <hr>
    구입갯수 : <input type="number" v-model.lazy="count"> 개
    <p>결제금액 : {{ total }}원 </p>
    <p>부 가 세 : {{ tax }}원 </p>
    <p>순 수 익 : {{ revenue }}원 </p>
    <hr>
    <p>금지문자는 [ {{ forbiddenText }} ]</p>
    <textarea v-model="inputText"></textarea>
</div>
`;

export default {
    template,
    data(){
        return {
            price : 0,
            count : 0,
            inputText : '',
            forbiddenText : '오늘'
        }
    },
    computed : { // 프로퍼티다 => 매개변수X, return필수, 내부에 (data,computed) 프로퍼티가 반드시 포함되어야.
        taxIncluded() {
            return this.price * 1.1;
        },
        total() {
            return this.taxIncluded * this.count;
        },
        tax() {
            return Math.round(this.total * (0.1/1.1));
        },
        revenue() {
            return this.total - this.tax;
        }
    },
    methods : {
        getFinalPrice(){
            return this.price * 1.1;
        }
    },
    watch : { // 프로퍼티가 변경될 경우 무슨 작업을 하겠다 정의 => return X
        count(){
            alert('구입갯수를 입력했습니다.');
        },
        inputText(){
            let pos = this.inputText.indexOf(this.forbiddenText);
            console.log(pos);
            if(pos > -1){
                alert(this.forbiddenText + '는 입력할 수 없습니다.');
                this.inputText = this.inputText.substr(0, pos);
            }            
        }
    }
}
