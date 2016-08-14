# -*- coding: UTF-8 -*-
import tornado.httpserver
import tornado.ioloop
import tornado.options
import tornado.web
import libhouse365
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

from tornado.options import define, options
define("port", default=80, help="run on the given port", type=int)

class IndexHandler(tornado.web.RequestHandler):
      def get(self):
          libhouse365.get_vdcode()
          self.write('<body><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>')
          self.write('* 目前程序不支持商业房源，以及跃层房源。如有需求，请联系张正宇(v0.2)')
          self.write('<form action="http://104.223.53.33:82" method="post">')
          self.write('<p>需要分析的网址: <input type="text" name="src_url" size="64"/></p>')
          self.write('<p>用户: <input type="text" name="user" /></p>')
          self.write('<p>密码: <input type="text" name="pwd" /></p>')
          self.write('<img src="./vdcode_img.png" height="40" width="100" />')
          self.write('<p>验证码: <input type="text" name="vdcode" /></p>')
          self.write('<input type="submit" value="提交" /></form></body>')

      def post(self):
           ret=''
           src_url = self.get_argument('src_url', '0')
           ## Just for temp
           #_tmp=libhouse365.parse(src_url)
           self.write('<body><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>')
           #self.write(_tmp['address'].decode('gb2312').encode('utf8'))
           #self.write('<br>')
           #self.write(_tmp['remark'].decode('gb2312').encode('utf8'))
           user = self.get_argument('user', '0')
           pwd = self.get_argument('pwd', '0')
           ret=libhouse365.add_sell(src_url, user, pwd)
           if ret=='SUCCESS':
               self.write('已成功加入房源。请登录 '+user+' 在失效房源列表中查看、修改和激活。')
           elif ret=='WRONGTYPE':
               self.write('本程序暂时不能处理商业房产信息。')
           elif ret=='WRONGMAGIC':
               self.write('加入失败。请检查失效房源是否已满。')
           elif ret=='LOGINFAILED':
               self.write('登陆失败。请核对用户名和密码。')
           elif ret=='FAILED':
               self.write('程序出错。')
           self.write('如果程序出现问题，请及时联系张正宇。')
           self.write('</body>')

if __name__ == "__main__":
      tornado.options.parse_command_line()
      app = tornado.web.Application(handlers=[(r"/", IndexHandler)])
      http_server = tornado.httpserver.HTTPServer(app)
      http_server.listen(options.port)
      tornado.ioloop.IOLoop.instance().start()
