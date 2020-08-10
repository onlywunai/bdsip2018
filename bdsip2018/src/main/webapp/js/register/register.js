// 本地使用 /bdsip2018/guest/addGuest.do
// 服务器使用 /guest/addGuest.do
var guesturl = "/guest/addGuest.do";

$(function() {
	var hintText = {
		name: {
			hint: "⚠️请输入2-10字姓名",
			right: "√姓名输入正确",
			wrong: "×姓名输入有误，请重新输入"
		},
		phone: {
			hint: "⚠️请输入11位电话号码",
			right: "√电话号码输入正确",
			wrong: "×电话号码输入有误，请重新输入"
		},
		email: {
			hint: "⚠️邮箱是您登录的唯一账号，请谨慎填写",
			right: "√邮箱格式正确",
			wrong: "×邮箱格式有误，请重新输入"
		},
		gender: {
			hint: "⚠️请输入性别",
			right: "√性别格式正确",
			wrong: "×性别格式有误，请重新输入"
		},
		company: {
			hint: "⚠️请输入3-15字公司名",
			right: "√公司名输入正确",
			wrong: "×公司名输入有误，请重新输入"
		},
		job: {
			hint: "⚠️请输入2-10职务",
			right: "√职务输入正确",
			wrong: "×职务输入有误，请重新输入"
		},
		job_title: {
			hint: "⚠️请输入2-10职称",
			right: "√职称输入正确",
			wrong: "×职称输入有误，请重新输入"
		},

		user_name: {
			hint: "⚠️请输入3-12个字符的用户名（包括字母/数字/下划线）",
			right: "√用户名格式正确",
			wrong: "×用户名格式有误，请重新输入"
		},

		address: {
			hint: "⚠️请输入正确地址",
			right: "√地址输入正确",
			wrong: "×地址输入有误，请重新输入"
		},
		id_card: {
			hint: "⚠️请输入18位身份证号码",
			right: "√身份证号码输入正确",
			wrong: "×身份证号码输入有误，请重新输入"
		},
	};

	// 2.放入对应提示
	// 另一种写法
	// var regEvent = function(node, event, func) {
	function regEvent(node, event, func) {
		if(node.addEventListener)
			node.addEventListener(event, func);
		else if(node.attachEvent)
			node.attachEvent("on" + event, func);
		else
			node["on" + event] = func;
	};
	// 3.校验数据
	function regValue(id, i) {
		var flag = false,
			input = document.getElementById(id),
			value = input.value;
		switch(id) {
			case "name":
				flag = /^[a-zA-Z]{3,20}$/.test(value.replace(/[\u0391-\uFFE5]/g,
					"nn"));
				id = "name";
				break;
			case "phone":
				flag = /^((\(\d{2,3}\))|(\d{3}\-))?1[3,4,5,7,8,9]{1}\d{9}$/
					.test(value);
				id = "phone";
				break;
			case "email":
				flag = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}/
					.test(value);
				id = "email";
				break;
			case "gender":
				flag = /^[a-zA-Z]{2}$/
					.test(value.replace(/[\u0391-\uFFE5]/g, "nn"));
				id = "gender";
				break;
			case "company":
				flag = /^[a-zA-Z]{6,30}$/.test(value.replace(/[\u0391-\uFFE5]/g,
					"nn"));
				id = "company";
				break;
			case "job":
				flag = /^[a-zA-Z]{2,20}$/.test(value.replace(/[\u0391-\uFFE5]/g,
					"nn"));
				id = "job";
				break;
			case "job_title":
				flag = /^[a-zA-Z]{2,20}$/.test(value.replace(/[\u0391-\uFFE5]/g,
					"nn"));
				id = "job_title";
				break;

			case "user_name":
				flag = /^[a-zA-Z0-9_]{4,16}$/.test(value.replace(
					/[\u0391-\uFFE5]/g, "nn"));
				id = "user_name";
				break;
			case "address":
				flag = /^\S{6,16}$/.test(value.replace(/[\u0391-\uFFE5]/g, "nn"));
				id = "address";
				break;
			case "id_card":
				flag = /^\S{18}$/.test(value);
				break;
			case "password":
				flag = /^\S{6,16}$/.test(value);
				id = "password";
				break;
			case "repassword":
				flag = document.getElementById("password").value == value &&
					value != "" && value != null &&
					(/^\S{6,16}$/.test(value));
				break;
			default:
				break;
		}
		if(flag) {
			index = 1;
			input.className = "right input";
			hint[i].className = "hint hint_right";
			hint[i].innerHTML = hintText[id].right;
		} else {
			input.className = "wrong input";
			hint[i].className = "hint hint_wrong";
			hint[i].innerHTML = hintText[id].wrong;
			index = 0;
		}
	};

	// 1.获取元素数据
	// 另一种写法
	// var inputs = document.getElementsByClassName("input"), id, hint = document.getElementsByClassName("hint"), index = 0;
	var inputs = document.getElementsByClassName("input");
	var id;
	var hint = document.getElementsByClassName("hint");
	var index = 0;
	for(var j = 0; j < inputs.length; j++) {
		(function(i) {
			// 格式提示
			regEvent(inputs[i], "focus", function() {
				hint[i].style.visibility = "visible";
				id = inputs[i].id;
			});
			// 错误提示
			regEvent(inputs[i], "blur", function() {
				regValue(id, i);
			});
		})(j)
	}
});

$("#button").click(function() {
	var name = $.trim($('#name').val());
	var phone = $.trim($('#phone').val());
	var email = $.trim($('#email').val());
	var gender = $.trim($('#gender').val());
	var company = $.trim($('#company').val());
	var job = $.trim($('#job').val());
	var jobTitle = $.trim($('#job_title').val());
	if(name == '' || name == null || phone == '' || phone == null || email == ''|| email == null  || gender == '' || gender == null || company == '' || company == null || job == '' || job == null || jobTitle == ''|| jobTitle == null ) {
		alert("您的输入有误，请检查并重新输入！");
	} else {
		// 塞入数据
		var reqData = {
			'name': name,
			'phone': phone,
			'email': email,
			'gender': gender,
			'company': company,
			'job': job,
			'jobTitle': jobTitle
		};
		// 转换为Json
		var reqDataJson = JSON.stringify(reqData);
		// 封装
		var parms = {};
		// 后端请求参数为这里的reqJson
		parms.reqJson = reqDataJson;
		$.ajax({
			url: guesturl,
			type: 'post',
			dataType: 'json',
			async: false,
			data: parms,
			success: function(data) {
				if(data.success == true) {
					alert("提交成功！请留意网站信息！");
					location.reload();
				} else if(data.success == false) {
					alert("请求失败！请稍后重试！");
				}
			},
			error: function() {
				alert("请求失败！请稍后重试！");
			}
		});
	}
});