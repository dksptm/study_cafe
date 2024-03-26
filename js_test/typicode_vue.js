//typicode_vue.js
// 뷰는 태그를 내장한다.(컴포넌트)

const { createApp } = Vue
//  createApp => root를 말한다. 하나만 있음(일반적으로) 그리고 트리모양.

// 본인이 보여야하는 구조를 아예 가지고 있다 -> 템플릿.
// 뷰는 태그안에 직접적으로 문법을 사용.(v-~~)
let template =`
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
            <tr v-for="(post, idx) in postList" :key="idx"
                v-on:click="getPostInfo(post.id)">
                <td>{{post.id}}</td>
                <td>{{post.title}}</td>
                <td>{{post.userId}}</td>
            </tr>
        </tbody>
    </table>
</div>
`;

// 템플릿을 객체 내부에 변수로 집어넣음.
createApp({
  template,
  data() {
    return {
      postList : []
    }
  },
  created(){
    this.getPostList();
  },
  methods : {
    async getPostList(){
        this.postList = await fetch('https://jsonplaceholder.typicode.com/posts')
                              .then(res => res.json());
    },
    getPostInfo(id){
        fetch('https://jsonplaceholder.typicode.com/posts/'+id)
        .then(res => res.json())
        .then(data =>{
            console.log(data);
        });
    }
  }
}).mount('#list')