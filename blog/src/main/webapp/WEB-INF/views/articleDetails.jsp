<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<link rel="stylesheet" href="https://at.alicdn.com/t/font_409173_5hmwp8ra99e5qaor.css">
<link rel="stylesheet" href="../../assets/css/articleDetails.css">
<section ref="main" class="main" id="articleDetails">
    <div class="blog_left">

        <%--<%=request.getAttribute("jsonObject")%>--%>
        <div v-html="articleData.content">

        </div>
        <div class="comment_content">
                <div class="list_log">
                    
                    <div class="list_comment">
                        <div v-for="(item, index) in articleData.comments_list" class="first_comment">
                            <div class="user_logo_details">
                                <img :src="item.from_img" alt="" class="img_comment">
                            </div>
                            <div class="comment_info">
                                <div class="comment_name">
                                    <span class="comment_color">{{item.from_name}}</span>
                                    <%--<span >回复</span>--%>
                                    <%--<span class="comment_color">谭业涛</span>--%>
                                    <p>
                                        {{item.content}}
                                    </p>
                                </div>
                                <div class="comment_time">
                                    <label>{{item.date_time}} </label>  &nbsp;
                                    <span data-id="1" class="comment_del iconfont icon-delete"></span>&nbsp;&nbsp;
                                    <span class="comment_cre iconfont icon-edit icon-edit2"></span>
                                </div>
                                <div v-if="item.second_child" v-for="(item2, index2) in item.second_child" class="first_comment second_comment">
                                        <div class="user_logo_details"><img :src="item2.from_img" alt="" class="img_comment"></div>
                                        <div class="comment_info">
                                            <div class="comment_name">
                                                <span class="comment_color">{{item2.from_name}}</span>
                                                <span>回复</span>
                                                <span class="comment_color">{{item2.to_name}}</span>
                                                <p>
                                                    {{item2.content}}
                                                </p>
                                            </div>
                                            <div class="comment_time">
                                                <label for="">{{item2.date_time}} </label>  &nbsp;
                                                <span data-id="1" class="comment_del iconfont icon-delete"></span>&nbsp;&nbsp;
                                                <span class="comment_cre iconfont icon-edit"></span>
                                            </div>
                                            <div class="div_comment">
                                                <div class="new_comment clearfloat">
                                                    <button class="new_comment_1">回复：{{item2.from_name}}</button>
                                                    <div class="new_comment_2" contenteditable="true"></div>
                                                </div>
                                                <div class="send_div" data-fromId="1" :data-parentId="item.id" :data-toId="item2.from_id" :data-articleid="articleData.id">
                                                    <button class="send">发表</button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                            </div>
                            <div class="div_comment1 clearfloat">
                                    <div class="new_comment clearfloat">
                                        <button class="new_comment_1">回复：{{item.from_name}}</button>
                                        <div class="new_comment_2" contenteditable="true"></div>
                                    </div>
                                    <div class="send_div" data-fromId="1" :data-parentId="item.id" :data-toId="item.from_id" :data-articleid="articleData.id">
                                        <button class="send">发表</button>
                                    </div>
                            </div>
                        </div>
                    </div>
                    <div class="div_comment2 clearfloat">
                        <div class="new_comment clearfloat">
                            <div class="new_comment_2" contenteditable="true"></div>
                        </div>
                        <div class="send_div" data-fromId="1" data-parentId="0" data-toId="1" :data-articleid="articleData.id">
                            <button class="send">发表</button>
                        </div>
                    </div>
                </div>
            </div>

        <%--<div id="layui-form-user" style="display: none">--%>
            <%--<div class="layui-form-item">--%>
                <%--<label class="layui-form-label">用户名：</label>--%>
                <%--<div class="layui-input-block">--%>
                    <%--<input type="text" id="username" name="username" required  lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">--%>
                <%--</div>--%>
            <%--</div>--%>
            <%--<div class="layui-form-item">--%>
                <%--<label class="layui-form-label">密码：</label>--%>
                <%--<div class="layui-input-block">--%>
                    <%--<input type="text" id="password" name="password" required  lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input">--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>

            <div id="layui-form-user" class="layui-tab layui-tab-card">
                <ul class="layui-tab-title">
                    <li class="layui-this">登录</li>
                    <li>注册</li>
                </ul>
                <div class="layui-tab-content" style="height: 100px;">
                    <div class="layui-tab-item layui-show">
                        <div class="layui-form-item">
                            <label class="layui-form-label">用户名：</label>
                            <div class="layui-input-block">
                                <input type="text" id="username" name="username" required lay-verify="required"
                                       placeholder="请输入用户名" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">密码：</label>
                            <div class="layui-input-block">
                                <input type="text" id="password" name="password" required lay-verify="required"
                                       placeholder="请输入密码" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                    </div>
                </div>
            </div>


    </div>
    <%@ include file="blogRight.jsp" %>
</section>
<%@include file="footer.jsp"%>
<%--<script type="text/javascript" src="../../assets/js/articleDetails.js"></script>--%>
<script>
    $(function(){
        layui.use('layer');
        layui.use('element');
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
            var send = $(this).parent().siblings(".new_comment").find(".new_comment_2").text();
            if (!send) {
                console.log("评价内容为空");
                layer.msg("评价内容为空");
                return;
            }

            var userInfo = '${sessionScope.userInfo}';
            //在这里面输入任何合法的js语句
            if (!userInfo) {
                layer.open({
                    type: 1 //Page层类型
                    , area: ['500px', '300px']
                    , title: false
                    , shade: 0.6 //遮罩透明度
                    , anim: 1 //0-6的动画形式，-1不开启
                    , content: $('#layui-form-user')
                    , btn: ['登录/注册', '取消']
                    , yes: function (index, layero) {
                        console.log("登录")
                        var username = $("#username").val();
                        var password = $("#password").val();

                        var type = $(".layui-this").html();
                        var method = type == "登录" ? "login" : "register";

                        console.log(type);
                        console.log(username);
                        console.log(password);
                        var data = {
                            "username": username,
                            "method": method,
                            "password": password
                        };
                        //layer.closeAll();

                        $.ajax({
                            type: 'post',
                            url: '/user',
                            data: JSON.stringify(data),
                            success: function (res) {
                                if (res && res.status == 1) {
                                    console.log("登录成功")
                                    layer.closeAll();
                                } else {
                                    layer.msg(res.msg);
                                    console.log("登录失败")
                                }
                            },
                            error: function (err) {
                                console.log(err);
                                return;
                            }
                        });
                    }, btn2: function (index, layero) {
                        console.log("取消")
                    }

                });
            } else {


                console.log(userInfo)

                var fromId = $(this).parent().data("fromid");
                var parentId = $(this).parent().data("parentid");
                var toId = $(this).parent().data("toid");
                var articleId = $(this).parent().data("articleid");


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
                    success: function (res) {
                        console.log(res)
                        document.location.reload();
                    },
                    error: function (err) {
                        console.log(err)
                        document.location.reload();
                    }
                });
            }
        });


    });

    var articleDetailsVm = new Vue({
        el: "#articleDetails",
        data: {
            mainOffsetLeft: 0,
            articleData:${jsonObject}
        },
        computed: {
            // 计算div.main到窗口左边缘的left值
            handleOffsetLeft: function () {
                return this.mainOffsetLeft
            }
        },
        methods: {
            filterData:function (articleData) {
                var data1 = articleData.comments_list;
                var res = [];
                if (data1) {
                    data1.forEach(function (item, index, c) {
                        if (item.parent_id == 0) {
                            res.push(item);
                        }
                    });
                    res.forEach(function (item, index, c) {
                        var second_child = [];
                        data1.forEach(function (item2, index, c) {
                            if (item.id == item2.parent_id) {
                                second_child.push(item2);
                            }
                        });
                        item.second_child = second_child;
                    });
                }
                this.articleData.comments_list = res;
                console.log(this.articleData)
            }
        },
        mounted: function() {
            this.mainOffsetLeft = this.$refs.main.offsetLeft + 710;
            var _this = this;

            // 监听窗口变化事件
            window.onresize = function(){
                _this.mainOffsetLeft = _this.$refs.main.offsetLeft + 710
            }
        },
        created: function () {
            this.filterData(this.articleData);
        }
    })
</script>