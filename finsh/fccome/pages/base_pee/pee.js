// pages/pee/pee.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    volunteers:{
      first:"武汉工程大学",
      second:"武汉大学",
      third:"武汉理工大学",
      finalschool:'武汉工程大学',
      major:'计算机',
      type:"专硕"
    },
    
    choose1:[{
      name:'学硕',
      checked:'true',
    }, {
      name: '专硕',
      checked: 'true'
    }
    ],
    
  },
  radioChange: function (e) {
    
    console.log('radio发生change事件，携带value值为：', e.detail.value)
    this.data.volunteers.type=e.detail.value
    
  },


  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.showBase();
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
  showBase: function () {
    var that = this
    var sid = getApp().globalData.userid
    wx.request({
      url: "https://wushan.mynatapp.cc/ComeOut/basepeeServlet",
      data: {
        sid: sid
      },
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {
        console.log(res.data);
        that.setData({
          volunteers: res.data[0]
        })
      }
    })
  },
  inputChange1: function (e) {
    this.data.volunteers.first = e.detail.value;
    console.log(e.detail.value)
  },
  inputChange2: function (e) {
    this.data.volunteers.second = e.detail.value;
    console.log(e.detail.value)
  },
  inputChange3: function (e) {
    this.data.volunteers.third = e.detail.value;
    console.log(e.detail.value)
  },
  inputChange4: function (e) {
    this.data.volunteers.finalschool= e.detail.value;
    console.log(e.detail.value)
  },
  inputChange5: function (e) {
    this.data.volunteers.major = e.detail.value;
    console.log(e.detail.value)
  },

  onpee: function () {
    var that = this
    var sid = getApp().globalData.userid
    var first = that.data.volunteers.first
    var second = that.data.volunteers.second
    var third = that.data.volunteers.third
    var finalschool = that.data.volunteers.finalschool
    var major = that.data.volunteers.major
    var type = that.data.volunteers.type
    wx.request({
      url: "https://wushan.mynatapp.cc/ComeOut/repeeServlet",
      data: {
        sid: sid,
        first: first,
        second: second,
        third: third,
        finalschool: finalschool,
        major: major,
        type: type
      },
      header: {
        'Content-Type': 'application/json'
      },
      success: function (res) {

      }
    })
  }
})