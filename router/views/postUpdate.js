// postUpdate.js

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
            <button type="button" @click="modifyPostInfo()">SAVE</button>
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
    created() {
        let id = this.$route.query.id; // => 라우터를 호출했기때문에 당연히 라우터에 등록되어야함.
        this.getPostInfo(id);
    },
    methods : {
        async getPostInfo(id) {
            this.post= await fetch('https://jsonplaceholder.typicode.com/posts/' + id)
                                  .then(res => res.json())
                                  .catch(err => console.error(err));
        },
        modifyPostInfo() {
            let data = {
                id : this.post.id,
                title : this.post.title,
                body : this.post.body,
                userId : this.post.userId
            }
            
            fetch('https://jsonplaceholder.typicode.com/posts/' + data.id, {
                method : 'PUT',
                headers : {
                    'content-type' : 'application/json'
                },
                body : JSON.stringify(data)
            })
            .then(res => res.json())
            .then(result => {
                console.log(result);
                if(result != null){
                    alert('정상적으로 수정되었습니다.');
                } else {
                    alert('수정되지 않았습니다.\n데이터를 확인해주세요');
                }
            })
            .catch(err => console.error(err));
        }
    }
}