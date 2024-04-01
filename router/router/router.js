// router.js
import HomeComponent from '../views/home.js';
import PostListComponent from '../views/postList.js';
import PostInfoComponent from '../views/postInfo.js';
import PostInsertComponent from '../views/postInsert.js';
import PostUpdateComponent from '../views/postUpdate.js';

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
    },
    {
        path : '/postInsert',
        name : 'postInsert',
        component : PostInsertComponent
    },
    {
        path : '/postUpdate',
        name : 'postUpdate',
        component : PostUpdateComponent
    }
]

const router = createRouter({
    history : createWebHistory(),
    routes
});

export default router;
