<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>注册-IM</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
	<meta name="renderer" content="webkit">
	<meta name="mobile-agent" content="format=html5; url=https://m.suiyixiao.com">
	<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<link rel="stylesheet" type="text/css" href="/static/css/base.css" />
	<link rel="stylesheet" type="text/css" href="/static/css/login.css" />
    <link rel="stylesheet" href="/layui/css/layui.mobile.css">
    <link id="layuicss-skinlayim-mobilecss" rel="stylesheet" href="/layui/css/modules/layim/mobile/layim.css?v=2.0" media="all">
    <script src="/layui/layui.js"></script>
	<script src="/static/js/jquery-1.8.3.min.js"></script>
	<script src="/layui/layer.js"></script>
    <script src="/static/js/md5.js"></script>
    <script src="/static/js/common.js"></script>

</head>
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
			<p>已有账号，<a href="/rest/mobile/login.html"><b>点击登录</b></a></p>
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
        if( $("[name=password]").val() == $("[name=password]").val() ){
            return true
		}else{
            alert('<span style="color: red">两次输入密码不一致！</span>')
            return false;
		}
	}


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
			var url = '/rest/mobile/register.ajax'
			post(url, postData)
		}
	})



</script>
</html>
