// pages/base/base.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    idnames:{
      teacher:"null",
      sid:"null",
      name:"null",
      major:"null",
      class:"null"
    }
    
  },
  repair:function(){
    var that = this
    var idnames = that.data.idnames
    // console.log(idnames)
    wx.navigateTo({
      url: '/pages/repair/repair',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
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
      this.showBase();
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
  
  },


  showBase:function(){
    var that = this
    var sid = getApp().globalData.userid
    wx.request({
      url: "https://wushan.mynatapp.cc/ComeOut/baseServlet",
          data:{
            sid:sid
          },
          header: {
              'Content-Type': 'application/json'
          },
          success: function (res) {
              console.log(res.data);
              that.setData({
                idnames: res.data[0]
              })
          }
      })
  }
})