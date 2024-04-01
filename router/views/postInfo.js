// postInfo.js
let template = `
<table>
    <tr>
        <th>id</th>
        <td>{{ postInfo.id }}</td>
    </tr>
    <tr>
        <th>title</th>
        <td>{{ postInfo.title }}</td>
    </tr>
    <tr>
        <th>userId</th>
        <td>{{ postInfo.userId }}</td>
    </tr>
    <tr>
        <th>body</th>
        <td>
            <textarea rows="10" cols="70" readonly>{{ postInfo.body }}</textarea>
        </td>
    </tr>
    <tr>
        <button type="button" @click="goToUpdateForm()">UPDATE</button>
        <button type="button" @click="delPostInfo()">DELETE</button>
    </tr>
</table>
`;

export default {
    template,
    data() {
        return {
            postInfo : {}
        }
    },
    created() {
        let id = this.$route.query.id;
        this.getPostInfo(id);
    },
    methods : {
        async getPostInfo(id) {
            this.postInfo = await fetch('https://jsonplaceholder.typicode.com/posts/' + id)
                                  .then(res => res.json())
                                  .catch(err => console.error(err));
        },
        // async await => 이 컴포넌트를 열때 이 데이터가 필요한 경우.
        // 예) 쪽지보내기, 이메일보내기는 async await 필요없다.
        goToUpdateForm() {
            this.$router.push({ name : 'postUpdate',
                                query : { id : this.postInfo.id } });
        },
        delPostInfo() {
            fetch('https://jsonplaceholder.typicode.com/posts/' + this.postInfo.id, {
                method : 'DELETE'
            })
            .then(res => {
                console.log(res.status);
                console.log(res.ok);
                return res.json();
            })
            .then(data => {
                //console.log(data); // {} => null은 아니다!
                let result = Object.keys(data).length;
                //console.log(result);
                if(result == 0) {
                    alert('정상적으로 삭제되었습니다.');
                } else {
                    alert('삭제되지 않았습니다.');
                }
            })
            .catch(err => console.error(err));
        }
    }
}