<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
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
                    <a :href="'/articleDetails?id=' + item.id">
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
            <div v-if="isLast" class="last_div">
                我是有底线的
            </div>
        </div>
        <%@ include file="blogRight.jsp" %>
    </section>
<%@include file="footer.jsp"%>
<script>
    NProgress.start();
    NProgress.done();
    var homeVm = new Vue({
        el: '#home',
        data: {
            data: {
                currentPage: 0,
                pageCount: 6
            },
            articleJson: [],
            showLoading: true,
            mainOffsetLeft: 0,
            totalCount: 0,
            isLast: false
        },
        methods: {
            // 加载数据
            handleLoadArticlList: function() {
                var _this = this
                if (_this.data) {
                    _this.showLoading = true
                    axios.post('/articleList', _this.data)
                        .then(function (response) {
                            _this.showLoading = false
                            _this.totalCount = response.data.totalCount
                            if (response.data.data.length > 0) {
                                _this.articleJson = _this.articleJson.concat(response.data.data)
                            }
                        })
                        .catch(function () {
                            _this.showLoading = false
                        })
                }
            },
            // 判断是否滚动到底部
            isScrollBottom: function() {
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
            this.handleLoadArticlList()
        },
        mounted: function() {
            this.mainOffsetLeft = this.$refs.main.offsetLeft + 710
            var _this = this

            // 监听滚动条事件
            window.addEventListener('scroll', function() {
                _this.isLast =  _this.articleJson.length === _this.totalCount
                if (_this.isScrollBottom() && !_this.isLast) {
                    _this.data.currentPage ++
                    _this.handleLoadArticlList()
                }
            }, false)

            // 监听窗口变化事件
            window.onresize = function(){
                _this.mainOffsetLeft = _this.$refs.main.offsetLeft + 710
            }
        }
    })
</script>
