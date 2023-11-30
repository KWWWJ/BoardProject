const commentListElem = document.getElementById("comment-list");
const commentForm = document.getElementById("board-comment");
console.log("id : " + commentListElem.dataset.boardId);

const getList = async () => {
    const data = (await axios.get(`/comments?id=${commentListElem.dataset.boardId}&start=${commentListElem.children.length}`)).data;
    console.log(data);

    if (data.end) {
        document.getElementById("add-comment-btn").outerHTML = "";
    }

    setList(data.list, commentListElem);

}

function addComment(boarId, id) {
    //session id같은 경우 commentForm["user_id"].value; 로 받아올 수도 있다.
    const commentIn = '<form action="/comments/add" method="post">'
        + '<input type="hidden" name="board_id" value="' + boarId + '">'
        + '<input type="hidden" name="comment_id" value="' + id + '"></input>'
        + '<textarea type="text" name="comment-input" class="teaxarea-size form-control"'
        + ' id="floatingPassword" placeholder="댓글을 작성해 주세요." onkeyup="autoResize(this)"'
        + 'onkeydown="autoResize(this)" rows="3"></textarea>'
        + '<div class="form-floating mb-3"></div>'
        + '<button class="btn btn-primary">댓글 등록</button>'
        + ' <div class="form-floating mb-5"></div>'
        + ' </form>'
    return commentIn;
}

function autoResize(textarea) {
    textarea.style.height = 'auto';
    textarea.style.height = textarea.scrollHeight + 'px';
}

function setList(list, parentElem) {
    list.forEach(item => {
        const tempLiElem = document.createElement('li');
        const tempDivElemUserName = document.createElement('div');
        const tempDivElemContent = document.createElement('div');
        const tempDivElemCommentBox = document.createElement('div');
        const tempFormElemCommentIn = document.createElement('div');
        const tempCommentShowBtn = document.createElement('button');
        const tempBtnElem = document.createElement('button');


        tempDivElemUserName.innerHTML = `${item.name}`;
        tempDivElemContent.innerHTML = `${item.content}`;
        tempDivElemCommentBox.innerHTML = `${new Date(item.createdAt)}`

        tempLiElem.className = 'comment-box'
        tempDivElemUserName.className = 'comment-user-name'
        tempDivElemContent.className = 'comment-content'

        tempBtnElem.className = 'comment-btn'

        tempDivElemCommentBox.className = 'create-box';

        tempLiElem.append(tempDivElemUserName);
        tempLiElem.append(tempDivElemContent);
        tempDivElemContent.append(tempDivElemCommentBox);

        if (commentForm) {
            tempBtnElem.innerText = "답글 쓰기";
            tempFormElemCommentIn.innerHTML = addComment(`${item.boardId}`, `${item.id}`);
            tempDivElemCommentBox.append(tempBtnElem);
            tempDivElemCommentBox.append(tempFormElemCommentIn);

            tempBtnElem.addEventListener('click', function () {
                if (tempFormElemCommentIn.children[0].className == "on") {
                    tempFormElemCommentIn.children[0].className == "";
                }
                tempFormElemCommentIn.children[0].classList.toggle("on");
            })

        }

        const tempOlElem = document.createElement('ol');

        tempOlElem.className = 'recoment-box'

        setList(item.children, tempOlElem);

        tempLiElem.append(tempOlElem);

        parentElem.append(tempLiElem);
    });
}


getList();

document.getElementById("add-comment-btn").onclick = () => {
    getList();
};

// list.forEach(item => {
//     const tempLiElem = document.createElement('li');
//     const tempDivElemUserName = document.createElement('div');
//     const tempDivElemContent = document.createElement('div');
//     const tempDivElemCommentBox = document.createElement('div');
//     const tempFormElemCommentIn = document.createElement('div');
//     const tempBtnElem = document.createElement('button');

//     tempDivElemUserName.innerHTML = `${item.name}`;
//     tempDivElemContent.innerHTML = `${item.content}`;
//     tempDivElemCommentBox.innerHTML = `${new Date(item.createdAt)}`
//     tempBtnElem.innerText = "답글 쓰기";

//     tempBtnElem.style.background = 'white';
//     tempBtnElem.style.border = 'none';
//     tempBtnElem.style.fontSize = '13px';
//     tempBtnElem.style.color = '#b2b2b2';

//     tempDivElemCommentBox.style.fontSize = '13px';
//     tempDivElemCommentBox.style.color = '#b2b2b2';


//     tempLiElem.append(tempDivElemUserName);
//     tempLiElem.append(tempDivElemContent);
//     tempFormElemCommentIn.innerHTML = addComment(`${item.boardId}`, `${item.id}`);
//     tempDivElemContent.append(tempDivElemCommentBox);
//     tempDivElemCommentBox.append(tempBtnElem);
//     tempDivElemCommentBox.append(tempFormElemCommentIn);

//     tempFormElemCommentIn.style.display = 'none'

//     tempBtnElem.addEventListener('click', function () {
//         tempFormElemCommentIn.style.display = 'block';
//     })

//     const tempOlElem = document.createElement('ol');
//     item.children.forEach((child) => {
//         const tempLiElem2 = document.createElement('li');
//         const tempDivElemUserName2 = document.createElement('div');
//         const tempDivElemContent2 = document.createElement('div');
//         const tempDivElemCommentBox2 = document.createElement('div');
//         const tempFormElemCommentIn2 = document.createElement('div');
//         const tempBtnElem2 = document.createElement('button');

//         tempBtnElem2.innerText = "답글 쓰기";
//         tempBtnElem2.style.background = 'white';
//         tempBtnElem2.style.border = 'none';
//         tempBtnElem2.style.fontSize = '13px';
//         tempBtnElem2.style.color = '#b2b2b2';

//         tempDivElemCommentBox2.style.fontSize = '13px';
//         tempDivElemCommentBox2.style.color = '#b2b2b2';

//         tempDivElemUserName2.innerHTML = `${child.name}`;
//         tempDivElemContent2.innerHTML = `${child.content}`;
//         tempFormElemCommentIn2.innerHTML = addComment(`${child.boardId}`, `${child.id}`);
//         tempDivElemCommentBox2.innerHTML = `${new Date(child.createdAt)}`


//         tempLiElem2.append(tempDivElemUserName2);
//         tempLiElem2.append(tempDivElemContent2);
//         tempDivElemContent2.append(tempDivElemCommentBox2);
//         tempDivElemCommentBox2.append(tempBtnElem2);
//         tempDivElemCommentBox2.append(tempFormElemCommentIn2);
//         tempOlElem.append(tempLiElem2);

//         tempFormElemCommentIn2.style.display = 'none'

//         tempBtnElem2.addEventListener('click', function () {
//             for (let index = 0; index < `${child.id}`.length; index++) {
//                 tempFormElemCommentIn2.style.display = 'block';
//             }

//         })
//     })

//     tempLiElem.append(tempOlElem);
//     commentListElem.append(tempLiElem);

// });