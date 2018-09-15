// pages/choose/choose.js
const app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    mychoose:''
  },
  pee:function(){
    wx.redirectTo({
      url: '/pages/base_pee/pee',
    })
    app.globalData.mychoose='pee'
    this.setData({
      mychoose: getApp().globalData.mychoose
    });
    console.log(this.data.mychoose)
    console.log(getApp().globalData.mychoose)
    this.mychoice();
  },
  job:function(){
    wx.redirectTo({
      url: '/pages/base_job/job',
    })
    getApp().globalData.mychoose = 'job'
    this.setData({
      mychoose: getApp().globalData.mychoose
    });
    this.mychoice();
  },
  mychoice:function(){
    var that = this
    var sid = getApp().globalData.userid
    var mychoose = getApp().globalData.mychoose
    wx.request({
      url: "https://wushan.mynatapp.cc/ComeOut/mychoiceServlet",
      data: {
        sid: sid,
        mychoose: mychoose
      },
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        
      }
    })
  }
})