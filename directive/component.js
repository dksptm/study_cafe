//component.js

let component = {
    name : '',     
	template : ``,
	data(){},     
	computed : {},
    methods : {},
    watch : {},     
    components : {}
};


// data(){} =>
// data : function(){
//     return {
//     }
// }
// => 반드시 return 필요.

// {
//     title : 'Vue.js'
// } 이 부분을 프로퍼티라고 한다. 뷰가 자신들이 다루는 데이터. 
//말하자면 데이터는 내자식. 프로퍼티는 내손자

// 모듈에서 export 했기때문에 사용하는 입장에선 import해야.