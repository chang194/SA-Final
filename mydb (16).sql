-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2023-12-28 21:52:58
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
-- 資料表結構 `tbl_hotel`
--

CREATE TABLE `tbl_hotel` (
  `hotel_id` int(11) NOT NULL,
  `hotel_name` varchar(30) NOT NULL,
  `hotel_location` varchar(30) NOT NULL,
  `hotel_image` varchar(250) DEFAULT NULL,
  `hotel_facilities` text DEFAULT NULL,
  `hotel_intro` text DEFAULT NULL,
  `hotel_phone` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_hotel`
--

INSERT INTO `tbl_hotel` (`hotel_id`, `hotel_name`, `hotel_location`, `hotel_image`, `hotel_facilities`, `hotel_intro`, `phone`) VALUES
(1, 'hotel test 1', '中大路300號', 'static/img/中壢樹屋.jpg\r\n', '沒有床', '適合親子活動的地點，會有大樹守衛出來跟孩子互動，很推薦大朋友小朋友一同參與。', '000000'),
(2, '旅居文旅-中壢館', '320桃園市中壢區延平路216號', 'static/img/旅居文旅-中壢館.jpg', '\r\n公共停車位\r\n接機服務\r\n機場接駁服務\r\n圖書館\r\n咖啡廳\r\n行李寄存\r\n免費\r\n喚醒服務\r\n24小時櫃檯服務', '旅居 中壢館', '03 462 2345'),
(3, '二十輪旅店大安館', '106台北市大安區大安路一段185號', 'static/img/二十輪.jpg', '浴缸、創意房間設計\r\n', '「白色是最豐富的顏色，兼具主角與配角的雙重身份，也最能展現自我主張。」\r\n由設計師方序中操刀的品牌視覺，以白色與黑色為主軸，金銀雙色為支線，定位出SWIIO座落於大安區的極簡優雅座標。Logo取建築外觀的角落形狀構成第一視覺，以簡潔俐落線條活化了通透流動的循環感，呼應了包覆在SWIIO外觀的純白色澤之下，以獨特幾何形狀及通透大片玻璃，讓建築得以在水泥區塊包夾中，緩慢且自在呼吸的狀態。', '0227032220'),
(4, '桃園福緣山莊民宿(Fu-Yam Hostel)', '336, 桃園, 復興區, 華陵村11鄰180-7號', 'static/img/福緣山莊.jpg', '山莊共有三幢獨棟木屋、位於拉拉山風景特定區', '福緣山莊位在桃園縣北台灣－最有氧的拉拉山風景特定區，比鄰拉拉溪畔。山莊共有三幢獨棟木屋，提供旅客安靜、幹凈、寬敞的環境,來到青山環抱、溪水依偎的環境中，福緣山莊可以給您一個完全舒壓的假期。福緣的主人本著惜福的心歡迎您來分福緣山莊的一切！在前往福緣山莊的沿途之中，相信北橫沿線的美麗風景，也會讓您駐足許久！', '03 391 2090'),
(5, '朋趣豪華露營車', '園市龍潭區悅華路100號', 'static/img/朋趣豪華露營車.jpg', '相鄰著桃園高爾夫球場、旋轉木馬、電動車、戲水池', '不僅相鄰著桃園高爾夫球場，\r\n\r\n戶外更有旋轉木馬、電動車、戲水池，\r\n\r\n加上摩天輪後，根本是小型遊樂園，\r\n\r\n訂一泊四食全包式奢華渡假整個享受。', '02-8978-6688'),
(6, '名人堂花園大飯店', '桃園市龍潭區民生路141巷150號', 'static/img/名人堂照片.webp', '有超多間客房，還有史努比樂園', '有超多間客房，還有史努比樂園，\r\n\r\n可以讓你一住進來就拍個不停，\r\n\r\n室內有泳池跟棒球場可以享受，\r\n\r\n不用到戶外就能飯店內待一整天，\r\n\r\n難怪谷歌評價是4.5顆星完美。', '+88634339090'),
(7, '桃園大溪笠復威斯汀度假飯店', '桃園, 大溪區, 日新路166號', 'static/img/桃園大溪笠復威斯汀度假飯店.jpg', '高爾夫球場\r\n室內泳池\r\n室外泳池\r\n三溫暖\r\nSpa\r\n健身房\r\n停車免費\r\n酒吧\r\n餐廳\r\n兒童遊樂場\r\n兒童俱樂部\r\n24小時櫃檯服務\r\n公共空間 Wi-Fi免費\r\n', '桃園大溪笠復威斯汀度假飯店位處大北部地區的美麗後花園－大溪，距離北部主要都會地區只要一小時內車程，比鄰全台灣最優質的高爾夫球場之一的大溪球場。飯店有舒適宜人的客套房，每間客房皆有面向蔥鬱林蔭景觀的獨立陽台且備有聞名於世的威斯汀天夢之床，使賓客在度假時擁享酣然好夢。為了滿足家庭市場，飯店為了3-12歲的孩童設置專屬的遊憩空間－威斯汀孩童俱樂部，在這個威斯汀孩童俱樂部，無論是玩樂在各項遊樂設備或體驗手遊創作，全家人皆可以全方位的感受幸福的度假時光，留下最鮮活的度假回憶。', '032725777'),
(8, '和逸飯店桃園館', '桃園市中壢區春德路101號', 'static/img/和逸飯店.webp', '健身房、早餐、浴缸、空調、電視', '和逸飯店桃園館超級強大，\r\n\r\n除了距離華泰名品城超近，\r\n\r\n還可以順遊超美的水生公園，\r\n\r\n房間設備可使用健身房，\r\n\r\n房型是親子會喜歡的海底王國，\r\n\r\n早餐方面也是豐富好吃不雷，\r\n\r\n谷歌評價是不錯的4.0顆星。', '+88632737699'),
(9, '古華花園飯店', '桃園市中壢區民權路398號', 'static/img/古華花園飯店.webp', '無邊際泳池、洗衣房、接駁車、餐廳', '距離知名中壢夜市很近，\r\n\r\n飯店內提供無邊際泳池和洗衣房，\r\n\r\n谷歌評價是良好的4.1顆星，\r\n\r\n算是桃園少數的大型飯店，\r\n\r\n很適合安排遊樂園住宿一晚。', '+88632811818'),
(10, '福容大飯店 桃園機場捷運A8店', '桃園市大溪區日新路166號', 'static/img/福容大飯店.jpg', '三溫暖，獨立衛浴，離捷運近', '福容大飯店桃園機場捷運A8店就位於桃園機場捷運的長庚醫院站(A8)，機捷上樓直接就抵達，樓下還有購物中心，很適合來洽公或者想要離機場近交通方便的朋友。飯店裡除了房間寬敞，還設有三溫暖，晚上舒服的泡個澡很棒。', '03 328 5688'),
(11, '六星旅館', '桃園市桃園區經國路721號', 'static/img/六星旅館.webp', 'MINI BAR餅乾跟飲料免費', '內部空間符合現代人需求，\r\n\r\n走路到經國轉運站只要6分鐘，\r\n\r\nMINI BAR餅乾跟飲料免費享受，\r\n\r\n早餐則是中西式都有不難吃，\r\n\r\n谷歌評價是不錯的4.3顆星。', '+88633467668'),
(12, '四季行館', '桃園市大溪區中央路37號', 'static/img/四季行館.webp', '停車位、餐廳', '走路十分鐘可到老街吃美食，\r\n\r\n房間明亮乾淨，設備算是新穎，\r\n\r\n四人房價位3000出頭算便宜了，\r\n\r\n難怪谷歌評價4.9顆星接近完美。', '+88633881818'),
(13, '拉拉山雲山房', '桃園市復興區華陵里9鄰巴崚88-1號', 'static/img/拉拉山雲山房.webp', 'WI-FI、機場接送、早餐', '雲山房櫻花季超難訂，\r\n\r\n算是拉拉山上面等級不錯民宿，\r\n\r\n房間硬體設備很新，室內乾淨，\r\n\r\n隔天可以欣賞山嵐和雲霧，\r\n\r\n房間也是乾淨舒適又有停車場，\r\n\r\n難怪可以獲得好評價4.5顆星。', '+88633912010'),
(14, 'ihotel中壢旗艦館', '桃園市中壢區中美路一段18號5樓', 'static/img/ihotel中壢旗艦館.webp', '專業電競設備、專用電競場、WI-FI', 'hotel中壢旗艦館是以電競為主題旅館，\r\n\r\n客房設有專業電競設備，大廳並設置專用電競場，\r\n\r\n全館提供免費 WiFi，距離夜市也很近，\r\n\r\n客房內都配備高規格的電腦和電競座椅。', '03 280 6888'),
(15, '新驛旅店台北車站二館', '103台北市大同區長安西路81號', 'static/img/新驛旅店台北車站二館.jpg', '吧檯免費咖啡零食泡麵、附微波爐和咖啡機、免費洗烘衣機', '新驛旅店台北車站二館離京站時尚廣場1分鐘，吃喝購物都超方便。房間小巧乾淨，備品很齊全，隔音普通，人員無敵貼心生日還送小禮物。吧檯免費咖啡零食泡麵， 24小時公共小廚房附微波爐和咖啡機，免費洗烘衣機也很讚，高CP值的台北住宿便宜大推薦！', ' 02 2555 5577'),
(16, '台北誠品行旅', '台北市信義區菸廠路98號', 'static/img/台北誠品行旅.jpg', '酒吧、健身房、陽台', '以文青質感旅宿著稱的誠品行旅，就坐落在松菸文創文區內，剛剛好的觀賞距離，恰巧可以捕捉101全貌。而誠品行旅也會針對2024跨年煙火推出結合餐飲與住房的「迷樂跨年夜」活動，不只帶來充滿視、聽、味覺的奢華饗宴，也讓狂歡後的派對夜有舒適的落腳處。\r\n\r\n', '0266262888'),
(17, '台北時代寓所', '台北市中正區林森南路7號', 'static/img/台北時代寓所.webp', '自助餐、健身房、SPA', '隸屬於知名飯店「希爾頓集團」旗下的【台北車站住宿台北時代寓所】這間台北住宿，以電影膠卷盒為設計理念的建築。\r\n這間台北住宿濃濃藝術感與設計感不說，最重要的早餐有別於一般飯店的自助餐，這裡特別邀請大家最愛的星巴克進駐飯店一樓，入住的房客們可以盡享高品質咖啡與餐點，享受包場星巴克的快感。', '02 7752 1888'),
(18, '中山喜瑞飯店', '104台北市中山區長安東路一段64號', 'static/img/中山喜瑞飯店.jpg', '酒吧、停車場、機場接駁車', '有著緊鄰一整片台北市景透明落地窗的浴缸\r\n這間台北住宿從外部建築到內部基調全都由白色組成，潔白而舒適。\r\n一樓交誼廳還有提供免費下午茶，無論是商務或度假都非常適合', '02 2541 0077'),
(19, '歐遊國際連鎖精品旅館', '新北市新莊區思源路746號', 'static/img/歐遊國際連鎖精品旅館.webp', '浴缸、SPA、KTV', '位於新莊的台北汽車旅館歐遊精品旅館這間台北汽車旅館，五種房型簡約典雅，剛好來台北洽公很剛好、情侶一起做台北情趣旅館很適合，帶家人孩子入住也很可以。\r\n這間台北motel房間寬敞、浴缸大，有些房型還附有SPA水療池和KTV設備，乾淨清爽環境適合台北汽車旅館休息，深受網友好評！', '02-8522-1100'),
(20, '華大旅店 南西館', '台北市南京西路151號6樓', 'static/img/華大旅店 南西館.jpg', 'WI-FI、客房服務', '位於捷運中山站6號出口附近的華大旅店，地點近台北圓環、寧夏夜市、南西商圈非常方便。\r\n華大旅店是一間3星級的台北飯店，但每晚2000元內非常平價，且評價高於4.5顆星。\r\n華大旅店有24小時櫃台服務，且房內免費Wi-Fi，可寄放行李及送餐服務。\r\n台北住宿華大旅店，在agoda上沒有免費取消方案，但有特惠價格。', '0225593232');

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
  `checkout_date` date NOT NULL,
  `email` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_order`
--

INSERT INTO `tbl_order` (`order_id`, `room_id`, `customer_id`, `order_number`, `order_price`, `guest_number`, `booking_date`, `checkin_date`, `checkout_date`, `email`) VALUES
(1, 1, 1, 1, 1000, 2, '2023-12-23', '2023-12-26', '2023-12-27', 'tcytcy@gmail.com'),
(2, 2, 2, 2, 3000, 4, '2023-12-23', '2023-12-27', '2023-12-28', 'cytcyt@gmail.com');

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_room`
--

CREATE TABLE `tbl_room` (
  `room_id` int(11) NOT NULL,
  `hotel_id` int(11) NOT NULL,
  `room_type` text NOT NULL,
  `room_image` varchar(200) DEFAULT NULL,
  `room_price` int(11) NOT NULL,
  `max_capacity` int(11) NOT NULL,
  `room_number` int(11) NOT NULL,
  `room_info` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_room`
--

INSERT INTO `tbl_room` (`room_id`, `hotel_id`, `room_type`, `room_image`, `room_price`, `max_capacity`, `room_number`, `room_info`) VALUES
(1, 1, '超級樹屋', 'static/img/中壢大樹屋.jpg', 1000, 5, 1, NULL),
(2, 2, '雙人房', 'static/img/room1.jpg', 1500, 2, 10, NULL),
(3, 2, '三人房', 'static/img/room2.jpg', 1500, 3, 5, NULL),
(4, 2, '單人房', 'static/img/room3.jpg', 500, 1, 3, NULL),
(5, 3, '四人房', 'static/img/room5.jpg', 1500, 4, 3, NULL),
(6, 3, '雙人房', 'static/img/room4.jpg', 1000, 2, 5, NULL),
(7, 4, '雙人房', 'static/img/room6.webp', 1000, 2, 4, NULL),
(8, 4, '四人房', 'static/img/room7.jpg', 500, 1, 3, NULL),
(13, 1, 'ttttt', 'image_path', 800, 3, 3, 'nono'),
(14, 1, 'ttt', 'image_path', 1000, 2, 2, 'nooono'),
(15, 1, '.', 'image_path', 2000, 2, 2, 'vope'),
(16, 1, '.', 'image_path', 24, 2, 2, 'bbf'),
(17, 1, '房型1', 'image_path', 1000, 2, 3, 'vm;'),
(18, 1, ' 測試房間', 'image_path', 1000, 3, 3, 'nono'),
(19, 1, '房型1', 'image_path', 1100, 3, 3, 'hiohoi'),
(20, 3, '四人房', 'static/img/room4.jpg', 1500, 4, 3, NULL);

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
  `order_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_shoppingcart`
--

INSERT INTO `tbl_shoppingcart` (`customer_id`, `room_id`, `order_number`) VALUES
(1, 1, 1);

--
-- 已傾印資料表的索引
--

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
-- 資料表索引 `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD PRIMARY KEY (`order_id`),
  ADD KEY `Order_to_Customer` (`customer_id`),
  ADD KEY `Order_to_Room` (`room_id`);

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
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_order`
--
ALTER TABLE `tbl_order`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_room`
--
ALTER TABLE `tbl_room`
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- 已傾印資料表的限制式
--

--
-- 資料表的限制式 `tbl_hotelowner`
--
ALTER TABLE `tbl_hotelowner`
  ADD CONSTRAINT `HotelOwner_to_Hotel` FOREIGN KEY (`hotel_id`) REFERENCES `tbl_hotel` (`hotel_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 資料表的限制式 `tbl_order`
--
ALTER TABLE `tbl_order`
  ADD CONSTRAINT `Order_to_Customer` FOREIGN KEY (`customer_id`) REFERENCES `tbl_customer` (`customer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Order_to_Room` FOREIGN KEY (`room_id`) REFERENCES `tbl_room` (`room_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

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
