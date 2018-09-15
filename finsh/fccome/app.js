//app.js
var hotapp = require('utils/hotapp.js');
App({
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // // 登录
    // wx.login({
    //   success: res => {
    //     // 发送 res.code 到后台换取 openId, sessionKey, unionId
    //     console.log(res.code)
    //     this.globalData.code=res.code
    //   }
    // })
    // // 获取用户信息
    // wx.getSetting({
    //   success: res => {
    //     if (res.authSetting['scope.userInfo']) {
    //       // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
    //       wx.getUserInfo({
    //         success: res => {
    //           // 可以将 res 发送给后台解码出 unionId
    //           this.globalData.userInfo = res.userInfo

    //           // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
    //           // 所以此处加入 callback 以防止这种情况
    //           if (this.userInfoReadyCallback) {
    //             this.userInfoReadyCallback(res)
    //           }
    //         }
    //       })
    //     }
    //   }
    // })
    // this.onfakeid()
    this.getUserInfo();
  },
  //得出fakeid
  onfakeid: function () {
      var that=this
      hotapp.wxlogin(function (openID) {
          that.globalData.fakeopenid = openID
      })
  },
  getUserInfo: function (cb) {
      var that = this

      if (this.globalData.userInfo) {
          typeof cb == "function" && cb(this.globalData.userInfo)
      } else {
          //调用登录接口
          wx.login({
              success: function (r) {

                  // 获取用户信息 
                  wx.getUserInfo({
                      success: function (res) {
                          that.globalData.userInfo = res.userInfo
                          typeof cb == "function" && cb(that.globalData.userInfo)
                      }
                  })

                  // 获取用户openid
                  wx.request({
                    url: 'https://wushan.mynatapp.cc/ComeOut/code2id',
                      data: {
                          code: r.code
                      },
                      method: 'GET',
                      success: function (res) {
                          console.log('---code 换取 openid---');
                          wx.setStorageSync('openid', res.data.openid);
                          console.log(res.data.openid)
                      }
                  })
              }
          })
      }
  },
  globalData: {
    userInfo: null,
    code: "",
    appid: "wxea91df7d6e137e92",
    secret: "e1098542ac9dbeadc7e7499071b28604",
    openid:"",
    jobList: [],
    mychoose:"pee",
    userid:""
  }
})