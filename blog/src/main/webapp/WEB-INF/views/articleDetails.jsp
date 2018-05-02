<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<section ref="main" class="main" id="articleDetails">
    <div class="blog_left">
        articleDetails
    </div>
    <%@ include file="blogRight.jsp" %>
</section>
<%@include file="footer.jsp"%>
<script>
    var articleDetailsVm = new Vue({
        el: "#articleDetails",
        data: {
            mainOffsetLeft: 0,
        },
        computed: {
            // 计算div.main到窗口左边缘的left值
            handleOffsetLeft: function () {
                return this.mainOffsetLeft
            }
        },
        mounted: function() {
            this.mainOffsetLeft = this.$refs.main.offsetLeft + 710
            var _this = this

            // 监听窗口变化事件
            window.onresize = function(){
                _this.mainOffsetLeft = _this.$refs.main.offsetLeft + 710
            }
        }
    })
</script>