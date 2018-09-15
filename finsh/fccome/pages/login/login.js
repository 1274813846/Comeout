// pages/login/login.js
const app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userid:"",
    psd:""
  },
  inputChangeU: function (e) {
    this.data.userid = e.detail.value;
    console.log(e.detail.value)
  },
  inputChangeP: function (e) {
    this.data.psd = e.detail.value;
    console.log(e.detail.value)
  },
  onlogin:function(){
    var that = this
    var userid=that.data.userid
    var psd = that.data.psd
    wx.request({
      url: "https://wushan.mynatapp.cc/ComeOut/logonServlet",
      data:{
          userid:userid,
          psd:psd
      },
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        var len = res.data.length
        console.log(res.data.length);
        if(len==0){
          wx.showToast({
            title: '登录失败',
            icon: 'loading'
          })
        }else{
          getApp().globalData.userid = userid;
          wx.redirectTo({
            url: '/pages/base/base',
          })
        }
      }
    })
  }
})