<!DOCTYPE html>
<html>
<#include "header.ftl">
<body>

	<div class="login-bg"></div>
	<form action="" method="post">
		<img src="/static/images/logo.png" class="login-logo" />
		<div class="login-wrap login-con">
			<input type="text" name="username"  class="form-item" placeholder="用户名">
			<input type="password" name="password" class="form-item" placeholder="密码">
			<button type="submit" id="submit">登录</button>
			<p>还没有账号？<a href="/rest/login/register.html"><b>点击注册</b></a></p>
		</div>
	</form>

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


    layui.config({
        version: '20171011'
    }).use('mobile', function() {

        mobile = layui.mobile
        layer = mobile.layer;


        $("#submit").click(function (event) {
            event.preventDefault();
            if (checkItem($(".form-item"))) {
                var postData = {
                    userName: $('[name=username]').val(),
                    password: $.md5($('[name=password]').val())
                }
                var url = '/rest/login/login.ajax'
                post(url, postData, function (response) {
                    if (response.code == 1) {
                        location.href = '/rest/mobile/mobile.html'
                    }
                })
            }
        })
    })

</script>
</body>
</html>
