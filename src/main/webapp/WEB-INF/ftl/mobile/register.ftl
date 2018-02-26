<!DOCTYPE html>
<html>
<#include "header.ftl">
<body>

<div class="login-bg"></div>
	<form action="" method="post">
		<img src="/static/images/logo.png" class="login-logo" />
		<div class="/login-wrap login-con">
			<input type="text" name="username" class="form-item" placeholder="用户名">
			<input type="text" name="nickname" class="form-item" placeholder="昵称">
			<input type="password" name="password" class="form-item" placeholder="密码">
			<input type="password" name="rePassword" class="form-item" placeholder="确认密码">
			<button type="submit" id="submit">登录</button>
			<p>已有账号，<a href="/rest/login/login.html"><b>点击登录</b></a></p>
		</div>
	</form>
</body>

<script>

    function checkItem($items){

        var flag = true
        $items.each(function(i, item){
			if( !$(item).val() ){
    			flag = false;
                var itemName = $(item).attr('placeholder')
				var msg = '<span style="color: red">' + itemName + '</span>' + ' 不能为空'

				alert(msg)
			}
        })

		return flag;
    }

    function checkoutPassword(){
        if( $("[name=password]").val() == $("[name=rePassword]").val() ){
            return true
		}else{
            alert('<span style="color: red">两次输入密码不一致！</span>')
            return false;
		}
	}


    layui.config({
        version: '20171011'
    }).use('mobile', function() {

        mobile = layui.mobile
        layer = mobile.layer;


        $(".form-item").blur(function(event){
            checkItem($(this))
        })


        $("#submit").click(function(event){
            event.preventDefault();
            if (checkItem($(".form-item"))  &&  checkoutPassword()){
                var postData = {
                    userName: $('[name=username]').val(),
                    nickName: $('[name=nickname]').val(),
                    password: $.md5($('[name=password]').val())
                }
                var url = '/rest/login/register.ajax'
                post(url, postData, function(response){
                    if(response.code == 1){
                        alert('注册成功！')
                        setTimeout(function(){
                            location.href = '/rest/mobile/mobile.html'
                        }, 2000)
                    }
                })
            }
        })


        $('[name=username]').blur(function(){
            var userName = $(this).val();
            if(userName){
                post('/rest/login/check_username.ajax',{userName:userName})
            }
        })


    })




</script>
</html>
