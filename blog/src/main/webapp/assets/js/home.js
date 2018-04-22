$(function () {
    var type = 'post';
    var url = 'http://localhost:19090/articleList';
    var data = {
        "currentPage": 1,
        "pageCount": 6
    };

    $.ajax({
        type: type,
        url: url,
        data: JSON.stringify(data),
        success: function(res) {
            console.log(res)
        },
        error: function (err) {
            console.log(err)
        }
    });

    $.ajax({
        type: 'get',
        url: 'http://localhost:19090/Menu',
        success: function(res) {
            console.log(res)
        },
        error: function (err) {
            console.log(err)
        }
    });
});