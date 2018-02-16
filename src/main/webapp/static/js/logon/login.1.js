$(function(){
	// 复选框
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
    
	// 登陆.规则校验
	var loginFormValid = $("#loginForm").validate({
		errorElement : 'span',  
        errorClass : 'help-block',
        focusInvalid : true,  
        rules : {  
        	userName : {  
        		required : true ,
                minlength: 2,
                maxlength: 18
            },  
            password : {  
            	required : true ,
                minlength: 5,
                maxlength: 18
            } 
        }, 
        messages : {  
        	userName : {  
                required :"请输入登陆账号."  ,
                minlength:"登陆账号不应低于2位",
                maxlength:"登陆账号不应超过18位"
            },  
            password : {
            	required :"请输入登陆密码."  ,
                minlength:"登陆密码不应低于5位",
                maxlength:"登陆密码不应超过18位"
            }
        }, 
		highlight : function(element) {  
            $(element).closest('.form-group').addClass('has-error');  
        },
        success : function(label) {  
            label.closest('.form-group').removeClass('has-error');  
            label.remove();  
        },
        errorPlacement : function(error, element) {  
            element.parent('div').append(error);  
        },
        submitHandler : function(form) {
			
			$.ajax({
				url : '/logon/in.json',
				data : $("#loginForm").serialize(),
				type : "POST",
				cache : false,
				success : function(result) {
					if (result.exception) {
						alert("报错啦！" + result.exception);
					} else {
						window.location.href = base_url + redirectURL;
					}
				},
				error : function(e) {
					ComAlert.show(2, "未知错误:http状态 >" + e.status);
				}
			}); 
		}
	});
	
	
	
	// jquery.validate 自定义校验 “英文字母开头，只含有英文字母、数字和下划线”
	jQuery.validator.addMethod("equalTo", function(value, element, param) {

		var pwd = $(param).val();
		
		return pwd == value; 
	}, "两次密码不一致");

	
});