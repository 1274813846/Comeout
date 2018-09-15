// pages/job/job.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    choose2:[{
      name:"已签三方单位"
    },{
      name:"就业意向单位"
    },{
      name:"暂不就业原因"
    }],
    job_state:"暂不就业原因",
    company:""
  },
  inputChange: function (e) {
    console.log(e)
    this.data.company = e.detail.value;
    console.log(e.detail.value)
  },
  radioChange: function (e) {
    console.log('radio发生change事件，携带value值为：', e.detail.value)
    this.setData({
      job_state:e.detail.value
    })
  },

  success: function () {
    wx.showToast({
      title: '提交成功',
      icon: 'success'
    })
    console.log(this.data.value)
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

  submit:function(){
    var that = this
    var job_state = that.data.job_state
    var company = that.data.company
    var sid = getApp().globalData.userid

    wx.request({
      url: "https://wushan.mynatapp.cc/ComeOut/rejobServlet",
      data: {
        sid:sid,
        job_state: job_state,
        company: company
      },
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        wx.showToast({
          title: '提交成功',
          icon: 'success'
        })
      },
      fail:function(){
        wx.showToast({
          title: '提交失败',
          icon: 'loading'
        })
      }
    })
  }
})