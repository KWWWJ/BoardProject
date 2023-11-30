const closeModalBtn = document.getElementById('close-btn');


document.getElementById("regist-form").onsubmit = function (e) {
    const registFail = new bootstrap.Modal(document.getElementById('regist-fail'));
    const msgBox = document.getElementById("msg");

    const idReg = /^[a-z0-9]{3,20}$/i;
    const pwReg = /^[a-z](?=.*[\!\@\#\$\%\^\&\*])(?=.*[0-9]).{10,30}$/i;
    const koReg = /^[ㄱ-ㅎㅏ-ㅣ가-힣]/g;
    const phoneReg = /^\d{3}-\d{3,4}-[0-9]{4}$/;
    const emailReg = /^[A-Z0-9\.\_\%\+\-]+@[[A-Z0-9\.\-]+(.com|.net|.co.kr|.org)$/i;

    const tempName = e.target.name.value.replace(koReg, "aa");
    const tempPhone = e.target.phoneNumber.value.replace(/^(\d{3})(\d{3,4})(\d{4})$/, `$1-$2-$3`);

    let msg = "";


    // id = 3~20자 영어, 숫자
    if (!idReg.test(e.target.userId.value)) {
        if (e.target.userId.value.length < 3 || e.target.userId.value.length > 20) {
            msg = "아이디의 길이는 3~20자 입니다.";
        } else {
            msg = "아이디에는 숫자와 영어만 입력할 수 있습니다.";
        }
    } else if (!pwReg.test(e.target.password.value)) {
        if (e.target.password.value.length < 10 || e.target.password.value.length > 30) {
            msg = "비밀번호의 길이는 10~30자 입니다.";
        } else {
            msg = "비밀번호에는 대소문자, 특수문자가 하나 이상씩 포함되어야합니다.";
        }
    } else if (tempName.length < 4 || tempName.length > 20) {
        msg = "이름의 길이는 한글 2~10자, 영문 4~20자 입니다.";
    } else if (!phoneReg.test(tempPhone)) {
        msg = "전화번호는 한국식으로 맞춰주세요."
    } else if (e.target.address.value.length > 0 &&
        e.target.address.value.length < 5 ||
        e.target.address.value.length > 100) {
        msg = "주소는 5~100자 입니다."
    } else if (!emailReg.test(e.target.email.value)) {
        msg = "이매일 형식에 맞춰주세요."
    } else if (e.target.gitAddress.value.length > 0 &&
        !e.target.gitAddress.value.startsWith("https://github.com/")) {
        e.target.gitAddress.value = "https://github.com/" + e.target.gitAddress.value;
    }

    if (msg) {
        registFail.show();
        msgBox.innerHTML = msg;
        e.preventDefault();
    } else {
        console.log("number : " + e.target.phoneNumber.value)
        e.target.phoneNumber.value = tempPhone;
        console.log(e.target.gitAddress.value);
    }
    closeModalBtn.addEventListener('click', function () {
        modal.style.display = "none";
        document.body.style.overflow = "auto";
    })



};







// ===============================================================================================








// const pattern1 = /[0-9]/;
// const pattern2 = /[a-z]/;
// const pattern3 = /[A-Z]/;
// const pattern4 = /[~!@#$%^&*]/;
// const pwMessage = "";

// function handleOnInput(e) {
//     e.value = e.value.replace(/[^0-9a-zA-Z]/ig, '');
// }

// function phone(e) {
//     e.value = e.value
//         .replace(/[^0-9]/g, '')
//         .replace(/(^02.{0}|^01.{1}|[0-9]{3,4})([0-9]{3,4})([0-9]{4})/g, "$1-$2-$3");
//     console.log("work2");
// }

// function passwordCheck(e) {
//     console.log("input : " + e.value.length + "|" + e.value);

//     if (pattern1.test(e.value) && pattern2.test(e.value) && pattern3.test(e.value) && pattern4.test(e.value) && e.value.length >= 10 && e.value.length <= 30) {
//         document.getElementById("password").innerHTML = "조건을 만족했습니다."
//         document.getElementById("password").style.color = "green"
//         console.log("success : " + "|" + pattern1.test(e.value) + "|" + pattern2.test(e.value) + "|" + pattern3.test(e.value) + "|" + pattern4.test(e.value));
//     } else {
//         document.getElementById("password").style.display = "block";
//         console.log("resean : " + "|" + pattern1.test(e.value) + "|" + pattern2.test(e.value) + "|" + pattern3.test(e.value) + "|" + pattern4.test(e.value));
//         document.getElementById("regist-btn").addEventListener("submit", (e) => {
//             alert("정보를 올바르게 작성해주세요.")
//         })

//     }
// }

// // const regex = new RegExp('[a-z0-9]+@[a-z]+\.[com, org, co.kr, net]]');

// // function email(e) {
// //     e.value = e.regex;
// // }

// // function gitAddress(e) {

// //     if (e.startsWith('https://github.com/')) {
// //         return e;
// //     } else {
// //         e = "https://github.com/" + e;
// //     }
// //     return e;
// // }

// // $('#datePicker').datepicker({
// //     format: "yyyy-mm-dd",
// //     // startDate: "-1d",
// //     endDate: "1d",
// //     autoclose: true,
// //     clearBtn: true,
// //     title: "Birth",
// //     language: "ko",
// // });
