# -*- coding: UTF-8 -*-
import sys
from PyQt4.QtGui import *
from PyQt4.QtCore import *
from libhouse365 import *

reload(sys)
sys.setdefaultencoding('utf-8')

LOGIN_WARNING='login failed, check info'
PUBLISH_WARNING='publish failed'
FULL_WARNING='publish failed, not enough space?'
PUBLISH_OK='OK'

class House365_GUI(QWidget):
    def __init__(self):
        QWidget.__init__(self)
        self.setWindowTitle('House365')
        self.s, self.cookies = get_cookies()
        get_vdcode(self.s, self.cookies)
        self.lineedit_user=QLineEdit('BK51190')
        self.lineedit_pwd=QLineEdit('111111')
        img=QImage()
        img.load('./vdcode.img')
        self.label=QLabel()
        self.label.setPixmap(QPixmap.fromImage(img))
        self.lineedit_vdcode=QLineEdit()
        self.lineedit_url=QLineEdit()
        self.btn_ok=QPushButton('OK')
        self.connect(self.btn_ok, SIGNAL('clicked()'), self.onBtn)
        layout=QGridLayout() 
        layout.addWidget(self.lineedit_user,1,0)
        layout.addWidget(self.lineedit_pwd,2,0)
        layout.addWidget(self.label, 3, 0) 
        layout.addWidget(self.lineedit_vdcode,4,0)
        layout.addWidget(self.lineedit_url,5,0)
        layout.addWidget(self.btn_ok)
        self.setLayout(layout)

    def onBtn(self):
        user = cc(unicode(self.lineedit_user.text().toUtf8(), 'utf-8', 'ignore'))
        pwd = cc(unicode(self.lineedit_pwd.text().toUtf8(), 'utf-8', 'ignore'))
        vdcode = cc(unicode(self.lineedit_vdcode.text().toUtf8(), 'utf-8', 'ignore'))
        url = cc(unicode(self.lineedit_url.text().toUtf8(), 'utf-8', 'ignore'))
        if ( 'LOGINFAILED' == login(self.s, self.cookies, user, pwd, vdcode)):
            QMessageBox.warning( self, "ERROR", LOGIN_WARNING, QMessageBox.Yes)
        else:
            ret = add_sell(self.s, self.cookies, url)
            if ('FAILED' == ret):
                QMessageBox.warning( self, "ERROR", PUBLISH_WARNING, QMessageBox.Yes)
            elif ('FULLFAILED' == ret):
                QMessageBox.warning( self, "ERROR", FULL_WARNING, QMessageBox.Yes)
            elif ('SUCCESS' == ret):
                QMessageBox.warning( self, "OK", PUBLISH_OK, QMessageBox.Yes)
        self.close()
        #self.s, self.cookies = get_cookies()
        #get_vdcode(self.s, self.cookies)
        #img=QImage()
        #img.load('./vdcode.img')
        #self.label.setPixmap(QPixmap.fromImage(img))


if __name__ == '__main__':

      app = QApplication(sys.argv)
      gui = House365_GUI()
      gui.show()
      sys.exit(app.exec_())
