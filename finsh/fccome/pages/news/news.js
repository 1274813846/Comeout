// pages/news/news.js
var util = require('../../utils/util');
Page({

  /**
   * 页面的初始数据
   */
  data: {
        id:"",
        news:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      this.setData({
          id: options.id
      })
      console.log(options.id)
      this.query()
  },
query: function (e) {
    var that = this
    var options = {
      url: "https://wushan.mynatapp.cc/ComeOut/detailNews",
        data: {
            id:that.data.id
        }
    };

    util.request(options, (res, err) => {
        console.log(res.data[0])
        that.setData({
            news:res.data[0]
        })
    });
},
  /**
   * 生命
   * 周期函数--监听页面初次渲染完成

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