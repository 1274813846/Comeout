var hotapp = require('/hotapp.js');

function formatTime(date) {
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()

    var hour = date.getHours()
    var minute = date.getMinutes()
    var second = date.getSeconds()

    return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function getYMD(date) {
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()

    return [year, month, day].map(formatNumber).join('-')
}

function getHM(date) {
    var hour = date.getHours()
    var minute = date.getMinutes()

    return [hour, minute].map(formatNumber).join(':')
}

function getW(date) {
    var d = date.getDay();
    var arr = ['日', '一', '二', '三', '四', '五', '六'];

    return '星期' + arr[d];
}

function formatNumber(n) {
    n = n.toString()
    return n[1] ? n : '0' + n
}  

function formatDate(date) {
    var year = date.getFullYear()
    var month = date.getMonth() + 1
    var day = date.getDate()

    return [year, month, day].map(formatNumber).join('')
}

function request(option, cb) {

    wx.request({
        url: option.url,
        data: option.data ? option.data : {},
        method: option.method ? option.method : 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
        header: option.header ? option.header : { 'content-type': 'application/json' }, // 设置请求的 header
        success: function (res) {
            cb(res);
        },
        fail: function (err) {
            cb(err);
        }
    });

}

function showLoading() {
    wx.showToast({
        title: '加载中',
        icon: 'loading',
        duration: 10000
    });
}

function hideLoading() {
    wx.hideToast();
}

function showSuccess(msg, time, cb) {

    wx.showToast({
        title: msg,
        icon: 'success',
        duration: time
    });

    setTimeout(function () {
        cb();
    }, time);

}

function genData(res) {
    var items = [];
    for (var i = 0; i < res.data.length; i++) {
        var item = res.data[i];
        items.push(item);
    }
    return items;
}

function getObjectKeys(obj) {
    var keys = [];
    for (var key in obj) {
        keys.push(key);
    }
    return keys;
}

function getObjectValues(obj) {
    var values = [];
    for (var key in obj) {
        values.push(obj[key]);
    }
    return values;
}


// //获取fakeid
// function getfakeid(){
//     var that = this
//     hotapp.wxlogin(function (openID) {
//         return openID;
//     })
// }


module.exports = {
    formatTime: formatTime,
    request: request,
    showLoading: showLoading,
    hideLoading: hideLoading,
    genData: genData,
    showSuccess: showSuccess,
    formatDate: formatDate,
    getYMD: getYMD,
    getHM: getHM,
    getW: getW
    // getfakeid: getfakeid
}
