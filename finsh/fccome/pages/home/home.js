// pages/home/home.js
var util = require('../../utils/util');
Page({

  /**
   * 页面的初始数据
   */
  data: {
      imgUrls: [{
          image: "/images/jd1.jpg"
      }, {
          image: "/images/jd2.png"
        }, {
          image: "/images/jd4.jpg"
      }
        ],
      logos: [{
          image:"/images/icon1.png",
          title:"找工作"
      },{
          image: "/images/icon2.png",
          title: "考研"
      },{
          image: "/images/icon3.png",
          title: "计划"
      },{
          image: "/images/icon4.png",
          title: "轻松一下"
      }],
      indicatorDots: true,
      autoplay: true,
      interval: 3000,
      duration:1000,
      toastText:"吐司",
      toastHidden: true, //吐司
      funUrl: [
          "../Job/Job",
          "../Pee/Pee",
          "../new/new",
          "../story/story"
          ],
      keyword:"",
      news: []
      
  },
  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
      wx.showLoading({
          title: '加载中...',
      })
      setTimeout(function () {
          wx.hideLoading()
          wx.stopPullDownRefresh()
      }, 2000)
  },
  onLoad: function (options) {
      this.query()
  },
  scanCode:function(){
      var that=this;
      wx.scanCode({
          success: (res) => {
              
              console.log(res.result)
              that.setData({
                  toastText: res.result,
                  toastHidden: !this.data.toastHidden
              })
          }
      })
  },
  onToastChanged: function () {
      this.setData({ 
          toastHidden: true
          });
  },  
  goToDetailPage: function (e) {
      var that=this;
      var id = e.currentTarget.id;
      console.log(id);
      var qty = e.currentTarget.dataset.qty;
      wx.navigateTo({
          url: that.data.funUrl[id]
      });
    },
    goToNews:function(e){
        var id = e.currentTarget.dataset.id
        console.log(e.currentTarget.dataset.id)
        wx.navigateTo({
            url: '../news/news?id=' + id
        });
    },
  clear: function () {
      this.setData({
          keyword: ""
      })
  },
  query: function (e) {
      var that=this
      var options = {
          url: "https://wushan.mynatapp.cc/ComeOut/newsServlet",
          data: {
          }
      };

      util.request(options, (res, err) => {
          console.log(res.data.length)
          var news = [];
          for (var i = 0; i < res.data.length; i++) {
              news.push(res.data[i]);
          }
          that.setData({
              news: news
          });
      });
  }
})