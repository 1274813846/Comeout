// pages/Pee/Pee.js
var util = require('../../utils/util');
Page({

  /**
   * 页面的初始数据
   */
  data: {
      Retest_fractional_line: [
          {
              years: "2017",
              source: "武汉大学",
              Department: "计算机科学与工程",
              Professional_code: "10086",
              Professional_name: "软件工程",
              total_score: "450",
              Politics: "100",
              English: "100",
              Subject_three: "150",
              Subject_four: "150"
          }
      ],
          source: "",
          Professional_code: "",
          years: "",
          showable:false
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },
  inputChange1:function(e){
        this.setData({
            source:e.detail.value
        })
  },
  inputChange2: function (e) {
      this.setData({
          Professional_code: e.detail.value
      })
  },
  inputChange3: function (e) {
      this.setData({
          years: e.detail.value
      })
  },
  query: function (e) {
      var that = this
      var options = {
        url: "https://wushan.mynatapp.cc/ComeOut/peeServlet",
          data: {
              source: that.data.source ,
              Professional_code: that.data.Professional_code,
              years: that.data.years
          }
      };

      util.request(options, (res, err) => {
          console.log(res.data.length)
          var Retest_fractional_line = [];
          for (var i = 0; i < res.data.length; i++) {
              Retest_fractional_line.push(res.data[i]);
          }
          that.setData({
              Retest_fractional_line: Retest_fractional_line,
              showable:true
          });
      });
  }
 
})