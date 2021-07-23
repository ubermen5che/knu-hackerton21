import requests
from bs4 import BeautifulSoup
from html_table_parser import parser_functions as parser
import json
from datetime import datetime

url = "http://covid19.daegu.go.kr/00937400.html"
res = requests.get(url)
res.raise_for_status()
res.encoding = 'utf-8'

soup = BeautifulSoup(res.text, "lxml")
data_list = []
dic_list = []
data_rows = soup.find("table", attrs={"id":"bbsList"}).find("tbody").find_all("tr")
for row in data_rows:
    columns = row.find_all("td")
    if len(columns) >= 50 or len(columns) <= 1:
        continue
    data = [columns.get_text(strip="true") for columns in columns]
    data_list.append(data)

print(data_list[1])

for data in data_list:
    data[4].replace(" ", "")
    tmp_dic = {}
    tmp_dic['id'] = None
    tmp_dic['u_name'] = None
    tmp_dic['college'] = None
    tmp_dic['type'] = data[2]
    tmp_dic['address'] = data[0] + ' ' + data[4]
    tmp_dic['exposure_sdate'] = None
    tmp_dic['exposure_edate'] = None
    tmp_dic['latitude'] = None
    tmp_dic['longitude'] = None
    tmp_dic['crawled_date'] = None #서버에서 처리
    tmp_dic['content'] = None
    tmp_dic['disinfection'] = data[6]
    tmp_dic['region'] = data[0] + ' ' + data[1]

    #tmp_dic['latitude'] = latitude
    #tmp_dic['longitude'] = longitude
    dic_list.append(tmp_dic)

print(dic_list[1])

server_url = "http://3.36.121.93:8084/data"
headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
response = requests.post(server_url, json=dic_list, headers=headers)
print(response)