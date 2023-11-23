
function checkId(form) {
    const idReg = /^[a-z0-9]{3,20}$/i;
    if (!idReg.test(form.userId.value)) {
        console.log("id length : " + form.userId.value.length);
        if (form.userId.value.length < 3 || form.userId.value.length > 20) {
            msg = "아이디의 길이는 3~20자 입니다.";
        } else {
            msg = "아이디에는 숫자와 영어만 입력할 수 있습니다.";
        }
    }
    return msg;
}

function checkPw(form) {
    const pwReg = /^[a-z](?=.*[\!\@\#\$\%\^\&\*])(?=.*[0-9]).{10,30}$/i;
    if (!pwReg.test(form.password.value)) {
        if (form.password.value.length < 10 || form.password.value.length > 30) {
            msg = "비밀번호의 길이는 10~30자 입니다.";
        } else {
            msg = "비밀번호에는 대소문자, 특수문자가 하나 이상씩 포함되어야합니다.";
        }
    }
    return msg;
}
function checkMsg(msg, e) {
    if (msg) {
        e.preventDefault();
        // document.getElementById("modal-container").classList.add("on");
        // document.getElementById("modal-msg").innerHTML = msg;
        return true;
    }
    return false;
}

document.getElementById("login-form").onsubmit = (e) => {
    e.preventDefault();
    console.log("실재 들어온 id : " + e.target.userId.value);
    let msg = checkId(e.target);
    if (!msg) msg = checkPw(e.target);
    if (msg) {
        checkMsg(msg, e);
    }
    console.log("2번 콘솔" + msg);
}