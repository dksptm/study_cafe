// ajax.js

// https://jsonplaceholder.typicode.com/posts
// 전체조회
fetch('https://jsonplaceholder.typicode.com/posts')
.then(response => {
    //console.log(response);  // response 객체. 우리가 못다룬다.
    return response.json(); // json, text, body, blob
})
.then(data => {
    //console.log(data);
})
.catch(err => {             // catch는 반드시 있어줘야한다.
    console.error(err);
});

// https://jsonplaceholder.typicode.com/posts/1
// 단건조회 => ?로 파라미터 안보내고 경로에 추가
fetch('https://jsonplaceholder.typicode.com/posts/' + 1) // 
.then(res => {
    //console.log(res);  // response 객체. 우리가 못다룬다.
    return res.json();
})
.then(data => {
    //console.log(data);
})
.catch(err => console.error(err));

// 등록. JSON => {}, [] (제이슨은 객체 또는 배열이다)
let post = {
    id : 100,
    title : 'Hello',
    userId : 10,
    body : 'Today is Friday! 아 힘들어'
}

fetch('https://jsonplaceholder.typicode.com/posts/', { // 경로자체는 전체조회와 같지만,
    method : 'POST', // 메소드가 다름 -> post는 반드시 body가 있어야 함.
    headers : {
        'Content-type' : 'application/json' // 그리고 헤더에 (내가 등록할 데이터의) 타입.
    },
    body : JSON.stringify(post) // 바디에는 제이슨형태로 변경해줘야.
})
.then(res => res.json())
.then(data => {
    console.log(data);
})
.catch(err => console.error(err));

// 수정.
// 단건조회 + 등록인것처럼..
fetch('https://jsonplaceholder.typicode.com/posts/' + 1, { // 단건조회처럼 특정짓는 uri.
    method : 'PUT', // 그리고 수정데이터를 통으로 보냄.
    headers : {
        'Content-type' : 'application/json' 
    },
    body : JSON.stringify({ // 이렇게 원래 데이터에 수정한걸 엎어서 하는걸 보통 사용한다... 
        title : 'Welcome!',
        userId : 20,
        body : 'Test!'
    })
})
.then(res => res.json())
.then(data => {
    console.log(data);
})
.catch(err => console.error(err));

// 삭제.
fetch('https://jsonplaceholder.typicode.com/posts/' + 1, { // 단건조회처럼 특정짓는 uri.
    method : 'DELETE'
})
.then(res => res.json())
.then(data => {
    console.log('DEL ', data);
})
.catch(err => console.error(err));

// EX. .then(res => res.json()) 만 하려고 하지말것.
fetch('https://reqres.in/api/users/2',{
    method : 'DELETE'
})
.then(res => {
    console.log(res.status); //204.
})