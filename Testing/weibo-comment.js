function commentWeibo(content, mid) {
  var formData = new FormData();
  formData.append('act', 'post');
  formData.append('mid', mid);
  formData.append('uid', '1689761914');
  formData.append('forward', '0');
  formData.append('isroot', '0');
  formData.append('content', content);
  formData.append('location', 'page_100808_super_index');
  formData.append('module', 'scommlist');
  formData.append('group_source', '');
  formData.append('pdetail', '100808a8fa6bef39d9fb73fcf8431471211e9e');
  formData.append('is_comment_base', '1');
  formData.append('_t', '0');

  var xhr = new XMLHttpRequest();
  xhr.timeout = 3000;
  xhr.responseType = "text";
  xhr.open('POST', 'http://weibo.com/aj/v6/comment/add?ajwvr=6&__rnd=' + new Date().getTime(), true);
  xhr.onload = function(e) {
    if (this.status == 200 || this.status == 304) {
      var data = JSON.parse(this.responseText);
      if (data.code == "100000") {
        console.log(content);
      } else if (data.code == "100027") {
        console.log(data);
      } else {
        console.log(data);
      }
    }
  };
  xhr.send(formData);
}

var mids = ['4133109547214697','4133121748177998','4133107269924253','4133108200288025'];
var count = 330;
var content = '[心]';

setInterval(function() {
  count++;
  content = content + ' ' + count;
  commentWeibo(content, mids[0]);
  commentWeibo(content, mids[1]);
  commentWeibo(content, mids[2]);
  commentWeibo(content, mids[3]);
}, 610000); 
