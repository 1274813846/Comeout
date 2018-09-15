// pages/story/story.js
var util = require('../../utils/util');
var app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
        date:"",
        today:"",
  },
  onLoad: function () {
      // 调用函数时，传入new Date()参数，返回值是日期和时间  
      var date = util.formatDate(new Date());
      var timeDate = util.formatTime(new Date());
      // 再通过setData更改Page()里面的data，动态更新页面的数据  
      this.setData({
          date: date,
          today: timeDate,
      });
    //   console.log(this.data.timeDate)
  },
  onReady:function(){
    this.story()
  },
  story:function(){
      var that=this;
      var options = {
          url: "https://interface.meiriyiwen.com/article/day?dev=1",
          data: {
              date: this.data.date
          }
      };

      util.request(options, (res, err) => {
            var story=[];
            var resData=res.data.data;
            resData.content=resData.content.replace(/\<p>/g, "");
            resData.content=resData.content.replace(/\<\/p>/g, "");
            console.log()
            that.setData({
                story: resData
            })
            console.log(that.data.story)
      });
  }

})