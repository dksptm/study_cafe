// userUpdate.js

let template = `
<div>
  <table :class="myTable">
    <thead>
      <tr :class="myTr">
        <th>아이디</th>
        <td>{{ user.id }}</td>
        <th>이름</th>
        <td><input type="text" v-model="user.name"></td>
        <th>닉네임</th>
        <td><input type="text" v-model="user.username"></td>
      </tr>
    </thead> 
    <tbody>
      <tr>
        <th>연락처</th>
        <td><input type="text" v-model="user.phone"></td>
        <th>이메일</th>
        <td><input type="text" v-model="user.email"></td>
        <th>웹사이트</th>
        <td><input type="text" v-model="user.website"></td>
      </tr>
    </tbody>
  </table>
  <button type="button" :class="myBtn" @click="modifyUser()"> 저장 </button>
</div>
`;

export default {
    template,
    data() {
        return {
            user : {
                id : '',
                name : '',
                username : '',
                email : '',
                phone : '',
                website : ''
            },
            myTable : 'table table-bordered',
            myTr : 'table-success',
            myBtn : 'btn btn-outline-secondary btn-sm'
        }
    },
    created() {
      let id = this.$route.query.id;
      this.getUserInfo(id);
    },
    methods : {
        async getUserInfo(id) {
          this.user = await fetch('https://jsonplaceholder.typicode.com/users/' + id)
                                .then(res => res.json())
                                .catch(err => console.error(err));
        },
        modifyUser() {
            let data = {
                id : this.user.id,
                name : this.user.name,
                username : this.user.username,
                email : this.user.email,
                phone : this.user.phone,
                website : this.user.website
            }
            fetch('https://jsonplaceholder.typicode.com/users/' + data.id, {
                method : 'PUT',
                headers : {
                    'content-type' : 'application/json'
                },
                body : JSON.stringify(data)
            })
            .then(res => res.json())
            .then(ret => {
              console.log(ret);
              if(ret.id != null) {
                alert('정상수정!');
              } else {
                alert('수정오류!');
              } 
            })
            .catch(err => console.error(err));
        }
    }
}