$(function(){
    $(".comment_color").click(function(){
        alert("显示用户信息");
    });

    $(".comment_info").hover(function(){
        $(this).children(".comment_time").find("span").show();
    },function(){
        $(this).children(".comment_time").find("span").hide();
    });

    $(".comment_del").click(function(){
        var id = $(this).data("id");
        if(confirm("是否删除" + id + "?")){
            $(this).parent().parent().parent().hide();
        }
    });

    $(".comment_cre").click(function(){
        $(this).parent().siblings(".div_comment").toggle();
    });

    $(".icon-edit2").click(function(){
        $(this).parents(".comment_info").siblings(".div_comment1").toggle();
    });

    $(".send").click(function () {


        layer.alert('内容', {
            icon: 1,
            skin: 'layer-ext-moon'
        });

        var fromId = $(this).parent().data("fromid");
        var parentId = $(this).parent().data("parentid");
        var toId = $(this).parent().data("toid");
        var articleId = $(this).parent().data("articleid");
        var send = $(this).parent().siblings(".new_comment").find(".new_comment_2").text();

        var data = {
            "from_id": fromId,
            "parent_id": parentId,
            "to_id": toId,
            "article_id": articleId,
            "content": send
        };

        console.log(fromId)
        console.log(parentId)
        console.log(toId)
        console.log(send)
        console.log(articleId)

        $.ajax({
            type: 'post',
            url: '/saveComment',
            data: JSON.stringify(data),
            success: function(res) {
                console.log(res)
                document.location.reload();
            },
            error: function (err) {
                console.log(err)
                document.location.reload();
            }
        });
    });

    
});