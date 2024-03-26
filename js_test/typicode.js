//typicode.js

fetch('https://jsonplaceholder.typicode.com/posts')
.then(res => res.json())
.then(data =>{
    createTable(data);
});
let heads = ['id', 'title', 'userId'];
function createTable(list){
    let table = document.createElement('table');

    table.append(createThead());

    table.append(createTbody(list));

    document.querySelector('#list').append(table);
}

function createThead(){
    let thead = document.createElement('thead');

    let tr = document.createElement('tr');

    for(let head of heads){
        let th = document.createElement('th');
        th.textContent = head;
        tr.append(th);
    }
    
    thead.append(tr);

    return thead;
}

function createTbody(list){
    let tbody = document.createElement('tbody');
    for(let i=0; i < 10; i++){
        tbody.append(createTr(list[i]));
    }
    return tbody;
}

function createTr(data){
    let tr = document.createElement('tr');
    tr.addEventListener('click', getPostInfo);

    for(let head of heads){
        let td = document.createElement('td');
        td.textContent = data[head];
        tr.append(td);
    }
    return tr;
}

function getPostInfo(event){
    let id = event.currentTarget.firstElementChild.textContent;

    fetch('https://jsonplaceholder.typicode.com/posts/'+id)
    .then(res => res.json())
    .then(data =>{
        printPostInfo(data);
    });
}

let infos = ['id', 'title', 'userId', 'body'];
function printPostInfo(data){
    let div = document.createElement('div');
    for(let info of infos){
        let label = document.createElement('label');
        label.textContent = info;        

        let p = document.createElement('p');
        p.textContent = data[info];
        label.append(p);
        div.append(label);
    }
    document.querySelector('#info').append(div);
};