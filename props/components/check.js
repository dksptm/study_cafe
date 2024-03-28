// check.js

// Child.
let ChildComponent = {
    template : `
    <div>
        <p> 숫  자 : {{ num }} </p>
        <p> 문자열 : {{ str }} </p>
        <p> 짝  수 : {{ even }} </p>
        <p> 객  체 : {{ obj }} </p>
    </div>
    `,
    props : {
        num : {
            type : Number,
            required : true
        },
        str : {
            type : String,
            default : 'Hello!'
        },
        even : {
            validator(value) {
                return (value % 2 == 0);
            }
        },
        // obj : Object
        obj : {
            type : Object,
            default : () => {
                return {
                    name : 'Hong',
                    age : 20
                }
            }
        }
    }
}

// Parent.
export default {
    template : `
    <div>
        <ChildComponent v-bind:num="myNumber" :str="sendMsg" :even="myNumber" :obj="newObj" />
    </div>
    `,
    data(){
        return {
            myNumber : 55,
            sendMsg : 'Hello, World!',
            newObj : null
        }
    },
    components : {
        ChildComponent
    }
}