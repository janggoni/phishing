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
    
     
   1. ChatGPT (검색 시작) 

     역할: 사용자가 검색 키워드를 입력합니다.
     결과: 키워드가 Kafka로 전송되어 처리 대기 상태가 됩니다.

   2. Kafka (메시지 큐)

     역할: 검색 키워드를 큐잉하여 다음 단계로 전달합니다.
     데이터 흐름: ChatGPT → Kafka → Elasticsearch & MongoDB.

   3. Elasticsearch (검색 및 조회)

     역할: MariaDB에 저장된 피싱 메시지 및 유형을 빠르게 검색하고 결과를 반환합니다.
     데이터 흐름: MariaDB 데이터를 Elasticsearch에서 인덱싱 후 조회.

   4. MariaDB (데이터 저장)

     역할: 피싱 메시지 및 유형 데이터를 저장합니다.
     데이터 업데이트: Python Selenium을 통해 실시간으로 크롤링된 데이터가 추가됩니다.

   5. MongoDB (검색 로그 저장)

      역할: 사용자가 검색한 키워드와 그 빈도수를 저장하여, 검색 패턴을 분석할 수 있게 합니다. 
      데이터 흐름: Kafka → MongoDB.
   
   6. Python Selenium (크롤링)

      역할: 피싱 메시지와 유형 데이터를 웹에서 크롤링하여 MariaDB에 저장합니다.
      데이터 흐름: 웹 사이트 → Python Selenium → MariaDB.

   7. Kibana (모니터링 및 시각화)

      역할: MongoDB와 Elasticsearch에서 데이터를 시각화하여 검색 빈도, 인기 피싱 유형 등을 모니터링합니다.
      데이터 흐름: Elasticsearch & MongoDB → Kibana.

      
