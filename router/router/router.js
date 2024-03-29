// router.js
import HomeComponent from '../views/home.js';
import PostListComponent from '../views/postList.js';
import PostInfoComponent from '../views/postInfo.js';

const { createRouter, createWebHistory } = VueRouter

let routes = [
    {
        path : '/index.html', // 정규표현식
        redirect : '/'
    },
    {
        path : '/',
        name : 'home',
        component : HomeComponent
    },
    {
        path : '/postList',
        name : 'postList',
        component : PostListComponent
    },
    {
        path : '/postInfo',
        name : 'postInfo',
        component : PostInfoComponent
    }
]

const router = createRouter({
    history : createWebHistory(),
    routes
});

export default router;
