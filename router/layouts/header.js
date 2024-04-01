// header.js

// 특별한 기능은 없고, 레이아웃만.
// but 로그인같은 기능이 헤더에 들어가면 기능이 들어가야.
// 헤더는 보통 router-link가 있는경우 많다.
// 헤더는 라우터에 등록되지 않는다! 그냥 자식컴포넌트.
// 헤더는 하나의 페이지가 아닌, 페이지를 구성하는 자식컴포넌트라서.

let template =`
<header>
    <router-link to="/"> Home </router-link>
    || <router-link :to="{ path : 'postList' }"> 전체조회(10개만) </router-link>
    || <router-link :to="{ path : 'postInsert' }"> 단건등록 </router-link>
</header>
`;

export default {
    template,
    name : 'headerComponent'
}