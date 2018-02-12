$().ready(function() {
	validateRule();
	
	//检测手机号是否正确  
	jQuery.validator.addMethod("isMobile", function(value, element) {  
	    var length = value.length;  
	    var regPhone = /^1([3578]\d|4[57])\d{8}$/;  
	    return this.optional(element) || ( length == 11 && regPhone.test( value ) );    
	}, "请正确填写您的手机号码");  
});

$.validator.setDefaults({
	submitHandler : function() {
		update();
	}
});
function update() {
	$.ajax({
		cache : true,
		type : "POST",
		url : "/member/user/update",
		data : $('#signupForm').serialize(),// 你的formid
		async : false,
		error : function(request) {
			parent.layer.alert("Connection error");
		},
		success : function(data) {
			if (data.code == 0) {
				parent.layer.msg("操作成功");
				parent.reLoad();
				var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
				parent.layer.close(index);

			} else {
				parent.layer.alert(data.msg)
			}

		}
	});

}
function validateRule() {
	var icon = "<i class='fa fa-times-circle'></i> ";
	$("#signupForm").validate({
		rules : {
			userName : {
				required : true/*,
				isPhone : true*/
				,isMobile :true
			},
			mobile : {
				required : true,
				/*isPhone : true,*/
				equalTo : "#userName"
			},
			password : {
				required : true,
				minlength : 8,
				maxlength : 16
			},
			payPassword : {
				minlength : 8,
				maxlength : 16
			},	
			nickName : {
				maxlength : 64
			},
			personalitySign : {
				maxlength : 500
			},
			inviter : {
				/*isPhone : true,*/
				maxlength : 11
			},
			remark : {
				maxlength : 500
			}
		},
		messages : {
			userName : {
				required : icon + "请输入用户名"/*,
				isPhone : icon + "用户名必须是手机号"*/
			},
			mobile : {
				required : icon + "请输入手机号",
				/*isPhone : icon + "手机号码格式不正确",*/
				equalTo : icon + "手机号和用户名必须一致"
			},
			password : {
				required : icon + "请输入密码",
				minlength : icon + "密码长度最小长度为8位",
				maxlength : icon + "密码长度最大长度为16位"
			},
			payPassword : {
				minlength : icon + "支付密码长度最小长度为8位",
				maxlength : icon + "支付密码长度最大长度为16位"
			},	
			nickName : {
				maxlength : icon + "昵称长度最大长度为64位"
			},
			personalitySign : {
				maxlength : icon + "个性签名长度最大长度为500位"
			},
			inviter : {
				/*isPhone : icon + "邀请人必须是手机号",*/
				maxlength : icon + "邀请人长度最大长度为11位"
			},
			remark : {
				maxlength : icon + "备注长度最大长度为500位"
			}
		}
	})
}