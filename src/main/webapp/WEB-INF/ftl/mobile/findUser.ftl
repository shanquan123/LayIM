<div class="find-user-container-template" style="display: none; padding: 10px;">
    <form class="layui-form find-user" action="">
        <div class="layui-form-item">
            <div class="">
                <input type="text" name="keywords" required  lay-verify="required" placeholder="请输入用户昵称或者ID" autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>


    <div class="layim-tab-content layui-show" >
        <ul class="layim-list-friend layui-layim-list list-friend-future">
            <li class="friend-item" u-id="" style="display: none">
                <div>
                    <img class="avatar" src="">
                </div>
                <span class="nick-name"></span>
                <p class="sign"></p>
                <svg class="icon apply-btn btn-icon" aria-hidden="true">
                    <use xlink:href="#icon-tianjiahaoyou"></use>
                </svg>
            </li>
        </ul>
    </div>
</div>


<script>

    function buildGroupSelect(){
        $select = $('<select class="form-control input-sm apply" name="groupID" lay-filter="category" aria-invalid="false"></select>')

        if (userGroupList && userGroupList instanceof Array){
            userGroupList.forEach(function(item){
                $select.append('<option value="'+ item.id +'">'+ item.groupname +'</option>')
            })
        }

        $label = $('<div class="layui-col-xs3 layui-col-sm3">选择分组</div>')
        $item = $('<div class="layui-col-xs9 layui-col-sm9"></div>').append($select)

        return $('<div class="layui-row"></div>').append($label).append($item)
    }

    function buildRemark(){
        $remark = $('<input type="text" name="remark" required  lay-verify="required" placeholder="请输入备注" autocomplete="off" class="layui-input apply">');

        $label = $('<div class="layui-col-xs3 layui-col-sm3">备  注</div>')
        $item = $('<div class="layui-col-xs8 layui-col-sm8 layui-col-xs-offset1 layui-col-sm-offset1"></div>').append($remark)
        return $('<div class="layui-row"></div>').append($label).append($item);

    }

    function buildApplyForm(){
        $form = $('<div class="layui-container">')
        $form.append(buildGroupSelect())
        $form.append(buildRemark())

        return $form.get(0).outerHTML
    }


    //申请添加好友
    function applyFriend(item){
        var userID = $(item).attr('u-id')

        confirm(buildApplyForm(), function(){
            var url = '/rest/user/apply.ajax'
            post(url, {
                toUserID:userID,
                groupID: $('.apply[name=groupID]').val(),
                remark: $('.apply[name=remark]').val()

            },function(response){

                alert('发送请求成功！')
            })
        })
    }

    //显示查询的用户
    function showFindedUser(user){
        var userID = user.userID
        var nickName = user.nickName
        var avatar = user.avatar
        var signature = user.signature

        $item = $('.find-user-container ul.layim-list-friend li:first').clone().show()
        $item.find('.avatar').attr('src', avatar)
        $item.find('.nick-name').text(nickName)
        $item.find('.friend-item').attr('u-id', userID)
        $item.find('.apply-btn').attr('u-id', userID)
        if(!!signature){
            $item.find('.sign').text(signature)
        }

        $('.find-user-container ul.layim-list-friend').append($item)
    }

    //查找好友
    function findUser(keywords){
        var url = "/rest/user/find.ajax"
        post(url, {
            keywords: keywords
        }, function (response) {

            $('.find-user-container ul.layim-list-friend li:gt(0)').remove()

            var userList = response.msg_data.userList
            if(userList && userList instanceof Array){
                userList.forEach(function(item, i){
                    showFindedUser(item)
                })
            }
        })
    }

    //查找朋友
    $(document).on("submit", ".find-user-container .layui-form.find-user", function(event){
        event.preventDefault();

        var keywords = $(this).find('[name=keywords]').val()
        findUser(keywords);
    })


    //添加好友
    $(document).on('touchend', '.list-friend-future .friend-item', function(event){
        event.preventDefault();
        event.stopPropagation();
        applyFriend(this);
    })

</script>