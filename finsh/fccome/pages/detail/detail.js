// pages/detail/detail.js
var id;
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
        jobMsg:[],
        isLoading: true,
        windowWidth:'',
        windowHeight:'',
        pixelRatio:'',
        showBookBtn:true
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      id = options.id;
      that.setData({
          jobMsg: app.globalData.jobList[id],
      })
      console.log(that.data.jobMsg);

      //1.动态获取设备屏幕的高度，然后计算scroll view的高度
      wx.getSystemInfo({
          success: function (res) {
              that.setData({
                  windowWidth: res.windowWidth,
                  windowHeight: res.windowHeight,
                  pixelRatio: res.pixelRatio
              });
          }
      });

      //2.从豆瓣查询某本书的相关信息
      id = options.id;
      that.setData({
          isLoading: true
      });
  },


  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
        
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
  
  }
})