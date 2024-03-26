// components : {}

let template = `
<div>
    <!-- bind directive -->
    <p v-text="'Hello, ' + title" />
    <p> Hello, {{ title }} </p>
    <!-- bind directive -->
    <p v-bind:id="postId"
       name="temp" 
       text="Testing" />
    <img v-bind:src="fileName">
    <a v-bind:href="url">naver</a>
    <!-- bind directive style -->
    <p style="color : skyblue;">문자색 직접 지정</p>
    <p v-bind:style="{ color : myColor }">Vue 방식</p>
    <p style="color : skyblue; font-size : 200%;">두가지 속성을 직접 지정</p>
    <p v-bind:style="{ color : myColor, fontSize : myFont }">Vue 방식</p>
    <p v-bind:style="myStyle">Vue 방식</p>
</div>
`;

export default {
    template,
    data(){
        return {
            title : 'Vue.js',
            postId : 'raw',
            fileName : 'dog12.jpg',
            url : 'http://www.naver.com',
            myColor : 'red',
            myFont : '100px',
            myStyle : { 
                color : 'green',
                fontSize : '200%'
            }
        }
    }
}