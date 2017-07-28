// The content you want to add when you posting, forwarding or adding comments
// If you add new one, please split the sentence with ","
// You can add as many as you want here.
var comment_contents = [
'#甄萱# 我萱又瘦了; 我萱又瘦了; 我萱又瘦了',
'#甄萱# 馥甄 信; 馥甄 信; 馥甄 信',
'#甄萱# 你要什麼我就給你什麼 不管是精神上的陪伴還是物質上的東西 只要我能做到我都會給',
'#甄萱# 因為她很美 她是我心目中的睡美人啊',
'#甄萱# [爱][爱]',
'#甄萱# 330 [爱] 1031',
'#甄萱# 因為我知道你會很長壽 我知道你想讓我活很長會陪著你',
'#甄萱# 我愛她 我可以包容她的一切',
'#甄萱# 我前世一定是​​Selina的奴隸 上輩子已經被她累死了 但是債沒換完',
'#甄萱# [爱]',
'#甄萱# Selina是我心目中的睡美人啊',
'#甄萱# [爱][爱][爱][爱]',
'#甄萱# [doge][doge][doge]'
];


// Weibo URLs you want to comment or forward
// Please make sure the URL format like below:
// If you add new one, please split the sentence with ","
var weiboUrls = [
  'http://weibo.com/6329047861/Feo7ye59O'
];

// Your own weibo link, once you login please click your "flower" link to get this
const MY_WEIBO_URL =  'http://weibo.com/1689761914/follow';

// What I want to do?
// 0: Comment and forward
// 1: Just comment
// 2: Forward into Group
// 3: Posting new weibo
// 4. Forward with two comments
// 5: ALL -> Can do all above togetger
const WHAT_I_WANT_TODO = 200;

// send weibo frequence (seconds)
const TIME_OUT = 10;

/*********************!!!! YOU DONT NEED TO TUNCH ANYTHING BELOW. JUST STUPID SCRIPT !!!!!******************************************/
/***********************************************************************************************************************************/
function forwardWeibo(mid, content) {
    var formData = new FormData();

    // page_100505_home, page_100808_super_index
    formData.append('location', 'page_100808_super_index');
    formData.append('reason', content);
    formData.append('style_type', 1);
    formData.append('pdetail', '100808a8fa6bef39d9fb73fcf8431471211e9e');
    formData.append('mid', mid);
    formData.append('rank', 0);
    formData.append('_t', '0');

    var xhr = new XMLHttpRequest();
    //xhr.timeout = 3000;
    //xhr.responseType = "text";

    xhr.open('POST', 'http://weibo.com/aj/v6/mblog/forward?ajwvr=6&domain=100808&__rnd=' + new Date().getTime(), true);
    //xhr.withCredentials = true;

    xhr.onload = function(e) {
        if (this.status == 200 || this.status == 304) {
            var data = JSON.parse(this.responseText);
            if (data.code == "100000") {
                console.log(data);
            }
            else if (data.code == "100027") {
                console.log(data);
            }
            else {
                console.log(data);
            }
        }
    };
    xhr.send(formData);
}

function forwardWeiboPrivate(mid, content) {
    var formData = new FormData();

    formData.append('location', 'page_100505_home');
    formData.append('reason', content);
    formData.append('style_type', 1);
    formData.append('visible', 1);
    formData.append('mid', mid);
    formData.append('rank', 0);
    formData.append('pub_type','dialog');
    formData.append('_t', '0');

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://weibo.com/aj/v6/mblog/forward?ajwvr=6&domain=ocdnini0220&__rnd=' + new Date().getTime(), true);

    xhr.onload = function(e) {
        if (this.status == 200 || this.status == 304) {
            var data = JSON.parse(this.responseText);
            if (data.code == "100000") {
                console.log(data);
            }
            else if (data.code == "100027") {
                console.log(data);
            }
            else {
                console.log(data);
            }
        }
    };
    xhr.send(formData);
}

function forwardWeiboToHome(mid, content) {
    var formData = new FormData();

    formData.append('location', 'page_100505_single_weibo');
    formData.append('reason', content);
    formData.append('style_type', 2);
    formData.append('mid', mid);
    formData.append('rank', 0);
    formData.append('_t', '0');
    formData.append('is_comment_base',1);
    formData.append('is_comment', 1);

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://weibo.com/aj/v6/mblog/forward?ajwvr=6&domain=10505&__rnd=' + new Date().getTime(), true);

    xhr.onload = function(e) {
        if (this.status == 200 || this.status == 304) {
            var data = JSON.parse(this.responseText);
            if (data.code == "100000") {
                console.log(data);
            }
            else if (data.code == "100027") {
                console.log(data);
            }
            else {
                console.log(data);
            }
        }
    };
    xhr.send(formData);
}

function postWeibo(content) {
    var formData = new FormData();

    //formData.append('mid', mid);
    formData.append('location', 'page_100808_super_index');
    //formData.append('id', '100808a8fa6bef39d9fb73fcf8431471211e9e');
    formData.append('domain', '100808');
    formData.append('module', 'share_topic');
    formData.append('api_url', 'http://i.huati.weibo.com/page/modulepublisher');
    formData.append('api', 'http://i.huati.weibo.com/pcpage/operation/publisher/sendcontent?sign=super&page_id=100808a8fa6bef39d9fb73fcf8431471211e9e');
    formData.append('object_id','1022:100808a8fa6bef39d9fb73fcf8431471211e9e');
    formData.append('module','publish_913');
    formData.append('page_module_id','913');
    formData.append('longtext','1');
    formData.append('topic_id','1022:100808a8fa6bef39d9fb73fcf8431471211e9e');
    formData.append('text', content);
    formData.append('style_type', 1);
    formData.append('pub_source', 'page_1');
    formData.append('pdetail', '100808a8fa6bef39d9fb73fcf8431471211e9e');
    formData.append('sync_wb', 0);
    formData.append('pub_type', 'dialog');
    formData.append('_t', '0');

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://weibo.com/p/aj/proxy?ajwvr=6&__rnd=' + new Date().getTime(), true);

    xhr.onload = function(e) {
        if (this.status == 200 || this.status == 304) {
            var data = JSON.parse(this.responseText);
            if (data.code == "100000") {
                console.log(data);
            }
            else if (data.code == "100027") {
                console.log(data);
            }
            else {
                console.log(data);
            }
        }
    };
    xhr.send(formData);
}

function commentWeibo(content, mid, uid, forward) {
  var formData = new FormData();
  formData.append('act', 'post');
  formData.append('mid', mid);
  formData.append('uid', uid);
  formData.append('forward', 1);
  formData.append('isroot', '0');
  formData.append('content', content);
  formData.append('location', 'page_100808_super_index');
  formData.append('module', 'scommlist');
  formData.append('group_source', '');
  formData.append('pdetail', '100808a8fa6bef39d9fb73fcf8431471211e9e');
  formData.append('is_comment_base', '1');
  formData.append('_t', '0');

  var xhr = new XMLHttpRequest();

  xhr.open('POST', 'http://weibo.com/aj/v6/comment/add?ajwvr=6&__rnd=' + new Date().getTime(), true);

  xhr.onload = function(e) {
    if (this.status == 200 || this.status == 304) {
      var data = JSON.parse(this.responseText);
      if (data.code == "100000") {
        console.log(data);
      }
      else if (data.code == "100027") {
        console.log(data);
      }
      else {
        console.log(data);
      }
    }
  };
  xhr.send(formData);
}

function getRandomInt(max) {
    return Math.floor(Math.random() * (max + 1));
}


function getUrls() {
    var urls = [];

    for (var i=0; i<weiboUrls.length*2; i++){
         urls[i]='';
    }

    for (var i=0; i<weiboUrls.length; i++) {
        var text = weiboUrls[i];
        if (text && text.length > 0) {
            urls[i] = text.substring(text.lastIndexOf("/")+1, text.indexOf("?")>0 ? text.indexOf("?") : text.length);
        }
    }

    return urls;
}

// http://weibo.com/1689761914/follow?rightmod=1&wvr=6
function getMyUID(url) {
    return url.substring("http://weibo.com/".length, url.indexOf("/follow"));
}

var str62keys = [
    "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
    "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
    "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
];

function str62to10(str62) {
    var i10 = 0;
    for (var i = 0; i < str62.length; i++)
    {
        var n = str62.length - i - 1;
        var s = str62[i];
        i10 += this.str62keys.indexOf(s) * Math.pow(62, n);
    }
    return i10;
};


function int10to62(int10) {
    var s62 = '';
    var r = 0;
    while (int10 != 0)
    {
        r = int10 % 62;
        s62 = str62keys[r] + s62;
        int10 = Math.floor(int10 / 62);
    }
    return s62;
};


function url2mid(url) {
    var mid = '';
    if (url && url.length > 0) {
        for (var i = url.length - 4; i > -4; i = i - 4)
        {
            var offset1 = i < 0 ? 0 : i;
            var offset2 = i + 4;
            var str = url.substring(offset1, offset2);

            str = str62to10(str);
            if (offset1 > 0)
            {
                while (str.length < 7)
                {
                    str = '0' + str;
                }
            }

            mid = str + mid;
        }
    }

    return mid;
};

var runCount = 0;

function commentWeiboTimerExec() {
    var urls = getUrls();
    var uid = getMyUID(MY_WEIBO_URL);

    var urlIndex = getRandomInt(urls.length-1);
    var content = comment_contents[getRandomInt(comment_contents.length-1)];
    var mid = url2mid(urls[urlIndex]);

    console.log ("You dont get the lucky number this time, wait for next try in " + TIME_OUT + " seconds!");

    if (mid && mid.length > 0 && uid && uid.length > 0 && content && content.length > 0) {

        console.log ("for url=" +urls[urlIndex] + " => mid=" + mid);
        console.log ("content=" + content);
        var action = '';
        if (WHAT_I_WANT_TODO == 0) {
            action = 'comment and foward..';
            commentWeibo(content, mid, uid, 1);
        }
        else if (WHAT_I_WANT_TODO == 1) {
            action = 'comment only';
            commentWeibo(content, mid, uid, 0);
        }
        else if (WHAT_I_WANT_TODO == 2) {
            action = 'forward only..';
            forwardWeibo(mid, content);
        }
        else if (WHAT_I_WANT_TODO == 3) {
            action = 'post to group.';
            postWeibo(content);
        }
        else if (WHAT_I_WANT_TODO == 4) {
            action = 'forward weibo and add comment to source publisher and current poster.';
            forwardPrivateWeibo(mid, content);
        }
        else if (WHAT_I_WANT_TODO == 5) {
            action = 'add new post, comment and forward..';
            postWeibo(content);
            forwardWeibo(mid, content);
            commentWeibo(content, mid, uid, 1);
        }
        else {
            action = 'forward privately';
            forwardWeiboPrivate(mid, content);
        }
        console.log ("Going to " + action);
    }
};

var gap = TIME_OUT * 1000;
var interval = setInterval (commentWeiboTimerExec, gap);
