// router.js
import HomeComponent from '../views/home.js';

const { createRouter, createWebHistory } = VueRouter
let routes = [
    {
        path : '/home',
        name : 'home',
        component : HomeComponent
    }
]

const router = createRouter({
    history : createWebHistory(),
    routes
});

export default router;
