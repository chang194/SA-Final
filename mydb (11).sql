-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2023-12-27 14:38:41
-- 伺服器版本： 10.4.32-MariaDB
-- PHP 版本： 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `mydb`
--

-- --------------------------------------------------------

--
-- 資料表結構 `tbllinking_attractionjourney`
--

CREATE TABLE `tbllinking_attractionjourney` (
  `attraction_id` int(11) NOT NULL,
  `journey_id` int(11) NOT NULL,
  `attraction_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbllinking_attractionjourney`
--

INSERT INTO `tbllinking_attractionjourney` (`attraction_id`, `journey_id`, `attraction_time`) VALUES
(1, 1, '2023-12-23 11:11:10'),
(2, 1, '2023-12-23 11:11:10'),
(3, 1, '2023-12-23 11:11:10'),
(4, 1, '2023-12-23 11:11:10');

-- --------------------------------------------------------

--
-- 資料表結構 `tbllinking_communityattraction`
--

CREATE TABLE `tbllinking_communityattraction` (
  `community_id` int(11) NOT NULL,
  `attraction_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbllinking_communityattraction`
--

INSERT INTO `tbllinking_communityattraction` (`community_id`, `attraction_id`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 4);

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_attraction`
--

CREATE TABLE `tbl_attraction` (
  `attraction_id` int(11) NOT NULL,
  `attraction_name` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `bussiness_hours` varchar(100) NOT NULL,
  `website` varchar(200) DEFAULT NULL,
  `rating` varchar(500) DEFAULT NULL,
  `intro` text DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `googleMap` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_attraction`
--

INSERT INTO `tbl_attraction` (`attraction_id`, `attraction_name`, `address`, `bussiness_hours`, `website`, `rating`, `intro`, `image`, `googleMap`) VALUES
(1, '中壢樹屋', '320桃園市中壢區龍川二街134號', '24hr', '', '100', '大樹守衛等你來挑戰', 'https://truth.bahamut.com.tw/s01/202210/166a79d676195a817c283432ad545e37.JPG', 'https://www.google.com/maps/place/%E4%B8%AD%E5%A3%A2%E6%A8%B9%E5%B1%8B%2F%E9%9C%B8%E4%B8%BB%2F%E7%8E%8B%E5%B8%8C%E9%8A%98%2F%E4%B8%AD%E5%A3%A2%E8%80%B6%E8%AA%95%E5%9F%8E%2F%E4%B8%AD%E5%A3%A2%E9%BB%83%E9%87%91%E6%A8%B9%2F%E5%94%90%E8%A3%9D%E5%A4%A7%E6%A8%B9%E5%AE%88%E8%A1%9B%2FNA%E8%96%A9%E5%8A%9B%E5%85%8B%E5%9C%B0%E4%B8%8B%E5%A4%A7%E5%A2%B3%E5%A2%93/@24.9449132,120.9383935,10z/data=!4m10!1m2!2m1!1z546L5biM6YqY5qi55bGL!3m6!1s0x346823e7ba5cf187:0xb5e4baea5c943a1a!8m2!3d24.9374333!4d121.2482643!15sCg_njovluIzpipjmqLnlsYtaEyIR546L5biM6YqYIOaouSDlsYuSAQ9ob3VzaW5nX3NvY2lldHmaASRDaGREU1VoTk1HOW5TMFZKUTBGblNVTldkV05RUlY5M1JSQULgAQA!16s%2Fg%2F11vj5w5mb2?entry=ttu'),
(2, '國立中央大公園', '中壢區中大路300號', '8:00~17:00', 'https://www.ncu.edu.tw/tw/', '假日讓小孩玩的好地方', '小孩超吵，K中外面都是小孩的聲音', 'https://www.ncu.edu.tw/upload/news/20230508085503_0.jpg', 'https://www.google.com/maps/place/%E5%9C%8B%E7%AB%8B%E4%B8%AD%E5%A4%AE%E5%A4%A7%E5%AD%B8/@24.9681606,121.1927239,17z/data=!3m1!4b1!4m6!3m5!1s0x346823c1ec904dcb:0xcdc129d4455ce456!8m2!3d24.9681558!4d121.1952988!16zL20vMDJ2dmx4?entry=ttu'),
(3, '羊世界牧場', '320桃園市中壢區三芝路169號', '9:00~17:00', 'https://goat-world.com.tw/', '還行', '這裡超冷我的老天', 'https://travel.tycg.gov.tw/content/images/attractions/77165/1024x768_attractions-image-wozpinyxf0wa2irosze39q.jpg', 'https://www.google.com/maps/place/%E7%BE%8A%E4%B8%96%E7%95%8C%E7%89%A7%E5%A0%B4/@24.982002,121.2077318,17z/data=!3m1!4b1!4m6!3m5!1s0x346823d6024f07ed:0x4ef9269cfd582828!8m2!3d24.9819972!4d121.2103067!16s%2Fg%2F1v7pw64j?entry=ttu'),
(4, 'Costco', ' 320桃園市中壢區民族路六段508號', '10:00~21:30', 'https://www.costco.com.tw/Frozen-Foods/c/908?q=:relevance:Publisher:%E6%A1%83%E5%9C%92%E5%B8%82%E4%B8%AD%E5%A3%A2%E5%8D%80%E5%8D%97%E5%9C%92%E8%B7%AF2-16%E8%99%9F&page=1', '幾霸婚', '好市多還要啥解釋', 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMUExYUFBMWFhYYFyIbFhgZFhkbGxkcIhwZGxsZHxkZHioiGSEnHB4eIzMjKistMDAwHyI2OzYyOiovMC0BCwsLDw4PGxERHC8oIicxLS8vMS8vLy8xLy8vLy8vMDEyMTEvMS84MTAvL', 'https://www.google.com/maps/place/%E5%A5%BD%E5%B8%82%E5%A4%9A+%E6%A1%83%E5%9C%92%E4%B8%AD%E5%A3%A2%E5%BA%97/@24.9640146,121.117483,14z/data=!4m10!1m2!2m1!1scostco!3m6!1s0x3468246f6f043f6d:0xab5a9b03cdb419cc!8m2!3d24.9640146!4d121.1555918!15sCgZjb3N0Y28iA4gBAVoIIgZjb3N0Y2-SAQ93YXJlaG91c2Vfc3RvcmXgAQA!16s%2Fg%2F11dyzcs6dq?entry=ttu'),
(5, '自來水博物館', '100台北市中正區思源街1號', '9:09-17:00', 'https://waterpark.water.gov.taipei/', '4.3', '「自來水博物館」堪稱台北最物超所值的親子玩水景點！門票百元有找、12歲以下半價、6歲以下免費，在此除了可以學習到抽水機組設備等相關知識以外，最令孩子們期待的就是夏天開放的玩水派對啦！有滑水道及花式灑水設施，且泳池水深僅到大人膝蓋，一旁還有救生員待命，安全感大幅提升呀，推薦此等夏天好去處給親子爸媽們唷！', 'static/img/自來水博物館', 'https://www.google.com/maps/place/%E8%87%AA%E4%BE%86%E6%B0%B4%E5%8D%9A%E7%89%A9%E9%A4%A8/@25.0129061,121.527597,17z/data=!4m10!1m2!2m1!1z6Ieq5L6G5rC05Y2a54mp6aSo!3m6!1s0x3442a98cb364546b:0x14f1dd58b3c3073e!8m2!3d25.0125283!4d121.5300223!15sChLoh6rkvobmsLTljZrnianppKiSAQZtdXNldW3gAQA!16s%2Fm%2F0w1f0nl?entry=ttu'),
(6, '寶藏巖國際藝術村', '100台北市中正區汀州路三段230巷14弄2號', '24H', 'https://www.facebook.com/artthav?mibextid=LQQJ4d', '4.3', '「寶藏巖國際藝術村」一個隱匿在公館附近，於民國60-70年依山而建的眷村小社區，從2010年開始有設計師進駐在此開設工作室，也使這裡添增不少彩繪牆面、裝置藝術及文青擺設，而這個台北秘境僅需從捷運公館站1號出口步行約10分鐘即可抵達，很適合親子一起輕鬆爬個小山、在錯落有序的小山坡開啟尋寶旅途喔！', 'static/img/寶藏巖國際藝術村', 'https://www.google.com/maps/place/%E5%AF%B6%E8%97%8F%E5%B7%96%E5%9C%8B%E9%9A%9B%E8%97%9D%E8%A1%93%E6%9D%91/@25.0107236,121.5322601,17z/data=!3m1!4b1!4m6!3m5!1s0x3442a96e2986161d:0x1292997b402b34ad!8m2!3d25.0107236!4d121.5322601!16s%2Fm%2F02w4g7c?hl=zh-Hant-TW&entry=ttu'),
(7, '南港公園兒童遊戲場', '115台北市南港區東新街170之1號', '24H', 'https://play4u.gov.taipei/News_Content.aspx?n=4773608F226124D4&sms=7B56BA5392EB632C&s=A0AC2F7E195386D2', '4.3', '共有六大主題每區都好有趣！木屑鋪面的松鼠攀爬區，高低落差的樹屋中間有繩索通道連結，在其中爬上爬下的孩子們是不是很像小松鼠？還有號稱全台最刺激的飛鳥溜索、佈滿跳樁的家家酒平台、小甲蟲遊戲場超長超酷的鞦韆、小小孩專區，以及佔地最大的地鼠秘密基地，兩層式的遊樂設施，上層驚險的天網可以鑽入溜滑梯滑到下層沙坑，感覺就像是小地鼠的家呢！', 'static/img/南港公園共融遊戲場', 'https://www.google.com/maps/place/%E5%8D%97%E6%B8%AF%E5%85%AC%E5%9C%92%E5%85%B1%E8%9E%8D%E9%81%8A%E6%88%B2%E5%A0%B4/@25.0419294,121.591044,17z/data=!3m1!4b1!4m6!3m5!1s0x3442ab73219f6309:0x8ef92ad14f1450cc!8m2!3d25.0419294!4d121.591044!16s%2Fg%2F11qf_vnk33?hl=zh-Hant-TW&entry=ttu'),
(8, '松山療養所所長宿舍', '115台北市南港區昆陽街164號', '11:00-19:00', 'https://www.facebook.com/JingHsinYuan?mibextid=LQQJ4d', '4.5', '沿著車水馬龍的南港昆陽街拐進一條僻靜的巷弄，躲藏在此處已百年的日式老建築「松山療養所所長宿舍」，突如其來的映入眼簾。\r\n\r\n庭院前的人力車、六角窗、八角柱、敞開的日式拉門與長廊，無處不彰顯著這裡的漫長歲月，這是一個會讓人想緩下腳步、靜下心，享受放空也無妨的靜謐空間，現在由「靜心苑」經營為餐廳，提供台日融合料理，一起來體驗緩慢生活吧！', 'static/img/松山療養所所長宿舍', 'https://maps.app.goo.gl/CUDEceQxWRtwFAZj9?g_st=ic'),
(9, '台北101', '台北市信義區市府路45號', '10:00–21:00', 'https://www.taipei-101.com.tw/tw/observatory', '4.5', '身為台北最重要的地標，當然要前去拍照打卡一下啦～台北101內的地下1樓至4樓為購物中心，有名的小籠包餐廳鼎泰豐也位於此。另外，搭乘快速恆壓電梯至89樓的觀景台，從高處一覽整個台北市也是個不錯的體驗唷！', 'static/img/台北101', 'https://maps.app.goo.gl/iuvCody4ST4oVwpUA'),
(10, '國立故宮博物院', '111台北市士林區至善路二段221號', '09:00–17:00', 'https://www.npm.gov.tw/', '4.5', '國立故宮博物院是世界典藏最多寶物的博物館之一，典藏許多宮廷文物、藝術品等，例如翠玉白菜、肉形石都是裡面著名的珍藏品。故宮不只可以參觀，位於其左側的至善園，以王羲之的八大勝景為構思，很適合在逛完展覽後，以悠閒的步調漫步在書香氣息當中。', 'static/img/台北故宮博物院', 'https://www.google.com/maps/place/%E5%9C%8B%E7%AB%8B%E6%95%85%E5%AE%AE%E5%8D%9A%E7%89%A9%E9%99%A2/@24.2875719,120.261336,9z/data=!3m1!5s0x3442ac3b2ddb9a43:0x7a84c798191dd2cf!4m6!3m5!1s0x3442ac3acd404a7d:0x5d6d7018397a09c1!8m2!3d25.1023554!4d121.5484925!16zL20vMGhod2w?authuser=0&entry=ttu'),
(11, '西門紅樓', '108台北市萬華區成都路10號', '11:00–20:00', 'https://www.redhouse.taipei/', '4.2', '西門町是台北著名的逛街地點，流行服飾、美妝都可以在這裡大採購一番。西門町也是台北著名的電影街，許多電影院在此林立。另外，西門紅樓更是許多喜歡文創朋友們的天堂，每週六日還有市集可以逛逛喲！', 'static/img/紅樓', 'https://www.google.com/maps/place/%E8%A5%BF%E9%96%80%E7%B4%85%E6%A8%93/@25.0420139,121.5068592,17z/data=!3m2!4b1!5s0x3442a909a49c352f:0x94934848da84e6ed!4m6!3m5!1s0x3442a909a4acec8b:0x7c34275cfedcc1c5!8m2!3d25.0420139!4d121.5068592!16s%2Fm%2F02qfxx9?authuser=0&entry=ttu'),
(12, '瓶蓋工廠', '115台北市南港區南港路二段13號', '10:00-18:00', 'https://popoptaipei.com/', '4.2', '「瓶蓋工廠」的起源為日治時期的國產軟木工業株式會社，二戰後更改為臺灣菸酒公賣局瓶蓋工廠，於2004年正式熄燈，而2018年經過整修後，以手創、文青的展場樣貌再次重新開放！\r\n\r\n瓶蓋工廠就在南港車站旁，園區的戶外廣場有一面瓶蓋印象造景牆，拍照起來很亮眼，這裡有時也會舉辦假日市集、現場live表演，發展非常多元呢！', 'static/img/瓶蓋工廠', 'https://www.google.com/search?q=%E7%93%B6%E8%93%8B%E5%B7%A5%E5%BB%A0&sca_esv=593975244&rlz=1C1CHBD_zh-TWTW1034TW1034&tbm=isch&sxsrf=AM9HkKlX-MlYWwnqoVw5PyYy84B2opbqGQ:1703679958673&source=lnms&sa=X&sqi=2&ved=2ahUKEwj-nf21zq-DAxX1sFYBHZHrD20Q_AUoAnoECAIQBA&biw=1920&bih=945&dpr=1#imgrc=HBkt9iy8DUsRGM');

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_community`
--

CREATE TABLE `tbl_community` (
  `community_id` int(11) NOT NULL,
  `community_name` varchar(30) NOT NULL,
  `intro` text NOT NULL,
  `customer_id` int(11) NOT NULL,
  `image` text DEFAULT NULL,
  `create_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_community`
--

INSERT INTO `tbl_community` (`community_id`, `community_name`, `intro`, `customer_id`, `image`, `create_time`) VALUES
(1, 'first_community', 'this is intro', 1, 'https://i4.disp.cc/imgur/0TJftfkh.jpg', NULL),
(2, 'community 2', 'community 2 intro/ community made by customer 3', 3, 'https://i4.disp.cc/imgur/mHrCeik.jpg', NULL);

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_creditcard`
--

CREATE TABLE `tbl_creditcard` (
  `card_num` varchar(10) NOT NULL,
  `card_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_creditcard`
--

INSERT INTO `tbl_creditcard` (`card_num`, `card_id`) VALUES
('110403553', 1),
('110403009', 2),
('110403510', 3),
('110403014', 4),
('110403026', 5);

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_customer`
--

CREATE TABLE `tbl_customer` (
  `customer_id` int(11) NOT NULL,
  `customer_name` varchar(30) NOT NULL,
  `customer_email` varchar(50) NOT NULL,
  `customer_password` varchar(30) NOT NULL,
  `modified_time` datetime DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `intro` text DEFAULT NULL,
  `customer_point` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_customer`
--

INSERT INTO `tbl_customer` (`customer_id`, `customer_name`, `customer_email`, `customer_password`, `modified_time`, `birthday`, `intro`, `customer_point`) VALUES
(1, 'joe', 'jojo@gmail.com', 'passpass', NULL, NULL, NULL, NULL),
(2, 'this is 2', '22@gmail.com', '22pass', '2023-12-22 00:00:00', '2028-12-01', 'this is 22 you know!', 10),
(3, 'this is 3', '33@gmail.com', '33pass', '2023-12-08 00:00:00', '2015-12-26', 'haha i like SA ', 10),
(4, 'fourfourfour', '44@gmail.com', '44pass', '2023-12-23 00:00:00', '2007-12-15', 'this is fourth customer intro!', 10),
(5, '蔡知遠', 'tcy9213@gmail.com', '11', '2023-12-24 07:25:07', '2023-12-23', '', 0),
(6, 'bigjoe', '111@gmail.com', '11', '2023-12-25 12:21:45', '2023-12-27', '5615d56', 0);

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_favoritelist`
--

CREATE TABLE `tbl_favoritelist` (
  `favoritelist_id` int(11) NOT NULL,
  `journey_id` int(11) NOT NULL,
  `community_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_favoritelist`
--

INSERT INTO `tbl_favoritelist` (`favoritelist_id`, `journey_id`, `community_id`, `customer_id`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 2);

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_follower`
--

CREATE TABLE `tbl_follower` (
  `follower_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_follower`
--

INSERT INTO `tbl_follower` (`follower_id`, `customer_id`) VALUES
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 4),
(3, 4),
(4, 1),
(4, 2);

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_hotel`
--

CREATE TABLE `tbl_hotel` (
  `hotel_id` int(11) NOT NULL,
  `hotel_name` varchar(30) NOT NULL,
  `hotel_location` varchar(30) NOT NULL,
  `hotel_image` varchar(250) DEFAULT NULL,
  `hotel_facilities` text DEFAULT NULL,
  `hotel_intro` text DEFAULT NULL,
  `phone` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_hotel`
--

INSERT INTO `tbl_hotel` (`hotel_id`, `hotel_name`, `hotel_location`, `hotel_image`, `hotel_facilities`, `hotel_intro`, `phone`) VALUES
(1, 'hotel test 1', '中大路300號', 'static/img/中壢樹屋', '沒有床', '適合親子活動的地點，會有大樹守衛出來跟孩子互動，很推薦大朋友小朋友一同參與。', '000000'),
(2, '旅居文旅-中壢館', '320桃園市中壢區延平路216號', 'static/img/旅居文旅-中壢館', '\r\n公共停車位\r\n接機服務\r\n機場接駁服務\r\n圖書館\r\n咖啡廳\r\n行李寄存\r\n免費\r\n喚醒服務\r\n24小時櫃檯服務', '旅居 中壢館', '03 462 2345'),
(3, '二十輪旅店大安館', '106台北市大安區大安路一段185號', 'static/img/二十輪旅店大安館', '浴缸、創意房間設計\r\n', '「白色是最豐富的顏色，兼具主角與配角的雙重身份，也最能展現自我主張。」\r\n由設計師方序中操刀的品牌視覺，以白色與黑色為主軸，金銀雙色為支線，定位出SWIIO座落於大安區的極簡優雅座標。Logo取建築外觀的角落形狀構成第一視覺，以簡潔俐落線條活化了通透流動的循環感，呼應了包覆在SWIIO外觀的純白色澤之下，以獨特幾何形狀及通透大片玻璃，讓建築得以在水泥區塊包夾中，緩慢且自在呼吸的狀態。', '0227032220'),
(4, '桃園福緣山莊民宿(Fu-Yam Hostel)', '336, 桃園, 復興區, 華陵村11鄰180-7號', 'static/img/福緣山莊', '山莊共有三幢獨棟木屋、位於拉拉山風景特定區', '福緣山莊位在桃園縣北台灣－最有氧的拉拉山風景特定區，比鄰拉拉溪畔。山莊共有三幢獨棟木屋，提供旅客安靜、幹凈、寬敞的環境,來到青山環抱、溪水依偎的環境中，福緣山莊可以給您一個完全舒壓的假期。福緣的主人本著惜福的心歡迎您來分福緣山莊的一切！在前往福緣山莊的沿途之中，相信北橫沿線的美麗風景，也會讓您駐足許久！', '03 391 2090'),
(5, '朋趣豪華露營車', '園市龍潭區悅華路100號', 'static/img/朋趣豪華露營車', '相鄰著桃園高爾夫球場、旋轉木馬、電動車、戲水池', '不僅相鄰著桃園高爾夫球場，\r\n\r\n戶外更有旋轉木馬、電動車、戲水池，\r\n\r\n加上摩天輪後，根本是小型遊樂園，\r\n\r\n訂一泊四食全包式奢華渡假整個享受。', '02-8978-6688'),
(6, '名人堂花園大飯店', '桃園市龍潭區民生路141巷150號', 'static/img/名人堂照片', '有超多間客房，還有史努比樂園', '有超多間客房，還有史努比樂園，\r\n\r\n可以讓你一住進來就拍個不停，\r\n\r\n室內有泳池跟棒球場可以享受，\r\n\r\n不用到戶外就能飯店內待一整天，\r\n\r\n難怪谷歌評價是4.5顆星完美。', '+88634339090'),
(7, '桃園大溪笠復威斯汀度假飯店', '桃園, 大溪區, 日新路166號', 'static/img/桃園大溪笠復威斯汀度假飯店', '高爾夫球場\r\n室內泳池\r\n室外泳池\r\n三溫暖\r\nSpa\r\n健身房\r\n停車免費\r\n酒吧\r\n餐廳\r\n兒童遊樂場\r\n兒童俱樂部\r\n24小時櫃檯服務\r\n公共空間 Wi-Fi免費\r\n', '桃園大溪笠復威斯汀度假飯店位處大北部地區的美麗後花園－大溪，距離北部主要都會地區只要一小時內車程，比鄰全台灣最優質的高爾夫球場之一的大溪球場。飯店有舒適宜人的客套房，每間客房皆有面向蔥鬱林蔭景觀的獨立陽台且備有聞名於世的威斯汀天夢之床，使賓客在度假時擁享酣然好夢。為了滿足家庭市場，飯店為了3-12歲的孩童設置專屬的遊憩空間－威斯汀孩童俱樂部，在這個威斯汀孩童俱樂部，無論是玩樂在各項遊樂設備或體驗手遊創作，全家人皆可以全方位的感受幸福的度假時光，留下最鮮活的度假回憶。', '032725777'),
(8, '和逸飯店桃園館', '桃園市中壢區春德路101號', 'static/img/和逸飯店', '健身房、早餐、浴缸、空調、電視', '和逸飯店桃園館超級強大，\r\n\r\n除了距離華泰名品城超近，\r\n\r\n還可以順遊超美的水生公園，\r\n\r\n房間設備可使用健身房，\r\n\r\n房型是親子會喜歡的海底王國，\r\n\r\n早餐方面也是豐富好吃不雷，\r\n\r\n谷歌評價是不錯的4.0顆星。', '+88632737699'),
(9, '古華花園飯店', '桃園市中壢區民權路398號', 'static/img/古華花園飯店', '無邊際泳池、洗衣房、接駁車、餐廳', '距離知名中壢夜市很近，\r\n\r\n飯店內提供無邊際泳池和洗衣房，\r\n\r\n谷歌評價是良好的4.1顆星，\r\n\r\n算是桃園少數的大型飯店，\r\n\r\n很適合安排遊樂園住宿一晚。', '+88632811818'),
(10, '笠復威斯汀度假酒店', '桃園市大溪區日新路166號', 'static/img/笠復威斯汀度假酒店', '獨立陽台、游泳池，機場接駁車，餐廳', '笠復威斯汀度假酒店比鄰大溪高爾夫球場，\r\n\r\n有205間舒適房型，每間皆設有獨立陽台，\r\n\r\n桃園大溪一日遊住宿擁綠意遼闊的無敵景觀；\r\n\r\n威斯汀天夢之床，更讓賓客能擁享酣然好夢。\r\n\r\n峇里島風格池畔以及完備多元的休閒設施', '+886 3 272 5777'),
(11, '六星旅館', '桃園市桃園區經國路721號', 'static/img/六星旅館', 'MINI BAR餅乾跟飲料免費', '內部空間符合現代人需求，\r\n\r\n走路到經國轉運站只要6分鐘，\r\n\r\nMINI BAR餅乾跟飲料免費享受，\r\n\r\n早餐則是中西式都有不難吃，\r\n\r\n谷歌評價是不錯的4.3顆星。', '+88633467668'),
(12, '四季行館', '桃園市大溪區中央路37號', 'static/img/四季行館', '停車位、餐廳', '走路十分鐘可到老街吃美食，\r\n\r\n房間明亮乾淨，設備算是新穎，\r\n\r\n四人房價位3000出頭算便宜了，\r\n\r\n難怪谷歌評價4.9顆星接近完美。', '+88633881818'),
(13, '拉拉山雲山房', '桃園市復興區華陵里9鄰巴崚88-1號', 'static/img/拉拉山雲山房', 'WI-FI、機場接送、早餐', '雲山房櫻花季超難訂，\r\n\r\n算是拉拉山上面等級不錯民宿，\r\n\r\n房間硬體設備很新，室內乾淨，\r\n\r\n隔天可以欣賞山嵐和雲霧，\r\n\r\n房間也是乾淨舒適又有停車場，\r\n\r\n難怪可以獲得好評價4.5顆星。', '+88633912010'),
(14, 'ihotel中壢旗艦館', '桃園市中壢區中美路一段18號5樓', 'static/img/ihotel中壢旗艦館', '專業電競設備、專用電競場、WI-FI', 'hotel中壢旗艦館是以電競為主題旅館，\r\n\r\n客房設有專業電競設備，大廳並設置專用電競場，\r\n\r\n全館提供免費 WiFi，距離夜市也很近，\r\n\r\n客房內都配備高規格的電腦和電競座椅。', '03 280 6888'),
(15, '新驛旅店台北車站二館', '103台北市大同區長安西路81號', 'static/img/新驛旅店台北車站二館', '吧檯免費咖啡零食泡麵、附微波爐和咖啡機、免費洗烘衣機', '新驛旅店台北車站二館離京站時尚廣場1分鐘，吃喝購物都超方便。房間小巧乾淨，備品很齊全，隔音普通，人員無敵貼心生日還送小禮物。吧檯免費咖啡零食泡麵， 24小時公共小廚房附微波爐和咖啡機，免費洗烘衣機也很讚，高CP值的台北住宿便宜大推薦！', ' 02 2555 5577'),
(16, '台北誠品行旅', '台北市信義區菸廠路98號', 'static/img/台北誠品行旅', '酒吧、健身房、陽台', '以文青質感旅宿著稱的誠品行旅，就坐落在松菸文創文區內，剛剛好的觀賞距離，恰巧可以捕捉101全貌。而誠品行旅也會針對2024跨年煙火推出結合餐飲與住房的「迷樂跨年夜」活動，不只帶來充滿視、聽、味覺的奢華饗宴，也讓狂歡後的派對夜有舒適的落腳處。\r\n\r\n', '0266262888'),
(17, '台北時代寓所', '台北市中正區林森南路7號', 'static/img/台北時代寓所', '自助餐、健身房、SPA', '隸屬於知名飯店「希爾頓集團」旗下的【台北車站住宿台北時代寓所】這間台北住宿，以電影膠卷盒為設計理念的建築。\r\n這間台北住宿濃濃藝術感與設計感不說，最重要的早餐有別於一般飯店的自助餐，這裡特別邀請大家最愛的星巴克進駐飯店一樓，入住的房客們可以盡享高品質咖啡與餐點，享受包場星巴克的快感。', '02 7752 1888'),
(18, '中山喜瑞飯店', '104台北市中山區長安東路一段64號', 'static/img/中山喜瑞飯店', '酒吧、停車場、機場接駁車', '有著緊鄰一整片台北市景透明落地窗的浴缸\r\n這間台北住宿從外部建築到內部基調全都由白色組成，潔白而舒適。\r\n一樓交誼廳還有提供免費下午茶，無論是商務或度假都非常適合', '02 2541 0077'),
(19, '歐遊國際連鎖精品旅館', '新北市新莊區思源路746號', 'static/img/歐遊國際連鎖精品旅館', '浴缸、SPA、KTV', '位於新莊的台北汽車旅館歐遊精品旅館這間台北汽車旅館，五種房型簡約典雅，剛好來台北洽公很剛好、情侶一起做台北情趣旅館很適合，帶家人孩子入住也很可以。\r\n這間台北motel房間寬敞、浴缸大，有些房型還附有SPA水療池和KTV設備，乾淨清爽環境適合台北汽車旅館休息，深受網友好評！', '02-8522-1100'),
(20, '華大旅店 南西館', '台北市南京西路151號6樓', 'static/img/華大旅店 南西館', 'WI-FI、客房服務', '位於捷運中山站6號出口附近的華大旅店，地點近台北圓環、寧夏夜市、南西商圈非常方便。\r\n華大旅店是一間3星級的台北飯店，但每晚2000元內非常平價，且評價高於4.5顆星。\r\n華大旅店有24小時櫃台服務，且房內免費Wi-Fi，可寄放行李及送餐服務。\r\n台北住宿華大旅店，在agoda上沒有免費取消方案，但有特惠價格。', '0225593232');

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_hotelowner`
--

CREATE TABLE `tbl_hotelowner` (
  `hotelowner_id` int(11) NOT NULL,
  `hotelowner_name` varchar(30) NOT NULL,
  `hotelowner_email` varchar(50) NOT NULL,
  `hotelowner_password` varchar(30) NOT NULL,
  `modified_time` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `hotel_id` int(11) DEFAULT NULL,
  `intro` text DEFAULT NULL,
  `birthday` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_hotelowner`
--

INSERT INTO `tbl_hotelowner` (`hotelowner_id`, `hotelowner_name`, `hotelowner_email`, `hotelowner_password`, `modified_time`, `hotel_id`, `intro`, `birthday`) VALUES
(1, '蔡知遠', 'DuaTaoGa@gmail.com', '110403553', '2023-12-22 16:00:00', 1, 'this is 蔡知遠', '2004-12-11'),
(2, 'hotelOwner2', 'Owner2@gmail.com', '22pass', '2023-12-22 16:00:00', 2, NULL, '2028-12-01'),
(3, 'hotelOwner3', 'Owner3@gmail.com', '33pass', '2023-12-22 16:00:00', 3, NULL, NULL),
(4, 'hotelOwner4', 'owner4@gmail.com', '44pass', '2023-12-22 16:00:00', 4, NULL, NULL),
(5, '蔡知遠', '110403553@gmail.com', '110403553', '2023-12-27 12:45:16', 5, 'SA期末', '2003-02-13'),
(7, '陳升嶸', '110403014@gmail.com', '110403014', '2023-12-27 13:24:06', 6, 'SA期末', '2023-12-21'),
(8, '張綺容', '110403510@gmail.com', '110403510', '2023-12-27 13:28:27', 7, 'SA期末', '2023-12-23'),
(9, '陳莉豐', '110403009@gmail.com', '110403009', '2023-12-27 13:29:14', 8, 'SA期末介紹', '2023-12-09'),
(10, '李亦喬', '110403026@gmail.com', '110403026', '2023-12-27 13:29:59', 9, 'SA期末介紹', '2023-12-16'),
(11, 'o1', 'o1@gamil.com', 'pass', '2023-12-27 13:31:24', 10, 'noono', '2023-12-22'),
(12, 'o2', 'o2@gmail.com', 'pass', '2023-12-27 13:32:11', 11, 'noono', '2023-12-08'),
(13, 'o3', 'o3@gmial.com', 'pass', '2023-12-27 13:32:51', 12, 'nonoo', '2023-12-12'),
(14, 'o13', 'o13@gmail.com', 'pass', '2023-12-27 13:38:01', 13, 'nonoononononononoo', '2023-12-11'),
(15, 'o14', 'o14@gmail.com', 'pass', '2023-12-27 13:38:01', 14, 'intro intro intro', '2023-12-10'),
(16, 'o15', 'o15@gmail.com', 'pass', '2023-12-27 13:38:01', 15, 'no intro no intro o6 intro', '2023-12-06'),
(17, 'o16', 'o16@gmail.com', 'pass', '2023-12-27 13:38:01', 16, 'o16 intro', '2023-12-16'),
(18, 'o17', 'o17@gmail.com', 'pass', '2023-12-27 13:38:01', 17, 'o17 intro', '2023-12-17'),
(19, 'o18', 'o18@gmail.com', 'pass', '2023-12-27 13:38:01', 18, 'o18 intro', '2023-12-18'),
(20, 'o19', 'o19@gmail.com', 'pass', '2023-12-27 13:38:01', 19, 'o19 intro', '2023-12-19'),
(21, 'o20', 'o20@gmail.com', 'pass', '2023-12-27 13:38:01', 20, '20 20 20 20 end !!!', '2023-12-20');

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_journey`
--

CREATE TABLE `tbl_journey` (
  `journey_id` int(11) NOT NULL,
  `journey_name` varchar(30) NOT NULL,
  `intro` text NOT NULL,
  `journey_day` date NOT NULL,
  `customer_id` int(11) NOT NULL,
  `privat` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_journey`
--

INSERT INTO `tbl_journey` (`journey_id`, `journey_name`, `intro`, `journey_day`, `customer_id`, `privat`) VALUES
(1, '旅程1', 'this is public journey 1', '2023-12-23', 1, 0),
(2, '旅程2', 'this is public journey 2', '2023-12-23', 1, 0),
(3, 'private journey 1', 'this is a private journey，set private to 1，means private journey', '2023-12-23', 2, 1),
(4, 'public journey', '\"testing testing\"', '2023-12-23', 3, 0);

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_order`
--

CREATE TABLE `tbl_order` (
  `order_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `order_number` int(11) NOT NULL,
  `order_price` int(11) NOT NULL,
  `guest_number` int(11) NOT NULL,
  `booking_date` date NOT NULL,
  `checkin_date` date NOT NULL,
  `checkout_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_order`
--

INSERT INTO `tbl_order` (`order_id`, `room_id`, `customer_id`, `order_number`, `order_price`, `guest_number`, `booking_date`, `checkin_date`, `checkout_date`) VALUES
(1, 1, 1, 1, 1000, 2, '2023-12-23', '2023-12-26', '2023-12-27'),
(2, 2, 2, 2, 3000, 4, '2023-12-23', '2023-12-27', '2023-12-28');

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_review`
--

CREATE TABLE `tbl_review` (
  `review_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `review_date` datetime NOT NULL,
  `replied_to_review_id` int(11) DEFAULT NULL,
  `review_content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_review`
--

INSERT INTO `tbl_review` (`review_id`, `customer_id`, `hotel_id`, `review_date`, `replied_to_review_id`, `review_content`) VALUES
(1, 1, 1, '2023-12-23 04:13:19', NULL, '大樹守衛不好打\r\n'),
(2, 2, 1, '2023-12-23 04:14:58', 1, '其實偏簡單');

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_room`
--

CREATE TABLE `tbl_room` (
  `room_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `room_type` text NOT NULL,
  `room_image` varchar(200) NOT NULL,
  `room_price` int(11) NOT NULL,
  `max_capacity` int(11) NOT NULL,
  `room_number` int(11) NOT NULL,
  `room_info` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_room`
--

INSERT INTO `tbl_room` (`room_id`, `hotel_id`, `room_type`, `room_image`, `room_price`, `max_capacity`, `room_number`, `room_info`) VALUES
(1, 1, '超級樹屋', 'https://lh5.googleusercontent.com/p/AF1QipMHtRJQr73kLHD0hjZGJC3o6s32UdM-XuaJhOc=w408-h725-k-no', 1000, 5, 1, NULL),
(2, 2, '雙人房', 'https://ak-d.tripcdn.com/images/22030x000000li29o6DFA_R_121_92_R5_D.jpg', 1500, 2, 10, NULL),
(3, 2, '三人房', 'https://ak-d.tripcdn.com/images/22051900000164l2h7D56_R_121_92_R5_D.jpg', 1500, 3, 5, NULL),
(4, 2, '單人房', 'https://ak-d.tripcdn.com/images/220i1900000160wct573C_R_121_92_R5_D.jpg', 500, 1, 3, NULL),
(5, 3, '四人房', 'https://ak-d.tripcdn.com/images/0204s120005p5p3q6C02D_R_224_126_R5_D.jpg', 1500, 4, 3, NULL),
(6, 3, '雙人房', 'https://ak-d.tripcdn.com/images/0223x120009mani5i0955_R_224_126_R5_D.jpg', 1000, 2, 5, NULL),
(7, 4, '雙人房', 'https://ak-d.tripcdn.com/images/0227012000ci5kyno5793_R_600_400_R5_D.jpg', 1000, 2, 4, NULL),
(8, 4, '四人房', 'https://ak-d.tripcdn.com/images/0226s12000aks50f85B54_R_600_400_R5_D.jpg', 500, 1, 3, NULL),
(13, 1, 'ttttt', 'image_path', 800, 3, 3, 'nono'),
(14, 1, 'ttt', 'image_path', 1000, 2, 2, 'nooono'),
(15, 1, '.', 'image_path', 2000, 2, 2, 'vope'),
(16, 1, '.', 'image_path', 24, 2, 2, 'bbf'),
(17, 1, '房型1', 'image_path', 1000, 2, 3, 'vm;'),
(18, 1, ' 測試房間', 'image_path', 1000, 3, 3, 'nono'),
(19, 1, '房型1', 'image_path', 1100, 3, 3, 'hiohoi');

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_roomavailability`
--

CREATE TABLE `tbl_roomavailability` (
  `room_id` int(11) NOT NULL,
  `date` date NOT NULL,
  `available_quantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_roomavailability`
--

INSERT INTO `tbl_roomavailability` (`room_id`, `date`, `available_quantity`) VALUES
(1, '2023-12-23', 1),
(1, '2023-12-24', 0),
(2, '2023-12-23', 3),
(2, '2023-12-24', 2),
(3, '2023-12-23', 3),
(3, '2023-12-24', 2),
(4, '2023-12-23', 3),
(4, '2023-12-24', 2),
(5, '2023-12-23', 3),
(5, '2023-12-24', 2),
(6, '2023-12-23', 3),
(6, '2023-12-24', 2),
(7, '2023-12-23', 3),
(7, '2023-12-24', 2),
(8, '2023-12-23', 3),
(8, '2023-12-24', 2),
(13, '2023-12-26', 3),
(13, '2023-12-27', 3),
(13, '2023-12-28', 3),
(13, '2023-12-29', 3),
(13, '2023-12-30', 3),
(13, '2023-12-31', 3),
(13, '2024-01-01', 3),
(13, '2024-01-02', 3),
(13, '2024-01-03', 3),
(13, '2024-01-04', 3),
(13, '2024-01-05', 3),
(13, '2024-01-06', 3),
(13, '2024-01-07', 3),
(13, '2024-01-08', 3),
(13, '2024-01-09', 3),
(13, '2024-01-10', 3),
(13, '2024-01-11', 3),
(13, '2024-01-12', 3),
(13, '2024-01-13', 3),
(13, '2024-01-14', 3),
(13, '2024-01-15', 3),
(13, '2024-01-16', 3),
(13, '2024-01-17', 3),
(13, '2024-01-18', 3),
(13, '2024-01-19', 3),
(13, '2024-01-20', 3),
(13, '2024-01-21', 3),
(13, '2024-01-22', 3),
(13, '2024-01-23', 3),
(13, '2024-01-24', 3),
(14, '2023-12-26', 2),
(14, '2023-12-27', 2),
(14, '2023-12-28', 2),
(14, '2023-12-29', 2),
(14, '2023-12-30', 2),
(14, '2023-12-31', 2),
(14, '2024-01-01', 2),
(14, '2024-01-02', 2),
(14, '2024-01-03', 2),
(14, '2024-01-04', 2),
(14, '2024-01-05', 2),
(14, '2024-01-06', 2),
(14, '2024-01-07', 2),
(14, '2024-01-08', 2),
(14, '2024-01-09', 2),
(14, '2024-01-10', 2),
(14, '2024-01-11', 2),
(14, '2024-01-12', 2),
(14, '2024-01-13', 2),
(14, '2024-01-14', 2),
(14, '2024-01-15', 2),
(14, '2024-01-16', 2),
(14, '2024-01-17', 2),
(14, '2024-01-18', 2),
(14, '2024-01-19', 2),
(14, '2024-01-20', 2),
(14, '2024-01-21', 2),
(14, '2024-01-22', 2),
(14, '2024-01-23', 2),
(14, '2024-01-24', 2),
(15, '2023-12-26', 2),
(15, '2023-12-27', 2),
(15, '2023-12-28', 2),
(15, '2023-12-29', 2),
(15, '2023-12-30', 2),
(15, '2023-12-31', 2),
(15, '2024-01-01', 2),
(15, '2024-01-02', 2),
(15, '2024-01-03', 2),
(15, '2024-01-04', 2),
(15, '2024-01-05', 2),
(15, '2024-01-06', 2),
(15, '2024-01-07', 2),
(15, '2024-01-08', 2),
(15, '2024-01-09', 2),
(15, '2024-01-10', 2),
(15, '2024-01-11', 2),
(15, '2024-01-12', 2),
(15, '2024-01-13', 2),
(15, '2024-01-14', 2),
(15, '2024-01-15', 2),
(15, '2024-01-16', 2),
(15, '2024-01-17', 2),
(15, '2024-01-18', 2),
(15, '2024-01-19', 2),
(15, '2024-01-20', 2),
(15, '2024-01-21', 2),
(15, '2024-01-22', 2),
(15, '2024-01-23', 2),
(15, '2024-01-24', 2),
(16, '2023-12-26', 2),
(16, '2023-12-27', 2),
(16, '2023-12-28', 2),
(16, '2023-12-29', 2),
(16, '2023-12-30', 2),
(16, '2023-12-31', 2),
(16, '2024-01-01', 2),
(16, '2024-01-02', 2),
(16, '2024-01-03', 2),
(16, '2024-01-04', 2),
(16, '2024-01-05', 2),
(16, '2024-01-06', 2),
(16, '2024-01-07', 2),
(16, '2024-01-08', 2),
(16, '2024-01-09', 2),
(16, '2024-01-10', 2),
(16, '2024-01-11', 2),
(16, '2024-01-12', 2),
(16, '2024-01-13', 2),
(16, '2024-01-14', 2),
(16, '2024-01-15', 2),
(16, '2024-01-16', 2),
(16, '2024-01-17', 2),
(16, '2024-01-18', 2),
(16, '2024-01-19', 2),
(16, '2024-01-20', 2),
(16, '2024-01-21', 2),
(16, '2024-01-22', 2),
(16, '2024-01-23', 2),
(16, '2024-01-24', 2),
(17, '2023-12-26', 3),
(17, '2023-12-27', 3),
(17, '2023-12-28', 3),
(17, '2023-12-29', 3),
(17, '2023-12-30', 3),
(17, '2023-12-31', 3),
(17, '2024-01-01', 3),
(17, '2024-01-02', 3),
(17, '2024-01-03', 3),
(17, '2024-01-04', 3),
(17, '2024-01-05', 3),
(17, '2024-01-06', 3),
(17, '2024-01-07', 3),
(17, '2024-01-08', 3),
(17, '2024-01-09', 3),
(17, '2024-01-10', 3),
(17, '2024-01-11', 3),
(17, '2024-01-12', 3),
(17, '2024-01-13', 3),
(17, '2024-01-14', 3),
(17, '2024-01-15', 3),
(17, '2024-01-16', 3),
(17, '2024-01-17', 3),
(17, '2024-01-18', 3),
(17, '2024-01-19', 3),
(17, '2024-01-20', 3),
(17, '2024-01-21', 3),
(17, '2024-01-22', 3),
(17, '2024-01-23', 3),
(17, '2024-01-24', 3),
(18, '2023-12-26', 3),
(18, '2023-12-27', 3),
(18, '2023-12-28', 3),
(18, '2023-12-29', 3),
(18, '2023-12-30', 3),
(18, '2023-12-31', 3),
(18, '2024-01-01', 3),
(18, '2024-01-02', 3),
(18, '2024-01-03', 3),
(18, '2024-01-04', 3),
(18, '2024-01-05', 3),
(18, '2024-01-06', 3),
(18, '2024-01-07', 3),
(18, '2024-01-08', 3),
(18, '2024-01-09', 3),
(18, '2024-01-10', 3),
(18, '2024-01-11', 3),
(18, '2024-01-12', 3),
(18, '2024-01-13', 3),
(18, '2024-01-14', 3),
(18, '2024-01-15', 3),
(18, '2024-01-16', 3),
(18, '2024-01-17', 3),
(18, '2024-01-18', 3),
(18, '2024-01-19', 3),
(18, '2024-01-20', 3),
(18, '2024-01-21', 3),
(18, '2024-01-22', 3),
(18, '2024-01-23', 3),
(18, '2024-01-24', 3),
(19, '2023-12-27', 3),
(19, '2023-12-28', 3),
(19, '2023-12-29', 3),
(19, '2023-12-30', 3),
(19, '2023-12-31', 3),
(19, '2024-01-01', 3),
(19, '2024-01-02', 3),
(19, '2024-01-03', 3),
(19, '2024-01-04', 3),
(19, '2024-01-05', 3),
(19, '2024-01-06', 3),
(19, '2024-01-07', 3),
(19, '2024-01-08', 3),
(19, '2024-01-09', 3),
(19, '2024-01-10', 3),
(19, '2024-01-11', 3),
(19, '2024-01-12', 3),
(19, '2024-01-13', 3),
(19, '2024-01-14', 3),
(19, '2024-01-15', 3),
(19, '2024-01-16', 3),
(19, '2024-01-17', 3),
(19, '2024-01-18', 3),
(19, '2024-01-19', 3),
(19, '2024-01-20', 3),
(19, '2024-01-21', 3),
(19, '2024-01-22', 3),
(19, '2024-01-23', 3),
(19, '2024-01-24', 3),
(19, '2024-01-25', 3);

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_shoppingcart`
--

CREATE TABLE `tbl_shoppingcart` (
  `customer_id` int(11) NOT NULL,
  `room_id` int(11) NOT NULL,
  `order_number` int(11) NOT NULL,
  `checkin_date` date DEFAULT NULL,
  `checkout_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_shoppingcart`
--

INSERT INTO `tbl_shoppingcart` (`customer_id`, `room_id`, `order_number`, `checkin_date`, `checkout_date`) VALUES
(1, 1, 1, NULL, NULL);

--
-- 已傾印資料表的索引
--

--
-- 資料表索引 `tbllinking_attractionjourney`
--
ALTER TABLE `tbllinking_attractionjourney`
  ADD PRIMARY KEY (`attraction_id`,`journey_id`),
  ADD KEY `Attraction_to_Journey` (`journey_id`);

--
-- 資料表索引 `tbllinking_communityattraction`
--
ALTER TABLE `tbllinking_communityattraction`
  ADD PRIMARY KEY (`community_id`,`attraction_id`),
  ADD KEY `Community_to_Attraction` (`attraction_id`);

--
-- 資料表索引 `tbl_attraction`
--
ALTER TABLE `tbl_attraction`
  ADD PRIMARY KEY (`attraction_id`);

--
-- 資料表索引 `tbl_community`
--
ALTER TABLE `tbl_community`
  ADD PRIMARY KEY (`community_id`,`customer_id`),
  ADD KEY `Community_to_Customer` (`customer_id`);

--
-- 資料表索引 `tbl_creditcard`
--
ALTER TABLE `tbl_creditcard`
  ADD PRIMARY KEY (`card_id`);

--
-- 資料表索引 `tbl_customer`
--
ALTER TABLE `tbl_customer`
  ADD PRIMARY KEY (`customer_id`);

--
-- 資料表索引 `tbl_favoritelist`
--
ALTER TABLE `tbl_favoritelist`
  ADD PRIMARY KEY (`favoritelist_id`,`customer_id`,`community_id`,`journey_id`),
  ADD KEY `FavoriteList_to_Community` (`community_id`),
  ADD KEY `FavoriteList_to_Journey` (`journey_id`),
  ADD KEY `FavoriteList_to_Customer` (`customer_id`);

--
-- 資料表索引 `tbl_follower`
--
ALTER TABLE `tbl_follower`
  ADD PRIMARY KEY (`follower_id`,`customer_id`),
  ADD KEY `Follower_to_Customer` (`customer_id`);

--
-- 資料表索引 `tbl_hotel`
--
ALTER TABLE `tbl_hotel`
  ADD PRIMARY KEY (`hotel_id`);

--
-- 資料表索引 `tbl_hotelowner`
--
ALTER TABLE `tbl_hotelowner`
  ADD PRIMARY KEY (`hotelowner_id`),
  ADD KEY `HotelOwner_to_Hotel` (`hotel_id`);

--
-- 資料表索引 `tbl_journey`
--
ALTER TABLE `tbl_journey`
  ADD PRIMARY KEY (`journey_id`),
  ADD KEY `Journey_to_Customer` (`customer_id`);

--
-- 資料表索引 `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `Order_to_Customer` (`customer_id`),
  ADD KEY `Order_to_Room` (`room_id`);

--
-- 資料表索引 `tbl_review`
--
ALTER TABLE `tbl_review`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `Review_to_Customer` (`customer_id`),
  ADD KEY `hotel_id` (`hotel_id`);

--
-- 資料表索引 `tbl_room`
--
ALTER TABLE `tbl_room`
  ADD PRIMARY KEY (`room_id`),
  ADD KEY `Room_to_Hotel` (`hotel_id`);

--
-- 資料表索引 `tbl_roomavailability`
--
ALTER TABLE `tbl_roomavailability`
  ADD PRIMARY KEY (`room_id`,`date`);

--
-- 資料表索引 `tbl_shoppingcart`
--
ALTER TABLE `tbl_shoppingcart`
  ADD PRIMARY KEY (`customer_id`,`room_id`),
  ADD KEY `ShoppingCart_to_Room` (`room_id`);

--
-- 在傾印的資料表使用自動遞增(AUTO_INCREMENT)
--

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_attraction`
--
ALTER TABLE `tbl_attraction`
  MODIFY `attraction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_community`
--
ALTER TABLE `tbl_community`
  MODIFY `community_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_creditcard`
--
ALTER TABLE `tbl_creditcard`
  MODIFY `card_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_customer`
--
ALTER TABLE `tbl_customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_favoritelist`
--
ALTER TABLE `tbl_favoritelist`
  MODIFY `favoritelist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_hotel`
--
ALTER TABLE `tbl_hotel`
  MODIFY `hotel_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_hotelowner`
--
ALTER TABLE `tbl_hotelowner`
  MODIFY `hotelowner_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_journey`
--
ALTER TABLE `tbl_journey`
  MODIFY `journey_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_order`
--
ALTER TABLE `tbl_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_review`
--
ALTER TABLE `tbl_review`
  MODIFY `review_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_room`
--
ALTER TABLE `tbl_room`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `tbllinking_attractionjourney`
--
ALTER TABLE `tbllinking_attractionjourney`
  ADD CONSTRAINT `Attraction_to_Journey` FOREIGN KEY (`journey_id`) REFERENCES `tbl_journey` (`journey_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Journey_to_Attration` FOREIGN KEY (`attraction_id`) REFERENCES `tbl_attraction` (`attraction_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbllinking_communityattraction`
--
ALTER TABLE `tbllinking_communityattraction`
  ADD CONSTRAINT `Attraction_to_Community` FOREIGN KEY (`community_id`) REFERENCES `tbl_community` (`community_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Community_to_Attraction` FOREIGN KEY (`attraction_id`) REFERENCES `tbl_attraction` (`attraction_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_community`
--
ALTER TABLE `tbl_community`
  ADD CONSTRAINT `Community_to_Customer` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_favoritelist`
--
ALTER TABLE `tbl_favoritelist`
  ADD CONSTRAINT `FavoriteList_to_Community` FOREIGN KEY (`community_id`) REFERENCES `tbl_community` (`community_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FavoriteList_to_Customer` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FavoriteList_to_Journey` FOREIGN KEY (`journey_id`) REFERENCES `tbl_journey` (`journey_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_follower`
--
ALTER TABLE `tbl_follower`
  ADD CONSTRAINT `Follower_to_Customer` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_hotelowner`
--
ALTER TABLE `tbl_hotelowner`
  ADD CONSTRAINT `HotelOwner_to_Hotel` FOREIGN KEY (`hotel_id`) REFERENCES `tbl_hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_journey`
--
ALTER TABLE `tbl_journey`
  ADD CONSTRAINT `Journey_to_Customer` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD CONSTRAINT `Order_to_Customer` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Order_to_Room` FOREIGN KEY (`room_id`) REFERENCES `tbl_room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_review`
--
ALTER TABLE `tbl_review`
  ADD CONSTRAINT `Review_to_Customer` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `hotel_id` FOREIGN KEY (`hotel_id`) REFERENCES `tbl_hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_room`
--
ALTER TABLE `tbl_room`
  ADD CONSTRAINT `Room_to_Hotel` FOREIGN KEY (`hotel_id`) REFERENCES `tbl_hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_roomavailability`
--
ALTER TABLE `tbl_roomavailability`
  ADD CONSTRAINT `RoomAvailability_to_Room` FOREIGN KEY (`room_id`) REFERENCES `tbl_room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_shoppingcart`
--
ALTER TABLE `tbl_shoppingcart`
  ADD CONSTRAINT `ShoppingCart_to_Customer` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ShoppingCart_to_Room` FOREIGN KEY (`room_id`) REFERENCES `tbl_room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
