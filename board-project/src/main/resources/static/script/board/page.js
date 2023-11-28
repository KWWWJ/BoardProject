
function contentPageOn(id) {
    location.href = "/content?id=" + id;
}
function createPage(number) {
    let page = '<li class="page-item"><a class="page-link" href="?page=' + number + '">' + number + '</a></li>';
    const pageBox = document.createElement('li')
    pageBox.innerHTML = page;
    document.querySelector('.created-page').prepend(pageBox);
}

const list = [[${ pageLength }]];
for (let index = 0; index < list; index++) {
    createPage(index + 1);
}

function showUser(userId) {
    console.log("user id : " + userId);
}
