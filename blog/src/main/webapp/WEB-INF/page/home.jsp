<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>首页</title>
    <link rel="stylesheet" href="../../assets/css/home.css">
    <link rel="stylesheet" href="../../assets/fonts/iconfont.css">
</head>

<body>
    <section ref="main" class="main" id="home">
        <div class="blog_left">
            <div class="blog_left_in">
                <article class="blog_article" v-for="(item, index) in articleJson">
                    <div class="blog_title">
                        <h1>{{item.name}}</h1>
                    </div>
                    <div class="blog_other_info">发表于
                        <span>{{item.date_time}}</span>&nbsp;&nbsp;|&nbsp;&nbsp;分类于
                        <span>{{item.classify}}</span> &nbsp;&nbsp;|&nbsp;&nbsp; 阅读数
                        <span>{{item.reading}}</span>次
                    </div>
                    <p class="blog_contetn">
                        {{item.desc}}
                    </p>
                    <a href="#">
                        <button class="blog_details">
                            阅读全文>>
                        </button>
                    </a>
                </article>
            </div>
            <div v-if="showLoading" class="adticle_loading">
                <div class="spinner">
                    <div class="double-bounce1"></div>
                    <div class="double-bounce2"></div>
                </div>
                玩命加载中...
            </div>
        </div>
        <div class="blog_right" v-bind:style="{left: handleOffsetLeft + 'px'}">
            <div class="menu">
                <div class="menu_img">
                    <div class="menu_img_font">
                        TYT'S Blog
                        <div>世界很大，我真的很想去看看</div> 
                    </div>
                </div>
                <div></div>
                <div class="menu_list">
                    <li><i class="iconfont icon-basic1007househome"></i>  首页</li>
                    <li> <i class="iconfont icon-basic1025bookreading"></i> 分类</li>
                    <li><i class="iconfont icon-basic1007househome"></i>关于</li>
                    <li><i class="iconfont icon-basic1035mobilephone"></i>归档</li>
                    <li><i class="iconfont icon-iconfontshouji"></i>标签</li>
                </div>
            </div>
            <div class="user_info">
                <div class="user_logo">
                    <img src="../../assets/img/logo.jpg" alt="资源未找到" width="106" height="106">
                </div>
                <div class="username">
                    <b>username</b>
                </div>
                <div class="user_desc">javascript | HTML5 | CSS3 | Node | React | Angular | Vue | MongoDB</div>
                <div class="user_other">
                    <div class="item">
                        <div>8</div>
                        <div class="user_font">日志</div>
                    </div>
                    <div class="item">
                        <div>64</div>
                        <div class="user_font">分类</div>
                    </div>
                    <div class="item">
                        <div>100</div>
                        <div class="user_font">标签</div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</body>
<script src="../../assets/js/vue2.5.16.js"></script>
<script src="../../assets/js/axios.js"></script>

<script>
    var homeVm = new Vue({
        el: '#home',
        data: {
            data: {
                currentPage: 0,
                pageSize: 6
            },
            articleJson: [],
            showLoading: true,
            mainOffsetLeft: 0,
            timer: null
        },
        methods: {
            // 加载数据
            handleLoadArticlList: function() {
                var _this = this
                if (_this.data) {
                    axios.post('/articleList', this.data)
                        .then(function (response) {
                            _this.showLoading = false
                            clearTimeout( _this.timer)
                            if (response.data.length > 0) {
                                _this.articleJson  =  _this.articleJson.concat(response.data)
                            }
                        })
                        .catch(function (error) {
                            _this.showLoading = false
                        })
                }
            },
            // 判断是否滚动到底部
            lowEnough: function() {
                var pageHeight = Math.max(document.body.scrollHeight, document.body.offsetHeight)// 滚动内容的高度
                var viewportHeight = window.innerHeight || // 窗口的高度
                    document.documentElement.clientHeight ||
                    document.body.clientHeight || 0
                var scrollHeight = window.pageYOffset || // 滚动的距离
                    document.documentElement.scrollTop ||
                    document.body.scrollTop || 0
                return pageHeight - viewportHeight - scrollHeight === 0
            }
        },
        computed: {
            // 计算div.main到窗口左边缘的left值
            handleOffsetLeft: function () {
                return this.mainOffsetLeft
            }
        },
        created: function () {
            var _this = this
            setTimeout(function () {
                _this.handleLoadArticlList()
            },3000)
        },
        mounted: function() {
            this.mainOffsetLeft = this.$refs.main.offsetLeft + 710
            var _this = this
            _this.timer = null
            // 监听滚动条事件
            window.addEventListener('scroll', function() {
               if (_this.lowEnough()) {
                   _this.showLoading = true
                   _this.timer = setTimeout(function () {
                       _this.data.currentPage ++
                       _this.handleLoadArticlList()
                   },3000)
               }
             }, false)
            // 监听窗口变化事件
            window.onresize = function(){
                _this.mainOffsetLeft = _this.$refs.main.offsetLeft + 710
            }
        }
    })

</script>
</html>
