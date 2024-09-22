<!-- 포스팅 할 내용

org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'entityManagerFactory' defined in class path resource [org/springframework/boot/autoconfigure/orm/jpa/HibernateJpaConfiguration.class]: No identifier specified for entity: com.phishing.fast.api.search.vo.PhishingRecord -->

-- 미니 프로젝트 개요 --

 한국의 피싱 메시지 검색 api로서 쳇gpt plugin을 통해 피싱 메시지의 여부를 확인할수 있는 plugin

사용 스펙 : 
  - 서버 : springboot
  - db : mariaDb , mongoDb
  - 검색관련 : elastic search, kafka
  - 서버 배포 예정 :
     api 서버 : vercerl,
     mariadb서버 : PlanetScale,
     mongodb 서버 :  MongoDB Atlas,
     elastic search : Elasticsearch Service by Elastic (Elastic Cloud)이거나 Bonsai,
     
  - 그외 : kibana, momgoose, python

  - 아키텍처 예상 구조 -
    
     
    1. chatgpt를 통하여 키워드를 검색하면 대기하고 있던 kafka를 통하여 elastricsearch를 통해 mariadb내에 있는 피싱 메시지 및 유형을 확인
    2. 동시에 검색 keyword와 빈도수를 저장하기 위해 mongodb에 저장
    3. mariadb내의 피싱 메시지 데이터의 경우 파이썬 셀레니움 모듈 사용하여 피싱 메시지 데이터 크롤링
    4. kibana를 통해 검색 빈도 수 및 모니터링을 통해 사람들이 많이 검색한 피싱 유형 열람
