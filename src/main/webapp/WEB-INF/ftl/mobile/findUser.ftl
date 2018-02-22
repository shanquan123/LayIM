<div class="find-user-container-template" style="display: none;">
    <form class="layui-form find-user" action="">
        <div class="layui-form-item">
            <div class="layui-input-block">
                <input type="text" name="keywords" required  lay-verify="required" placeholder="请输入用户昵称或者ID" autocomplete="off" class="layui-input">
            </div>
        </div>
    </form>


    <div class="layim-tab-content layui-show" >
        <ul class="layim-list-friend layui-layim-list layim-list-history">
            <#--<ul class="layui-layim-list layim-list-history">-->
                <li layim-event="chat" data-type="history" data-index="friend1" class="layim-friend1 friend-item" u-id="1" style="display: none">
                    <div>
                        <img class="avatar" src="https://avatars2.githubusercontent.com/u/14289678?s=460&amp;v=4">
                    </div>
                    <span class="nick-name">王不留行</span>
                    <p class="sign">null</p>
                    <i class="apply-btn" onclick="applyFriend(this)"></i>
                </li>
           <#--</ul>-->
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

        $label = $('<label class="layui-form-label">选择分组</label>')
        $item = $('<div class="layui-input-block"></div>').append($select)

        return $('<div class="layui-form-item"></div>').append($label).append($item)
    }

    function buildRemark(){
        $remark = $('<input type="text" name="remark" required  lay-verify="required" placeholder="请输入备注" autocomplete="off" class="layui-input apply">');
        $label = $('<label class="layui-form-label">备注</label>');
        $item = $('<div class="layui-input-block"></div>').append($remark);

        return $('<div class="layui-form-item"></div>').append($label).append($item);

    }

    function buildApplyForm(){
        $form = $('<form class="layui-form layui-form-pane" action="">')
        $form.append(buildGroupSelect())
        $form.append(buildRemark())

        return $form.get(0).outerHTML
    }

    //申请添加好友
    function applyFriend(item){
        var userID = $(item).data('u-id')


        confirm(buildApplyForm(), function(){
            var url = '/rest/user/apply.ajax'
            post(url, {
                toUserID:userID,
                groupID: $('.apply[name=groupID]').val(),
                remark: $('.apply[name=remark]').valueOf()

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
        $item.find('.friend-item').data('u-id', userID).attr('u-id', userID)
        $item.find('.apply-btn').data('u-id', userID)
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


    $(document).on('touchstart', '.apply-btn', function(event){
        event.preventDefault();
        event.stopPropagation();
        applyFriend(this);
    })
</script>