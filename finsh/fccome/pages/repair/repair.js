// pages/repair/repair.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    idnames: {
      teacher: "null",
      sid: "null",
      name: "null",
      major: "null",
      class: "null",
      psd: "null"
    },
   

  },
  success:function(){
    var that=this
    var teacher= that.data.idnames.teacher
    var sid = that.data.idnames.sid
    var name = that.data.idnames.name
    var major = that.data.idnames.major
    var _class = that.data.idnames.class
    var psd = that.data.idnames.psd
    wx.request({
      url: "https://wushan.mynatapp.cc/ComeOut/repairServlet",
      data: {
        teacher:teacher,
        sid :sid,
        name :name,
        major : major,
        _class : _class,
        psd : psd
      },
      header: {
        'Content-Type': 'application/json'
        //'content-type': 'application/x-www-form-urlencoded'
      },
      success: function (res) {
        wx.showToast({
          title: '提交成功',
          icon: 'success'
        })
      },
      fail: function (res) {
        wx.showToast({
          title: '提交失败',
          icon: 'loading'
        })
      }
    })
   
  },

  onLoad:function(options){
    this.showBase();
  },
  inputChange1: function (e) {
    this.data.idnames.major = e.detail.value;
    console.log(e.detail.value)
  },
  inputChange2: function (e) {
    this.data.idnames.class = e.detail.value;
    console.log(e.detail.value)
  },
  inputChange3: function (e) {
    this.data.idnames.teacher = e.detail.value;
    console.log(e.detail.value)
  },
  inputChange4: function (e) {
    this.data.psd = e.detail.value;
    console.log(e.detail.value)
  },
  showBase: function () {
    var that = this
    var sid = getApp().globalData.userid
    wx.request({
      url: "https://wushan.mynatapp.cc/ComeOut/baseServlet",
      data:{
        sid: sid
      },
      header: {
        'Content-Type': 'application/json'
        //'content-type': 'application/x-www-form-urlencoded'
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