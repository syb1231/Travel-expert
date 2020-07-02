# Travel Expert
#여행지 추천어플

1. 국내와 해외를 선택하세요.
2. 원하는 항공권 가격대를 선택해주세요.
3. 유명한 관광지 또는 호캉스로 여행컨셉을 고르세요.
4. 최적화된 장소를 찾아드립니다.
5. 혼자 가기 쓸쓸하시다면, 동반자를 구하세요! 
6. 여행지에서의 파티를 구하시나요?
7. 근처에 가까운 파티 장소를 찾아드릴게요. 
8. Travel Expert에서 지상최고의 경험을 해보세요 ! 

# Application Manual
## Database Manual
 - create user `travel` with password `expert`
 - create schema `travel_expert`
 - grant all privileges of travel_expert to user travel
 - before run the mobile application, should run this code to upload board
 ```sql
 INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("쿠바 하바나",0, "#사진명소 #해외 #유적지

여행자들의 로망인 쿠바는 특유의 올드한 분위기와 살사댄스로 유명해요.특히 쿠바의 하바나는 쿠바의 유적지를 잘 볼 수있는 곳입니다", 0);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("보성 녹차밭",1, "#자연 #휴양

전남 보성에 있는 넓은 녹차밭으로 한국 차의 40%가 이곳에서 생산되며 많은 드라마와 영화의 배경이 되기도 합니다. 
끝없이 이어지는 차밭 풍경은 감탄을 자아냅니다. 
매년 5월에 녹차 축제가 열리고 겨울에는 들판이 많은 작은 전구로 장식되어 야경의 명소로 변신합니다.", 3);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("태국 아담섬",2, "#바다 #힐링 #해외

태국의 몰디브라고 불리는 리뻬섬 옆에 위치한 코 아당섬은 특이한 상호와 까라방하를 볼 수 있고 
아름다운 해변 모래사장이 멋진 곳입니다. 특히 해변에 보는 석양이 완벽하다고 알려져있어요.스쿠버다이빙과 스노클링을 즐기기에 정말 좋은 곳입니다.", 1);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("화성 방화수류정",3, "#유적지 #사진명소

경기도 수원에 있는 조선시대의 건축물입니다. 
이 정자는 정조대왕에 대한 많은 역사적 사실을 간직하고 있는 곳이며, 
지금은 연못, 버드나무, 꽃으로 둘러쌓여 있으며 한국 전통음악 연주와 시낭송회를 개최하고 있습니다.", 0);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("순천만",4, "#국내 #사진명소 #갈대

전남 순천에 위치하며 세계에서 가장 큰 습지 중 한 곳입니다. 
수많은 식물과 해양 생물의 서식처로 한국에서 가장 긴 5.4km에 달하는 갈대밭이 있습니다. 
순천만 갈대축제는 10월말 또는 11월초에 열리며 가을에 꼭 가봐야할 필수 관광지입니다.", 4);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("울릉도 해안도로",5, "#바다 #드라이브 #힐링

미스테리 섬이라는 별명이 붙은 울릉도는 주말 휴양지로 유명합니다. 
그 섬의 해안도로는 기이한 바위들, 해안 절벽, 그리고 수많은 폭포들로 눈을 뗄 수가 없습니다. 
울릉도 주변을 걷거나 자전거를 타고 갈 계획이면 경치를 감상하기 위해 충분한 시간을 할애해야합니다.", 1);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("경주 안압지",6, "#유적지 #사진명소 #야경

신라시대의 유물로 674년 만들어진 인공연못으로 보물 18호로 지정되어 있습니다. 
큰 연못 가운데 3개의 섬을 배치하고 그 섬에 건물이 세워졌으며 연꽃 조각들이 연못의 테두리를 이루고 있어요. 
일몰 후에 켜지는 조명은 신비한 모습을 자아냅니다.", 0);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("우포늪",7, "#산책 #힐링 #자연

경남 창녕의 우포늪은 우리나라 최대의 내륙습지로 여의도 면적의 3배에 이릅니다. 
1억 4천만년전에 형성된 이 습지에는 1,500여종의 식물과 동물이 서식하고 있으며 일부는 멸종 위기에 처해있습니다.
 방문객들은 자전거나 도보로 철새들이 낮게 날기로 유명한 이곳을 구경할 수 있습니다.", 5);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("성산일출봉",8, "#일몰  #사진명소 #섬

제주도 성산읍에 있는 182m의 산으로 커다란 사발 모양의 분화구가 특징이며 세계문화유산지로 수많은 희귀식물들이 자생하고 있습니다. 
성산일출봉에서의 일출은 한국에서 가장 아름다운 해돋이 중 하나로 꼽힙니다. 
봉우리까지는 도보로 약 30~40분 정도 걸립니다", 5);
INSERT INTO `board` (`title`,`image`,`body`,`pref`) VALUES ("홍콩 샤프섬",9, "#바다 #힐링 #해외

물이 맑고 깨끗해서 해수욕과 다이빙을 하러 가는 여행자들도이 많이 찾는 곳이고 낚시를 즐기는 사람들도 볼 수 있습니다. ", 1);

/* 글제목, 이미지파일 이름, 게시판 내용, 선호코드*/ 
 ```

## Server Manual
- in cmd, go to project directory and server folder. run `node app`
- when the server starts, there should be database(schema) and correct user, password
- when the server starts, it creates tables automatically

