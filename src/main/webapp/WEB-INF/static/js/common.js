if (typeof layer){
    var layer
}

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
            yesCallback(index)
            setTimeout(function(){
                layer.close(index)
            }, 100)
        }
    });
}


function onLayimReady(callback){
    if (typeof callback != 'function'){
        console.error('callback must be a function!' )
        return false;
    }

    if (layim){
        callback()

    }else{
        setTimeout(function () {
            onLayimReady(callback)
        }, 50)
    }
}


function post(url, postData, success){
    $.post(url, postData, function(response){
        if(response.code == 1) {
            if (typeof success == 'function')
                success(response)

        }else if(response.code == 2){
            alert('服务正忙，请稍后重试！')

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

function postQuietly(url, postData, success){
    $.post(url, postData, function(response){
        if(response.code == 1){
            if( typeof success == 'function')
                success(response)
        }

    }, 'json')
}