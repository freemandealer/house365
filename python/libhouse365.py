# -*- coding: UTF-8 -*-
from requests import session 
from bs4 import BeautifulSoup
#import pytesseract
#from PIL import Image
import logging
import time
import re
import sys
reload(sys)
sys.setdefaultencoding('utf-8')

LOG_PATH='./house365.log'
VDCODE_PATH='./vdcode.img'
TEST_URL='http://nj.sell.house365.com/s_108012594.html'

logging.basicConfig(
  filename=LOG_PATH,
  level=logging.DEBUG,
  format='%(asctime)s  %(filename)s[line:%(lineno)d] %(levelname)s %(message)s',
  datefmt='%a, %d %b %Y %H:%M:%S',
)

# convert encoding from utf8 to gb2312
def cc(str):
  return str.decode('utf8').encode('gb2312')

def parse(url):
  ps=session()
  headers={'User-Agent':'freeman'}
  pr=ps.get(url, headers=headers)
  bs = BeautifulSoup(pr.text, "html.parser")
  # bs = BeautifulSoup(open('test1.htm'), "html.parser")
  _list=eval(re.findall(r'\{\S*\}', bs.find(name="a", attrs={'class':"btn_span fl"}).get("onclick"))[0])
  # infotype
  infotype=_list['infotype']
  if infotype != '1':
    logging.error("wrong info type")
    return 'WRONGTYPE'
  logging.info(infotype)
  # blockshowname, district, street
  blockshowname=\
  bs.find(name="dt", text='小区：').nextSibling.nextSibling.find_all('a')[0].text
  district=bs.find(name="dt", text='小区：').nextSibling.next.find_all('a')[1].text
  street=bs.find(name="dt", text='小区：').nextSibling.next.find_all('a')[2].text
  logging.info(blockshowname)
  logging.info(district)
  logging.info(street)
  # block_id
  _tmp=re.findall(r'community_id[\d]+', bs.find(name="dt", text='小区：').nextSibling.next.find_all('a')[0].get('href'))[0]
  block_id=_tmp.split("id")[1]
  logging.info(block_id)
  # streetid[optional]
  streetid=''
  # blockaddress=''
  blockaddress=bs.find(name="p", attrs={'class':'info zbinfo'}).text.split("地址：")[1]
  logging.info(blockaddress)
  # housekindname[optional]
  housekindname=''
  # price
  price=re.findall(r'^[\d]+', bs.find(name="dt", text='售价：').nextSibling.next.span.text)[0]
  logging.info(price)
  # priceterm_name, priceterm
  priceterm_name=bs.find(name="dt", text='税费方式：').nextSibling.next.text.strip()
  if priceterm_name=='双方各自付税':
    priceterm='1'
  else:
    priceterm='2'
  logging.info(priceterm_name)
  logging.info(priceterm)
  # pricetypename, pricetype, taxonlytypename, taxonlytype
  try:
    pricetypename=re.findall('[\S]+', bs.find(name="dt", text='税费：').nextSibling.next.text)[0]
  except Exception as e:
    pricetypename=''
  if pricetypename=='无增值税':
    pricetype='1'
  else:
    pricetype='2'
  try:
    taxonlytypename=re.findall('[\S]+', bs.find(name="dt", text='税费：').nextSibling.next.text)[1]
  except Exception as e:
    taxonlytypename=''
  if taxonlytypename=='无个税':
    taxonlytype='1'
  else:
    taxonlytype='2'
  logging.info(pricetypename)
  logging.info(pricetype)
  logging.info(taxonlytypename)
  logging.info(taxonlytype)
  # buildarea
  buildarea=re.findall(r'^[\d]+', bs.find(name="dt", text='面积：').next.next.next.text)[0]
  logging.info(buildarea)
  # room, hall, toilet
  _tmp= bs.find(name="dt", text='户型：').next.next.next.text
  room=_tmp.split("室")[0]
  hall=_tmp.split("室")[1].split("厅")[0]
  toilet=_tmp.split("室")[1].split("厅")[1].split("卫")[0]
  logging.info(room)
  logging.info(hall)
  logging.info(toilet)
  # won't consider two floors
  # lctype
  lctype='1'
  # floor, totalfloor
  [floor, totalfloor]=bs.find(name="dt", text='楼层：').next.next.next.text.split('/')
  logging.info(floor)
  logging.info(totalfloor)
  # subfloor, floor2, totalfloor2
  subfloor=''
  floor2=''
  totalfloor2=''
  # fitment
  fitment=bs.find(name="dt", text='装修：').next.next.next.text
  logging.info(fitment)
  # forward
  # XXX: will this cause any problem?
  forward=bs.find(name="dt", text='朝向：').next.next.next.text
  if forward == '南北通透':
    forward='南北'
  logging.info(forward)
  # mright
  mright=bs.find(name="dt", text='权属：').next.next.next.text
  logging.info(mright)
  # buildyear
  buildyear=_list['buildyear']
  # ownid[optional]
  ownid=''
  # address
  address=bs.find(name="h2", attrs={'class':'title clearfix'}).next.text
  logging.info(address)
  # remark
  remark=bs.find(name="div", attrs={'class':'info','id':'detail'}).text.replace(u'\xa0','').replace(u'\u30fb','')
  logging.info(remark)
  # feature
  feature=''
  # _id[optional]
  _id=''
  _payload={'infotype':infotype,\
      'blockshowname':cc(blockshowname),\
      'block_id':cc(block_id),\
      'district':cc(district),\
      'street':cc(street),\
      'streetid':streetid,\
      'blockaddress':cc(blockaddress),\
      'housekindname':housekindname,\
      'price':cc(price),\
      'priceterm_name':cc(priceterm_name),\
      'priceterm':priceterm,\
      'pricetypename':cc(pricetypename),\
      'pricetype':pricetype,\
      'taxonlytypename':cc(taxonlytypename),\
      'taxonlytype':taxonlytype,\
      'buildarea':cc(buildarea),\
      'room':cc(room),\
      'hall':cc(hall),\
      'toilet':cc(toilet),\
      'lctype':lctype,\
      'floor':cc(floor),\
      'totalfloor':cc(totalfloor),\
      'subfloor':subfloor,\
      'floor2':floor2,\
      'totalfloor2':totalfloor2,\
      'fitment':cc(fitment),\
      'forward':cc(forward),\
      'mright':cc(mright),\
      'buildyear':buildyear,\
      'ownid':ownid,\
      'address':cc(address),\
      'remark':cc(remark),\
      'feature':feature,\
      'id':_id}
  return _payload

def get_magic(cookies):
  _url='http://nj.zsb.house365.com/sell/add/'
  logging.info("get magic: " + _url)
  ms=session()
  headers={'User-Agent':'Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1'}
  mr=ms.get(_url, headers=headers,cookies=cookies)
  bs = BeautifulSoup(mr.text, "html.parser")
  _magic_name=bs.find(name="div", attrs={'class':'btnFBbox'}).nextSibling.nextSibling.get('name')
  if _magic_name == None:
    logging.error('failed to get magic')
    return 'WRONGMAGIC'
  _magic_val=bs.find(name="div", attrs={'class':'btnFBbox'}).nextSibling.nextSibling.get('value')
  return _magic_name, _magic_val

headers={'User-Agent':'Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1'}

def get_cookies():
  logging.info('get cookies')
  s=session()
  r=s.get('http://nj.zsb.house365.com/login/', headers=headers)
  return s, r.cookies

def get_vdcode(s, cookies):
  logging.info('get validating code')
  r=s.get('http://nj.zsb.house365.com/valid/', headers=headers, cookies=cookies)
  with open(VDCODE_PATH,"wb") as _png:
       _png.write(r.content)

def login(ss, cookies, user, pwd, vdcode):
  logging.info('login')
  s = session()
  headers={'User-Agent':'Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1'}
  payload={'action': 'check_login', 'username': user, 'password': pwd, 'yzm': vdcode} 
  r=s.post('http://nj.zsb.house365.com/login/',data=payload,cookies=cookies,headers=headers)
  if r.text != 'check_ok':
    logging.error("login failed")
    return 'LOGINFAILED'
  logging.info('login success')


def add_sell(s, cookies, src_url):
  # get the magic key:val
  logging.info('get magic')
  try:
      magic_name, magic_val = get_magic(cookies)
  except Exception as e:
      return 'FULLFAILED'
  try:
      # parse source page
      logging.info('parsing ' + src_url)
      payload=parse(src_url)
      payload[cc(magic_name)] = cc(magic_val)

      r=s.post('http://nj.zsb.house365.com/sell/insertdisable/', cookies=cookies, data=payload)
      check_bs = BeautifulSoup(r.text, "html.parser")
      check_result=check_bs.find(name="img", attrs={'src':'http://esf.s.house365.com/zsb/images/v1.0/okBg.png'})
  except Exception as e:
      return 'FAILED'
  if check_result == None:
    logging.error('failed to publish')
    return 'FAILED'
  logging.info('SUCCESS')
  return 'SUCCESS'

# example and test
if __name__ == '__main__':
  s,cookies=get_cookies()
  get_vdcode(s,cookies)
  user=raw_input('Username:')
  pwd=raw_input('Password:')
  vdcode=raw_input('VDCode:')
  login(s,cookies,user,pwd,vdcode)
  add_sell(s,cookies,TEST_URL)
