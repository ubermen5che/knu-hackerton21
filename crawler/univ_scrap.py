import requests
from bs4 import BeautifulSoup
import json
import copy

#regex = re.compile()
knu_place = ['사회과학대학', '누리관 식당', '공대8호관', '학내 헬스장', '중앙도서관', '경상대학', '인문학진흥관'
,'학내 수영장', '제2체육관', '제1체육관', '입학과', 'IT융합산업빌딩', '종합정보센터', '글로벌플라자', '농대9호관']
#chrome_options = Options()
#chrome_options.use_chromium = True
#chrome_options.add_experimental_option("debuggerAddress", "127.0.0.1:9222")
#driver = webdriver.Chrome('/Users/yong/github/go_practice/chromedriver', options=chrome_options)
#driver.implicitly_wait(1)

#driver.get("http://knu.ac.kr/wbbs/wbbs/bbs/btin/list.action?bbs_cde=34&menu_idx=224")

crawled_data = {
    'post_id' : None,
    'univ_name': '경북대학교',
    'subject' : None,
    'address' : None,
    'college' : None,
    'cont' : None,
    'disinfection': None,
    'confirmedDate' : None,
    'crawled_date' : None
}

domain = "http://knu.ac.kr"
url = "http://knu.ac.kr/wbbs/wbbs/bbs/btin/list.action?bbs_cde=34&menu_idx=224"
req = requests.get(url)
raw = req.text

html = BeautifulSoup(raw, "html.parser")
#board_list = html.select('div.board_list > table > tbody > td.subject > a.href')
#print(board_list[0])
num_of_post = html.select('div.board_list td.num')
subject = html.select('div.board_list td.subject')

links_with_text = []

for b in subject:
    a_href = b.find('a', href=True)
    if(a_href.text):
        links_with_text.append(domain+a_href['href'])

crawl_data_list = []
post_id_list = []
subject_list= []
address_list = []
confirmed_place = []

for i in range(len(num_of_post)):
    sub_text = subject[i].text
    sub_text = ''.join(sub_text.split())
    subject_list.append(sub_text)
    if('코로나19확진자발생현황' in sub_text):
        post_id_list.append(num_of_post[i].text)
        sub = sub_text
        req = requests.get(links_with_text[i])
        raw = req.text
        html = BeautifulSoup(raw, "html.parser")
        cont = html.select('div.board_cont')
        cont = str(cont)

        cnt = cont.count('ㅇ 학교출입')
        start_pos = 0
        for j in range(cnt):
            idx_1 = cont.find('ㅇ 학교출입', start_pos)
            idx_2 = cont.find('ㅇ 조치사항', start_pos)
            offset = idx_2 - idx_1
            start_pos = idx_2+1
            sub_board = cont[idx_1: idx_1+offset]
            for key_word in knu_place:
                if(key_word in sub_board):
                    confirmed_place.append(key_word)
                    
        confirmed_place = set(confirmed_place)
        #print('post_id: ' + crawled_data['post_id'])
        confirmed_place = list(confirmed_place)
        print(confirmed_place)

        for c in range(len(confirmed_place)):
            tmp = copy.deepcopy(crawled_data)
            tmp['post_id'] = num_of_post[i].text
            tmp['subject'] = sub
            tmp['address'] = crawled_data['univ_name'] + ' ' + confirmed_place[c]
            crawl_data_list.append(tmp)
        

for i in crawl_data_list:
    print(i['address'])

server_url = "http://3.36.121.93:8084/univ/data"
headers = {'Content-type': 'application/json', 'Accept': 'text/plain'}
response = requests.post(server_url, json=crawl_data_list, headers=headers)
print(response)


                
'''
게시글 내용 url 접속 코드
links_with_text = []

for b in board_list:
    a_href = b.find('a', href=True)
    if(a_href.text):
        links_with_text.append(a_href['href'])

print(links_with_text)

for i in range(0, len(links_with_text)):
    links_with_text[i] = domain + links_with_text[i]

req2 = requests.get(links_with_text[0])
raw2 = req2.text
print(raw2)
'''

'''
links_with_text = []
for a in html.find_all('div.board_list td.subject', href=True): 
    if a.text: 
        links_with_text.append(a['href'])

print(links_with_text)
'''

'''
for sb in subject:  
    sb = sb.select_one('a').text
    sb = ''.join(sb.split())
    print(sb)
'''

'''
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
'''