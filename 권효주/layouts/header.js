// header.js
let template = `
<header>
    <h3 class="text-bg-primary">HELLO! KHJ</h3>
    <nav>
        <router-link to="/home">HOME</router-link> &ensp;
        <router-link :to="{ name : 'userList' }">USERS</router-link> &ensp;
        <router-link :to="{ name : 'userInsert' }">ADD USER</router-link> &ensp;
    </nav>
</header>
`;

export default {
    name : 'headerComponent',
    template
}