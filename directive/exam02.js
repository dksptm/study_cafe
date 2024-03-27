// exam02.js => ForComponent

let template = `
<div>
    <div>
        <p v-for="line in 5" :key="line"> 
            <span v-for="num in line" :key="num"> {{ star }} </span>
        </p>
    </div>
    <br>
    <div>
        <p v-for="dan in dans" :key="dan"> 
            <p v-for="num in 9" :key="num"> {{ dan }} x {{ num }} = {{ dan * num }} </p><br>
        </p>
    </div>
    <br>
    <div>
        <p v-for="num in 9" :key="num"> 
            <p v-for="dan in dans" :key="dan"> {{ num }} x {{ dan }} = {{ dan * num }} </p><br>
        </p>
    </div>
</div>
`;

export default {
    template,
    data(){
        return {
            star : '☆',
            dans : [2, 3, 4, 5, 6, 7, 8, 9]
        }
    }
}