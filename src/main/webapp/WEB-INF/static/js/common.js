

function alert(msg){
    layer.open({
        anim: 'up'
        ,content: msg
        ,btn: ['确认']
    });
}

function confirm(smg, yesCallback){
    layer.open({
        anim: 'up'
        ,content: msg
        ,btn: ['确认', '取消']
        ,yes: yesCallback
    });
}


function post(url, postData, success){
    var url = '/rest/mobile/register.ajax'
    $.post(url, postData, function(response){
        if(response.code == 1){
            if( typeof success == 'function')
                success(response)
        }else{

            if(response.message){
                var msg = response.message
            }else{
                var msg = response.reason
            }

            alert(msg)
        }

    }, 'json')
}