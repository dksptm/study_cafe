// userInsert.js

let template = `
<div>
  <table :class="myTable">
    <thead>
      <tr :class="myTr">
        <th>아이디</th>
        <td>자동등록 => {{ user.id }}</td>
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
      <!--
      <tr>
        <th>주소</th>
        <td colspan="5">
          {{ userAddr.street }} &ensp;
          {{ userAddr.suite }} &ensp;
          {{ userAddr.city }}
        </td>
      </tr>
      <tr>
        <th>회사</th>
        <td colspan="5">
          <b>회사명</b> &ensp; {{ userCo.name }} &ensp;
          <b>구호</b> &ensp; {{ userCo.catchPhrase }} &ensp;
          <b>목표</b> &ensp; {{ userCo.bs }}
        </td>
      </tr>
      -->
    </tbody>
  </table>
  <button type="button" :class="myBtn" @click="createUser()"> 등록 </button>
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
            userAddr : {
                street : '',
                suite : '',
                city : ''
            },
            userCo : {
                name : '',
                catchPhrase : '',
                bs : ''
            },
            myTable : 'table table-bordered',
            myTr : 'table-success',
            myBtn : 'btn btn-outline-secondary btn-sm'
        }
    },
    methods : {
        createUser() {
            let data = {
                name : this.user.name,
                username : this.user.username,
                email : this.user.email,
                phone : this.user.phone,
                website : this.user.website
            }
            fetch('https://jsonplaceholder.typicode.com/users', {
                method : 'POST',
                headers : {
                    'content-type' : 'application/json'
                },
                body : JSON.stringify(data)
            })
            .then(res => res.json())
            .then(ret => {
                if(ret.id != null) {
                    alert('정상등록!');
                    this.user.id = ret.id;
                } else {
                    alert('등록오류!');
                }
            })
            .catch(err => console.error(err));
        }
    }
}