/**
 * 
 */
const password = document.querySelector("#inputPassword");
const error1 = document.querySelector(".error-pass");
let regExp1 = /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,16}/;
const btn = document.querySelector('.btn-primary');
function check1() {
	if (password.value.match(regExp1)) {
		password.style.borderColor = "#27ae60";
		error1.style.display = "none";
		btn.disabled = false;
	} else {
		password.style.borderColor = "#e74c3c";
		error1.style.display = "block";
		 btn.disabled = true;
	}
	if (password.value === "") {
		password.style.borderColor = "rgb(32, 18, 93)";
		error1.style.display = "none";
		btn.disabled = fasle;
	}
}