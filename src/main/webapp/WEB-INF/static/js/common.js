

function alert(msg){
    layer.open({
        anim: 'up'
        ,content: msg
        ,btn: ['确认']
    });
}

function confirm(msg, yesCallback){
    layer.open({
        anim: 'up'
        ,content: msg
        ,btn: ['确认', '取消']
        ,yes: function(index){
            layer.close(index)
            yesCallback(index)
        }
    });
}


function post(url, postData, success){
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