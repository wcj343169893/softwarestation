<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE wml PUBLIC "-//WAPFORUM//DTD WML 1.3//EN" "http://www.wapforum.org/DTD/wml13.dtd">
<%@page contentType="text/vnd.wap.wml;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${loginusers}" var="users"/>

<%@page import="cn.yulezu.utils.RequestUtil"%>
<%@page import="cn.yulezu.utils.SessionUtils"%><wml>
<card title="${users.nickname }的个人地盘">
<p>
${sid }<br />
${loginusers }<br />
${users.nickname }(ID:${users.id })形象<br />
<img src="../../images/1275355754838.gif" /><br />
<a href="myfriends.html">好友(2)</a>|<a href="mybox.html">信箱</a>|<a href="mydata.html">资料(10%)</a>|<a href="spaceManager.html">管理</a><br />
等级:<a href="mygrade.html">1级</a>,头衔:<a href="grade.html">上将</a>,<a href="meilihelp.html">魅力</a>:<img src="../../images/image_20100807143618.gif" width="16" height="16" /><br />
我的<a href="wealth.html">钱包,</a><a href="helpintegral.html">积分</a>:2,<a href="mygiftbox.html">礼品(3)</a><br />
职务:<a href="../groupdetails.html">爱的永恒[群主]</a><br />
<a href="xuzhang.html">勋章</a>:<img src="../../images/41844442.gif" /><img src="../../images/g1.gif" />&nbsp;<br />
<br />
<img src="../../images/1275355754838.gif" />糗事说出来大家乐呵乐呵<br />
<input name="text2" type="text"/>
<select>
  <option>表情</option>
  <option>/gx高兴</option>
  <option>/wx微笑</option>
</select>
<a href="mymood.html">发表</a><br />
我要<a href="writediary.html">写日志</a>.<a href="photostatement.html">传照片</a>.<a href="../releasesmallradio.html">发广播</a><br />
<br />
好友的 <a href="topdiary.html">日志</a> <a href="friendphoto.html">相册</a> <a href="tafriends.html"></a> <a href="friendgroup.html">群组</a> <a href="friendtoupiao.html">投票</a><br />
我的应用 <a href="../applicationmanagement.html">管理</a><br />
<a href="../forum.html" class="STYLE6">论坛</a> <a href="mydiary.html">日志</a> <a href="myphoto.html">相册</a> <a href="mymood.html">心情</a> <a href="../friends.html">交友</a> <a href="../chat.html">聊天</a> <a href="../mygroup.html">群组</a> <a href="../myapplications.html">+更多</a><br />
<br />
『谁来看过我』<a href="myvisitors.html">+更多访客</a><br />
<a href="otherzone.html">帅得婀娜多姿</a>(2秒前)<img src="../../images/online.gif" /><br />
<a href="otherzone.html">帅得婀娜多姿</a>(2秒前)<img src="../../images/online.gif" /><br />
<br />
『我关注的友友』<a href="moreattention.html">+更多关注</a><br />
2秒前<a href="otherzone.html">▲☆▲烟花╰☆╮</a>发表了帖子<a href="../forumdetails.html">友值多少钱？</a><br />
2秒前<a href="otherzone.html">▲☆▲烟花╰☆╮</a>发表了帖子<a href="../forumdetails.html">友值多少钱？</a><br />
<br />
『友友动态』<a href="myfriendsdynamic.html">+更多动态</a><strong><br />
</strong>[说什么]<a href="otherzone.html">帅得婀娜多姿</a>(2秒前)在你地盘发表了留言! <br />
[做什么]<a href="otherzone.html">帅得婀娜多姿</a>(4秒前)向<a href="otherzone.html">Kiss you</a>发出打招呼动作!<br />
[玩什么]
  2秒前猪猪玩了疯狂<a href="#">扎金花</a><br />
  <br />
  <img src="../../images/diary.gif" width="123" height="30" /><br />
『留言板』<a href="mymessage.html">+更多留言</a><br />
<a href="otherzone.html">帅得婀娜多姿</a>:留言板是什么?[<a href="messagemanagement.html">管理</a>]<br />
『我的空间状态』<br />
<a href="zonepaihang.html">人气:498/今天:2</a><br />
<a href="../../help/zonehelp/todaypaih.html">今日在线:1小时30分钟
</a><br />
<a href="updqianm.html">签名</a>:喜欢一个人的感觉<br />
最后访问:2010-07-30 14:15<br />
在线人数：<%=SessionUtils.getOnlineSize() %><br />
<img src="../../images/small.gif" /><br />
版本切换:<a href="../../coloryulezu/space/zoneindex.html">彩版</a>|简版<br />
  <a href="../../3gyulezuindex.html">首页</a>|<a href="index.html">空间</a>|<a href="../applicationmanagement.html">应用</a>|<a href="../../navigation.html">导航</a><br />
  <a href="../../systemnotice.html">公告</a>|<a href="../../customerservice/customerservice.html">帮助</a>|<a href="../../opinionssuggestions/postlist.html">反馈</a>|<a href="<%=response.encodeURL("/sns/logout") %>">退出</a><br />
  <img src="../../images/time.jpg" width="12" height="13" />2010-05-12 10:10<br />
  <a href="../../3gyulezuindex.html">3g娱乐族</a>:3g.yuLezu.cn<br />
</p>
</card>
</wml>