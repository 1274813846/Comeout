//util.js
function imageUtil(e){
    var imageSize={};
    var originalWidth=e.detail.width;    //图片原宽
    var orginalHeight=e.detail.height;   //图片原高
    var originalScale=orginalHeight/originalWidth;  //高宽比
    console.log('orginalWidth:'+originalWidth)
    console.log('orfinalHeight'+orginalHeight)

    //获取屏幕宽高
    wx.getSystemInfo({
        success: function(res) {
            var windowWidth = res.windowWidth;
            var windowHeight = res.windowHeight;
            var windowScale = windowHeight/windowWidth;
            console.log('windowWidth'+windowWidth);
            console.log('windowHeight'+windowHeight);
            if(originalScale>windowScale){
                //图片高宽比小于屏幕高宽比
                //图片缩放后的宽为屏幕宽
                imageSize.imageWidth = windowWidth;
                imageSize.imageHeight = (windowWidth * orginalHeight) / originalWidth;
            }else{
                imageSize.imageHeight = windowHeight;
                imageSize.imageWidth = (windowHeight * originalWidth) / orginalHeight;
            }
        }
    })
    console.log('缩放后的宽'+imageSize.imageWidth)
    console.log('缩放后的高'+imageSize.imageHeight)
    return imageSize;
}

module.exports={
    imageUtil:imageUtil
}