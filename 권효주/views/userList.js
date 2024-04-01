// userList.js

let template = `
<div>
  <table :class="[myTable, myTableOpt]">
    <thead>
      <tr>
        <th>아이디</th>
        <th>이름</th>
        <th>닉네임</th>
        <th>이메일</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="user in userList" v-bind:key="user.id" v-on:click="goToInfo(user.id)">
        <td>{{ user.id }}</td>
        <td>{{ user.name }}</td>
        <td>{{ user.username }}</td>
        <td>{{ user.email }}</td>
      </tr>
    </tbody>
  </table>
</div>
`;

export default {
  template,
  data() {
    return {
      userList : [],
      myTable : 'table table-bordered',
      myTableOpt : 'table-hover'
    }
  },
  created(){
    this.getUserList();
  },
  methods : {
    async getUserList() {
      this.userList = await fetch('https://jsonplaceholder.typicode.com/users')
                            .then(response => response.json())
                            .catch(err => console.log(err));
    },
    goToInfo(userId) {
      this.$router.push({ name : 'userInfo',
                          query : {id : userId} });
    }
  }
}

