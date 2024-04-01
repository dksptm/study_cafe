// postInsert.js

let template = `
<div>
    <table>
        <tr>
            <th>id</th>
            <td><input type="text" v-model="post.id" readonly></td>
        </tr>
        <tr>
            <th>title</th>
            <td><input type="text" v-model="post.title"></td>
        </tr>
        <tr>
            <th>userId</th>
            <td><input type="text" v-model="post.userId"></td>
        </tr>
        <tr>
            <th>body</th>
            <td>
                <textarea rows="10" cols="30" v-model="post.body"></textarea>
            </td>
        </tr>
        <tr>
            <button type="button" @click="createPostInfo()">INSERT</button>
        </tr>
    </table>
</div>
`;

export default {
    template,
    data() {
        return {
            post : {
                id :'',
                title : '',
                body : '',
                userId : ''
            }
        }
    },
    methods : {
        createPostInfo() {
            let data = {
                title : this.post.title,
                body : this.post.body,
                userId : this.post.userId
            }
            
            fetch('https://jsonplaceholder.typicode.com/posts',{
                method : 'POST',
                headers : {
                    'content-type' : 'application/json'
                },
                body : JSON.stringify(data)
            })
            .then(res => res.json())
            .then(result => {
                console.log(result);
                if(result != null){
                    alert('정상적으로 저장되었습니다.');
                    this.post.id = result.id;
                } else {
                    alert('저장되지 않았습니다.\n데이터를 확인해주세요');
                }
            })
            .catch(err => console.error(err));
        }
    }
}