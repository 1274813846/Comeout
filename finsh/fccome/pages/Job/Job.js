// pages/Job/Job.js
var util = require('../../utils/util');
var app=getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    jobList: [],
    inputValue: '',
    jobquery:{
        id: -1,
        location: "",
        salary: -1,
        position: "",
        tel: "",
        company: "",
        lng: -1,
        lat: -1,
        time: "",
        detail: "",
        imageUrl: "",
    },
    toastText: "已经到底了",
    toastHidden: true, //吐司
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  
  },
  inputChange: function (e) {
      this.data.inputValue = e.detail.value;
      console.log(e.detail.value)
  },
  query: function (e) {
      var that = this;
      that.data.jobquery.company = that.data.inputValue;
      var jobquery=[];
      jobquery.push(that.data.jobquery)
      var str = JSON.stringify(jobquery);
      console.log(str);
      var options = {
        url: "https://wushan.mynatapp.cc/ComeOut/queryJobServlet",
          data: { 
              jobquery: str
          }
      };

      util.request(options, (res, err) => {
          var jobs = [];
          for (var i = 0; i < res.data.length; i++) {
              jobs.push(res.data[i]);
          }
          that.setData({
              jobList: jobs
          });
          console.log(that.data.jobList)
          app.globalData.jobList=that.data.jobList;
      });
        
  },
  goToDetailPage: function (e) {
      var id = e.currentTarget.id;
      var qty = e.currentTarget.dataset.qty;
      wx.navigateTo({
          url: '../detail/detail?id=' + id
      });

  },
  onToastChanged: function () {
      this.setData({
          toastHidden: true
      });
  },  
  onReachBottom: function () {
      this.setData({
          toastHidden: !this.data.toastHidden
      })
  }
})