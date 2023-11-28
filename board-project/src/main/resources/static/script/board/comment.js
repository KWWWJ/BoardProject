const commentListElem = document.getElementById("comment-list");
console.log("id : " + commentListElem.dataset.boardId);

const getList = async () => {
    const list = (await axios.get(`/comments?id=${commentListElem.dataset.boardId}`)).data;
    console.log(list);

    list.forEach(item => {
        const tempLiElem = document.createElement('li');
        const tempDivElemUserName = document.createElement('div');
        const tempDivElemContent = document.createElement('div');
        const tempDivElemCommentBox = document.createElement('div');
        const tempFormElemCommentIn = document.createElement('div');
        const tempBtnElem = document.createElement('button');

        tempDivElemUserName.innerHTML = `${item.name}`;
        tempDivElemContent.innerHTML = `${item.content}`;
        tempDivElemCommentBox.innerHTML = `${item.createdAt}`
        tempBtnElem.innerText = "답글 쓰기";

        tempBtnElem.style.background = 'white';
        tempBtnElem.style.border = 'none';
        tempBtnElem.style.fontSize = '13px';
        tempBtnElem.style.color = '#b2b2b2';

        tempDivElemCommentBox.style.fontSize = '13px';
        tempDivElemCommentBox.style.color = '#b2b2b2';


        tempLiElem.append(tempDivElemUserName);
        tempLiElem.append(tempDivElemContent);
        tempFormElemCommentIn.innerHTML = addComment(`${item.boardId}`, `${item.id}`);
        tempDivElemContent.append(tempDivElemCommentBox);
        tempDivElemCommentBox.append(tempBtnElem);
        tempDivElemCommentBox.append(tempFormElemCommentIn);

        tempFormElemCommentIn.style.display = 'none'

        tempBtnElem.addEventListener('click', function () {
            tempFormElemCommentIn.style.display = 'block';
        })

        const tempOlElem = document.createElement('ol');
        item.children.forEach((child) => {
            const tempLiElem2 = document.createElement('li');
            const tempDivElemUserName2 = document.createElement('div');
            const tempDivElemContent2 = document.createElement('div');
            const tempDivElemCommentBox2 = document.createElement('div');
            const tempFormElemCommentIn2 = document.createElement('div');
            const tempBtnElem2 = document.createElement('button');

            tempBtnElem2.innerText = "답글 쓰기";
            tempBtnElem2.style.background = 'white';
            tempBtnElem2.style.border = 'none';
            tempBtnElem2.style.fontSize = '13px';
            tempBtnElem2.style.color = '#b2b2b2';

            tempDivElemCommentBox2.style.fontSize = '13px';
            tempDivElemCommentBox2.style.color = '#b2b2b2';

            tempDivElemUserName2.innerHTML = `${child.name}`;
            tempDivElemContent2.innerHTML = `${child.content}`;
            tempFormElemCommentIn2.innerHTML = addComment(`${item.boardId}`, `${item.id}`);
            tempDivElemCommentBox2.innerHTML = `${item.createdAt}`


            tempLiElem2.append(tempDivElemUserName2);
            tempLiElem2.append(tempDivElemContent2);
            tempDivElemContent2.append(tempDivElemCommentBox2);
            tempDivElemCommentBox2.append(tempBtnElem2);
            tempDivElemCommentBox2.append(tempFormElemCommentIn2);
            tempOlElem.append(tempLiElem2);

            tempFormElemCommentIn2.style.display = 'none'

            tempBtnElem2.addEventListener('click', function () {
                for (let index = 0; index < `${child.id}`.length; index++) {
                    tempFormElemCommentIn2.style.display = 'block';
                }

            })
        })

        tempLiElem.append(tempOlElem);
        commentListElem.append(tempLiElem);

    });
}

function addComment(session, boarId, id) {

    const commentIn = '<form action="/comments/add" method="post">'
        + '<input type="hidden" name="board_id" th:value="' + boarId + '">'
        + '<input type="hidden" name="comment_id" th: value="' + id + '"></input>'
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


getList();