// pages/face/face.js
var imageUtil = require('../../utils/size.js')
var app=getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
      imagesrc: "/images/face.png",
      windowHeight: 0,
      windowWidth: 0,
      source: "爪哇人",
      hitokoto: "世界上最远的距离不是404而是502，，。",
      timedown:7,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
      //json数据传输
      var that = this
      wx.request({
          url: "https://wxapi.hotapp.cn/proxy/?appkey=hotapp389356023&url=http://hitokoto.jijidown.com/v1/api/hitokoto?maxlength=50",
          header: {
              'Content-Type': 'application/json'
          },
          success: function (res) {
              console.log(res.data);
              that.setData({
                  source: res.data["source"],
                  hitokoto: res.data["hitokoto"]
              })
          }
      })
      wx.getSystemInfo({
          success: function (res) {
              that.setData({
                windowHeight: res.windowHeight,
                windowWidth: res.windowWidth
            })
          }
      })
      //页面跳转
      setTimeout(function () {
          wx.switchTab({
              url: '../home/home'
          })
      }, 7000)
  },
  imageLoad:function(e){
      var imageSize=imageUtil.imageUtil(e)
      this.setData({
          imagewidth:imageSize.imageWidth,
          imageheight:imageSize.imageHeight
      })
  },
  jump2url:function(){
    wx.switchTab({
        url: '../home/home'
    })
  },
})