## SOCIAL MEDIA APPLICATION

## Yapilacak Listesi
* Dependency Management icin gerekli ayarlamalar yapilmali 👍
* Projede kullanacagimiz surumler onceki proje ile ayni olsun. 👍
* Icerisine simdilik sadece AuthService modulu ekleyecegiz (MikroService) 👍
* Auth entity olacak(String username, String password, Sting email, (Enum(Role) Admin,User), activationCode, (Enum(Status)Active,Deleted,Pending,Banned) ) 👍
*  Activation code : 5 haneli bir kod olusturup onu kullaniciya donmek istiyoruz. Rastgele bir UUID uretelim ve onun bas harflarinden 5 haneli bor kod cikaran bir metod yazalim 👍
   Ornegin: 23c8b54e-28d7-a5c8-b6f3-4b3870ed12b9 == 22ab4
* Validation islemleri icin ilgili bagimlilik eklenecek ve register olurken validation islemleri yapilacak 👍
* Register ve Login islemleri olacak 👍
* Login islemleri olacak. request -> email, password,response (id, username, email, role, status) 
* Hesabi aktif etmeliyiz. Aktif ederken hangi bigilere ihtiyacimiz var? id, activationCode bilgisi alip, gerekli kontrollerden sonra statusu Aktive duruma getirmeliyiz.
* Registerda validation kullanilmali 👍
* Exceptionlari uygun sekilde kullanalim 👍
* JWT token mekanizmasini da kullanmaliyiz.Login olan kullaniciya token donmeliyiz. 👍
```bash
docker run --name java14MongoDB -d -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=root  -p  27027:27017  mongo:jammy
```

## Yapilacak List-2
* JWT token mekanizmasini da kullanmaliyiz. 👍
 Login olan kullaniciya token donmeliyiz. Islemleri adim adim yapiniz. 👍

* Donecegimiz tokenda id ve role bilgisi olmali 👍
* Decode icin getIdFromToken metodunu yazalim 👍
* Decode icin getRoleFromToken metodunu yazalim 👍
* Secretkey ve issuer bilgileri environment variablestan gelsin 👍
* AuthControllerda bu 3 metodu denemek icin endpointler acalim 👍
* getToken endpointi token yaratacak 👍
* getIdFromToken endpointi token alacak id verecek 👍
* getRoleFromToken endpointi token alacak role string olarak verecek 👍
* AuthService'de login  metodu basarili ise token donecek. Token olustururken id ve role bilgisi alacak bir metodumuz olmali 👍
* Token uretme asamasinda hatalar icin uygun errorlar firlatalim. 👍
* UserService modulu ekleyelim.Gerekli packagelari ele alip gerekli degisklikleri yapiniz. 👍
* Entity acalim. UserProfile (id, authId, username, email, phone, address, about,status(enum)) 👍
* application.yml'de ayarlamalari yapalim. db olarak olarak docker icindeki mongoyu kullanalim. 👍
* Repository katmanini olusturalim  👍
* Service katmanini olusturalim 👍
* Controller katmanini olusturalim 👍
* Controller icinde save metodu olsun. UserSaveRequestDto ile kayit almaliyiz. (authId, username, email, phone,photo, address, about,)👍
* Donusum icin mapper yazin.(UserMapper)👍
* Authta kayit olan user icin userService de save metodu tetiklenmeli 👍
* OpenFeign kullanarak bu işlemi gerçekleştiriniz. 👍
* Araştırma: Eğer auth'a kaydedip user'a kaydedemezse authdaki kaydıda silmesini nasıl sağlayabiliriz?
* En sonunda projenin tamamını deneyelim. Çalıştığından emin olalım.
* 
````bash
docker run --name java14MongoDB -d -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=root  -p  27027:27017  mongo:jammy`
 `````

* -------------------------------Yeni Liste----------------- 14.05.2024-----------------------------
* [] NoSQL ve SQL farki?
* [] NoSQL db tipleri nelerdir ? ve altindaki databaseler nelerdir?
* [] CAP teoremi nedir?
* [] ACID nedir?
* [] BASE nedir?
* AuthService de register olduktan sonra activasyon işlemleri yapılırken bu işlem UserProfile tarafına yansıtılmalı. (openfeign) UserProfile tarafında endpointte pathVariable ile authid alabilirsiniz. UserService kapalı veya bir nedenden işlem gerçekleşemezse authservicede de bu işlem geriye alınmalı.
* Login olduktan sonra dönen token ile beraber UserProfile'ını güncelleyebilmeli.
* Ama burada sadece username değiştirememeli. Onun dışındaki alanlar değişmeli. Örnek olarak: email bilgisini değiştirirse auth'da da değişmeli.
* Update ederken eğer bir değer verilmiyorsa, eski değerler kalsın.
*  Authdaki silme UserProfile tarafına yansıtacak bir mekanizma geliştirin.


* -----------------------------------------------15.05.2024------------------------------------------
* @RestController ile @Controller farki ? 
* Spring Boot uygulamasi ayaga kalkarken neler oluyor?
* RestApi yazarken nelere dikkat edersiniz?
* Richardson Maturity Model nedir? Son asamasi nedir ?
* @SpringBootApplication anotasyonun sinifi spring packege'in icine atip calistirirsak calisir mi? serkanguner -> spring -> SpringBootApplication class
* Sisteme ConfigServer dahil ediniz.(Local ve Git(ayri bir repo ve key olusturarak)) 👍
* Butun microserviceler yml dosyalarini bu configserverdan cekecek sekilde ayarlanmalidir. 👍
* Git-config-server'daki bir ymldaki degisiklikten servisin haberdar olmasi icin neler yapabiliriz ? (arastir)
* 
* ------------------------------------------------------------16.05.2024------------------------------
* 1. PostService yaziniz. 
1. Bir user login olduktan sonra postservice araciligi ile post atabilmelidir. 👍
2. User kendi attigi postlari listeleyebilecegi bir endpoint olmali
3. User atilan tum postlari da gorebilecegi bir endpointi olmali.
4. User kendi attigi postu silmeli.
4. User kendi attigi postu guncelleyebilmeli.
5. Database siz belirleyin(Mongo veya PostgreSQL)
6. Optional -ApiGateway ekleyebilirsiniz.
   Bu kısımda openFeign kullanmaya devam edelim. Rabbitmq dahil etmiyoruz.
   Araştırma Soruları: Hangi veritabanını seçmek daha uygun olur, MongoDB mi yoksa PostgreSQL mi? Her birinin avantajları ve dezavantajları nelerdir?
   PostService'in nasıl tasarlanması gerekiyor ve hangi endpointler sunulmalıdır? Bu endpointlerin işlevleri ve kullanım senaryoları nelerdir?
   Kullanıcıların kendi attıkları postları güncelleyebilmesi ve silmesi için nasıl bir mekanizma sağlanmalıdır? Bu işlemlerin güvenliği ve doğrulaması nasıl yapılmalıdır?
   PostService için hangi veri modeli ve ilişkiler kullanılmalıdır? Kullanıcılar ve postlar arasındaki ilişki nasıl olmalıdır?
   PostService'in performansı ve ölçeklenebilirliği nasıl artırılabilir? Büyük ölçekli kullanım senaryolarını desteklemek için neler yapılabilir?
   Todo List 6 17.05.2024
   Kullanıcı post atarken tokenla atmalıdır. (Önce user login olacak, oradan aldığınız token bilgisini post atarken ekleyeceksiniz.)
   Diğer user'a özel tüm endpointlerde aynı şekilde token değerini de yollayıp, kontrol ederek işlem yaptırmalısınız.
   Postun createAt,updateAt,status gibi fieldları olmalıdır. BaseEntityden alınabilir.
   Database olarak Mongo kullanılmadıysa mongo kullanılmalıdır.
   RabbitMq'yi de projemize dahil edelim.
   AuthService'den register olunca UserService'e openFeignle attığımız isteği rabbit ile yapalım. Bunun için registerWithRabbit adlı yeni bir metod ekleyelim. Diğeri de dursun.
   DirectExchange kullanabilirsiniz.
   Exchange,Queue,RouteKey bu isimlerini alırken yml dosyasında kendi yazdığınız key-valuelardan çekin.
   Bİr tane de queue oluşturunuz.
   Yani auth producer olmalı, user da consumer olmalı.
   Dönüşüm için messageConverter eklenebilir RabbitTemplate için.
   Bu işlem dışında ayrıca rabbitMQ kullanarak hesabı aktive ediniz. Onun için de ayrı bir kuyruk oluşturmalısınız. Daha önce openFeign kullanmıştık. onu gizleyebilirsiniz. Bu işlemde de DirectExchange kullanabilirsiniz.
   Araştırma Soruları:
   JWT (JSON Web Token) nedir ve nasıl çalışır? Token oluşturma ve doğrulama süreçlerini açıklayınız.
   JWT ile OAuth2 arasındaki farklar nelerdir? Hangi durumlarda JWT, hangi durumlarda OAuth2 kullanmalısınız?
   RabbitMQ nedir ve temel bileşenleri nelerdir?
   RabbitTemplate nedir ve nasıl kullanılır?
   @RabbitListener anotasyonu ne yapmak için kullanılmaktadır?
   Mesaj dönüşümü (message conversion) nedir ve RabbitMQ'da nasıl uygulanır?
   MongoDB nedir ve SQL tabanlı veritabanlarından farkları nelerdir? MongoDB'nin temel bileşenlerini açıklayınız (koleksiyon, doküman vs..)
   https://www.cloudamqp.com/blog/part1-rabbitmq-for-beginners-what-is-rabbitmq.html