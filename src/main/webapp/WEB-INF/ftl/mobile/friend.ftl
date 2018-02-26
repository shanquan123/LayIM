<div class="friend-apply-container-template" style="display: none; padding: 10px;">
    <div class="layim-tab-content layui-show" >
        <ul class="layim-list-friend layui-layim-list list-friend-apply">
            <li class="friend-item" u-id="1" style="display: none">
                <div>
                    <img class="avatar" src="">
                </div>
                <span class="nick-name"></span>
                <p class="remark">请求加为好友</p>
                <svg class="icon agree-btn friend-act-btn btn-icon" aria-hidden="true">
                    <use xlink:href="#icon-tongguo" act="agree"></use>
                </svg>
                <svg class="icon refuse-btn friend-act-btn btn-icon" aria-hidden="true">
                    <use xlink:href="#icon-weitongguo" act="refuse"></use>
                </svg>

            </li>
        </ul>
    </div>
</div>


<script>

;~(function($){

    //显示添加好友申请的用户
    globals.showApplyUser = function (isForce){

        if(isForce){
            $('.friend-apply-container ul.layim-list-friend li:gt(0)').remove();
        }

        if(globals.applyList && globals.applyList instanceof Array){

            globals.applyList.forEach(function(userApply){

                var applyItemID = userApply.applyItemID
                var nickName = userApply.nickName;
                var avatar = userApply.avatar;
                var remark = userApply.remark;

                $item = $('.friend-apply-container ul.layim-list-friend li:first').clone().show()
                $item.find('.avatar').attr('src', avatar)
                $item.find('.nick-name').text(nickName)
                $item.find('.remark').text(remark)

                $item.attr('a-i-id', applyItemID)

                $('.friend-apply-container ul.layim-list-friend').append($item)
            })
        }
    }

    //获取好友添加申请
    function getApplyList() {
        var url = "/rest/user/query_apply.ajax"

        postQuietly(url, {}, function (response) {
            if(response.code == 1){
                var applyList = response.msg_data.applyList
                if (applyList instanceof Array && applyList.length > 0){

                    globals.applyList = applyList


                    //"更多"设置为有新动态
                    layim.showNew('More', true);
                    layim.showNew('apply', true)
                }
            }
        })
    }


    function buildAgreeForm(nickName){
        var msg = '确定<span style="color: red">添加</span><span style="color: green"> '+ nickName +' </span>为好友？';

        $form = $('<div class="layui-container">')
        $form.append($label = $('<div class="layui-col-xs12 layui-col-sm12">'+ msg + '</div>'))
        $form.append(buildGroupSelect())

        return $form.get(0).outerHTML
    }


    function buildAlertMsg(isAgree, nickName){
        if(isAgree == 'agree'){
            return buildAgreeForm(nickName);
        }else{
            return '确定<span style="color: red">拒绝</span><span style="color: green"> '+ nickName +' </span>的好友申请？';
        }
    }


    //同意好友申请
    function agreeFriend(item){
        var applyItemID = $(item).attr('a-i-id')
        var nickName = $(item).find('.nick-name').text()

        confirm(buildAlertMsg('agree', nickName), function(){
            var url = '/rest/user/agree.ajax'
            var groupID = $('.apply[name=groupID]').val()
            post(url, {
                applyItemID:applyItemID,
                toGroupID: groupID

            },function(response){

                alert('添加成功！')
                removeApply(applyItemID)
                var user = response.msg_data.user
                addUserToList(user, groupID)
            })
        })
    }

    //拒绝好友申请
    function refuseFriend(item){

        var applyItemID = $(item).attr('a-i-id')
        var nickName = $(item).find('.nick-name').text()

        confirm(buildAlertMsg('refuse', nickName), function(){
            var url = '/rest/user/refuse.ajax'
            post(url, {
                applyItemID:applyItemID

            },function(response){

                alert('拒绝成功！')
                removeApply(applyItemID)
            })
        })
    }

    //从页面上删除好友申请
    function removeApply(applyItemID){
        for(var i in globals.applyList){
            var applyItem = globals.applyList[i]
            if (applyItem.applyItemID == applyItemID){
                delete globals.applyList[i]
            }
        }
        reRenderApplyList()
    }

    //重新渲染好友申请页面
    function reRenderApplyList(){

        globals.showApplyUser(true);
    }

    //添加好友到列表
    function addUserToList(user, groupID){
        var data = {
            type: 'friend' //列表类型，只支持friend和group两种
            ,avatar: user.avatar
            ,username: user.username
            ,groupid: groupID //所在的分组id
            ,id: user.userID
            ,sign: user.signature
        }
        layim.addList(data)
    }

    $(function(){

        //获取好友申请
        onLayimReady(function(){
            setInterval(getApplyList, 10000)
        });



        $(document).on('touchend', '.list-friend-apply .friend-item', function(event){
            event.preventDefault();
            event.stopPropagation();
            var item = event.target

            if($(item).attr('act') == 'agree'){
                //同意好友申请
                agreeFriend(this);

            }else if($(item).attr('act') == 'refuse'){
                //拒绝好友申请
                refuseFriend(this);
            }
        })


    })
})($)

</script>
