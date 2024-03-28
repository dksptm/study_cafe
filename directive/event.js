// event.js
let template = `
<div>
    <button type="button" v-on:click="upCounter(), printMsg($event)">Add 1</button>
    <p>The counter is : {{ counter }}</p>
    <hr>
    <input type="number" v-model="num">
    <button type="button" v-on:click="increseCounter(num)">Add {{ num }}</button>
    <p>The counter is : {{ sum }}</p>
    <hr>
    <form v-on:click="showAlert('form')" style="border : 1px solid black;">
        <div v-on:click.self="showAlert('div')" style="border : 1px solid black;">
            <p v-on:click.once="showAlert('p')" style="border : 1px solid black;">
                <a v-on:click.prevent.stop href="http://www.naver.com"> 네이버 </a>
            </p>
            click div tag
        </div>
    </form>
    <hr>
    <input type="text" v-model="keyword" v-on:keyup.enter="showAlert(keyword)">
</div>
`;

export default {
    template,
    data() {
        return {
            counter : 0,
            num : 0,
            sum : 0,
            keyword : ''
        }
    },
    methods : {
        upCounter(data) {
            console.log(data);
            this.counter += 1;
        },
        printMsg(event) {
            console.log(event);
        },
        increseCounter(number) {
            this.sum += number;
        },
        showAlert(tag) {
           alert('click ' + tag);
        }
    }
}