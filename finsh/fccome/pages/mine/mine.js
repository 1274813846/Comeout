// pages/message/message.js
var app = getApp();
var hotapp = require('../../utils/hotapp.js');
//导入common.js
var common = require('../../utils/common.js');
var QR = require("../../utils/qrcode.js");
var util = require("../../utils/util.js");
Page({
    /**
     * 页面的初始数据
     */
    data: {
        motto: 'Hello World',
        userInfo: [],
        hasUserInfo: false,
        canIUse: wx.canIUse('button.open-type.getUserInfo'),
        fakeopenid: '',
        canvasHidden: true,
        canvasId: "qrcCanvas",
        imagesrc:"../../images/mine.jpg",
        windowHeight:0,
        windowWidth:0,
        casArray: ['计算机', '材料化学', '艺术设计', '英语翻译','工程力学','土木工程'],
        casIndex: 0,
        major: "未分类",
        codeMessage:{
            openid: "",
        },
        toastText: "吐司",
        toastHidden: true, //吐司
        mychoose:"",
        userid:""
    },
    base: function () {
      if(this.data.userid=="") {
        wx.navigateTo({
          url: '/pages/login/login',
        })
      }
      else{
        wx.navigateTo({
          url: '/pages/base/base',
        })

      }
      
    },
    choose: function () {
      if (this.data.userid == "") {
        wx.navigateTo({
          url: '/pages/login/login',
        })
      }
      else {
        wx.navigateTo({
          url: '/pages/choose/choose',
        })

      }
    },
    result: function () {
      wx.navigateTo({
        url: '/pages/final/final',
      })
    },
    final: function () {
      wx.navigateTo({
        url: '/pages/final/final',
      })
    },
    exit:function(){
      wx.showToast({
        title: '已退出',
        icon: 'success'
      });
      getApp().globalData.userid="";
      this.onShow()
    },
    onLoad: function () {
        if (app.globalData.userInfo) {
            this.setData({
                userInfo: app.globalData.userInfo,
                hasUserInfo: true
            })
            console.log(this.data.userInfo)
        } else if (this.data.canIUse) {
            // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
            // 所以此处加入 callback 以防止这种情况
            app.userInfoReadyCallback = res => {
                this.setData({
                    userInfo: res.userInfo,
                    hasUserInfo: true
                })
            }
        } else {
            // 在没有 open-type=getUserInfo 版本的兼容处理
            wx.getUserInfo({
                success: res => {
                    app.globalData.userInfo = res.userInfo
                    this.setData({
                        userInfo: res.userInfo,
                        hasUserInfo: true
                    })
                }
            })
        };
        this.setData({
          mychoose: getApp().globalData.mychoose,
          userid:getApp().globalData.userid
        });
    },
    //选择器
    bindCasPickerChange: function (e) {
        var that = this;
        that.setData({
            major: that.data.casArray[e.detail.value]
        })
    },
    getUserInfo: function (e) {
        console.log(e)
        app.globalData.userInfo = e.detail.userInfo
        this.setData({
            userInfo: e.detail.userInfo,
            hasUserInfo: true
        })
    },
    // send2Jee: function () {
    //     wx.showToast(this.data);
    //     console.log(this.data)
    //     wx.request({
    //         url: "http://localhost:8888/ComeOut/loginServlet",
    //         data: {
    //             fakeopenid: this.data.fakeopenid,
    //             nickName: this.data.userInfo.nickName,
    //             avatarUrl: this.data.userInfo.avatarUrl,
    //             major: this.major
    //         },
    //         method: 'GET',
    //         header: {
    //             'Content-Type': 'application/json'
    //         },
    //         success: function (res) {
    //             console.log(res.data);
    //         },
    //         fail: function () {
    //             console.log("fail");
    //         }
    //     })
    // },
    // jobtap: function () {
    //     var that = this;
    //     wx.request({
    //         url: "http://localhost:8888/ComeOut/jobAllServlet",
    //         data: {

    //         },
    //         method: "POST",
    //         header: {
    //             'Content-Type': 'application/json'
    //         },
    //         success: function (res) {
    //             console.log(res.data);
    //         },
    //         fail: function () {
    //             console.log("fail");
    //         }
    //     })
    // },
    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh: function () {
        wx.showLoading({
            title: '加载中...',
        })
        setTimeout(function () {
            wx.hideLoading()
            wx.stopPullDownRefresh()
        }, 2000)
    },

    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom: function () {
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {
        return {
            "title": "消息转发"
        }
    },
    onPaegScroll: function (e) {
        console.log('pagescoll')
        console.log(e)
    },
    //二维码功能
    onGenQrc: function (e) {
        var code=[];
        code.push({
            openid: wx.getStorageSync('openid'),
        });
        var str = JSON.stringify(code);
        console.log(str)
        this.createQrCode(str, this.data.canvasId, this.size.w, this.size.h);
        this.setData({
            canvasHidden: !this.data.canvasHidden
        })
    },
    /**
       * 生命周期函数--监听页面初次渲染完成
       */
    onReady: function () {
        this.size = this.setCanvasSize();//动态设置画布大小  
        // this.code();
        // **code to id
    },
    //适配不同屏幕大小的canvas  
    setCanvasSize: function () {
        var size = {};
        try {
            var res = wx.getSystemInfoSync();
            var scale = 750 / 686;//不同屏幕下canvas的适配比例；设计稿是750宽  
            var width = res.windowWidth / scale;
            var height = width;//canvas画布为正方形  
            size.w = width;
            size.h = height;
        } catch (e) {
            // Do something when catch error  
            console.log("获取设备信息失败" + e);
        }
        return size;
    },
    createQrCode: function (str, canvasId, cavW, cavH) {
        //调用插件中的draw方法，绘制二维码图片  
        QR.api.draw(str, canvasId, cavW, cavH);
    },

    //存储用户信息
    // saveUser: function () {
    //     var that = this;
    //     var User = [];
    //     var major = that.data.major;
    //     var fakeid = { "openid": wx.getStorageSync('openid'), "major": major }
    //     User.push(that.data.userInfo);
    //     User.push(fakeid);
    //     var str = JSON.stringify(User);
    //     console.log(str)
    //     console.log();
    //     wx: wx.request({
    //         url: "https://comeout.mynatapp.cc/ComeOut/loginServlet",
    //         data: {
    //             login: str,
    //         },
    //         header: { 'Content-Type': 'application/x-www-form-urlencoded' },
    //         method: 'POST',
    //         dataType: 'json',
    //         success: function (res) {
    //             console.log(res)
    //         },
    //         fail: function (res) { },
    //     })
    // },
    // showfakeid: function () {
    //     console.log(app.globalData.fakeopenid)
    // },
    //点击隐藏二维码
    hidden:function(){
        this.setData({
            canvasHidden:true,
        })
    },
    onScancode:function(){
        var that = this;
        wx.scanCode({
            success: (res) => {
                console.log(res.result)
                that.setData({
                    toastText: res.result,
                    toastHidden: !this.data.toastHidden
                })
            }
        })
    },
    onToastChanged: function () {
        this.setData({
            toastHidden: true
        });
    },
    onShow: function () {
      this.setData({
        mychoose: getApp().globalData.mychoose,
        userid: getApp().globalData.userid
      });
      console.log("onshow")
      console.log(app.globalData.mychoose)
    },
    //,  
//     code:function(){
//         var code;
//         wx.login({
//             success: res => {
//                 var options = {
//                     url: "https://comeout.mynatapp.cc/ComeOut/code2id",
//                     data: {
//                         code: res.code,
//                     }
//                 };

//                 util.request(options, (res, err) => {
//                     console.log(res.data.openid)
//                     wx.setStorageSync('openid', res.data.openid)
//                     var value = wx.getStorageSync('openId')
//                      if (value) {
//                             console.log(value)
//                         }
//                 });
//             }
//         })
        
//     }

})