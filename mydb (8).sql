-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- 主機： 127.0.0.1
-- 產生時間： 2023-12-24 08:51:48
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
(4, 'Costco', ' 320桃園市中壢區民族路六段508號', '10:00~21:30', 'https://www.costco.com.tw/Frozen-Foods/c/908?q=:relevance:Publisher:%E6%A1%83%E5%9C%92%E5%B8%82%E4%B8%AD%E5%A3%A2%E5%8D%80%E5%8D%97%E5%9C%92%E8%B7%AF2-16%E8%99%9F&page=1', '幾霸婚', '好市多還要啥解釋', 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMUExYUFBMWFhYYFyIbFhgZFhkbGxkcIhwZGxsZHxkZHioiGSEnHB4eIzMjKistMDAwHyI2OzYyOiovMC0BCwsLDw4PGxERHC8oIicxLS8vMS8vLy8xLy8vLy8vMDEyMTEvMS84MTAvL', 'https://www.google.com/maps/place/%E5%A5%BD%E5%B8%82%E5%A4%9A+%E6%A1%83%E5%9C%92%E4%B8%AD%E5%A3%A2%E5%BA%97/@24.9640146,121.117483,14z/data=!4m10!1m2!2m1!1scostco!3m6!1s0x3468246f6f043f6d:0xab5a9b03cdb419cc!8m2!3d24.9640146!4d121.1555918!15sCgZjb3N0Y28iA4gBAVoIIgZjb3N0Y2-SAQ93YXJlaG91c2Vfc3RvcmXgAQA!16s%2Fg%2F11dyzcs6dq?entry=ttu');

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
(5, '蔡知遠', 'tcy9213@gmail.com', '11', '2023-12-24 07:25:07', '2023-12-23', '', 0);

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
  `hotel_intro` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_hotel`
--

INSERT INTO `tbl_hotel` (`hotel_id`, `hotel_name`, `hotel_location`, `hotel_image`, `hotel_facilities`, `hotel_intro`) VALUES
(1, 'hotel test 1', '中大路300號', 'https://media.cnn.com/api/v1/images/stellar/prod/150514125141-3-hotel-ritz-paris.jpg?q=w_2000,h_1500,x_0,y_0,c_fill/h_618', '只有床', '大型縫合怪旅店'),
(2, '旅居文旅-中壢館', '320桃園市中壢區延平路216號', 'https://lh3.googleusercontent.com/gps-proxy/AMy85WKD7hcPdwHJ0QhqHC18VtwrFqjvaMpMcuET8T0G7n6F2m8irFJgnL80igXBUXcD0AwQBPlwq00ao_6DTg36DTE5s95Sexj_qRe4_OzRjh9V-Bm8GD-s750zq8ps_nvlMM_acJlF3-sq2vGYgI1FAH9chxzOWVcGc4PnEYe6HWahAPiO1TsYxb6_5A=w408-h544-k-no', '\r\n公共停車位\r\n接機服務\r\n機場接駁服務\r\n圖書館\r\n咖啡廳\r\n行李寄存\r\n免費\r\n喚醒服務\r\n24小時櫃檯服務', '旅居 中壢館'),
(3, '桃園大溪笠復威斯汀度假飯店', '桃園, 大溪區, 日新路166號', 'https://ak-d.tripcdn.com/images/0202v120009bxk551DBDF_R_960_660_R5_D.webp', '高爾夫球場\r\n室內泳池\r\n室外泳池\r\n三溫暖\r\nSpa\r\n健身房\r\n停車免費\r\n酒吧\r\n餐廳\r\n兒童遊樂場\r\n兒童俱樂部\r\n24小時櫃檯服務\r\n公共空間 Wi-Fi免費\r\n', '桃園大溪笠復威斯汀度假飯店位處大北部地區的美麗後花園－大溪，距離北部主要都會地區只要一小時內車程，比鄰全台灣最優質的高爾夫球場之一的大溪球場。飯店有舒適宜人的客套房，每間客房皆有面向蔥鬱林蔭景觀的獨立陽台且備有聞名於世的威斯汀天夢之床，使賓客在度假時擁享酣然好夢。為了滿足家庭市場，飯店為了3-12歲的孩童設置專屬的遊憩空間－威斯汀孩童俱樂部，在這個威斯汀孩童俱樂部，無論是玩樂在各項遊樂設備或體驗手遊創作，全家人皆可以全方位的感受幸福的度假時光，留下最鮮活的度假回憶。'),
(4, '桃園福緣山莊民宿(Fu-Yam Hostel)', '336, 桃園, 復興區, 華陵村11鄰180-7號', 'https://ak-d.tripcdn.com/images/0224b12000a5q8i0c1BE3_R_452_274_R5_D.jpg', NULL, '福緣山莊位在桃園縣北台灣－最有氧的拉拉山風景特定區，比鄰拉拉溪畔。山莊共有三幢獨棟木屋，提供旅客安靜、幹凈、寬敞的環境,來到青山環抱、溪水依偎的環境中，福緣山莊可以給您一個完全舒壓的假期。福緣的主人本著惜福的心歡迎您來分福緣山莊的一切！在前往福緣山莊的沿途之中，相信北橫沿線的美麗風景，也會讓您駐足許久！');

-- --------------------------------------------------------

--
-- 資料表結構 `tbl_hotelowner`
--

CREATE TABLE `tbl_hotelowner` (
  `hotelowner_id` int(11) NOT NULL,
  `hotelowner _name` varchar(30) NOT NULL,
  `hotelowner _email` varchar(50) NOT NULL,
  `hotelowner_password` varchar(30) NOT NULL,
  `modified_time` datetime DEFAULT NULL,
  `hotel_id` int(11) NOT NULL,
  `intro` text DEFAULT NULL,
  `birthday` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_hotelowner`
--

INSERT INTO `tbl_hotelowner` (`hotelowner_id`, `hotelowner _name`, `hotelowner _email`, `hotelowner_password`, `modified_time`, `hotel_id`, `intro`, `birthday`) VALUES
(1, '蔡知遠', 'DuaTaoGa@gmail.com', '110403553', '2023-12-23 00:00:00', 1, 'this is 蔡知遠', '2004-12-11'),
(2, 'hotelOwner2', 'Owner2@gmail.com', '22pass', '2023-12-23 00:00:00', 2, NULL, NULL),
(3, 'hotelOwner3', 'Owner3@gmail.com', '33pass', '2023-12-23 00:00:00', 3, NULL, NULL),
(4, 'hotelOwner4', 'owner4@gmail.com', '44pass', '2023-12-23 00:00:00', 4, NULL, NULL);

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
  `room_type` varchar(30) NOT NULL,
  `room_image` varchar(200) NOT NULL,
  `room_price` int(11) NOT NULL,
  `max_capacity` int(11) NOT NULL,
  `room_number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- 傾印資料表的資料 `tbl_room`
--

INSERT INTO `tbl_room` (`room_id`, `hotel_id`, `room_type`, `room_image`, `room_price`, `max_capacity`, `room_number`) VALUES
(1, 1, '超級樹屋', 'https://lh5.googleusercontent.com/p/AF1QipMHtRJQr73kLHD0hjZGJC3o6s32UdM-XuaJhOc=w408-h725-k-no', 1000, 5, 1),
(2, 2, '雙人房', 'https://ak-d.tripcdn.com/images/22030x000000li29o6DFA_R_121_92_R5_D.jpg', 1500, 2, 10),
(3, 2, '三人房', 'https://ak-d.tripcdn.com/images/22051900000164l2h7D56_R_121_92_R5_D.jpg', 1500, 3, 5),
(4, 2, '單人房', 'https://ak-d.tripcdn.com/images/220i1900000160wct573C_R_121_92_R5_D.jpg', 500, 1, 3),
(5, 3, '四人房', 'https://ak-d.tripcdn.com/images/0204s120005p5p3q6C02D_R_224_126_R5_D.jpg', 1500, 4, 3),
(6, 3, '雙人房', 'https://ak-d.tripcdn.com/images/0223x120009mani5i0955_R_224_126_R5_D.jpg', 1000, 2, 5),
(7, 4, '雙人房', 'https://ak-d.tripcdn.com/images/0227012000ci5kyno5793_R_600_400_R5_D.jpg', 1000, 2, 4),
(8, 4, '四人房', 'https://ak-d.tripcdn.com/images/0226s12000aks50f85B54_R_600_400_R5_D.jpg', 500, 1, 3);

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
(8, '2023-12-24', 2);

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
  MODIFY `attraction_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_favoritelist`
--
ALTER TABLE `tbl_favoritelist`
  MODIFY `favoritelist_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_hotel`
--
ALTER TABLE `tbl_hotel`
  MODIFY `hotel_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- 使用資料表自動遞增(AUTO_INCREMENT) `tbl_hotelowner`
--
ALTER TABLE `tbl_hotelowner`
  MODIFY `hotelowner_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
  MODIFY `room_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

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
