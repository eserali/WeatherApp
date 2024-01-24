
<h3 align="left">WeatherApp</h3>

* Proje bir hava durumu uygulamasıdır.
* Project is an weather application.

### Technology Used

<a href="https://kotlinlang.org" target="_blank" rel="noreferrer"> <img src="https://www.vectorlogo.zone/logos/kotlinlang/kotlinlang-icon.svg" alt="kotlin" width="50" height="50"/>   </a> 
<a href="https://square.github.io/retrofit" target="_blank" rel="noreferrer"> <img src="https://github.com/eserali/WeatherApp/assets/157403007/1ed49e5a-259b-4d98-8f8b-55f92b20d316" alt="retrofit" width="50" height="50"/> </a>

### Project Explanation(TR)

* WeatherApp, bir hava durumu uygulamasıdır. 
* Uygulama Türkçe-İngilizce dil desteğine sahiptir.
* Activity ve Fragmentlar arası ekran geçişleri navigaasyon grafiği ile yapılmıştır.
* İlk olarak giriş sayfası sizleri karşılar devamında konum bilgileriniz için izin isteyen sayfa açılır.
* Bu sayfada konum izni vermeniz durumunda mevcut konumunuzdaki şehrin konum bilgileri yaklaşım konum alınarak ve de “Geo coder” kullanılarak yaklaşık konumunuzdaki
  şehir room kütüphanesi sayesinde telefonunun hafızasına kayıt edilir ve devamında yönlendirildiğiniz sayfada konumunuza ait hava durumu bilgileri size sunulur.
* Konum izni vermemeniz durumunda ise mevcut sayfada bulunan “Retrofit” kütüphanesi ile birlikte 2 farklı webservis(API) kullanılarak listelenmiş
  Exposed Dropdown menüler sayesinde 81 ile ait il, ilçe, mahalle bilgileri yer almaktadır.
* Hava durumu bilgilerini almak istediğiniz ili, ilçenizi ve mahallenizi şeçip “Save” butonuna tıkladıktan sonra yine room kütüphanesi sayesinde il
  bilgileriniz kayıt edilerek diğer sayfaya yönlendirilirsiniz.
* Bu sayfada önceki sayfada seçmiş olduğunuz il veya konumunuzdaki şehre göre saatlik ve haftalık hava durumlarının yer almasının yanı sıra,
  konumuzun hava durumuna göre değişen gif arka planı ve hava durumuna göre değişen gif iconlar ile tasarım yapılmıştır.

### Project Explanation(EN)

* WeatherApp is a weather forecast application.
* The application has Turkish-English language support.
* Screen transitions between Activity and Fragments are made with navigation graphics.
* Firstly, the login page welcomes you and then the page requesting permission for your location information opens.
* If you give location permission on this page, the location information of the city in your current location is saved in the phone's memory thanks
  to the room library in your approximate location by taking the approximate location and using the "Geo coder",
  and then the weather information of your location is presented to you on the page you are directed to.
* If you do not give location permission, the "Retrofit" library on the current page with the "Exposed Dropdown menus listed
  using 2 different webservis (API) with the "Retrofit" library on the current page, Turkey's 81 provinces of the province, district, neighbourhood
  information is located.
* After selecting the province, district and neighbourhood you want to get weather information and clicking the "Save" button,
  you will be directed to the next page by saving your province information thanks to the room library.
* On this page, in addition to the 24-hour and weekly weather conditions according to the province you selected on the previous page or the city in your location,
  our subject has been designed with a gif background that changes according to the weather and gif icons that change according to the weather.

### İmages
Language Turkish
| Welcome  | Use Location  | Chose City  | Chose Neighborhood  | Home  |
|---|---|---|---|---|
| ![Welcome](https://github.com/eserali/WeatherApp/assets/157403007/aba5e734-344e-4b07-9513-1273da9957bb) |![use location](https://github.com/eserali/WeatherApp/assets/157403007/00c61b19-9d31-4819-98b5-b9c3f41e4dbd) | ![chose city](https://github.com/eserali/WeatherApp/assets/157403007/9a02b4fb-16e1-4b12-b900-c22228323094)| ![chose neighborhood](https://github.com/eserali/WeatherApp/assets/157403007/d078956f-447a-4e73-9c30-ccef8e4beadc)|![home](https://github.com/eserali/WeatherApp/assets/157403007/08272bf3-7220-41e9-bbe7-7769ed683c6a)|

Language English
| Welcome  | Use Location  | Chose City  |  Home  |
|---|---|---|---|
|![welcome en](https://github.com/eserali/WeatherApp/assets/157403007/d7f0e9e2-4eb8-434d-87f1-4f4abd2b6ca2) |![use location en](https://github.com/eserali/WeatherApp/assets/157403007/e29c232a-cfaf-4a88-a0d6-330063d367df) |![chose city en](https://github.com/eserali/WeatherApp/assets/157403007/6fc390d0-2785-4983-8319-f533325dbfbf) | ![home](https://github.com/eserali/WeatherApp/assets/157403007/2f7afc69-4553-40ff-85fc-2e7781b73abc)|
