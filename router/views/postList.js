// postList.js

let template = `
<div>
    <table>
        <thead>
            <tr>
                <th>id</th>
                <th>title</th>
                <th>userId</th>
            </tr>
        </thead>
        <tbody>
            <template v-for="post in postList" v-bind:key="post.id">
            <tr v-if="post.id <= 10" @click="goToDetail(post.id)">
                <td> {{ post.id }} </td>
                <td> {{ post.title }} </td>
                <td> {{ post.userId }} </td>
            </tr>
            </template>
        </tbody>
    </table>
</div>
`;

export default {
    template,
    data() {
        return {
            postList : []
        }
    },
    created() {
        this.getPostList(); // 비동기.
    },
    methods : {
        // 비동기 통신의 순서를 동기식으로 진행.
        async getPostList() {
            // ajax => fetch() / async await fetch()
            // fetch('').then(res => res.json()).then(data => this.postList = data).catch(err => console.error(err));
            // async await 를 사용하려면 this.postList = await fetch()..... 로 해야함.
            // this.postList 에 fetch().. 의 결과가 담기기 전까지 기다리라는 뜻.
            this.postList = await fetch('https://jsonplaceholder.typicode.com/posts')
                                  .then(res => res.json())
                                  .catch(err => console.error(err));
        },
        goToDetail(postId) {
            this.$router.push({ name : 'postInfo',
                                query : { id : postId } }); // http://~:5500/postInfo?id=6
        }
    }
}