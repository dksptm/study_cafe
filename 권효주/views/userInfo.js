// userInfo.js

let template = `
<div>
  <table :class="myTable">
    <thead>
      <tr :class="myTr">
        <th>아이디</th>
        <td>{{ userInfo.id }}</td>
        <th>이름</th>
        <td>{{ userInfo.name }}</td>
        <th>닉네임</th>
        <td>{{ userInfo.username }}</td>
      </tr>
    </thead> 
    <tbody>
      <tr>
        <th>연락처</th>
        <td>{{ userInfo.phone }}</td>
        <th>이메일</th>
        <td>{{ userInfo.email }}</td>
        <th>웹사이트</th>
        <td>{{ userInfo.website }}</td>
      </tr>
      <tr>
        <th>주소</th>
        <td colspan="5">
          {{ userInfoAddr.street }} &ensp;
          {{ userInfoAddr.suite }} &ensp;
          {{ userInfoAddr.city }}
        </td>
      </tr>
      <tr>
        <th>회사</th>
        <td colspan="5">
          <b>회사명</b> &ensp; {{ userInfoCo.name }} &ensp;
          <b>구호</b> &ensp; {{ userInfoCo.catchPhrase }} &ensp;
          <b>목표</b> &ensp; {{ userInfoCo.bs }}
        </td>
      </tr> 
    </tbody>
  </table>
  <button type="button" :class="myBtn" @click="goToUpdate(userInfo.id)">수정</button> &ensp;
  <button type="button" :class="myBtn" @click="delUser(userInfo.id)">삭제</button>
</div>
`;

export default {
  template,
  data() {
    return {
      userInfo : {},
      userInfoAddr : {},
      userInfoCo: {},
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
      this.userInfo = await fetch('https://jsonplaceholder.typicode.com/users/' + id)
                            .then(res => res.json())
                            .then(data => {
                              this.userInfoAddr = data.address;
                              this.userInfoCo = data.company;
                              return data;
                            })
                            .catch(err => console.error(err));
    },
    goToUpdate(userId) {
      this.$router.push({ name : 'userUpdate',
                          query : {id : userId} });
    },
    delUser(userId) {
      fetch('https://jsonplaceholder.typicode.com/users/' + userId, {
        method : 'DELETE'
      })
      .then(res => res.json())
      .then(data => {
        console.log(data);
        let result = Object.keys(data).length;
        if(result == 0) {
          alert('삭제성공!');
        } else {
          alert('삭제실패!');
        }
      })
      .catch(err => console.error(err))
    }
  }
}